package visitor;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import symbol.*;
//import symbol.Table;
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
import syntaxtree.VarDecl;
import syntaxtree.While;
import syntaxtree.Type;
//import java.util.LinkedHashMap;
/**
 * @author Dener Miranda
 * 
 **/
public class BuildSymbolTableVisitor implements Visitor {
	Symbol id;
	private Table table = new Table();
	private ClassTable classTable = new ClassTable();
	private MethodTable methodTable = new MethodTable();
	private HashMap<Symbol, Symbol> hashMain = new HashMap<Symbol, Symbol>();
	private HashMap<Symbol, Symbol> args = new HashMap<Symbol, Symbol>();

	@Override
	public void visit(Program n) {

		n.m.accept(this);
		for (int i = 0; i < n.cl.size(); i++) {
			n.cl.elementAt(i).accept(this);
		}
	}

	@Override
	public void visit(MainClass n) {

		n.i1.accept(this);
		try {
			table.addClass(Symbol.symbol(n.i1.toString()));
			hashMain.put(Symbol.symbol("main"), Symbol.symbol("public static void"));
			args.put(Symbol.symbol(n.i2.toString()), Symbol.symbol("String[]"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		n.i2.accept(this);
		n.s.accept(this);
	}

	@Override
	public void visit(ClassDeclSimple n) {

		try {
			n.i.accept(this);
			classTable = table.addClass(Symbol.symbol(n.i.toString()));
			// System.out.println(" { ");
			for (int i = 0; i < n.vl.size(); i++) {
				// System.out.print(" ");
				n.vl.elementAt(i).accept(this);
				// if ( i+1 < n.vl.size() ) { System.out.println(); }
			}
			for (int i = 0; i < n.ml.size(); i++) {
				// System.out.println();
				n.ml.elementAt(i).accept(this);
			}
		} catch (Exception e1) {

			e1.printStackTrace();
		}
	}

	@Override
	public void visit(ClassDeclExtends n) {

		try {
			// System.out.print("class ");
			n.i.accept(this);
			classTable = table.addClass(Symbol.symbol(n.i.toString()));
			// System.out.println(" extends ");
			// n.j.accept(this);

			// System.out.println(" { ");
			for (int i = 0; i < n.vl.size(); i++) {

				n.vl.elementAt(i).accept(this);

			}
			for (int i = 0; i < n.ml.size(); i++) {
				// System.out.println();
				n.ml.elementAt(i).accept(this);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void visit(VarDecl n) {
		n.t.accept(this);
		// System.out.print(" ");
		n.i.accept(this);
		// System.out.print(";");
		try {
			if (classTable.getMethodsTable().isEmpty()) {
				classTable.addField(Symbol.symbol(n.i.toString()), n.t);
			} else {
				methodTable.addLocal(Symbol.symbol(n.i.toString()), n.t);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void visit(MethodDecl n) {
		// System.out.print(" public ");
		n.t.accept(this);
		// System.out.print(" ");
		n.i.accept(this);
		// System.out.print(" (");
		try {
			methodTable = classTable.addMethod(Symbol.symbol(n.i.toString()), n.t, methodTable);
			for (int i = 0; i < n.fl.size(); i++) {
				n.fl.elementAt(i).accept(this);
				// if (i+1 < n.fl.size()) { System.out.print(", "); }
			}
			// System.out.println(") { ");
			for (int i = 0; i < n.vl.size(); i++) {
				// System.out.print(" ");
				n.vl.elementAt(i).accept(this);
				// System.out.println("");
			}
			for (int i = 0; i < n.sl.size(); i++) {
				// System.out.print(" ");
				n.sl.elementAt(i).accept(this);
				if (i < n.sl.size()) {
					System.out.println("");
				}
			}
			// System.out.print(" return ");
			n.e.accept(this);
			// System.out.println(";");
			// System.out.print(" }");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void visit(Formal n) {
		n.t.accept(this);
		// System.out.print(" ");
		n.i.accept(this);
		try {
			methodTable.addParam(Symbol.symbol(n.i.toString()), n.t);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void visit(IntArrayType n) {

	}

	@Override
	public void visit(BooleanType n) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(IntegerType n) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(IdentifierType n) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(Block n) {
		for (int i = 0; i < n.sl.size(); i++) {
			// System.out.print(" ");
			n.sl.elementAt(i).accept(this);
			// System.out.println();
		}
	}

	@Override
	public void visit(If n) {
		// System.out.print("if (");
		n.e.accept(this);
		// System.out.println(") ");
		// System.out.print(" ");
		n.s1.accept(this);
		// System.out.println();
		// System.out.print(" else ");
		n.s2.accept(this);

	}

	@Override
	public void visit(While n) {
		// System.out.print("while (");
		n.e.accept(this);
		// System.out.print(") ");
		n.s.accept(this);

	}

	@Override
	public void visit(Print n) {
		n.e.accept(this);

	}

	@Override
	public void visit(Assign n) {
		n.i.accept(this);
		// System.out.print(" = ");
		n.e.accept(this);
		// System.out.print(";");

	}

	@Override
	public void visit(ArrayAssign n) {

		n.i.accept(this);
		// System.out.print("[");
		n.e1.accept(this);
		// System.out.print("] = ");
		n.e2.accept(this);
		// System.out.print(";");
	}

	@Override
	public void visit(And n) {
		// System.out.print("(");
		n.e1.accept(this);
		// System.out.print(" && ");
		n.e2.accept(this);
		// System.out.print(")");

	}

	@Override
	public void visit(LessThan n) {
		// System.out.print("(");
		n.e1.accept(this);
		// System.out.print(" < ");
		n.e2.accept(this);
		// System.out.print(")");

	}

	@Override
	public void visit(Plus n) {
		// System.out.print("(");
		n.e1.accept(this);
		// System.out.print(" + ");
		n.e2.accept(this);
		// System.out.print(")");

	}

	@Override
	public void visit(Minus n) {
		// System.out.print("(");
		n.e1.accept(this);
		// System.out.print(" - ");
		n.e2.accept(this);
		// System.out.print(")");

	}

	@Override
	public void visit(Times n) {

		// System.out.print("(");
		n.e1.accept(this);
		// System.out.print(" * ");
		n.e2.accept(this);
		// System.out.print(")");
	}

	@Override
	public void visit(ArrayLookup n) {
		n.e1.accept(this);
		// System.out.print("[");
		n.e2.accept(this);
		// System.out.print("]");

	}

	@Override
	public void visit(ArrayLength n) {
		n.e.accept(this);
		// System.out.print(".length");
	}

	@Override
	public void visit(Call n) {
		n.e.accept(this);
		// System.out.print(".");
		n.i.accept(this);
		// System.out.print("(");
		for (int i = 0; i < n.el.size(); i++) {
			n.el.elementAt(i).accept(this);
			if (i + 1 < n.el.size()) {
				System.out.print(", ");
			}
		}
		// System.out.print(")");

	}

	@Override
	public void visit(IntegerLiteral n) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(True n) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(False n) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(IdentifierExp n) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(This n) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(NewArray n) {
		// System.out.print("new int [");
		n.e.accept(this);
		// System.out.print("]");

	}

	@Override
	public void visit(NewObject n) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(Not n) {
		// System.out.print("!");
		n.e.accept(this);

	}

	@Override
	public void visit(Identifier n) {
		// TODO Auto-generated method stub

	}
	
	public void print() {
		for(Map.Entry<Symbol, ClassTable> entry : table.getTable().entrySet()) {
			System.out.println("Class: " + entry.getKey());
			if(entry.getValue().getMethodsTable().isEmpty()) {
			    for(Map.Entry<Symbol, Symbol> entry8 : hashMain.entrySet()) {
				System.out.println(" Method: " + entry8.getKey() + " -> " + entry8.getValue());
			    }
				for(Map.Entry<Symbol, Symbol> entry9 : args.entrySet()) {
					System.out.println(" Argument: Main method: " + entry9.getKey() + " -> " + entry9.getValue());
			    }
			}
			if(!entry.getValue().getFieldsTable().isEmpty()) {
				System.out.println(" Class variables: " + entry.getKey() + ":");
				for(Map.Entry<Symbol, Type> entry2 : entry.getValue().getFieldsTable().entrySet()) {
					System.out.println(" - " + entry2.getKey() + " -> " + entry2.getValue());
				}
			}
			if(!entry.getValue().getMethodsTable().isEmpty()) {
				System.out.println(" Class methods for " + entry.getKey() + ":");
				for(Entry<Symbol, LinkedHashMap<Type, MethodTable>> entry3 : entry.getValue().getMethodsTable().entrySet()) {
					System.out.print(" - " + entry3.getKey() + " -> ");
					for(Map.Entry<Type, MethodTable> entry4 : entry3.getValue().entrySet()) {
						System.out.print(entry4.getKey() + "\n");
						MethodTable entry5 = entry4.getValue();
						if(!entry5.getParams().entrySet().isEmpty()) {
							//System.out.println(entry5.getParams());
							System.out.println("    Methods parameters for " + entry3.getKey() + ":");
							for(Map.Entry<Symbol, Type> entry6 : entry5.getParams().entrySet()) {
								System.out.println("     - " + entry6.getKey() + " -> " + entry6.getValue());
							}
						}
						if(!entry5.getLocals().entrySet().isEmpty()) {
							System.out.println("    Method variables for " + entry3.getKey() + ":");
							for(Map.Entry<Symbol, Type> entry7 : entry5.getLocals().entrySet()) {
								System.out.println("     - " + entry7.getKey() + " -> " + entry7.getValue());
							}		
						}
					}
				}
			}
			System.out.println("");
		}
	}

	public HashMap<Symbol, Symbol> getHashMain() {
		return hashMain;
	}

	public void setHashMain(HashMap<Symbol, Symbol> hashMain) {
		this.hashMain = hashMain;
	}
	
	public HashMap<Symbol, Symbol> getArgs() {
		return args;
	}

	public void setArgs(HashMap<Symbol, Symbol> args) {
		this.args = args;
	}

}
