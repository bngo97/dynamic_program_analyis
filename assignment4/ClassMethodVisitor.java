import org.objectweb.asm.Opcodes;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ClassMethodVisitor extends ClassVisitor {

    public static void main(String[] args) throws IOException {
        byte[] code = Files.readAllBytes(new File("src/Input.class").toPath());
        ClassReader cr = new ClassReader(code);
        ClassWriter cw = new ClassWriter(cr, 0);
        ClassMethodVisitor dv = new ClassMethodVisitor("Input", cw);
        cr.accept(dv, 0);
        byte[] newCode = cw.toByteArray();
        Files.write(new File("Input.class").toPath(), newCode);
    }

    String className;

    public ClassMethodVisitor(String className, ClassWriter cw) {
        super(Opcodes.ASM5, cw);
        this.className = className;
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
        if (!name.equals("<init>")) {
            mv = new MethodCoverageVisitor(mv, className, name);
        }
        return mv;
    }

}

class MethodCoverageVisitor extends MethodVisitor {
    private String className;
    private String methodName;

    public MethodCoverageVisitor(MethodVisitor mv, String className, String methodName) {
        super(Opcodes.ASM5, mv);
        this.className = className;
        this.methodName = methodName;
    }

    @Override
    public void visitCode() {
        if(className.equals("Input") && methodName.equals("main")) {
            MethodCounter.initialize();
        }
        MethodCounter.addMethod(className, methodName);
        mv.visitLdcInsn(className);
        mv.visitLdcInsn(methodName);
        mv.visitMethodInsn(Opcodes.INVOKESTATIC, "MethodCounter","incrementCount", "(Ljava/lang/String;Ljava/lang/String;)V", false);
        super.visitCode();
    }

    @Override
    public void visitInsn(int opcode) {
        // print result when before main method in Input returns
        if(opcode==Opcodes.RETURN && className.equals("Input") && methodName.equals("main")) {
            mv.visitMethodInsn(Opcodes.INVOKESTATIC, "MethodCounter", "writeResults", "()V", false);
        }
        super.visitInsn(opcode);
    }

}
