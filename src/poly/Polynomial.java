package poly;

import java.io.IOException;
import java.util.Scanner;

/**
 * This class implements evaluate, add and multiply for polynomials.
 * 
 * @author runb-cs112
 *
 */
public class Polynomial {
	
	/**
	 * Reads a polynomial from an input stream (file or keyboard). The storage format
	 * of the polynomial is:
	 * <pre>
	 *     <coeff> <degree>
	 *     <coeff> <degree>
	 *     ...
	 *     <coeff> <degree>
	 * </pre>
	 * with the guarantee that degrees will be in descending order. For example:
	 * <pre>
	 *      4 5
	 *     -2 3
	 *      2 1
	 *      3 0
	 * </pre>
	 * which represents the polynomial:
	 * <pre>
	 *      4*x^5 - 2*x^3 + 2*x + 3 
	 * </pre>
	 * 
	 * @param sc Scanner from which a polynomial is to be read
	 * @throws IOException If there is any input error in reading the polynomial
	 * @return The polynomial linked list (front node) constructed from coefficients and
	 *         degrees read from scanner
	 */
	public static Node read(Scanner sc) 
	throws IOException {
		Node poly = null;
		while (sc.hasNextLine()) {
			Scanner scLine = new Scanner(sc.nextLine());
			poly = new Node(scLine.nextFloat(), scLine.nextInt(), poly);
			scLine.close();
		}
		return poly;
	}
	
	/**
	 * Returns the sum of two polynomials - DOES NOT change either of the input polynomials.
	 * The returned polynomial MUST have all new nodes. In other words, none of the nodes
	 * of the input polynomials can be in the result.
	 * 
	 * @param poly1 First input polynomial (front of polynomial linked list)
	 * @param poly2 Second input polynomial (front of polynomial linked list
	 * @return A new polynomial which is the sum of the input polynomials - the returned node
	 *         is the front of the result polynomial
	 */
	public static Node add(Node poly1, Node poly2) {
		/** COMPLETE THIS METHOD **/
		// FOLLOWING LINE IS A PLACEHOLDER TO MAKE THIS METHOD COMPILE
		// CHANGE IT AS NEEDED FOR YOUR IMPLEMENTATION
		Node node=null;
		Node temp1=poly1;
		Node temp2=poly2;
		Node nodereturn=null;
		while (temp2 != null && temp1 !=null)
		{
			Term r=temp1.term;
			int s=r.degree;
			float t=r.coeff;
			Term u=temp2.term;
			int v=u.degree;
			float w=u.coeff;
			if (v<s)
			{
				node=new Node(w,v,node);
				temp2=temp2.next;
			}
			else if(v>s)
			{
				node=new Node (t,s,node);
				temp1=temp1.next;
			}
			else
			{
				float temp=w+t;
				if (temp !=0)
				{
					node=new Node(temp,s, node);
				}
				temp2=temp2.next;
				temp1=temp1.next;
			}
		}
		while (temp2==null && temp1!=null)
		{
			Term r=temp1.term;
			int s=r.degree;
			float t=r.coeff;
			node=new Node (t,s,node);
			temp1=temp1.next;
		}
		while (temp1==null && temp2!=null)
		{
			Term u=temp2.term;
			int v=u.degree;
			float w=u.coeff;
			node=new Node(w,v,node);
			temp2=temp2.next;
		}
		if (node==null)
		{
			return node;
		}
		else 
		{
			while (node!=null)
			{
				Term u=node.term;
				int v=u.degree;
				float w=u.coeff;
				nodereturn=new Node(w,v,nodereturn);
				node=node.next;
			}
		}
		return nodereturn;
	}
	
	/**
	 * Returns the product of two polynomials - DOES NOT change either of the input polynomials.
	 * The returned polynomial MUST have all new nodes. In other words, none of the nodes
	 * of the input polynomials can be in the result.
	 * 
	 * @param poly1 First input polynomial (front of polynomial linked list)
	 * @param poly2 Second input polynomial (front of polynomial linked list)
	 * @return A new polynomial which is the product of the input polynomials - the returned node
	 *         is the front of the result polynomial
	 */
	public static Node multiply(Node poly1, Node poly2) {
		/** COMPLETE THIS METHOD **/
		// FOLLOWING LINE IS A PLACEHOLDER TO MAKE THIS METHOD COMPILE
		// CHANGE IT AS NEEDED FOR YOUR IMPLEMENTATION
		Node node=null;
		Node nodetemp=null;
		Node temp=poly1;
		Node temp2=poly2;
		Node other=null;
		while (temp2 != null && temp !=null)
		{
			Term r=temp.term;
			int s=r.degree;
			float t=r.coeff;
			Term u=temp2.term;
			int v=u.degree;
			float w=u.coeff;
			float coeff=t*w;
			int degree=s+v;
			if (coeff != 0)
			{
				other=new Node(coeff,degree,other);
			}
			if (temp2.next!=null)
			{
				temp2=temp2.next;
			}
			else
			{
				while (other!=null)
				{
					Term h=other.term;
					int i=h.degree;
					float j=h.coeff;
					nodetemp=new Node(j,i,nodetemp);
					other=other.next;
				}
				temp=temp.next;
				temp2=poly2;
				node=add(nodetemp,node);
				other= null;
				nodetemp=null;
			}
		}
		return node;
		
	}
		
	/**
	 * Evaluates a polynomial at a given value.
	 * 
	 * @param poly Polynomial (front of linked list) to be evaluated
	 * @param x Value at which evaluation is to be done
	 * @return Value of polynomial p at x
	 */
	public static float evaluate(Node poly, float x) {
		/** COMPLETE THIS METHOD **/
		// FOLLOWING LINE IS A PLACEHOLDER TO MAKE THIS METHOD COMPILE
		// CHANGE IT AS NEEDED FOR YOUR IMPLEMENTATION
		float sum=0;
		Node node=poly;
		while (node!=null)
		{
			Term r=node.term;
			int s=r.degree;
			float t=r.coeff;
			t=t*((float)(Math.pow(x,s)));
			sum+=t;
			node=node.next;
		}
		return sum;
	}
	
	/**
	 * Returns string representation of a polynomial
	 * 
	 * @param poly Polynomial (front of linked list)
	 * @return String representation, in descending order of degrees
	 */
	public static String toString(Node poly) {
		if (poly == null) {
			return "0";
		} 
		
		String retval = poly.term.toString();
		for (Node current = poly.next ; current != null ;
		current = current.next) {
			retval = current.term.toString() + " + " + retval;
		}
		return retval;
	}	
}
