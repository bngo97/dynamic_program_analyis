import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.MethodVisitor;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;


public class CfgVisitor extends ClassVisitor {

    public static void main(String[] args) throws IOException {
        byte[] code = Files.readAllBytes(new File("../tst/TestMultipleStatements.class").toPath());
        ClassReader reader = new ClassReader(code);
        CfgVisitor cv = new CfgVisitor();
        reader.accept(cv, 0);
    }

    public CfgVisitor() {
        super(Opcodes.ASM5);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        MethodVisitor mv = super.visitMethod(access, name, descriptor, signature, exceptions);
        if(!name.equals("<init>")) {
            mv = new CfgMethodVisitor(name);
        }
        return mv;
    }

}
