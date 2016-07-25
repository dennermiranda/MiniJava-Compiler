package tree;
import syntaxtree.Exp;
import syntaxtree.Type;
import temp.Label;
import temp.Temp;
import visitor.TypeVisitor;
import visitor.Visitor;
public class NAME extends Exp1 {
  public Label label;
  public NAME(Label l) {label=l;}
  public ExpList kids() {return null;}
  public Exp1 build(ExpList kids) {return this;}

}

