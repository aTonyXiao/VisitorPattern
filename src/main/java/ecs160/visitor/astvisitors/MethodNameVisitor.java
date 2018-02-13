package ecs160.visitor.astvisitors;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodDeclaration;

import java.util.ArrayList;
import java.util.List;

public class MethodNameVisitor extends ASTVisitor {
    private List<String> nameList = new ArrayList<String>();

    public List<String> getNameList() {
        return nameList;
    }

    public boolean visit(MethodDeclaration node) {
        nameList.add(node.getName().toString());

        return false;

    }
}
