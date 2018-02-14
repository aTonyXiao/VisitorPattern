package ecs160.visitor.astvisitors;

import ecs160.visitor.utilities.UtilReader;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StateCheckerVisitor {
    List<String> contextMethodName;
    List<String> stateMethodName;
    Map<String, String> contextCallAbsState;
    static Map<String, String> mapPtr;

    public StateCheckerVisitor(List<String> contextMethodName, List<String> stateMethodName, Map<String, String> contextCallAbsState) {
        this.contextMethodName = contextMethodName;
        this.stateMethodName = stateMethodName;
        this.contextCallAbsState = contextCallAbsState;
    }

    public static StateCheckerVisitor setUpGrader(String contextPath, String contextName,
                                                  String absStatePath, String absStateName) {

        List<String> contextMethodName = parsingMethodName(contextPath, contextName);
        List<String> stateMethodName = parsingMethodName(absStatePath, absStateName);
        Map<String, String> contextCallAbsState = getContextMethodMap(contextPath, contextName);

        return new StateCheckerVisitor(contextMethodName, stateMethodName, contextCallAbsState);
    }


    public static Map<String, String> getContextMethodMap(String path, String name) {
        File file = new File(path);
        String text = "";

        try {
            text = UtilReader.read(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ASTParser parser = ASTParser.newParser(AST.JLS8);
        Map<String, String> options = JavaCore.getOptions();
        options.put(JavaCore.COMPILER_SOURCE, JavaCore.VERSION_1_8);
        parser.setCompilerOptions(options);
        parser.setKind(ASTParser.K_COMPILATION_UNIT);
        parser.setResolveBindings(true);
        parser.setBindingsRecovery(true);
        String[] classpath = { System.getProperty("java.home") + "/lib/rt.jar" };
        parser.setEnvironment(classpath, new String[] { "" }, new String[] { "UTF-8" }, true);
        parser.setSource(text.toCharArray());
        parser.setUnitName(file.getAbsolutePath());
        CompilationUnit cu = (CompilationUnit) parser.createAST(null);

        MethodNameVisitor methodName = new MethodNameVisitor();
        cu.accept(methodName);

        return methodName.getContextCallAbsState();

    }



    public static List<String> parsingMethodName(String path, String name) {
        File file = new File(path);
        String text = "";

        try {
            text = UtilReader.read(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ASTParser parser = ASTParser.newParser(AST.JLS8);
        Map<String, String> options = JavaCore.getOptions();
        options.put(JavaCore.COMPILER_SOURCE, JavaCore.VERSION_1_8);
        parser.setCompilerOptions(options);
        parser.setKind(ASTParser.K_COMPILATION_UNIT);
        parser.setResolveBindings(true);
        parser.setBindingsRecovery(true);
        String[] classpath = { System.getProperty("java.home") + "/lib/rt.jar" };
        parser.setEnvironment(classpath, new String[] { "" }, new String[] { "UTF-8" }, true);
        parser.setSource(text.toCharArray());
        parser.setUnitName(file.getAbsolutePath());
        CompilationUnit cu = (CompilationUnit) parser.createAST(null);

        MethodNameVisitor methodName = new MethodNameVisitor();
        cu.accept(methodName);

        return methodName.getNameList();
    }

    public boolean gradeA() {
        for(String str : stateMethodName) {
            if(!((ArrayList<String>) contextMethodName).contains(str)) // down cast List to ArrayList
                return false;
        }
        return true;
    }

    public int gradeB() {
        int count = 0;
        for(String str: stateMethodName) {
            if(contextCallAbsState.get(str) != null) // down cast List to ArrayList
                count ++ ;
        }


        return count;
    }

}
