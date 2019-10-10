import jdk.internal.org.objectweb.asm.*;
import jdk.internal.org.objectweb.asm.tree.*;
//import org.objectweb.asm.*;
//import org.objectweb.asm.tree.ClassNode;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class CfgVisitor extends ClassVisitor {

    public static void main(String[] args) throws IOException {
        byte[] code = Files.readAllBytes(new File("src/SampleTest.class").toPath());
        ClassReader reader = new ClassReader(code);
        ClassNode classNode = new ClassNode();
        reader.accept(classNode, 0);
        MethodNode main = classNode.methods.get(1);
        InsnList instructions = main.instructions;
        for (int m_i = 0; m_i < instructions.size(); m_i++) {
            AbstractInsnNode instruction = instructions.get(m_i);
            if(instruction instanceof LabelNode) {
                //System.out.println(instruction.getClass());
                LabelNode labelNode = (LabelNode) instruction;
                Label label = labelNode.getLabel();
                System.out.println(label);
                //labelNode.getLabel().getOffset();
            } else if(instruction instanceof JumpInsnNode) {
                JumpInsnNode node = (JumpInsnNode) instruction;
                LabelNode labelNode = node.label;
                if(node.getOpcode() == Opcodes.GOTO) {
                    System.out.println("GO TO " +  labelNode.getLabel());
                } else {
                    System.out.println("JUMP TO " + labelNode.getLabel());
                }
            } else {
                System.out.println(instruction);
            }
        }
        System.out.println();
        System.out.println("-----------------------------------");
        System.out.println();
        CfgVisitor cv = new CfgVisitor(classNode);
        reader.accept(cv, 0);
    }

    public CfgVisitor(ClassNode cn) {
        super(Opcodes.ASM5, cn);
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
