import java.util.*;

public class MyVisitor extends Visitor {

    // Maps from id to type for variables in different scopes
    Map<String, String> globalVars;
    Map<String, String> classVars;
    Map<String, String> localVars;

    Map<String, Map<String, String>> enumProperties;

    // Map from class to vars/methods declared in the class
    Map<String, Set<String>> classProperties;

    // Map from interface to method declarations
    // method stored as string concatenation of returnType + name + input types
    Map<String, Set<String>> interfaceProperties;

    // program methods names
    Set<String> programMethods;

    public MyVisitor() {
        globalVars = new HashMap<>();
        classVars = new HashMap<>();
        localVars = new HashMap<>();
        interfaceProperties = new HashMap<>();
        classProperties = new HashMap<>();
        programMethods = new HashSet<>();
        enumProperties = new HashMap<>();
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
            programMethods.add(method.id);
        }
        if(!isMainMethod) {
            throw new RuntimeException("No main method declared in program!");
        }
        for(NodeClassDecl classDecl : node.classDecls) {
            classProperties.put(classDecl.id, null);
        }
        // Set globally defined variables
        for(NodeConstDecl constDecl : node.constDecls) {
            if(globalVars.containsKey(constDecl.id)) {
                throw new RuntimeException("Variable name \"" + constDecl.id + "\" exists in global scope already!");
            }
            globalVars.put(constDecl.id, constDecl.type);
        }
        for(NodeVarDecl var : node.varDecls) {
            if(globalVars.containsKey(var.id)) {
                throw new RuntimeException("Variable name \"" + var.id + "\" exists in global scope already!");
            }
            if(!isPrimitive(var.type) && !classProperties.containsKey(var.type)) {
                throw new RuntimeException("Type " + var.type + " is undefined");
            }
            globalVars.put(var.id, var.type);
        }
        for(NodeEnumDecl enumDecl : node.enumDecls) {
            enumProperties.put(enumDecl.id, enumDecl.vals);
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
        Set<String> properties = new HashSet<>();

        for(NodeVarDecl var : node.vars) {
            if(classVars.containsKey(var.id)) {
                throw new RuntimeException("Variable name \"" + var.id + "\" exists in class scope already!");
            }
            if(!isPrimitive(var.type) && !classProperties.containsKey(var.type)) {
                throw new RuntimeException("Type " + var.type + " is undefined");
            }
            classVars.put(var.id, var.type);
            properties.add(var.id);
        }
        Set<String> methods = new HashSet<>();
        for(NodeMethodDecl method : node.methods) {
            List<String> inputTypes = new ArrayList<String>(method.formPars.values());
            Collections.sort(inputTypes);
            String s = method.returnType + method.id + inputTypes.toString();
            methods.add(s);
            properties.add(method.id);
        }

        if(node.parent != null && !classProperties.containsKey(node.parent)) {
            throw new RuntimeException("Class " + node.id + " extends undeclared class!");
        }
        for(String interf : node.impls) {
            if(!interfaceProperties.containsKey(interf)) {
                throw new RuntimeException("Class " + node.id + " implements undeclared class!");
            }
            for(String method : interfaceProperties.get(interf)) {
                if(!methods.contains(method)) {
                    throw new RuntimeException("Class " + node.id + " does not implement " + interf + " properly");
                }
            }
        }

        classProperties.put(node.id, properties);
    }

    @Override
    public void visitEnd(NodeClassDecl node) {
        classVars = new HashMap<>();
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
        String id = node.id;
        if(node.nested.size() == 0) {
            if(!programMethods.contains(id) &&
                    !globalVars.containsKey(node.id) &&
                    !classVars.containsKey(node.id) &&
                    !localVars.containsKey(node.id)) {
                throw new RuntimeException("Variable \"" + id + "\" used but not declared");
            }
        } else {
            Object nestedId = node.nested.get(0);
            if(!(nestedId instanceof String)) {
                return;
            }
            String nextId = (String) nestedId;
            if(id.equals("this")) {
                if(!classVars.containsKey(nextId)) {
                    throw new RuntimeException("Variable \"" + nextId + "\" used but not declared");
                }
            } else if(enumProperties.containsKey(id)) {
                if(!enumProperties.get(id).containsKey(nextId)) {
                    throw new RuntimeException("key " + nextId + " not defined in enum " + id);
                }
            } else if(localVars.containsKey(id)) {
                String type = localVars.get(id);
                if(!classProperties.get(type).contains(nextId)) {
                    throw new RuntimeException("Class " + type + " does not contain property " + nextId);
                }
            } else if(classVars.containsKey(id)) {
                String type = classVars.get(id);
                if(!classProperties.get(type).contains(nextId)) {
                    throw new RuntimeException("Class " + type + " does not contain property " + nextId);
                }
            } else if(globalVars.containsKey(id)) {
                String type = globalVars.get(id);
                if(!classProperties.get(type).contains(nextId)) {
                    throw new RuntimeException("Class " + type + " does not contain property " + nextId);
                }
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
        for(Map.Entry<String,String> entry : node.formPars.entrySet()) {
            if(localVars.containsKey(entry.getKey())) {
                throw new RuntimeException("Variable name \"" + entry.getKey() + "\" exists in local scope already!");
            }
            localVars.put(entry.getKey(), entry.getValue());
        }
        for(NodeVarDecl var : node.vars) {
            if(localVars.containsKey(var.id)) {
                throw new RuntimeException("Variable name \"" + var.id + "\" exists in local scope already!");
            }
            if(!isPrimitive(var.type) && !classProperties.containsKey(var.type)) {
                throw new RuntimeException("Type " + var.type + " is undefined");
            }
            localVars.put(var.id, var.type);
        }
    }

    @Override
    public void visitEnd(NodeMethodDecl node) {
        localVars = new HashMap<>();
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

    @Override
    public void visit(NodeInterfaceDecl node) {
        Set<String> methods = new HashSet<String>();
        for(NodeInterfaceMethodDecl method : node.methods) {
            List<String> inputTypes = new ArrayList<String>(method.formPars.values());
            Collections.sort(inputTypes);
            String s = method.returnType + method.id + inputTypes.toString();
            methods.add(s);
        }
        interfaceProperties.put(node.id, methods);
    }

    @Override
    public void visitEnd(NodeInterfaceDecl node) {

    }

    @Override
    public void visit(NodeInterfaceMethodDecl node) {

    }

    @Override
    public void visitEnd(NodeInterfaceMethodDecl node) {

    }

    private boolean isPrimitive(String type) {
        return type.equals("int") | type.equals("bool") | type.equals("char") | type.equals("void");
    }

}
