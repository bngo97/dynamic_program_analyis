import jdk.internal.org.objectweb.asm.Label;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;

import java.util.*;

public class CfgMethodVisitorV3 extends MethodVisitor {

    String methodName;
    Set<Label> jumpedToLabels;
    Map<Label, BasicBlock> blocks;
    List<Instruction> instructions;


    public CfgMethodVisitorV3(String name) {
        super(Opcodes.ASM5);
        methodName = name;
        jumpedToLabels = new HashSet<>();
        blocks = new HashMap<>();
        instructions = new ArrayList<>();
    }

    @Override
    public void visitLabel(Label label) {
        super.visitLabel(label);
    }

    @Override
    public void visitJumpInsn(int i, Label label) {
        super.visitJumpInsn(i, label);
    }

    @Override
    public void visitTableSwitchInsn(int i, int i1, Label label, Label... labels) {
        super.visitTableSwitchInsn(i, i1, label, labels);
    }

    @Override
    public void visitEnd() {
        super.visitEnd();
    }
}
