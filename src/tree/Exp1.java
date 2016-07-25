package tree;
abstract public class Exp1 {
	abstract public ExpList kids();
	abstract public Exp1 build(ExpList kids);
}