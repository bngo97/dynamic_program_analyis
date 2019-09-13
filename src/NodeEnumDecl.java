import java.util.Map;

public class NodeEnumDecl extends NodeDecl {

    Map<String, String> vals;

    public NodeEnumDecl(String id, Map<String, String> vals) {
        super(id);
        this.vals = vals;
}

    @Override
    public void accept(Visitor v) {
        v.visit(this);
        v.visitEnd(this);
    }

    @Override
    public String toString() {
        String result = "enum " + id + "\nvalues=" + vals.toString();
        return result;
    }

}
