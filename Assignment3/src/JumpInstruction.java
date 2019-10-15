import jdk.internal.org.objectweb.asm.Label;

public class JumpInstruction extends Instruction {

    int opcode;
    Label label;

    public JumpInstruction(int i, Label l) {
        this.opcode = i;
        this.label = l;
    }
}
