import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.MethodVisitor;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;


public class CfgVisitor extends ClassVisitor {

    public static void main(String[] args) throws IOException {
        if(args.length == 0) {
            throw new RuntimeException("No input files given!");
        }
        byte[] code = Files.readAllBytes(new File(args[0]).toPath());
        ClassReader reader = new ClassReader(code);
        CfgVisitor cv = new CfgVisitor(args[0]);
        reader.accept(cv, 0);
    }

    String classFile;
    String outputFile;

    public CfgVisitor(String classFile) {
        super(Opcodes.ASM5);
        this.classFile = classFile;
        int idx1 = classFile.lastIndexOf('/') + 1;
        int idx2 = classFile.indexOf(".class");
        this.outputFile = "results/" + classFile.substring(idx1, idx2) + ".txt";
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        MethodVisitor mv = super.visitMethod(access, name, descriptor, signature, exceptions);
        if(!name.equals("<init>")) {
            mv = new CfgMethodVisitor(name, outputFile);
        }
        return mv;
    }

}
