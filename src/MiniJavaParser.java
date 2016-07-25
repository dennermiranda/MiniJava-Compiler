
/* Generated By:JavaCC: Do not edit this line. MiniJavaParser.java */
import syntaxtree.And;
import syntaxtree.ArrayAssign;
import syntaxtree.ArrayLength;
import syntaxtree.Assign;
import syntaxtree.BooleanType;
import syntaxtree.Call;
import syntaxtree.ClassDecl;
import syntaxtree.ClassDeclExtends;
import syntaxtree.ClassDeclList;
import syntaxtree.ClassDeclSimple;
import syntaxtree.Exp;
import syntaxtree.ExpList;
import syntaxtree.False;
import syntaxtree.Formal;
import syntaxtree.FormalList;
import syntaxtree.Identifier;
import syntaxtree.IdentifierExp;
import syntaxtree.IdentifierType;
import syntaxtree.If;
import syntaxtree.IntegerLiteral;
import syntaxtree.IntegerType;
import syntaxtree.LessThan;
import syntaxtree.MainClass;
import syntaxtree.MethodDecl;
import syntaxtree.MethodDeclList;
import syntaxtree.Minus;
import syntaxtree.NewArray;
import syntaxtree.NewObject;
import syntaxtree.Not;
import syntaxtree.Plus;
import syntaxtree.Print;
import syntaxtree.Program;
import syntaxtree.Statement;
import syntaxtree.StatementList;
import syntaxtree.This;
import syntaxtree.Times;
import syntaxtree.True;
import syntaxtree.Type;
import syntaxtree.VarDecl;
import syntaxtree.VarDeclList;
import syntaxtree.While;
import visitor.BuildSymbolTableVisitor;
import visitor.PrettyPrintVisitor;

public class MiniJavaParser implements MiniJavaParserConstants {
	public static void main(String[] args) throws ParseException {
		if (args.length == 0) {
			System.out.println("Sem argumento ate o momento.");
		} else if (args.length == 1) {
			System.out.println("Iniciando...");
			try {
				MiniJavaParser parser = new MiniJavaParser(new java.io.FileReader(args[0]));
				Program p = parser.Program();
				PrettyPrintVisitor print = new PrettyPrintVisitor();
				print.visit(p);

				System.out.println("Tabela de S�mbolos:\n");
				BuildSymbolTableVisitor bstv = new BuildSymbolTableVisitor();
				p.accept(bstv);
				bstv.print();

				System.out.println("Analise Sintatica OK!");
			} catch (java.io.FileNotFoundException e) {
				System.out.println("Arquivo " + args[0] + " nao encontrado.");
				return;
			}
		} else {
			System.out.println("Muitos argumentos.");
		}
	}

	static final public void Goal() throws ParseException {
		MainClass();
		label_1: while (true) {
			switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
			case CLASS:
				;
				break;
			default:
				jj_la1[0] = jj_gen;
				break label_1;
			}
			ClassDecl();
		}
		jj_consume_token(0);
	}

	static final public Program Program() throws ParseException {
		MainClass mc;
		ClassDeclList cdl = new ClassDeclList();
		ClassDecl cd;
		mc = MainClass();
		label_2: while (true) {
			switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
			case CLASS:
				;
				break;
			default:
				jj_la1[1] = jj_gen;
				break label_2;
			}
			cd = ClassDecl();
			cdl.addElement(cd);
		}
		jj_consume_token(0);
		{
			if (true)
				return new Program(mc, cdl);
		}
		throw new Error("Missing return statement in function");
	}

	static final public MainClass MainClass() throws ParseException {
		Token token1, token2;
		Statement s1;
		jj_consume_token(CLASS);
		token1 = jj_consume_token(ID);
		jj_consume_token(LBRACE);
		jj_consume_token(PUBLIC);
		jj_consume_token(STATIC);
		jj_consume_token(VOID);
		jj_consume_token(MAIN);
		jj_consume_token(LP);
		jj_consume_token(STRING);
		jj_consume_token(LBRACKETS);
		jj_consume_token(RBRACKETS);
		token2 = jj_consume_token(ID);
		jj_consume_token(RP);
		jj_consume_token(LBRACE);
		s1 = Statement();
		jj_consume_token(RBRACE);
		jj_consume_token(RBRACE);
		{
			if (true)
				return new MainClass(new Identifier(token1.image), new Identifier(token2.image), s1);
		}
		throw new Error("Missing return statement in function");
	}

	static final public ClassDecl ClassDecl() throws ParseException {
		Token token1, token2;
		MethodDeclList mdl = new MethodDeclList();
		VarDeclList vdl = new VarDeclList();
		MethodDecl md = new MethodDecl();
		VarDecl vd = new VarDecl();
		jj_consume_token(CLASS);
		token1 = jj_consume_token(ID);
		jj_consume_token(LBRACE);
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case PUBLIC:
		case RBRACE:
		case INT:
		case BOOLEAN:
		case ID:
			label_3: while (true) {
				switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
				case INT:
				case BOOLEAN:
				case ID:
					;
					break;
				default:
					jj_la1[2] = jj_gen;
					break label_3;
				}
				vd = VarDecl();
				vdl.addElement(vd);
			}
			label_4: while (true) {
				switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
				case PUBLIC:
					;
					break;
				default:
					jj_la1[3] = jj_gen;
					break label_4;
				}
				md = MethodDecl();
				mdl.addElement(md);
			}
			jj_consume_token(RBRACE); {
			if (true)
				return new ClassDeclSimple(new Identifier(token1.image), vdl, mdl);
		}
			break;
		case CLASS:
			jj_consume_token(CLASS);
			token2 = jj_consume_token(ID);
			jj_consume_token(EXTENDS);
			jj_consume_token(ID);
			jj_consume_token(LBRACE);
			label_5: while (true) {
				switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
				case INT:
				case BOOLEAN:
				case ID:
					;
					break;
				default:
					jj_la1[4] = jj_gen;
					break label_5;
				}
				VarDecl();
			}
			label_6: while (true) {
				switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
				case PUBLIC:
					;
					break;
				default:
					jj_la1[5] = jj_gen;
					break label_6;
				}
				MethodDecl();
			}
			jj_consume_token(RBRACE); {
			if (true)
				return new ClassDeclExtends(new Identifier(token1.image), new Identifier(token2.image), vdl, mdl);
		}
			break;
		default:
			jj_la1[6] = jj_gen;
			jj_consume_token(-1);
			throw new ParseException();
		}
		throw new Error("Missing return statement in function");
	}

	static final public VarDecl VarDecl() throws ParseException {
		Type t;
		Token token;
		t = Type();
		token = jj_consume_token(ID);
		jj_consume_token(PONTOVIRGULA);
		{
			if (true)
				return new VarDecl(t, new Identifier(token.image));
		}
		throw new Error("Missing return statement in function");
	}

	static final public MethodDecl MethodDecl() throws ParseException {
		Type t;
		Token token;
		FormalList fl;
		VarDeclList vdl = new VarDeclList();
		VarDecl vd;
		StatementList sl = new StatementList();
		Statement s;
		Exp exp;
		jj_consume_token(PUBLIC);
		t = Type();
		token = jj_consume_token(ID);
		jj_consume_token(LP);
		fl = FormalList();
		jj_consume_token(RP);
		jj_consume_token(LBRACE);
		label_7: while (true) {
			if (jj_2_1(2)) {
				;
			} else {
				break label_7;
			}
			vd = VarDecl();
			vdl.addElement(vd);
		}
		label_8: while (true) {
			switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
			case LBRACE:
			case IF:
			case WHILE:
			case PRINT:
			case ID:
				;
				break;
			default:
				jj_la1[7] = jj_gen;
				break label_8;
			}
			s = Statement();
			sl.addElement(s);
		}
		jj_consume_token(RETURN);
		exp = Exp();
		jj_consume_token(PONTOVIRGULA);
		jj_consume_token(RBRACE);
		{
			if (true)
				return new MethodDecl(t, new Identifier(token.image), fl, vdl, sl, exp);
		}
		throw new Error("Missing return statement in function");
	}

	static final public FormalList FormalList() throws ParseException {
		Type t;
		Token token;
		FormalList fl = new FormalList();
		t = Type();
		token = jj_consume_token(ID);
		fl.addElement(new Formal(t, new Identifier(token.image)));
		label_9: while (true) {
			switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
			case COMMA:
				;
				break;
			default:
				jj_la1[8] = jj_gen;
				break label_9;
			}
			jj_consume_token(COMMA);
			t = Type();
			token = jj_consume_token(ID);
			fl.addElement(new Formal(t, new Identifier(token.image)));
		}
		{
			if (true)
				return fl;
		}
		throw new Error("Missing return statement in function");
	}

	// FormalList FormalList() :
	// {
	// Type t;
	// Token token;
	// FormalList fl = new FormalList();
	// }
	// {
	// (t=Type() token=<ID> { (fl.addElement(new Formal(t,new
	// Identifier(token.image))); })*)?
	// {return fl;}
	// }
	static final public void Formal() throws ParseException {
		jj_consume_token(COMMA);
		Type();
		jj_consume_token(ID);
	}

	static final public Type Type() throws ParseException {
		Type t;
		Token token;
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case INT:
			jj_consume_token(INT);
			t = new IntegerType();
			break;
		case BOOLEAN:
			jj_consume_token(BOOLEAN);
			t = new BooleanType();
			break;
		case ID:
			token = jj_consume_token(ID);
			t = new IdentifierType(token.image);
			break;
		default:
			jj_la1[9] = jj_gen;
			jj_consume_token(-1);
			throw new ParseException();
		}
		{
			if (true)
				return t;
		}
		throw new Error("Missing return statement in function");
	}

	static final public Statement Statement() throws ParseException {
		Token token;
		Statement s1, s2, s3;
		Identifier id;
		Exp exp1, exp2;
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case LBRACE:
			jj_consume_token(LBRACE);
			s3 = new StatementList();
			label_10: while (true) {
				switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
				case LBRACE:
				case IF:
				case WHILE:
				case PRINT:
				case ID:
					;
					break;
				default:
					jj_la1[10] = jj_gen;
					break label_10;
				}
				s1 = Statement();
				((StatementList) s3).addElement(s1);
			}
			jj_consume_token(RBRACE);
			break;
		case IF:
			jj_consume_token(IF);
			jj_consume_token(LP);
			exp1 = Exp();
			jj_consume_token(RP);
			s1 = Statement();
			jj_consume_token(ELSE);
			s2 = Statement();
			s3 = new If(exp1, s1, s2);
			break;
		case WHILE:
			jj_consume_token(WHILE);
			jj_consume_token(LP);
			exp1 = Exp();
			jj_consume_token(RP);
			s1 = Statement();
			s3 = new While(exp1, s1);
			break;
		case PRINT:
			jj_consume_token(PRINT);
			jj_consume_token(LP);
			exp1 = Exp();
			jj_consume_token(RP);
			jj_consume_token(PONTOVIRGULA);
			s3 = new Print(exp1);
			break;
		case ID:
			token = jj_consume_token(ID);
			id = new Identifier(token.image);
			switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
			case IGUAL:
				jj_consume_token(IGUAL);
				exp1 = Exp();
				jj_consume_token(PONTOVIRGULA);
				s3 = new Assign(id, exp1);
				break;
			case LBRACKETS:
				jj_consume_token(LBRACKETS);
				exp1 = Exp();
				jj_consume_token(RBRACKETS);
				jj_consume_token(IGUAL);
				exp2 = Exp();
				jj_consume_token(PONTOVIRGULA);
				s3 = new ArrayAssign(id, exp1, exp2);
				break;
			default:
				jj_la1[11] = jj_gen;
				jj_consume_token(-1);
				throw new ParseException();
			}
			break;
		default:
			jj_la1[12] = jj_gen;
			jj_consume_token(-1);
			throw new ParseException();
		}
		{
			if (true)
				return s3;
		}
		throw new Error("Missing return statement in function");
	}

	static final public Exp Exp() throws ParseException {
		Exp e;
		e = ExpPrime();
		{
			if (true)
				return ExpPost(e);
		}
		throw new Error("Missing return statement in function");
	}

	static final public Exp ExpPrime() throws ParseException {
		Identifier i;
		Exp e;
		Token t;
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case INTEGER_LITERAL:
			t = jj_consume_token(INTEGER_LITERAL); {
			if (true)
				return new IntegerLiteral(Integer.parseInt(t.image));
		}
			break;
		case TRUE:
			jj_consume_token(TRUE); {
			if (true)
				return new True();
		}
			break;
		case FALSE:
			jj_consume_token(FALSE); {
			if (true)
				return new False();
		}
			break;
		case ID:
			i = Identifier(); {
			if (true)
				return new IdentifierExp(i.toString());
		}
			break;
		case THIS:
			jj_consume_token(THIS); {
			if (true)
				return new This();
		}
			break;
		default:
			jj_la1[13] = jj_gen;
			if (jj_2_2(2)) {
				jj_consume_token(NEW);
				jj_consume_token(INT);
				jj_consume_token(LBRACKETS);
				e = Exp();
				jj_consume_token(RBRACKETS);
				{
					if (true)
						return new NewArray(e);
				}
			} else {
				switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
				case NEW:
					jj_consume_token(NEW);
					i = Identifier();
					jj_consume_token(LP);
					jj_consume_token(RP); {
					if (true)
						return new NewObject(i);
				}
					break;
				case EXCLAMACAO:
					jj_consume_token(EXCLAMACAO);
					e = Exp(); {
					if (true)
						return new Not(e);
				}
					break;
				case LP:
					jj_consume_token(LP);
					e = Exp();
					jj_consume_token(RP); {
					if (true)
						return ExpPost(e);
				}
					break;
				default:
					jj_la1[14] = jj_gen;
					jj_consume_token(-1);
					throw new ParseException();
				}
			}
		}
		throw new Error("Missing return statement in function");
	}

	static final public Exp ExpPost(Exp e1) throws ParseException {
		Exp e2;
		Identifier i;
		ExpList el;
		switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
		case E:
			jj_consume_token(E);
			e2 = Exp(); {
			if (true)
				return new And(e1, e2);
		}
			break;
		case MENORQUE:
			jj_consume_token(MENORQUE);
			e2 = Exp(); {
			if (true)
				return new LessThan(e1, e2);
		}
			break;
		case MAIS:
			jj_consume_token(MAIS);
			e2 = Exp(); {
			if (true)
				return new Plus(e1, e2);
		}
			break;
		case MENOS:
			jj_consume_token(MENOS);
			e2 = Exp(); {
			if (true)
				return new Minus(e1, e2);
		}
			break;
		case VEZES:
			jj_consume_token(VEZES);
			e2 = Exp(); {
			if (true)
				return new Times(e1, e2);
		}
			break;
		default:
			jj_la1[15] = jj_gen;
			if (jj_2_3(2)) {
				jj_consume_token(PONTO);
				jj_consume_token(LENGTH);
				{
					if (true)
						return new ArrayLength(e1);
				}
			} else {
				switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
				case LBRACKETS:
					jj_consume_token(LBRACKETS);
					e2 = Exp();
					jj_consume_token(RBRACKETS); {
					if (true)
						return ExpPost(e2);
				}
					break;
				case PONTO:
					jj_consume_token(PONTO);
					i = Identifier();
					jj_consume_token(LP);
					el = ExpList();
					jj_consume_token(RP); {
					if (true)
						return new Call(e1, i, el);
				}
					break;
				default:
					jj_la1[16] = jj_gen; {
					if (true)
						return e1;
				}
				}
			}
		}
		throw new Error("Missing return statement in function");
	}

	static final public ExpList ExpList() throws ParseException {
		ExpList el = new ExpList();
		Exp e;
		e = Exp();
		el.addElement(e);
		label_11: while (true) {
			switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
			case COMMA:
				;
				break;
			default:
				jj_la1[17] = jj_gen;
				break label_11;
			}
			jj_consume_token(COMMA);
			e = Exp();
			el.addElement(e);
		}
		{
			if (true)
				return el;
		}
		throw new Error("Missing return statement in function");
	}

	static final public Identifier Identifier() throws ParseException {
		Token token;
		token = jj_consume_token(ID);
		{
			if (true)
				return new Identifier(token.image);
		}
		throw new Error("Missing return statement in function");
	}

	static private boolean jj_2_1(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_1();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(0, xla);
		}
	}

	static private boolean jj_2_2(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_2();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(1, xla);
		}
	}

	static private boolean jj_2_3(int xla) {
		jj_la = xla;
		jj_lastpos = jj_scanpos = token;
		try {
			return !jj_3_3();
		} catch (LookaheadSuccess ls) {
			return true;
		} finally {
			jj_save(2, xla);
		}
	}

	static private boolean jj_3R_14() {
		if (jj_scan_token(INT))
			return true;
		return false;
	}

	static private boolean jj_3R_13() {
		Token xsp;
		xsp = jj_scanpos;
		if (jj_3R_14()) {
			jj_scanpos = xsp;
			if (jj_3R_15()) {
				jj_scanpos = xsp;
				if (jj_3R_16())
					return true;
			}
		}
		return false;
	}

	static private boolean jj_3R_12() {
		if (jj_3R_13())
			return true;
		if (jj_scan_token(ID))
			return true;
		return false;
	}

	static private boolean jj_3_2() {
		if (jj_scan_token(NEW))
			return true;
		if (jj_scan_token(INT))
			return true;
		return false;
	}

	static private boolean jj_3_1() {
		if (jj_3R_12())
			return true;
		return false;
	}

	static private boolean jj_3R_16() {
		if (jj_scan_token(ID))
			return true;
		return false;
	}

	static private boolean jj_3R_15() {
		if (jj_scan_token(BOOLEAN))
			return true;
		return false;
	}

	static private boolean jj_3_3() {
		if (jj_scan_token(PONTO))
			return true;
		if (jj_scan_token(LENGTH))
			return true;
		return false;
	}

	static private boolean jj_initialized_once = false;
	/** Generated Token Manager. */
	static public MiniJavaParserTokenManager token_source;
	static JavaCharStream jj_input_stream;
	/** Current token. */
	static public Token token;
	/** Next token. */
	static public Token jj_nt;
	static private int jj_ntk;
	static private Token jj_scanpos, jj_lastpos;
	static private int jj_la;
	static private int jj_gen;
	static final private int[] jj_la1 = new int[18];
	static private int[] jj_la1_0;
	static private int[] jj_la1_1;

	static {
		jj_la1_init_0();
		jj_la1_init_1();
	}

	private static void jj_la1_init_0() {
		jj_la1_0 = new int[] { 0x8, 0x8, 0x60000, 0x4, 0x60000, 0x4, 0x6200c, 0xd00002, 0x200, 0x60000, 0xd00002,
				0x80400, 0xd00002, 0x0, 0x80, 0x3b000000, 0x80000400, 0x200, };
	}

	private static void jj_la1_init_1() {
		jj_la1_1 = new int[] { 0x0, 0x0, 0x400, 0x0, 0x400, 0x0, 0x400, 0x400, 0x0, 0x400, 0x400, 0x0, 0x400, 0x60e,
				0x30, 0x0, 0x0, 0x0, };
	}

	static final private JJCalls[] jj_2_rtns = new JJCalls[3];
	static private boolean jj_rescan = false;
	static private int jj_gc = 0;

	/** Constructor with InputStream. */
	public MiniJavaParser(java.io.InputStream stream) {
		this(stream, null);
	}

	/** Constructor with InputStream and supplied encoding */
	public MiniJavaParser(java.io.InputStream stream, String encoding) {
		if (jj_initialized_once) {
			System.out.println("ERROR: Second call to constructor of static parser.  ");
			System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
			System.out.println("       during parser generation.");
			throw new Error();
		}
		jj_initialized_once = true;
		try {
			jj_input_stream = new JavaCharStream(stream, encoding, 1, 1);
		} catch (java.io.UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		token_source = new MiniJavaParserTokenManager(jj_input_stream);
		token = new Token();
		jj_ntk = -1;
		jj_gen = 0;
		for (int i = 0; i < 18; i++)
			jj_la1[i] = -1;
		for (int i = 0; i < jj_2_rtns.length; i++)
			jj_2_rtns[i] = new JJCalls();
	}

	/** Reinitialise. */
	static public void ReInit(java.io.InputStream stream) {
		ReInit(stream, null);
	}

	/** Reinitialise. */
	static public void ReInit(java.io.InputStream stream, String encoding) {
		try {
			jj_input_stream.ReInit(stream, encoding, 1, 1);
		} catch (java.io.UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		token_source.ReInit(jj_input_stream);
		token = new Token();
		jj_ntk = -1;
		jj_gen = 0;
		for (int i = 0; i < 18; i++)
			jj_la1[i] = -1;
		for (int i = 0; i < jj_2_rtns.length; i++)
			jj_2_rtns[i] = new JJCalls();
	}

	/** Constructor. */
	public MiniJavaParser(java.io.Reader stream) {
		if (jj_initialized_once) {
			System.out.println("ERROR: Second call to constructor of static parser. ");
			System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
			System.out.println("       during parser generation.");
			throw new Error();
		}
		jj_initialized_once = true;
		jj_input_stream = new JavaCharStream(stream, 1, 1);
		token_source = new MiniJavaParserTokenManager(jj_input_stream);
		token = new Token();
		jj_ntk = -1;
		jj_gen = 0;
		for (int i = 0; i < 18; i++)
			jj_la1[i] = -1;
		for (int i = 0; i < jj_2_rtns.length; i++)
			jj_2_rtns[i] = new JJCalls();
	}

	/** Reinitialise. */
	static public void ReInit(java.io.Reader stream) {
		jj_input_stream.ReInit(stream, 1, 1);
		token_source.ReInit(jj_input_stream);
		token = new Token();
		jj_ntk = -1;
		jj_gen = 0;
		for (int i = 0; i < 18; i++)
			jj_la1[i] = -1;
		for (int i = 0; i < jj_2_rtns.length; i++)
			jj_2_rtns[i] = new JJCalls();
	}

	/** Constructor with generated Token Manager. */
	public MiniJavaParser(MiniJavaParserTokenManager tm) {
		if (jj_initialized_once) {
			System.out.println("ERROR: Second call to constructor of static parser. ");
			System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
			System.out.println("       during parser generation.");
			throw new Error();
		}
		jj_initialized_once = true;
		token_source = tm;
		token = new Token();
		jj_ntk = -1;
		jj_gen = 0;
		for (int i = 0; i < 18; i++)
			jj_la1[i] = -1;
		for (int i = 0; i < jj_2_rtns.length; i++)
			jj_2_rtns[i] = new JJCalls();
	}

	/** Reinitialise. */
	public void ReInit(MiniJavaParserTokenManager tm) {
		token_source = tm;
		token = new Token();
		jj_ntk = -1;
		jj_gen = 0;
		for (int i = 0; i < 18; i++)
			jj_la1[i] = -1;
		for (int i = 0; i < jj_2_rtns.length; i++)
			jj_2_rtns[i] = new JJCalls();
	}

	static private Token jj_consume_token(int kind) throws ParseException {
		Token oldToken;
		if ((oldToken = token).next != null)
			token = token.next;
		else
			token = token.next = token_source.getNextToken();
		jj_ntk = -1;
		if (token.kind == kind) {
			jj_gen++;
			if (++jj_gc > 100) {
				jj_gc = 0;
				for (int i = 0; i < jj_2_rtns.length; i++) {
					JJCalls c = jj_2_rtns[i];
					while (c != null) {
						if (c.gen < jj_gen)
							c.first = null;
						c = c.next;
					}
				}
			}
			return token;
		}
		token = oldToken;
		jj_kind = kind;
		throw generateParseException();
	}

	static private final class LookaheadSuccess extends java.lang.Error {
	}

	static final private LookaheadSuccess jj_ls = new LookaheadSuccess();

	static private boolean jj_scan_token(int kind) {
		if (jj_scanpos == jj_lastpos) {
			jj_la--;
			if (jj_scanpos.next == null) {
				jj_lastpos = jj_scanpos = jj_scanpos.next = token_source.getNextToken();
			} else {
				jj_lastpos = jj_scanpos = jj_scanpos.next;
			}
		} else {
			jj_scanpos = jj_scanpos.next;
		}
		if (jj_rescan) {
			int i = 0;
			Token tok = token;
			while (tok != null && tok != jj_scanpos) {
				i++;
				tok = tok.next;
			}
			if (tok != null)
				jj_add_error_token(kind, i);
		}
		if (jj_scanpos.kind != kind)
			return true;
		if (jj_la == 0 && jj_scanpos == jj_lastpos)
			throw jj_ls;
		return false;
	}

	/** Get the next Token. */
	static final public Token getNextToken() {
		if (token.next != null)
			token = token.next;
		else
			token = token.next = token_source.getNextToken();
		jj_ntk = -1;
		jj_gen++;
		return token;
	}

	/** Get the specific Token. */
	static final public Token getToken(int index) {
		Token t = token;
		for (int i = 0; i < index; i++) {
			if (t.next != null)
				t = t.next;
			else
				t = t.next = token_source.getNextToken();
		}
		return t;
	}

	static private int jj_ntk() {
		if ((jj_nt = token.next) == null)
			return (jj_ntk = (token.next = token_source.getNextToken()).kind);
		else
			return (jj_ntk = jj_nt.kind);
	}

	static private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
	static private int[] jj_expentry;
	static private int jj_kind = -1;
	static private int[] jj_lasttokens = new int[100];
	static private int jj_endpos;

	static private void jj_add_error_token(int kind, int pos) {
		if (pos >= 100)
			return;
		if (pos == jj_endpos + 1) {
			jj_lasttokens[jj_endpos++] = kind;
		} else if (jj_endpos != 0) {
			jj_expentry = new int[jj_endpos];
			for (int i = 0; i < jj_endpos; i++) {
				jj_expentry[i] = jj_lasttokens[i];
			}
			jj_entries_loop: for (java.util.Iterator<?> it = jj_expentries.iterator(); it.hasNext();) {
				int[] oldentry = (int[]) (it.next());
				if (oldentry.length == jj_expentry.length) {
					for (int i = 0; i < jj_expentry.length; i++) {
						if (oldentry[i] != jj_expentry[i]) {
							continue jj_entries_loop;
						}
					}
					jj_expentries.add(jj_expentry);
					break jj_entries_loop;
				}
			}
			if (pos != 0)
				jj_lasttokens[(jj_endpos = pos) - 1] = kind;
		}
	}

	/** Generate ParseException. */
	static public ParseException generateParseException() {
		jj_expentries.clear();
		boolean[] la1tokens = new boolean[51];
		if (jj_kind >= 0) {
			la1tokens[jj_kind] = true;
			jj_kind = -1;
		}
		for (int i = 0; i < 18; i++) {
			if (jj_la1[i] == jj_gen) {
				for (int j = 0; j < 32; j++) {
					if ((jj_la1_0[i] & (1 << j)) != 0) {
						la1tokens[j] = true;
					}
					if ((jj_la1_1[i] & (1 << j)) != 0) {
						la1tokens[32 + j] = true;
					}
				}
			}
		}
		for (int i = 0; i < 51; i++) {
			if (la1tokens[i]) {
				jj_expentry = new int[1];
				jj_expentry[0] = i;
				jj_expentries.add(jj_expentry);
			}
		}
		jj_endpos = 0;
		jj_rescan_token();
		jj_add_error_token(0, 0);
		int[][] exptokseq = new int[jj_expentries.size()][];
		for (int i = 0; i < jj_expentries.size(); i++) {
			exptokseq[i] = jj_expentries.get(i);
		}
		return new ParseException(token, exptokseq, tokenImage);
	}

	/** Enable tracing. */
	static final public void enable_tracing() {
	}

	/** Disable tracing. */
	static final public void disable_tracing() {
	}

	static private void jj_rescan_token() {
		jj_rescan = true;
		for (int i = 0; i < 3; i++) {
			try {
				JJCalls p = jj_2_rtns[i];
				do {
					if (p.gen > jj_gen) {
						jj_la = p.arg;
						jj_lastpos = jj_scanpos = p.first;
						switch (i) {
						case 0:
							jj_3_1();
							break;
						case 1:
							jj_3_2();
							break;
						case 2:
							jj_3_3();
							break;
						}
					}
					p = p.next;
				} while (p != null);
			} catch (LookaheadSuccess ls) {
			}
		}
		jj_rescan = false;
	}

	static private void jj_save(int index, int xla) {
		JJCalls p = jj_2_rtns[index];
		while (p.gen > jj_gen) {
			if (p.next == null) {
				p = p.next = new JJCalls();
				break;
			}
			p = p.next;
		}
		p.gen = jj_gen + xla - jj_la;
		p.first = token;
		p.arg = xla;
	}

	static final class JJCalls {
		int gen;
		Token first;
		int arg;
		JJCalls next;
	}

}