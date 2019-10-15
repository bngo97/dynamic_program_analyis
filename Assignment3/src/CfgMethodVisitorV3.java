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
        instructions.add(new LabelInstruction(label));
    }

    @Override
    public void visitJumpInsn(int i, Label label) {
        instructions.add(new JumpInstruction(i, label));
        jumpedToLabels.add(label);
    }

    @Override
    public void visitTableSwitchInsn(int i, int i1, Label label, Label... labels) {
        instructions.add(new TableSwitchInstruction(i, i1, label, labels));
    }

    @Override
    public void visitEnd() {
        System.out.println(jumpedToLabels);
        blockIdx = 0;
        for(Instruction instruction : instructions) {
            if(instruction instanceof LabelInstruction) {
                LabelInstruction labelInstruction = (LabelInstruction) instruction;
                visitLabel(labelInstruction);
                System.out.println(labelInstruction.label);
            } else if(instruction instanceof JumpInstruction) {
                JumpInstruction jumpInstruction = (JumpInstruction) instruction;
                visitJump(jumpInstruction);
                if(jumpInstruction.opcode == Opcodes.GOTO) {
                    System.out.println("GO TO " + jumpInstruction.label);
                } else {
                    System.out.println("JUMP TO " + jumpInstruction.label);
                }
            } else if(instruction instanceof TableSwitchInstruction) {
                TableSwitchInstruction switchInstruction = (TableSwitchInstruction) instruction;
                visitSwitch(switchInstruction);
            }
            prevInstruction = instruction;
        }

        System.out.println();
        System.out.println("++++++++++++++++++++++++++++");
        System.out.println();
        List<BasicBlock> blockList = new ArrayList<>(blocks.values());
        Collections.sort(blockList, (b1, b2) -> b1.blockId - b2.blockId);
        for(BasicBlock block : blockList) {
            Collections.sort(block.connections, (b1, b2) -> b1.blockId - b2.blockId);
            System.out.println(block + ": " + block.connections);
        }
    }

    BasicBlock prevBlock;
    Instruction prevInstruction;
    int blockIdx;

    public void visitLabel(LabelInstruction instruction) {
        Label label = instruction.label;
        if(!jumpedToLabels.contains(label) && prevInstruction instanceof LabelInstruction) {
            return;
        }
        if(!blocks.containsKey(label)) {
            BasicBlock block = new BasicBlock(label);
            blocks.put(label, block);
        }
        BasicBlock block = blocks.get(label);
        block.blockId = blockIdx++;
        if(prevBlock != null && !((prevInstruction instanceof JumpInstruction) && ((JumpInstruction)prevInstruction).opcode == Opcodes.GOTO)) {
            prevBlock.connections.add(block);
        }
        prevBlock = block;
    }

    public void visitJump(JumpInstruction instruction) {
        Label label = instruction.label;
        if(!blocks.containsKey(label)) {
            BasicBlock block = new BasicBlock(label);
            block.jumpedTo = true;
            blocks.put(label, block);
        }
        if(prevBlock != null) {
            prevBlock.connections.add(blocks.get(label));
        }
    }

    public void visitSwitch(TableSwitchInstruction instruction) {
        
    }
}
