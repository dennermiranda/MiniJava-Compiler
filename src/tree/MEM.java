package tree;
import syntaxtree.Exp;
import syntaxtree.Type;
import temp.Label;
import temp.Temp;
import visitor.TypeVisitor;
import visitor.Visitor;
public class MEM extends Exp1 {
  public Exp1 exp;
  public MEM(Exp1 e) {exp=e;}
  public ExpList kids() {return new ExpList(exp,null);}
  public Exp1 build(ExpList kids) {
    return new MEM(kids.head);
  }

}

