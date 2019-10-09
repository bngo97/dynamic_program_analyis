import org.objectweb.asm.ClassReader;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.Opcodes;


public class CfgMethodVisitor extends MethodVisitor {


    // LOOK AT JUMP INSTRUCTIONS
    // LOOK AT LABEL
    String methodName;
    public CfgMethodVisitor(MethodVisitor mv, String methodName) {
        super(Opcodes.ASM5, mv);
        this.methodName = methodName;
    }

    @Override
    public void visitCode() {
        super.visitCode();
    }

    @Override
    public void visitJumpInsn(int opcode, Label label) {
        super.visitJumpInsn(opcode, label);
    }

}
