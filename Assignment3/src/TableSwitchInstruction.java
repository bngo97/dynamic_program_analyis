import jdk.internal.org.objectweb.asm.Label;

import java.util.Arrays;
import java.util.List;

public class TableSwitchInstruction extends Instruction {
    int i; int i1;
    Label label;
    List<Label> labels;

    public TableSwitchInstruction(int i, int i1, Label l, Label... ls) {
        this.i = i;
        this.i1 = i1;
        this.label = l;
        this.labels = Arrays.asList(ls);
    }
}
