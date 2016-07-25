package symbol;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Set;

import syntaxtree.Type;

public class ClassTable {

	private String Name;
	private LinkedHashMap<Symbol, Type> varTable; // Variaveis
	private LinkedHashMap<Symbol, MethodTable> methodTable; // Binding
	private LinkedHashMap<Symbol, LinkedHashMap<Type, MethodTable>> methodsTable; //binding dos metodos da classe
	private LinkedHashMap<Type, MethodTable> hash;
	private Symbol currentMethod;
	public ClassTable(String name) {
		Name = name;
		varTable = new LinkedHashMap<Symbol, Type>();
		methodTable = new LinkedHashMap<Symbol, MethodTable>();
		methodsTable = new LinkedHashMap<Symbol, LinkedHashMap<Type,MethodTable>>();
	}

	public ClassTable() {
		varTable = new LinkedHashMap<Symbol, Type>();
		methodTable = new LinkedHashMap<Symbol, MethodTable>();
		methodsTable = new LinkedHashMap<Symbol, LinkedHashMap<Type,MethodTable>>();
	}

	public boolean addVar(Symbol s, Type t) {
		if (!varTable.containsKey(s)) {
			varTable.put(s, t);
			return true;
		}
		return false;
	}

	public boolean addMethod(Symbol s, MethodTable t) {
		if (!methodTable.containsKey(s)) {
			methodTable.put(s, t);
			return true;
		}
		return false;
	}

	public String toString() {
		return Name;
	}

	public void PrettyPrint() {
		Set<Symbol> keys = varTable.keySet();
		for (Symbol i : keys)
			System.out.println(i.toString());
		Set<Symbol> keys2 = methodTable.keySet();
		for (Symbol i : keys2) {
			System.out.print("\tMethod " + i.toString());
			methodTable.get(i).PrettyPrint();
			System.out.println("\t}");
		}
	}

	public MethodTable getMethod(Symbol s) {
		if (methodTable.containsKey(s))
			return methodTable.get(s);
		return null;
	}

	public Type getVar(Symbol s) {
		if (varTable.containsKey(s))
			return varTable.get(s);
		return null;
	}

	public LinkedHashMap<Symbol, LinkedHashMap<Type, MethodTable>> getMethodsTable() {
		
		return methodsTable;
	}

	public void addField(Symbol id, Type t) throws Exception {
		if(!varTable.containsKey(id)) {
			 varTable.put(id, t);
		 } else {
			 throw new Exception("Variavel " + id.toString() + "ja definida");
		 }
		
	}

	public MethodTable addMethod(Symbol id, Type t, MethodTable methodTable2) throws Exception {
		if (this.methodsTable == null){
			System.out.println("null");
			
		}
		if (!(this.methodsTable.containsKey(id))) {
			 hash = new LinkedHashMap<Type, MethodTable>();
			 methodTable2 = new MethodTable();
			 hash.put(t, methodTable2);
			 this.methodsTable.put(id, hash);
			 return methodTable2;
		} else {
			throw new Exception("Metodo " + id.toString() + "ja definido");
		}
	}

	public HashMap<Symbol, Type> getFieldsTable() {
		// TODO Auto-generated method stub
		return varTable;
	}

}