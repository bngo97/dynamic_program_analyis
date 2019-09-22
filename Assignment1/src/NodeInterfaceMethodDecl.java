import java.util.Map;

public class NodeInterfaceMethodDecl extends NodeDecl {

    String returnType;
    Map<String, String> formPars;

    public NodeInterfaceMethodDecl(String id, String returnType, Map<String, String> formPars) {
        super(id);
        this.returnType = returnType;
        this.formPars = formPars;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
        v.visitEnd(this);
    }

    @Override
    public String toString() {
        return "";
    }
}
