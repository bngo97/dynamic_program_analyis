import java.util.HashSet;
import java.util.Set;

public class MyVisitor extends Visitor {

    // We use sets because we do not care about value
    // We only care about declaration of variables
    // There are 3 types of scopes: global, class, and local/method
    // We use 3 sets to make sure there is no overlap in scope names
    Set<String> globalVars;
    Set<String> classVars;
    Set<String> localVars;

    public MyVisitor() {
        globalVars = new HashSet<>();
        classVars = new HashSet<>();
        localVars = new HashSet<>();
    }

    @Override
    public void visit(NodeProg node) {
        // Check methods defined in program
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
        // Set globally defined variables
        for(NodeConstDecl constDecl : node.constDecls) {
            if(!globalVars.add(constDecl.id)) {
                throw new RuntimeException("Variable name \"" + constDecl.id + "\" exists in global scope already!");
            }
        }
        for(NodeVarDecl var : node.varDecls) {
            if(!globalVars.add(var.id)) {
                throw new RuntimeException("Variable name \"" + var.id + "\" exists in global scope already!");
            }
        }
        // Assuming enum ids are global variables also
        for(NodeEnumDecl enumDecl : node.enumDecls) {
            for(String id : enumDecl.vals.keySet()) {
                if(!globalVars.add(id)) {
                    throw new RuntimeException("Variable name \"" + id + "\" exists in global scope already!");
                }
            }
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
        for(NodeVarDecl var : node.vars) {
            if(!classVars.add(var.id)) {
                throw new RuntimeException("Variable name \"" + var.id + "\" exists in class scope already!");
            }
        }
    }

    @Override
    public void visitEnd(NodeClassDecl node) {
        classVars = new HashSet<>();
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
        for(String id : node.ids) {
            if(!globalVars.contains(id) && !classVars.contains(id) && !localVars.contains(id)) {
                throw new RuntimeException("Variable \"" + id + "\" used but not declared");
            }
        }
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
        for(String id : node.formPars.keySet()) {
            if(!localVars.add(id)) {
                throw new RuntimeException("Variable name \"" + id + "\" exists in local scope already!");
            }
        }
        for(NodeVarDecl var : node.vars) {
            if (!localVars.add(var.id)) {
                throw new RuntimeException("Variable name \"" + var.id + "\" exists in local scope already!");
            }
        }
    }

    @Override
    public void visitEnd(NodeMethodDecl node) {
        localVars = new HashSet<>();
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
