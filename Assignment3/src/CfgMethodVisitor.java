import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

/*
    I determine control flow graph using two passes through the methods instructions.
    The first pass collects all the jump, label, and switch statements and determines where the jumps are.
    The second pass uses this information to build basic blocks in the order in which they appear.
 */
public class CfgMethodVisitor extends MethodVisitor {

    String methodName;
    String outputFile;

    Set<Label> jumpedToLabels;
    List<Instruction> instructions;

    Map<Label, BasicBlock> blocks;
    BasicBlock prevBlock;
    Instruction prevInstruction;
    int blockIdx;

    public CfgMethodVisitor(String name, String outputFile) {
        super(Opcodes.ASM5);
        this.methodName = name;
        this.outputFile = outputFile;
        this.jumpedToLabels = new HashSet<>();
        this.blocks = new HashMap<>();
        this.instructions = new ArrayList<>();
        this.blockIdx = 0;
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
        for(Label l : labels ) {
            jumpedToLabels.add(l);
        }
        jumpedToLabels.add(label);
    }

    @Override
    public void visitEnd() {
        // second pass of label and jump instructions
        for(Instruction instruction : instructions) {
            if(instruction instanceof LabelInstruction) {
                LabelInstruction labelInstruction = (LabelInstruction) instruction;
                visitLabel(labelInstruction);
            } else if(instruction instanceof JumpInstruction) {
                JumpInstruction jumpInstruction = (JumpInstruction) instruction;
                visitJump(jumpInstruction);
            } else if(instruction instanceof TableSwitchInstruction) {
                TableSwitchInstruction switchInstruction = (TableSwitchInstruction) instruction;
                visitSwitch(switchInstruction);
            }
            prevInstruction = instruction;
        }
        List<BasicBlock> blockList = new ArrayList<>(blocks.values());
        Collections.sort(blockList, (b1, b2) -> b1.blockId - b2.blockId);
        writeResult();
    }

    public void writeResult() {
        try {
            PrintWriter pw = new PrintWriter(new File(outputFile));
            List<BasicBlock> blockList = new ArrayList<>(blocks.values());
            Collections.sort(blockList, (b1, b2) -> b1.blockId - b2.blockId);
            for(BasicBlock block : blockList) {
                List<BasicBlock> connections = new ArrayList<>(block.connections);
                Collections.sort(connections, (b1, b2) -> b1.blockId - b2.blockId);
                String connectionsString = connections.toString()
                        .replace("[","")
                        .replace("]","");
                pw.println(block + " => " + connectionsString);
            }
            pw.close();
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }
    }

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
        if(prevBlock == null) {
            return;
        }
        for(Label label : instruction.labels) {
            if(!blocks.containsKey(label)) {
                blocks.put(label, new BasicBlock(label));
            }
            BasicBlock block = blocks.get(label);
            block.jumpedTo = true;
            prevBlock.connections.add(blocks.get(label));
        }
        Label label = instruction.label;
        if(!blocks.containsKey(label)) {
            blocks.put(label, new BasicBlock(label));
        }
        BasicBlock block = blocks.get(label);
        block.jumpedTo = true;
        prevBlock.connections.add(blocks.get(label));
    }
}
