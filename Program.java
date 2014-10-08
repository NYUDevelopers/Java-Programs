
public class Program {

	public static void main(String[] args) {
		
		Stack ObjStack = new Stack();
		
	  System.out.println("First we do pop operation when stack is empty "+ObjStack.pop());
	  
	  ObjStack.push("swapnil");
	  ObjStack.push("Aher");
	  ObjStack.push("Abhishek");
	  
	  System.out.println("We added 3 element to the stack");
	  
	  System.out.println("We poped the last item from stack "+ ObjStack.pop());
	  
	  
	  

	}

}
