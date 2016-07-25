package visitor;

import symbol.ClassTable;
import symbol.ErrorManager;
import symbol.MethodTable;
import symbol.ProgramTable;
import symbol.Symbol;
import syntaxtree.And;
import syntaxtree.ArrayAssign;
import syntaxtree.ArrayLength;
import syntaxtree.ArrayLookup;
import syntaxtree.Assign;
import syntaxtree.Block;
import syntaxtree.BooleanType;
import syntaxtree.Call;
import syntaxtree.ClassDeclExtends;
import syntaxtree.ClassDeclSimple;
import syntaxtree.False;
import syntaxtree.Formal;
import syntaxtree.Identifier;
import syntaxtree.IdentifierExp;
import syntaxtree.IdentifierType;
import syntaxtree.If;
import syntaxtree.IntArrayType;
import syntaxtree.IntegerLiteral;
import syntaxtree.IntegerType;
import syntaxtree.LessThan;
import syntaxtree.MainClass;
import syntaxtree.MethodDecl;
import syntaxtree.Minus;
import syntaxtree.NewArray;
import syntaxtree.NewObject;
import syntaxtree.Not;
import syntaxtree.Plus;
import syntaxtree.Print;
import syntaxtree.Program;
import syntaxtree.This;
import syntaxtree.Times;
import syntaxtree.True;
import syntaxtree.Type;
import syntaxtree.VarDecl;
import syntaxtree.While;

public class TypeCheckVisitor extends TypeDepthFirstVisitor {

	private ClassTable currentClass;
	private MethodTable currentMethod;
	private ProgramTable pt;
	private ErrorManager errorManager;

	public TypeCheckVisitor(ProgramTable pt) {
		this.pt = pt;
		this.errorManager = new ErrorManager();
	}

	public Type visit(IntArrayType n) {
		return new IntArrayType();
	}

	public Type visit(BooleanType n) {
		return new BooleanType();
	}

	public Type visit(IntegerType n) {
		return new IntegerType();
	}

	// String s;
	public Type visit(IdentifierType n) {
		return n;
	}

	// Program representa todo o programa, o método vai iterando em cima das
	// classes do programa e dando accept() nelas
	// Cl = class list
	// M = main class
	public Type visit(Program prog) { // prog que recebemos
		prog.m.accept(this);
		for (int i = 0; i < prog.cl.size(); i++) {
			prog.cl.elementAt(i).accept(this);
		}
		return null;
	}

	// ClassDeclSimple é a implementação da classe abstrata ClassDecl.
	// Representa a declaração de uma classe com sintaxe simples tipo "public
	// class Cachorro{}"
	// Tem uma lista de métodos e variáveis (ml, vl)
	public Type visit(ClassDeclSimple cds) {
		cds.i.accept(this);
		currentClass = pt.getClass(Symbol.symbol(cds.i.toString()));
		currentMethod = null;
		for (int i = 0; i < cds.vl.size(); i++) {
			cds.vl.elementAt(i).accept(this);
		}
		for (int i = 0; i < cds.ml.size(); i++) {
			cds.ml.elementAt(i).accept(this);
		}
		return null;
	}

	// Semelhante ao caso do ClassDeclSimple, mas pra sintaxes do tipo "public
	// class Cachorro extends Animal{}"
	public Type visit(ClassDeclExtends n) {
		n.i.accept(this);
		currentClass = pt.getClass(Symbol.symbol(n.i.toString()));
		n.j.accept(this);
		currentMethod = null;
		for (int i = 0; i < n.vl.size(); i++) {
			n.vl.elementAt(i).accept(this);
		}
		for (int i = 0; i < n.ml.size(); i++) {
			n.ml.elementAt(i).accept(this);
		}
		return null;
	}

	// Declaração de uma variável, com tipo e nome.
	public Type visit(VarDecl n) {
		n.t.accept(this);
		n.i.accept(this);
		return null;
	}

	public Type visit(IntegerLiteral n) {
		return new IntegerType();
	}

	public Type visit(True n) {
		return new BooleanType();
	}

	public Type visit(False n) {
		return new BooleanType();
	}

	// Declaração de um método, com tipo do método, nome, lista de
	// argumentos declarados (fl), variaveis e statements (vl e sl)
	public Type visit(MethodDecl n) {
		n.t.accept(this);
		n.i.accept(this);
		currentMethod = currentClass.getMethod(Symbol.symbol(n.i.toString()));
		for (int i = 0; i < n.fl.size(); i++) {
			n.fl.elementAt(i).accept(this);
		}
		for (int i = 0; i < n.vl.size(); i++) {
			n.vl.elementAt(i).accept(this);
		}
		for (int i = 0; i < n.sl.size(); i++) {
			n.sl.elementAt(i).accept(this);
		}
		Type t = n.e.accept(this);
		if (!t.toString().equals(n.t.toString()))
			errorManager.printError("Type returned is incorrect for method " + currentMethod.toString());
		return null;
	}

	// Formal é um argumento declarado num método
	public Type visit(Formal n) {
		n.t.accept(this);
		n.i.accept(this);
		return null;
	}

	public Type visit(Block n) {
		for (int i = 0; i < n.sl.size(); i++) {
			n.sl.elementAt(i).accept(this);
		}
		return null;
	}

	// Exp e;
	// Statement s1,s2;
	public Type visit(If n) {
		if (!(n.e.accept(this) instanceof BooleanType))
			errorManager.printError("If expression is not a boolean type");
		n.s1.accept(this);
		n.s2.accept(this);
		return null;
	}

	public void PrettyPrint() {
		pt.PrettyPrint();
	}

	public Type visit(Identifier n) {
		return whatType(n.s);
	}
	
	public Type visit(IdentifierExp n) {
		Type t = whatType(n.s);
		if (t == null)
			errorManager.printError("Identifier not found");
		return t;
	}

	public Type visit(This n) {
		if (currentClass == null)
			errorManager.printError("Class Environment not found");
		return new IdentifierType(currentClass.toString());
	}

	// Exp e;
	public Type visit(NewArray n) {
		if (! (n.e.accept(this) instanceof IntegerType))
			errorManager.printError("Array size must be integer");
		return new IntArrayType();
	}

	// Identifier i;
	public Type visit(NewObject n) {
		ClassTable c = pt.getClass(Symbol.symbol(n.i.toString()));
		if (c == null)
			errorManager.printError("Class used in new object not found");
		return new IdentifierType(c.toString());
	}

	public Type visit(Not n) {
		n.e.accept(this);
		return new BooleanType();
	}

	public Type visit(Assign assign) {
		Type var = whatType(assign.i.toString());
		Type exp = assign.e.accept(this);
		if (var instanceof IdentifierType) // Class
		{
			ClassTable c = pt.getClass(Symbol.symbol(((IdentifierType) var).toString()));
			if (c == null || !c.toString().equals(exp.toString()))
				errorManager.printError("Assign types not maching");
		} else {
			if (var == null || exp == null || !var.toString().equals(exp.toString()))
				errorManager.printError("Assign types not maching");
		}
		return null;
	}

	public Type visit(ArrayAssign n) {
		Type var = whatType(n.i.toString());
		if (var == null)
			errorManager.printError("Array Type not declared");
		else if (!(var instanceof IntegerType))
			errorManager.printError("Array must be integer");
		if (!(n.e1.accept(this) instanceof IntegerType))
			errorManager.printError("Iterator of Array must be integer");
		if (!(n.e2.accept(this) instanceof IntegerType))
			errorManager.printError("Right side expression of Array Assign must be integer");
		return null;
	}

	public Type visit(While w) {
		if (!(w.e.accept(this) instanceof BooleanType)) {
			System.err.println("While expression is not a boolean type");
		}
		w.s.accept(this);
		return null;
	}

	public Type visit(Print n) {
		n.e.accept(this);
		return null;
	}

	public Type visit(MainClass mc) {
		mc.i1.accept(this);
		currentClass = pt.getClass(Symbol.symbol(mc.i1.toString()));
		mc.i2.accept(this);
		currentMethod = currentClass.getMethod(Symbol.symbol(mc.i2.toString()));
		mc.s.accept(this);
		return null;
	}

	// Exp e1,e2;
	public Type visit(And n) {
		if (!(n.e1.accept(this) instanceof BooleanType))
			errorManager.printError("Left side of operator && must be boolean");
		if (!(n.e2.accept(this) instanceof BooleanType))
			errorManager.printError("Right side of operator && must be boolean");
		return new BooleanType();
	}

	// Exp e1,e2;
	public Type visit(LessThan n) {
		if (!(n.e1.accept(this) instanceof IntegerType))
			errorManager.printError("Left side of operator '<' must be integer");
		if (!(n.e2.accept(this) instanceof IntegerType))
			errorManager.printError("Right side of operator '<' must be integer");
		return new BooleanType();
	}

	// Exp e1,e2;
	public Type visit(Plus n) {
		if (!(n.e1.accept(this) instanceof IntegerType))
			errorManager.printError("Left side of operator '+' must be integer");
		if (!(n.e2.accept(this) instanceof IntegerType))
			errorManager.printError("Right side of operator '+' must be integer");
		return new IntegerType();
	}

	// Exp e1,e2;
	public Type visit(Minus n) {
		if (!(n.e1.accept(this) instanceof IntegerType))
			errorManager.printError("Left side of operator '-' must be integer");
		if (!(n.e2.accept(this) instanceof IntegerType))
			errorManager.printError("Right side of operator '-' must be integer");
		return new IntegerType();
	}

	// Exp e1,e2;
	public Type visit(Times n) {
		if (!(n.e1.accept(this) instanceof IntegerType))
			errorManager.printError("Left side of operator '*' must be integer");
		if (!(n.e2.accept(this) instanceof IntegerType))
			errorManager.printError("Right side of operator '*' must be integer");
		return new IntegerType();
	}

	// Metodo para achar o tipo da variavel com determinado id
	public Type getType(String id) {
		if ((currentMethod != null) && (currentMethod.getVar(Symbol.symbol(id)) != null))
			return currentMethod.getVar(Symbol.symbol(id));
		if ((currentMethod != null) && (currentMethod.getParam(Symbol.symbol(id)) != null))
			return currentMethod.getParam(Symbol.symbol(id));
		if ((currentClass != null) && (currentClass.getVar(Symbol.symbol(id)) != null))
			return currentClass.getVar(Symbol.symbol(id));
		if (pt.getClass(Symbol.symbol(id)) != null)
			return new IdentifierType(id);
		return null;
	}

	public Type visit(ArrayLookup n) {
		if (!(n.e1.accept(this) instanceof IntegerType))
			errorManager.printError("Array must be integer");
		if (!(n.e2.accept(this) instanceof IntegerType))
			errorManager.printError("Iterator of Array must be integer");
		return new IntegerType();
	}

	public Type visit(ArrayLength n) {
		if (!(n.e.accept(this) instanceof IntegerType))
			errorManager.printError("Array Length must be integer");
		return null;
	}

	public Type whatType(String id) {
		if ((currentMethod != null) && (currentMethod.getVar(Symbol.symbol(id)) != null))
			return currentMethod.getVar(Symbol.symbol(id));
		if ((currentMethod != null) && (currentMethod.getParam(Symbol.symbol(id)) != null))
			return currentMethod.getParam(Symbol.symbol(id));
		if ((currentClass != null) && (currentClass.getVar(Symbol.symbol(id)) != null))
			return currentClass.getVar(Symbol.symbol(id));
		if (pt.getClass(Symbol.symbol(id)) != null)
			return new IdentifierType(id);
		return null;
	}

	public Type visit(Call n) {
		Type t = n.e.accept(this);
		if (t == null || !(t instanceof IdentifierType))
			errorManager.printError("Calling some unknown class");
		ClassTable c = pt.getClass(Symbol.symbol(((IdentifierType) t).toString()));
		MethodTable m = c.getMethod(Symbol.symbol(n.i.toString()));
		if (m == null) {
			errorManager.printError("Calling some unknown method");
			return null;
		}
		if (n.el.size() != m.getNumParam()) {
			errorManager.printError("Incorrect number of parameters for " + m.toString() + ": Expected "
					+ m.getNumParam() + " founded " + n.el.size());
			return null;
		}
		for (int i = 0; i < n.el.size(); i++) {
			Type tparam = n.el.elementAt(i).accept(this);
			Type ti = m.getParam(i + 1);
			if (tparam instanceof IdentifierType) // Class
			{
				ClassTable cp = pt.getClass(Symbol.symbol(((IdentifierType) tparam).toString()));
				if (cp == null || !cp.toString().equals(ti.toString()))
					errorManager.printError(
							"Argument " + (i + 1) + " of method " + m.toString() + "must be " + ti.toString());
			} else {
				if ((tparam == null) || !tparam.toString().equals(ti.toString()))
					errorManager.printError(
							"Argument " + (i + 1) + " of method " + m.toString() + "must be " + ti.toString());
			}
		}
		return m.getType();
	}

}
