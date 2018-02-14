package ecs160.visitor.astvisitors;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodDeclaration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MethodNameVisitor extends ASTVisitor {
    private List<String> nameList = new ArrayList<String>();
    private Map<String, String> contextCallAbsState = new HashMap<String, String>();

    public Map<String, String> getContextCallAbsState() {
        return contextCallAbsState;
    }

    public List<String> getNameList() {
        return nameList;
    }

    public boolean visit(MethodDeclaration node) {
        nameList.add(node.getName().toString());
        MethodInvocationPrinter methodInvoke = new MethodInvocationPrinter();
        node.accept(methodInvoke);

        if(((ArrayList<String>)methodInvoke.invokeMethodName).contains(node.getName().toString()))
            contextCallAbsState.put(node.getName().toString(), node.getName().toString());

        return false;
    }




}
