import java.util.List;

public class NodeDesignator extends Node {

    String id;
    List<Object> nested;

    public NodeDesignator(String id, List<Object> n) {
        this.id = id;
        this.nested = n;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
        for(Object o : nested) {
            if(o instanceof NodeExpr) {
                ((NodeExpr) o).accept(v);
            }
        }
        v.visitEnd(this);
    }

    @Override
    public String toString() {
        return "id: " + id + " nested: " + nested;
    }

}
