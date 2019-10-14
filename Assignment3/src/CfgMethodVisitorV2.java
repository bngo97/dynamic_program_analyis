import jdk.internal.org.objectweb.asm.Label;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;

import java.util.*;

public class CfgMethodVisitorV2 extends MethodVisitor {

    String methodName;

    List<LabelNode> labelNodes;
    Map<Label,LabelNode> labels;

    LabelNode prevLabel;
    boolean skipLabel;
    List<BasicBlock> blocks;
    Map<Label, BasicBlock> labelBlocks;

    public CfgMethodVisitorV2(MethodVisitor mv, String name) {
        super(Opcodes.ASM5, mv);
        methodName = name;
        labelNodes = new ArrayList<>();
        labels = new HashMap<>();
        blocks = new ArrayList<>();
        labelBlocks = new HashMap<>();
    }

    @Override
    public void visitLabel(Label label) {
        System.out.println(label);
        super.visitLabel(label);
        if(!labels.containsKey(label)) {
            labels.put(label, new LabelNode(label));
        }
        LabelNode node = labels.get(label);
        labelNodes.add(node);
        if(!skipLabel) {
            if(prevLabel != null) {
                prevLabel.connections.add(label);
                prevLabel.next = node;
            }
        }
        node.prev = prevLabel;
        prevLabel = node;
        skipLabel = false;
    }

    @Override
    public void visitJumpInsn(int opcode, Label label) {
        if(opcode == Opcodes.GOTO) {
            System.out.println("GO TO " + label);
        } else {
            System.out.println("JUMP TO " + label);
        }
        super.visitJumpInsn(opcode, label);
        if(!labels.containsKey(label)) {
            labels.put(label, new LabelNode(label));
        }
        LabelNode node = labels.get(label);
        node.jumpedTo = true;
        prevLabel.connections.add(label);
        if(opcode == Opcodes.GOTO) {
            skipLabel = true;
        }
    }

    @Override
    public void visitTableSwitchInsn(int i, int i1, Label label, Label... labels) {
        super.visitTableSwitchInsn(i, i1, label, labels);
        for(Label l : labels) {
            if(!this.labels.containsKey(l)) {
                LabelNode node = new LabelNode(l);
                node.jumpedTo = true;
                prevLabel.connections.add(l);
            }
        }
        if(!this.labels.containsKey(label)) {
            LabelNode node = new LabelNode(label);
            node.jumpedTo = true;
            prevLabel.connections.add(label);
        }
    }

    @Override
    public void visitEnd() {
        super.visitEnd();
        System.out.println();
        System.out.println("--------------------------------------");
        System.out.println();
        mergeBlocks();
        for(BasicBlock block : blocks) {
            Collections.sort(block.connections, (b1, b2) -> b1.blockId - b2.blockId);
            System.out.println(block + ": " + block.connections);
        }
        System.out.println();
        System.out.println("--------------------------------------");
        System.out.println();
        for(LabelNode node : labelNodes) {
            if(node.jumpedTo) {
                System.out.println(node);
            } else {
                System.out.println(node);
            }
        }
    }

    public void mergeBlocks() {
        int idx = 0;
        BasicBlock currBlock = new BasicBlock(idx++);
        for(LabelNode label : labelNodes) {
            if(label.jumpedTo || (label.prev != null && label.prev.connections.size() > 1)) {
                blocks.add(currBlock);
                currBlock = new BasicBlock(idx++, label.label);
            }
            labelBlocks.put(label.label, currBlock);
            currBlock.labels.add(label.label);
        }
        blocks.add(currBlock);
    }
}
