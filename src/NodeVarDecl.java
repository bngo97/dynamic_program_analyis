public class NodeVarDecl extends NodeDecl {

    String type;

    public NodeVarDecl(String type, String id) {
        super(id);
        this.type = type;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
        v.visitEnd(this);
    }

    @Override
    public String toString() {
        return type + " " + id;
    }

}
