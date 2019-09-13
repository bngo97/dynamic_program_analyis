abstract class Visitor {

    public abstract void visit(NodeProg node);

    public abstract void visitEnd(NodeProg node);

    public abstract void visit(NodeActPars node);

    public abstract void visitEnd(NodeActPars node);

    public abstract void visit(NodeClassDecl node);

    public abstract void visitEnd(NodeClassDecl node);

    public abstract void visit(NodeCondFact node);

    public abstract void visitEnd(NodeCondFact node);

    public abstract void visit(NodeCondition node);

    public abstract void visitEnd(NodeCondition node);

    public abstract void visit(NodeCondTerm node);

    public abstract void visitEnd(NodeCondTerm node);

    public abstract void visit(NodeConstDecl node);

    public abstract void visitEnd(NodeConstDecl node);

    public abstract void visit(NodeDesignator node);

    public abstract void visitEnd(NodeDesignator node);

    public abstract void visit(NodeEnumDecl node);

    public abstract void visitEnd(NodeEnumDecl node);

    public abstract void visit(NodeExpr node);

    public abstract void visitEnd(NodeExpr node);

    public abstract void visit(NodeFactor node);

    public abstract void visitEnd(NodeFactor node);

    public abstract void visit(NodeFactorDesignator node);

    public abstract void visitEnd(NodeFactorDesignator node);

    public abstract void visit(NodeFactorNew node);

    public abstract void visitEnd(NodeFactorNew node);

    public abstract void visit(NodeFactorPrimitive node);

    public abstract void visitEnd(NodeFactorPrimitive node);

    public abstract void visit(NodeMethodDecl node);

    public abstract void visitEnd(NodeMethodDecl node);

    public abstract void visit(NodeStatement node);

    public abstract void visitEnd(NodeStatement node);

    public abstract void visit(NodeStatementDesignator node);

    public abstract void visitEnd(NodeStatementDesignator node);

    public abstract void visit(NodeStatementFor node);

    public abstract void visitEnd(NodeStatementFor node);

    public abstract void visit(NodeStatementIf node);

    public abstract void visitEnd(NodeStatementIf node);

    public abstract void visit(NodeStatementNested node);

    public abstract void visitEnd(NodeStatementNested node);

    public abstract void visit(NodeStatementNoOp node);

    public abstract void visitEnd(NodeStatementNoOp node);

    public abstract void visit(NodeStatementPrint node);

    public abstract void visitEnd(NodeStatementPrint node);

    public abstract void visit(NodeStatementRead node);

    public abstract void visitEnd(NodeStatementRead node);

    public abstract void visit(NodeStatementReturn node);

    public abstract void visitEnd(NodeStatementReturn node);

    public abstract void visit(NodeTerm node);

    public abstract void visitEnd(NodeTerm node);

    public abstract void visit(NodeVarDecl node);

    public abstract void visitEnd(NodeVarDecl node);

    public abstract void visit(NodeInterfaceDecl node);

    public abstract void visitEnd(NodeInterfaceDecl node);

    public abstract void visit(NodeInterfaceMethodDecl node);

    public abstract void visitEnd(NodeInterfaceMethodDecl node);

}
