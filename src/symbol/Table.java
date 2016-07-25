package symbol;

//import java.util.HashMap;
import java.util.LinkedHashMap;

public class Table {
	private LinkedHashMap<Symbol, ClassTable> table;
	
	public Table() {
		table = new LinkedHashMap<Symbol, ClassTable>();
	}
	
	public ClassTable getClass(Symbol id) throws Exception {
		if(table.containsKey(id)) {
			return (ClassTable)table.get(id);
		} else {
			throw new Exception("Classe " + id.toString() + "ainda nao definida");
		}
	}
	
	public LinkedHashMap<Symbol, ClassTable> getTable() {
		return table;
	}
	
	public void setTable(LinkedHashMap<Symbol, ClassTable> table) {
		this.table = table;
	}
	
	public ClassTable addClass(Symbol id) throws Exception {
		if(!table.containsKey(id)) {
			ClassTable table = new ClassTable();
			this.table.put(id, table);
			return table;
		}
		else {
			throw new Exception("Classe " + id.toString() + "ja existe na tabela");
		}
	}
}
