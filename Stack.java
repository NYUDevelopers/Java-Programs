
public class Stack {
	
	Node first= null;
	Node last = null;
	Node temp= null;
	
	boolean isEmpty()
	{
		if(first == null) 
			return true;
		else
			return false;

	}
	
	void push (String item)
	{
		if(isEmpty() == true)
		{
			first = new Node();
			first.item = item;
			first.next = null;
			last= first;
		}
		else
		{
			temp = first;
			first = new Node();
			first.item=item;
			first.next = temp;
		}
	}
	
	String pop()
	{
		String Result="";
		if(isEmpty()== true)
		{
			return "Stack is Empty";
		}
		else
		{
			Result = first.item;
			first = first.next;
			return Result;
		}
	}
}

class Node
{
	String item;
	Node next;
}