import org.objectweb.asm.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class CfgVisitor extends ClassVisitor {

    public static void main(String[] args) throws IOException {
        byte[] code = Files.readAllBytes(new File("").toPath());
        ClassReader cr = new ClassReader(code);
    }
    
    public CfgVisitor(ClassWriter cw) {
        super(Opcodes.ASM5, cw);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        MethodVisitor mv = super.visitMethod(access, name, descriptor, signature, exceptions);
        if(!name.equals("<init>")) {
            mv = new CfgMethodVisitor(mv, name);
        }
        return mv;
    }
}
