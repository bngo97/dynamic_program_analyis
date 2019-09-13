import java.util.HashSet;
import java.util.Set;

public class MyVisitor extends Visitor {

    Set<String> declaredVars;

    public MyVisitor() {
        declaredVars = new HashSet<>();
    }

    @Override
    public void visit(NodeProg node) {
        System.out.println("THIS IS BRENDAN");
        boolean isMainMethod = false;
        for(NodeMethodDecl method : node.methods) {
            if(method.id.equals("main") && method.returnType.equals("void") && method.formPars.size() == 0) {
                if(isMainMethod) {
                    throw new RuntimeException("Multiple main methods declared in program!");
                }
                isMainMethod = true;
            }
        }
        if(!isMainMethod) {
            throw new RuntimeException("No main method declared in program!");
        }
    }

    @Override
    public void visitEnd(NodeProg node) {

    }

    @Override
    public void visit(NodeActPars node) {

    }

    @Override
    public void visitEnd(NodeActPars node) {

    }

    @Override
    public void visit(NodeClassDecl node) {

    }

    @Override
    public void visitEnd(NodeClassDecl node) {

    }

    @Override
    public void visit(NodeCondFact node) {

    }

    @Override
    public void visitEnd(NodeCondFact node) {

    }

    @Override
    public void visit(NodeCondition node) {

    }

    @Override
    public void visitEnd(NodeCondition node) {

    }

    @Override
    public void visit(NodeCondTerm node) {

    }

    @Override
    public void visitEnd(NodeCondTerm node) {

    }

    @Override
    public void visit(NodeConstDecl node) {

    }

    @Override
    public void visitEnd(NodeConstDecl node) {

    }

    @Override
    public void visit(NodeDesignator node) {

    }

    @Override
    public void visitEnd(NodeDesignator node) {

    }

    @Override
    public void visit(NodeEnumDecl node) {

    }

    @Override
    public void visitEnd(NodeEnumDecl node) {

    }

    @Override
    public void visit(NodeExpr node) {

    }

    @Override
    public void visitEnd(NodeExpr node) {

    }

    @Override
    public void visit(NodeFactor node) {
        if(node instanceof NodeFactorDesignator) {
            this.visit((NodeFactorDesignator) node);
        } else if(node instanceof NodeFactorNew) {
            this.visit((NodeFactorNew) node);
        } else if(node instanceof NodeFactorPrimitive) {
            this.visit((NodeFactorPrimitive) node);
        }
    }

    @Override
    public void visitEnd(NodeFactor node) {
        if(node instanceof NodeFactorDesignator) {
            this.visitEnd((NodeFactorDesignator) node);
        } else if(node instanceof NodeFactorNew) {
            this.visitEnd((NodeFactorNew) node);
        } else if(node instanceof NodeFactorPrimitive) {
            this.visitEnd((NodeFactorPrimitive) node);
        }
    }

    @Override
    public void visit(NodeFactorDesignator node) {

    }

    @Override
    public void visitEnd(NodeFactorDesignator node) {

    }

    @Override
    public void visit(NodeFactorNew node) {

    }

    @Override
    public void visitEnd(NodeFactorNew node) {

    }

    @Override
    public void visit(NodeFactorPrimitive node) {

    }

    @Override
    public void visitEnd(NodeFactorPrimitive node) {

    }

    @Override
    public void visit(NodeMethodDecl node) {

    }

    @Override
    public void visitEnd(NodeMethodDecl node) {

    }

    @Override
    public void visit(NodeStatement node) {
        if(node instanceof NodeStatementDesignator) {
            this.visit((NodeStatementDesignator) node);
        } else if(node instanceof NodeStatementFor) {
            this.visit((NodeStatementFor) node);
        } else if(node instanceof NodeStatementIf) {
            this.visit((NodeStatementIf) node);
        } else if(node instanceof NodeStatementNested) {
            this.visit((NodeStatementNested) node);
        } else if(node instanceof NodeStatementNoOp) {
            this.visit((NodeStatementNoOp) node);
        } else if(node instanceof NodeStatementPrint) {
            this.visit((NodeStatementPrint) node);
        } else if(node instanceof NodeStatementRead) {
            this.visit((NodeStatementRead) node);
        } else if(node instanceof NodeStatementReturn) {
            this.visit((NodeStatementReturn) node);
        }
    }

    @Override
    public void visitEnd(NodeStatement node) {
        if(node instanceof NodeStatementDesignator) {
            this.visitEnd((NodeStatementDesignator) node);
        } else if(node instanceof NodeStatementFor) {
            this.visitEnd((NodeStatementFor) node);
        } else if(node instanceof NodeStatementIf) {
            this.visitEnd((NodeStatementIf) node);
        } else if(node instanceof NodeStatementNested) {
            this.visitEnd((NodeStatementNested) node);
        } else if(node instanceof NodeStatementNoOp) {
            this.visitEnd((NodeStatementNoOp) node);
        } else if(node instanceof NodeStatementPrint) {
            this.visitEnd((NodeStatementPrint) node);
        } else if(node instanceof NodeStatementRead) {
            this.visitEnd((NodeStatementRead) node);
        } else if(node instanceof NodeStatementReturn) {
            this.visitEnd((NodeStatementReturn) node);
        }
    }

    @Override
    public void visit(NodeStatementDesignator node) {

    }

    @Override
    public void visitEnd(NodeStatementDesignator node) {

    }

    @Override
    public void visit(NodeStatementFor node) {

    }

    @Override
    public void visitEnd(NodeStatementFor node) {

    }

    @Override
    public void visit(NodeStatementIf node) {

    }

    @Override
    public void visitEnd(NodeStatementIf node) {

    }

    @Override
    public void visit(NodeStatementNested node) {

    }

    @Override
    public void visitEnd(NodeStatementNested node) {

    }

    @Override
    public void visit(NodeStatementNoOp node) {

    }

    @Override
    public void visitEnd(NodeStatementNoOp node) {

    }

    @Override
    public void visit(NodeStatementPrint node) {

    }

    @Override
    public void visitEnd(NodeStatementPrint node) {

    }

    @Override
    public void visit(NodeStatementRead node) {

    }

    @Override
    public void visitEnd(NodeStatementRead node) {

    }

    @Override
    public void visit(NodeStatementReturn node) {

    }

    @Override
    public void visitEnd(NodeStatementReturn node) {

    }

    @Override
    public void visit(NodeTerm node) {

    }

    @Override
    public void visitEnd(NodeTerm node) {

    }

    @Override
    public void visit(NodeVarDecl node) {

    }

    @Override
    public void visitEnd(NodeVarDecl node) {

    }
}
