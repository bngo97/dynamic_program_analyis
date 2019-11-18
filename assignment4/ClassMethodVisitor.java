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
        //System.out.println(className);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
        //System.out.println(name);
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
        // 0: getstatic     #3                  // Field java/lang/System.out:Ljava/io/PrintStream;
        // 3: ldc           #4                  // String mmm
        // 5: invokevirtual #5                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V

        mv.visitFieldInsn(org.objectweb.asm.Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        mv.visitLdcInsn(methodName);
        mv.visitMethodInsn(org.objectweb.asm.Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);

        super.visitCode();
    }

    @Override
    public void visitInsn(int opcode) {
        //whenever we find a RETURN, we instert the code, here only crazy example code
        if(opcode==Opcodes.RETURN && className.equals("C")){
            //mv.visitVarInsn(Opcodes.ALOAD, 42);
            System.out.println(className + "." + methodName);
        }
        super.visitInsn(opcode);
    }

}
