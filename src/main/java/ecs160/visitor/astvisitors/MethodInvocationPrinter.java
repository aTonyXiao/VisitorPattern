package ecs160.visitor.astvisitors;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.MethodInvocation;

import ecs160.visitor.utilities.ASTNodeTypePrinter;

/**
 * Method to print out information about source code
 * calling a method.
 *
 */
public class MethodInvocationPrinter extends ASTVisitor{
	List<String> invokeMethodName = new ArrayList<String>();
	/**
	 * A visitor returning false means we do not check the child nodes further along in the tree.
	 * Returning true means we do visit the child nodes.
	 */
	public boolean visit(MethodInvocation node){
		invokeMethodName.add(node.getName().toString());
		return true; //If false, we wouldn't print out nested function calls.
	}
}
