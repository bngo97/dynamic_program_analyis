import jdk.internal.org.objectweb.asm.Label;

import java.util.List;
import java.util.ArrayList;

public class LabelNode {

    Label label;
    List<Label> connections;
    LabelNode next;
    LabelNode prev;
    boolean jumpedTo;
    Label labelJumpedFrom;

    public LabelNode(Label label) {
        this.label = label;
        connections = new ArrayList<>();
    }

    @Override
    public String toString() {
        return label + ": " + connections;
    }

}
