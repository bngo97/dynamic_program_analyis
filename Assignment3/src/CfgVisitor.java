import jdk.internal.org.objectweb.asm.*;
import jdk.internal.org.objectweb.asm.tree.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;


public class CfgVisitor extends ClassVisitor {

    public static void main(String[] args) throws IOException {
        byte[] code = Files.readAllBytes(new File("tst/TestMultipleStatements.class").toPath());
        ClassReader reader = new ClassReader(code);
        ClassNode classNode = new ClassNode();
        reader.accept(classNode, 0);
        MethodNode main = classNode.methods.get(1);
        InsnList instructions = main.instructions;
        AbstractInsnNode prevInstruction = null;
        for (int m_i = 0; m_i < instructions.size(); m_i++) {
            AbstractInsnNode instruction = instructions.get(m_i);
            if(instruction instanceof LabelNode) {
                LabelNode labelNode = (LabelNode) instruction;
                Label label = labelNode.getLabel();
                System.out.println(label);
                prevInstruction = instruction;
            } else if(instruction instanceof JumpInsnNode) {
                JumpInsnNode node = (JumpInsnNode) instruction;
                LabelNode labelNode = node.label;
                if(node.getOpcode() == Opcodes.GOTO) {
                    System.out.println("GO TO " +  labelNode.getLabel());
                } else {
                    System.out.println("JUMP TO " + labelNode.getLabel());
                }
                prevInstruction = instruction;
            }
        }

        System.out.println();
        System.out.println("-----------------------------------");
        System.out.println();
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
            mv = new CfgMethodVisitorV3(name);
        }
        return mv;
    }

    @Override
    public void visitEnd() {
        super.visitEnd();
    }
}
