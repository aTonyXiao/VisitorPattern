package ecs160.visitor.astvisitors;

import ecs160.visitor.utilities.UtilReader;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

public class SingletonCheckerVisitor {
    private static String filename;
    private static File file;
    static MethodPrinter methodPrinter;

    public static SingletonCheckerVisitor setUpGrader(String filePath, String fileName){

        file = new File(filePath);
        fileName = filename;
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

        methodPrinter = new MethodPrinter();

        cu.accept(methodPrinter);

        return new SingletonCheckerVisitor();
    }

    public boolean gradeA() {
        return SingletonCheckerVisitor.methodPrinter.hasPrivateConstruct();
    }

    public boolean gradeB() {
        return SingletonCheckerVisitor.methodPrinter.publicStaticMethod();
    }

    public boolean gradeC() {
        return SingletonCheckerVisitor.methodPrinter.privateStaticVar();
    }
}
