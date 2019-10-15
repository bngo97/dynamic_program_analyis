import jdk.internal.org.objectweb.asm.Label;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;

import java.util.*;

public class CfgMethodVisitorV2 extends MethodVisitor {

    String methodName;

    List<MyLabelNode> labelNodes;
    Map<Label, MyLabelNode> labels;

    MyLabelNode prevLabel;
    boolean skipLabel;
    List<BasicBlock> blocks;
    Map<Label, BasicBlock> labelBlocks;

    public CfgMethodVisitorV2(MethodVisitor mv, String name) {
        super(Opcodes.ASM5);
        methodName = name;
        labelNodes = new ArrayList<>();
        labels = new HashMap<>();
        blocks = new ArrayList<>();
        labelBlocks = new HashMap<>();
    }

    @Override
    public void visitLabel(Label label) {
        System.out.println(label);
        if(!labels.containsKey(label)) {
            labels.put(label, new MyLabelNode(label));
        }
        MyLabelNode node = labels.get(label);
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
            labels.put(label, new MyLabelNode(label));
        }
        MyLabelNode node = labels.get(label);
        node.jumpedTo = true;
        prevLabel.jumpedFrom = true;
        prevLabel.connections.add(label);
        if(opcode == Opcodes.GOTO) {
            skipLabel = true;
        }
    }

    @Override
    public void visitTableSwitchInsn(int i, int i1, Label label, Label... labels) {
        for(Label l : labels) {
            if(!this.labels.containsKey(l)) {
                MyLabelNode node = new MyLabelNode(l);
                node.jumpedTo = true;
                prevLabel.connections.add(l);
            }
        }
        if(!this.labels.containsKey(label)) {
            MyLabelNode node = new MyLabelNode(label);
            node.jumpedTo = true;
            prevLabel.connections.add(label);
        }
        prevLabel.jumpedFrom = true;
    }

    @Override
    public void visitEnd() {
        System.out.println();
        System.out.println("--------------------------------------");
        System.out.println();
        mergeBlocks();
        for(BasicBlock block : blocks) {
            List<BasicBlock> connections = new ArrayList<>(block.connections);
            Collections.sort(connections, (b1, b2) -> b1.blockId - b2.blockId);
            System.out.println(block + ": " + block.connections);
        }
        System.out.println();
        System.out.println("--------------------------------------");
        System.out.println();
        for(MyLabelNode node : labelNodes) {
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

        for(MyLabelNode label : labelNodes) {
            if(!label.jumpedTo && label.prev != null && !label.prev.jumpedFrom) {
                labelBlocks.put(label.label, currBlock);
                System.out.println("label " + label + " -> " + labelBlocks);
            }
            if(label.jumpedFrom) {
                System.out.println("WTF " + label);
                if(!labelBlocks.containsKey(label.label)) {
                    labelBlocks.put(label.label, new BasicBlock(idx++, label.label));
                }
                blocks.add(currBlock);
                currBlock = labelBlocks.get(label.label);
                if(currBlock.blockId == -1) {
                    currBlock.blockId = idx++;
                }
                for(Label l : label.connections) {
                    if(!labelBlocks.containsKey(l)) {
                        labelBlocks.put(l, new BasicBlock(l));
                    }
                    currBlock.connections.add(labelBlocks.get(l));
                }
            } else if(label.jumpedTo) {
                blocks.add(currBlock);
                if(!labelBlocks.containsKey(label.label)) {
                    labelBlocks.put(label.label, new BasicBlock(idx++, label.label));
                }
                currBlock = labelBlocks.get(label.label);
                if(currBlock.blockId == -1) {
                    currBlock.blockId = idx++;
                }
                for(Label l : label.connections) {
                    if(labels.get(l).jumpedTo) {
                        if(!labelBlocks.containsKey(l)) {
                            labelBlocks.put(l, new BasicBlock(l));
                        }
                        currBlock.connections.add(labelBlocks.get(l));
                    }
                }
            }
            else if(label.connections.size() == 1) {
                Label nextLabel = label.connections.get(0);
                MyLabelNode nextLabelNode = labels.get(nextLabel);
                if(nextLabelNode.jumpedTo) {
                    if(!labelBlocks.containsKey(nextLabel)) {
                        labelBlocks.put(nextLabel, new BasicBlock(nextLabel));
                    }
                    currBlock.connections.add(labelBlocks.get(nextLabel));
                }
            }

//            if(labelBlocks.containsKey(label)) {
//                blocks.add(currBlock);
//                //currBlock.connections.add(labelBlocks.get(label));
//                currBlock = labelBlocks.get(label);
//                if(currBlock.blockId == -1) {
//                    currBlock.blockId = idx++;
//                }
//            } else if(label.jumpedTo) {
//                blocks.add(currBlock);
//                currBlock = new BasicBlock(idx++, label.label);
//                labelBlocks.put(label.label, currBlock);
//            }
//            if(label.connections.size() > 1) {
//                for(Label l : label.connections) {
//                    if(!labelBlocks.containsKey(l)) {
//                        labelBlocks.put(l, new BasicBlock());
//                    }
//                }
//            }
//            if(label.jumpedTo || (label.prev != null && label.prev.connections.size() > 1)) {
//                blocks.add(currBlock);
//                currBlock = new BasicBlock(idx++, label.label);
//            }
//            labelBlocks.put(label.label, currBlock);
            if(currBlock != null) {
                currBlock.labels.add(label.label);
            }
        }
        blocks.add(currBlock);
    }
}
