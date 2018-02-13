package ecs160.visitor.astvisitors;


import java.util.List;

import org.eclipse.jdt.core.dom.*;

/**
 * Class to print out information about every method
 * declaration we visit in the AST.
 *
 */
public class MethodVisitor extends ASTVisitor {
	
	private boolean hasPrivateConstruct = false;
    private boolean publicStaticMethod = false;
    private boolean privateStaticVar = false;
    private boolean callConstructor = false;
    private int constructorNum = 0;

	public boolean visit(MethodDeclaration node) {
	    boolean findPrivate = false;
	    boolean[] findPublicStatic = new boolean[2];

		System.out.println(")");
		List<ASTNode> mods = (List<ASTNode>) node.modifiers();

		for (ASTNode m : mods)
		{
		    if(m.toString().equals("public"))
		        findPublicStatic[0] = true;
		    if(m.toString().equals("static"))
		        findPublicStatic[1] = true;
		    if(m.toString().equals("private"))
		        findPrivate = true;
		}

		if(findPublicStatic[0] && findPublicStatic[1]) {
			if (node.getReturnType2().toString().equals("DatabaseManager")) // since it only for hw1 otherwise check it in "SingletonCheckerVisitor"
				publicStaticMethod = true;
			IfVisitor ifVisitor = new IfVisitor();
			node.accept(ifVisitor);

			if(ifVisitor.callConstructor) {
				callConstructor = true;
			}
		}

		if(node.isConstructor() && findPrivate)
		    hasPrivateConstruct = true;

		return false;
	}

    public boolean visit(FieldDeclaration node) {
        boolean[] PrivateStatic = new boolean[2];
        List<ASTNode> mods = (List<ASTNode>) node.modifiers();
        for (ASTNode m : mods) {
            if (m.toString().equals("private"))
                PrivateStatic[0] = true;
            if (m.toString().equals("static"))
                PrivateStatic[1] = true;
        }

	    if(node.getType().toString().equals("DatabaseManager") && PrivateStatic[0] && PrivateStatic[1])
	        privateStaticVar = true;

	    return false;

    }


//	public boolean visit(ClassInstanceCreation node) {
//		CheckConstructor totalConstructor = new CheckConstructor();
//		node.accept(totalConstructor);
//		constructorNum = totalConstructor.count;
//		return false;
//	}



    public boolean hasPrivateConstruct() {
	    return hasPrivateConstruct;
    }

    public boolean publicStaticMethod() {
        return publicStaticMethod;
    }

    public boolean privateStaticVar() {
        return privateStaticVar;
    }

    public boolean callConstructorOnce() {
		return callConstructor && (constructorNum < 1);
	}
}
