
/**
 * Author: Dener Miranda Team Members: Dener Miranda, Edmilson Robson, Lucas
 * Brock, Pedro Frazao Course: Compilers I 2016.1 Professor: Heron Carvalho
 **/


public class Main {

	public static void main(String[] args) {
		System.out.println("Begin");
		try {
			new MiniJavaParser(System.in).Goal();
			System.out.println("Lexical analysis successfull");
		} catch (ParseException e) {
			System.out.println("Lexer Error : \n" + e.toString());
		}
	}
}
