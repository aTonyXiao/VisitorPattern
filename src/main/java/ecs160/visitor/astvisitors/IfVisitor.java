package ecs160.visitor.astvisitors;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.Statement;

import ecs160.visitor.utilities.ASTNodeTypePrinter;

/**
 * A visitor to print out information about if statements
 * including the expression, the body of the if, and the body of the 'else'.
 *
 */
public class IfVisitor extends ASTVisitor{

	public boolean callConstructor = false;
	
	public boolean visit(IfStatement node)
	{
		CheckConstructor checkConstructor = new CheckConstructor();
		node.accept(checkConstructor);

		if(checkConstructor.count == 1)
			callConstructor = true;


		return true; //True here allows us to recursively unwind an if statement with multiple blocks.
	}



}
