import org.objectweb.asm.Label;

public class LabelInstruction extends Instruction {

    Label label;

    public LabelInstruction(Label l) {
        this.label = l;
    }

}
