package tree;
import temp.Temp;
import syntaxtree.Exp;
import syntaxtree.Type;
import visitor.TypeVisitor;
import visitor.Visitor;
import temp.Label;
public class CONST extends Exp1 {
  public int value;
  public CONST(int v) {value=v;}
  public ExpList kids() {return null;}
  public Exp1 build(ExpList kids) {return this;}
}

