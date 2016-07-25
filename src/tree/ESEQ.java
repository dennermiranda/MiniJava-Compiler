package tree;
import syntaxtree.Exp;
import syntaxtree.Type;
import temp.Label;
import temp.Temp;
import visitor.TypeVisitor;
import visitor.Visitor;
public class ESEQ extends Exp1 {
  public Stm stm;
  public Exp1 exp;
  public ESEQ(Stm s, Exp1 e) {stm=s; exp=e;}
  public ExpList kids() {throw new Error("kids() not applicable to ESEQ");}
  public Exp1 build(ExpList kids) {throw new Error("build() not applicable to ESEQ");}

}

