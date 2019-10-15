import org.objectweb.asm.Label;

import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;

public class BasicBlock {

    int blockId;
    Label label;
    List<Label> labels;
    Set<BasicBlock> connections;
    boolean jumpedTo;

    public BasicBlock(Label l) {
        blockId = -1;
        labels = new ArrayList<>();
        connections = new HashSet<>();
        this.label = l;
    }

    @Override
    public String toString() {
        return "BB"+blockId;
    }
}
