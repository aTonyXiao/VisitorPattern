package ecs160.visitor.astvisitors;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.ClassInstanceCreation;

public class CheckConstructor extends ASTVisitor {
    public int count = 0;
    public boolean visit(ClassInstanceCreation node) {
        if(node.getType().toString().equals("DatabaseManager")){
            count ++;
        }
        return false;
    }
}
