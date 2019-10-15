import jdk.internal.org.objectweb.asm.*;
import java.util.*;

public class CfgMethodVisitor extends MethodVisitor {
    String methodName;
    Map<Label, BasicBlock> basicBlocks;
    List<BasicBlock> blocks;
    BasicBlock prevBlock;
    int prevOpcode;
    int blockIdx;
    boolean prevInstructionWasLabel;

    public CfgMethodVisitor(MethodVisitor mv, String methodName) {
        super(Opcodes.ASM5, mv);
        this.methodName = methodName;
        basicBlocks = new HashMap<>();
        blocks = new ArrayList<>();
        blockIdx = 0;
    }

    @Override
    public void visitCode() {
        super.visitCode();
    }

    @Override
    public void visitLabel(Label label) {
        super.visitLabel(label);
        System.out.println(label);
        // JUMPED TO OR CAME FROM JUMP
        if(prevInstructionWasLabel && !basicBlocks.containsKey(label) && !prevBlock.jumpedTo) {
            basicBlocks.put(label, prevBlock);
            return;
        }
        if(!basicBlocks.containsKey(label)) {
            BasicBlock block = new BasicBlock(label);
            basicBlocks.put(label, block);
            blocks.add(block);
        }
        BasicBlock block = basicBlocks.get(label);
        block.blockId = blockIdx++;
        if(prevBlock != null && prevOpcode != Opcodes.GOTO) {
            prevBlock.connections.add(block);
        }
        prevBlock = block;
        prevOpcode = -1;
        prevInstructionWasLabel = true;
    }

    @Override
    public void visitJumpInsn(int opcode, Label label) {
        super.visitJumpInsn(opcode, label);
        prevOpcode = opcode;
        if(opcode == Opcodes.GOTO) {
            System.out.println("GO TO " + label);
        } else {
            System.out.println("JUMP TO " + label);
        }
        if(!basicBlocks.containsKey(label)) {
            BasicBlock block = new BasicBlock(label);
            block.jumpedTo = true;
            basicBlocks.put(label, block);
            blocks.add(block);
        }
        if(prevBlock != null) {
            prevBlock.connections.add(basicBlocks.get(label));
            prevInstructionWasLabel = false;
        }
    }

    @Override
    public void visitTableSwitchInsn(int i, int i1, Label label, Label... labels) {
        System.out.println("SWITCH");
        for(Label l : labels) {
            BasicBlock block = new BasicBlock(l);
            prevBlock.connections.add(block);
        }
        BasicBlock block = new BasicBlock(label);
        prevBlock.connections.add(block);
        super.visitTableSwitchInsn(i, i1, label, labels);
    }

    @Override
    public void visitEnd() {
        super.visitEnd();
        System.out.println();
        System.out.println("--------------------------------------");
        System.out.println();
//        List<BasicBlock> blocks = new ArrayList<>(basicBlocks.values());
//        Collections.sort(blocks, (b1, b2) -> b1.blockId - b2.blockId);
        Collections.sort(blocks, (b1, b2) -> b1.blockId - b2.blockId);
        for(BasicBlock block : blocks) {
            List<BasicBlock> connections = new ArrayList<>(block.connections);
            Collections.sort(connections, (b1, b2) -> b1.blockId - b2.blockId);
            System.out.println(block + ": " + block.connections);
        }
    }

}
