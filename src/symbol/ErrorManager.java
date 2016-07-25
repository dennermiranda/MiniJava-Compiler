package symbol;

public class ErrorManager
{
	private boolean hasErrors;
	public ErrorManager(){
		hasErrors = false;
	}
	public boolean hasError(){
		return hasErrors;
	}
	public void printError(String msg){
		if (!hasErrors){
			System.out.println("Errors were found");
		}
		hasErrors = true;
		System.out.println(msg);
	}
}
