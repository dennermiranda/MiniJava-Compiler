package tree;
import syntaxtree.Exp;
import temp.Label;
import temp.LabelList;
import temp.Temp;
public class JUMP extends Stm {
  public Exp1 exp;
  public LabelList targets;
  public JUMP(Exp1 e, temp.LabelList t) {exp=e; targets=t;}
  public JUMP(temp.Label target) {
      this(new NAME(target), new temp.LabelList(target,null));
  }
  public ExpList kids() {return new ExpList(exp,null);}
  public Stm build(ExpList kids) {
    return new JUMP(kids.head,targets);
  }
}

