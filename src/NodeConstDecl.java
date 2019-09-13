public class NodeConstDecl extends NodeDecl {

    String type;
    String val;

    public NodeConstDecl(String type, String id, String val) {
        super(id);
        this.type = type;
        this.val = val;
    }

    @Override
    public void accept(Visitor v) {

    }

    @Override
    public String toString() {
        return type + " " + id + " = " + val;
    }

}
