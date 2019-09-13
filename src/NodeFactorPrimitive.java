public class NodeFactorPrimitive extends NodeFactor {

    String val;

    public NodeFactorPrimitive(String val) {
        this.val = val;
    }

    @Override
    public void accept(Visitor v) {

    }

    @Override
    public String toString() {
        return "primitive factor = " + val;
    }
}
