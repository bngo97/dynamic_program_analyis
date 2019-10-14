import jdk.internal.org.objectweb.asm.Label;

import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;

public class BasicBlock {

    int blockId;
    Label label;
    Set<Label> labels;
    List<BasicBlock> connections;
    boolean jumpedTo;
    BasicBlock next;
    BasicBlock prev;

    public BasicBlock() {
        labels = new HashSet<>();
        connections = new ArrayList<>();
    }

    public BasicBlock(int id) {
        labels = new HashSet<>();
        connections = new ArrayList<>();
        this.blockId = id;
    }

    public BasicBlock(Label l) {
        labels = new HashSet<>();
        connections = new ArrayList<>();
        this.label = l;
    }

    public BasicBlock(int id, Label l) {
        labels = new HashSet<>();
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
