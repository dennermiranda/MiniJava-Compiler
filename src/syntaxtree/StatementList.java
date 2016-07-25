package syntaxtree;

import java.util.Vector;

import visitor.TypeVisitor;
import visitor.Visitor;

public class StatementList extends Statement {
   private Vector list;

   public StatementList() {
      list = new Vector();
   }

   public void addElement(Statement n) {
      list.addElement(n);
   }

   public Statement elementAt(int i)  { 
      return (Statement)list.elementAt(i); 
   }

   public int size() { 
      return list.size(); 
   }

@Override
public void accept(Visitor v) {
	// TODO Auto-generated method stub
	
}

@Override
public Type accept(TypeVisitor v) {
	// TODO Auto-generated method stub
	return null;
}
}
