import jdk.internal.org.objectweb.asm.Label;

import java.util.List;
import java.util.ArrayList;

public class BasicBlock {

    int blockId;
    Label label;
    List<BasicBlock> connections;
    boolean jumpedTo;

    public BasicBlock() {
        connections = new ArrayList<>();
    }

    public BasicBlock(Label l) {
        this.label = l;
        connections = new ArrayList<>();
    }

    public BasicBlock(int id, Label l) {
        this.blockId = id;
        this.label = l;
        connections = new ArrayList<>();
    }

    @Override
    public String toString() {
        return label == null ? "null" : label.toString();
        //return "BB"+blockId;
    }
}
