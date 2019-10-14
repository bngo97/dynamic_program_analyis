import jdk.internal.org.objectweb.asm.Label;

import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;

public class BasicBlock {

    int blockId;
    Label label;
    List<Label> labels;
    List<BasicBlock> connections;
    boolean jumpedTo;
    BasicBlock next;
    BasicBlock prev;

    public BasicBlock() {
        blockId = -1;
        labels = new ArrayList<>();
        connections = new ArrayList<>();
    }

    public BasicBlock(int id) {
        labels = new ArrayList<>();
        connections = new ArrayList<>();
        this.blockId = id;
    }

    public BasicBlock(Label l) {
        blockId = -1;
        labels = new ArrayList<>();
        connections = new ArrayList<>();
        this.label = l;
    }

    public BasicBlock(int id, Label l) {
        labels = new ArrayList<>();
        connections = new ArrayList<>();
        this.blockId = id;
        this.label = l;
    }

    @Override
    public String toString() {
        return label == null ? "null" : label.toString();
        //return "BB"+blockId;
    }
}
