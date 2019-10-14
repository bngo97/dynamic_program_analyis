import jdk.internal.org.objectweb.asm.Label;

import java.util.List;
import java.util.ArrayList;

public class MyLabelNode {

    Label label;
    List<Label> connections;
    MyLabelNode next;
    MyLabelNode prev;
    boolean jumpedTo;
    Label labelJumpedFrom;
    boolean jumpedFrom;

    public MyLabelNode(Label label) {
        this.label = label;
        connections = new ArrayList<>();
    }

    @Override
    public String toString() {
        return label + ": " + connections;
    }

}
