/* MyParser.java */
/* Generated By:JavaCC: Do not edit this line. MyParser.java */
import java.io.StringReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class MyParser implements MyParserConstants {
    public static void main(String[] args) throws FileNotFoundException {
        if(args.length != 2) {
            throw new RuntimeException("Incorrect command arguments");
        }
        MyParser parser;
        if(args[0].equals("-t")) {
            parser = new MyParser(new StringReader(args[1]));
        } else if(args[0].equals("-f")) {
            File file = new File(args[1]);
            parser = new MyParser(new FileInputStream(file));
        } else {
            throw new RuntimeException("Incorrect input flag");
        }
        try {
            NodeProg p = parser.Prog();
            Visitor v = new MyVisitor();
            p.accept(v);
//            for(NodeConstDecl n : p.constDecls) {
//                System.out.println(n);
//            }
//            for(NodeEnumDecl n : p.enumDecls) {
//                System.out.println(n);
//            }
//            for(NodeVarDecl n : p.varDecls) {
//                System.out.println(n);
//            }
//            for(NodeClassDecl n : p.classDecls) {
//                System.out.println(n);
//            }
            System.out.println("Success!");
        } catch(Exception e) {
            System.out.println("Syntax check failed:");
            System.out.println(e.getMessage());
        }
    }

  static final public NodeProg Prog() throws ParseException {Token id;
    List<NodeConstDecl> constDecls = new ArrayList<NodeConstDecl>();
    List<NodeConstDecl> constDecl = null;
    List<NodeEnumDecl> enumDecls = new ArrayList<NodeEnumDecl>();
    NodeEnumDecl enumDecl = null;
    List<NodeVarDecl> varDecls = new ArrayList<NodeVarDecl>();
    List<NodeVarDecl> varDecl = null;
    List<NodeClassDecl> classDecls = new ArrayList<NodeClassDecl>();
    NodeClassDecl classDecl = null;
    List<NodeMethodDecl> methods = new ArrayList<NodeMethodDecl>();
    NodeMethodDecl method = null;
    jj_consume_token(PROG);
    id = jj_consume_token(IDENT);
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case CONST:
      case ENUM:
      case CLASS:
      case INTERFACE:
      case IDENT:{
        ;
        break;
        }
      default:
        jj_la1[0] = jj_gen;
        break label_1;
      }
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case CONST:{
        constDecl = ConstDecl();
constDecls.addAll(constDecl);
        break;
        }
      case ENUM:{
        enumDecl = EnumDecl();
enumDecls.add(enumDecl);
        break;
        }
      case IDENT:{
        varDecl = VarDecl();
varDecls.addAll(varDecl);
        break;
        }
      case CLASS:{
        classDecl = ClassDecl();
classDecls.add(classDecl);
        break;
        }
      case INTERFACE:{
        InterfaceDecl();
        break;
        }
      default:
        jj_la1[1] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
    jj_consume_token(43);
    label_2:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case VOID:
      case IDENT:{
        ;
        break;
        }
      default:
        jj_la1[2] = jj_gen;
        break label_2;
      }
      method = MethodDecl();
methods.add(method);
    }
    jj_consume_token(44);
    jj_consume_token(0);
{if ("" != null) return new NodeProg(id.image, constDecls, enumDecls, varDecls, classDecls, methods);}
    throw new Error("Missing return statement in function");
}

  static final public List<NodeConstDecl> ConstDecl() throws ParseException {List<NodeConstDecl> decls = new ArrayList<NodeConstDecl>();
    Token n, v;
    String t;
    jj_consume_token(CONST);
    t = Type();
    n = jj_consume_token(IDENT);
    jj_consume_token(EQ);
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case NUM:{
      v = jj_consume_token(NUM);
      break;
      }
    case BOOL:{
      v = jj_consume_token(BOOL);
      break;
      }
    case CHAR:{
      v = jj_consume_token(CHAR);
      break;
      }
    default:
      jj_la1[3] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
decls.add(new NodeConstDecl(t, n.image, v.image));
    label_3:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case 45:{
        ;
        break;
        }
      default:
        jj_la1[4] = jj_gen;
        break label_3;
      }
      jj_consume_token(45);
      n = jj_consume_token(IDENT);
      jj_consume_token(EQ);
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case NUM:{
        v = jj_consume_token(NUM);
        break;
        }
      case BOOL:{
        v = jj_consume_token(BOOL);
        break;
        }
      case CHAR:{
        v = jj_consume_token(CHAR);
        break;
        }
      default:
        jj_la1[5] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
decls.add(new NodeConstDecl(t, n.image, v.image));
    }
    jj_consume_token(46);
{if ("" != null) return decls;}
    throw new Error("Missing return statement in function");
}

  static final public NodeEnumDecl EnumDecl() throws ParseException {Token id;
    Token key = null, val = null;
    Map<String, String> vals = new HashMap<String, String>();
    jj_consume_token(ENUM);
    id = jj_consume_token(IDENT);
    jj_consume_token(43);
    key = jj_consume_token(IDENT);
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case EQ:{
      jj_consume_token(EQ);
      val = jj_consume_token(NUM);
      break;
      }
    default:
      jj_la1[6] = jj_gen;
      ;
    }
vals.put(key.image, val == null ? null : val.image); val = null;
    label_4:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case 45:{
        ;
        break;
        }
      default:
        jj_la1[7] = jj_gen;
        break label_4;
      }
      jj_consume_token(45);
      key = jj_consume_token(IDENT);
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case EQ:{
        jj_consume_token(EQ);
        val = jj_consume_token(NUM);
        break;
        }
      default:
        jj_la1[8] = jj_gen;
        ;
      }
vals.put(key.image, val == null ? null : val.image); val = null;
    }
    jj_consume_token(44);
{if ("" != null) return new NodeEnumDecl(id.image, vals);}
    throw new Error("Missing return statement in function");
}

  static final public List<NodeVarDecl> VarDecl() throws ParseException {List<NodeVarDecl> decls = new ArrayList<NodeVarDecl>();
    String t;
    Token id;
    t = Type();
    id = jj_consume_token(IDENT);
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case 47:{
      jj_consume_token(47);
      jj_consume_token(48);
      break;
      }
    default:
      jj_la1[9] = jj_gen;
      ;
    }
decls.add(new NodeVarDecl(t, id.image));
    label_5:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case 45:{
        ;
        break;
        }
      default:
        jj_la1[10] = jj_gen;
        break label_5;
      }
      jj_consume_token(45);
      id = jj_consume_token(IDENT);
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case 47:{
        jj_consume_token(47);
        jj_consume_token(48);
        break;
        }
      default:
        jj_la1[11] = jj_gen;
        ;
      }
decls.add(new NodeVarDecl(t, id.image));
    }
    jj_consume_token(46);
{if ("" != null) return decls;}
    throw new Error("Missing return statement in function");
}

  static final public void InterfaceDecl() throws ParseException {
    jj_consume_token(INTERFACE);
    jj_consume_token(IDENT);
    jj_consume_token(43);
    label_6:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case VOID:
      case IDENT:{
        ;
        break;
        }
      default:
        jj_la1[12] = jj_gen;
        break label_6;
      }
      InterfaceMethodDecl();
    }
    jj_consume_token(44);
}

  static final public void InterfaceMethodDecl() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case IDENT:{
      Type();
      break;
      }
    case VOID:{
      jj_consume_token(VOID);
      break;
      }
    default:
      jj_la1[13] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    jj_consume_token(IDENT);
    jj_consume_token(49);
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case IDENT:{
      FormPars();
      break;
      }
    default:
      jj_la1[14] = jj_gen;
      ;
    }
    jj_consume_token(50);
    jj_consume_token(46);
}

  static final public Map<String,String> FormPars() throws ParseException {Map<String,String> formPars = new HashMap<String,String>();
    String type;
    Token id;
    type = Type();
    id = jj_consume_token(IDENT);
formPars.put(id.image, type);
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case 47:{
      jj_consume_token(47);
      jj_consume_token(48);
      break;
      }
    default:
      jj_la1[15] = jj_gen;
      ;
    }
    label_7:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case 45:{
        ;
        break;
        }
      default:
        jj_la1[16] = jj_gen;
        break label_7;
      }
      jj_consume_token(45);
      type = Type();
      id = jj_consume_token(IDENT);
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case 47:{
        jj_consume_token(47);
        jj_consume_token(48);
        break;
        }
      default:
        jj_la1[17] = jj_gen;
        ;
      }
formPars.put(id.image, type);
    }
{if ("" != null) return formPars;}
    throw new Error("Missing return statement in function");
}

  static final public NodeClassDecl ClassDecl() throws ParseException {Token id;
    String parent = null;
    String impl = null;
    List<String> impls = new ArrayList<String>();
    List<NodeVarDecl> vars = new ArrayList<NodeVarDecl>();
    List<NodeVarDecl> var = null;
    List<NodeMethodDecl> methods = new ArrayList<NodeMethodDecl>();
    NodeMethodDecl method = null;
    jj_consume_token(CLASS);
    id = jj_consume_token(IDENT);
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case EXTENDS:{
      jj_consume_token(EXTENDS);
      parent = Type();
      break;
      }
    default:
      jj_la1[18] = jj_gen;
      ;
    }
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case IMPLEMENTS:{
      jj_consume_token(IMPLEMENTS);
      impl = Type();
impls.add(impl);
      label_8:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case 45:{
          ;
          break;
          }
        default:
          jj_la1[19] = jj_gen;
          break label_8;
        }
        jj_consume_token(45);
        impl = Type();
impls.add(impl);
      }
      break;
      }
    default:
      jj_la1[20] = jj_gen;
      ;
    }
    jj_consume_token(43);
    label_9:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case IDENT:{
        ;
        break;
        }
      default:
        jj_la1[21] = jj_gen;
        break label_9;
      }
      var = VarDecl();
vars.addAll(var);
    }
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case 43:{
      jj_consume_token(43);
      label_10:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case VOID:
        case IDENT:{
          ;
          break;
          }
        default:
          jj_la1[22] = jj_gen;
          break label_10;
        }
        method = MethodDecl();
methods.add(method);
      }
      jj_consume_token(44);
      break;
      }
    default:
      jj_la1[23] = jj_gen;
      ;
    }
    jj_consume_token(44);
{if ("" != null) return new NodeClassDecl(id.image, parent, impls, vars, methods);}
    throw new Error("Missing return statement in function");
}

  static final public NodeMethodDecl MethodDecl() throws ParseException {String returnType = null;
    Token id;
    Map<String,String> formPars = new HashMap<String, String>();
    List<NodeVarDecl> vars = new ArrayList<NodeVarDecl>();
    List<NodeVarDecl> var = null;
    List<NodeStatement> statements = new ArrayList<NodeStatement>();
    NodeStatement statement;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case IDENT:{
      returnType = Type();
      break;
      }
    case VOID:{
      jj_consume_token(VOID);
      break;
      }
    default:
      jj_la1[24] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
if(returnType == null) returnType = "void";
    id = jj_consume_token(IDENT);
    jj_consume_token(49);
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case IDENT:{
      formPars = FormPars();
      break;
      }
    default:
      jj_la1[25] = jj_gen;
      ;
    }
    jj_consume_token(50);
    label_11:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case IDENT:{
        ;
        break;
        }
      default:
        jj_la1[26] = jj_gen;
        break label_11;
      }
      var = VarDecl();
vars.addAll(var);
    }
    jj_consume_token(43);
    label_12:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case IF:
      case FOR:
      case BREAK:
      case CONTINUE:
      case RETURN:
      case READ:
      case PRINT:
      case IDENT:
      case 43:{
        ;
        break;
        }
      default:
        jj_la1[27] = jj_gen;
        break label_12;
      }
      statement = Statement();
statements.add(statement);
    }
    jj_consume_token(44);
{if ("" != null) return new NodeMethodDecl(id.image, returnType, formPars, vars, statements);}
    throw new Error("Missing return statement in function");
}

  static final public String Type() throws ParseException {Token id;
    id = jj_consume_token(IDENT);
{if ("" != null) return id.image;}
    throw new Error("Missing return statement in function");
}

  static final public NodeStatement Statement() throws ParseException {NodeStatement statement1 = null;
    NodeStatement statement2 = null;
    NodeStatement statement3 = null;
    NodeCondition condition = null;
    NodeExpr expression = null;
    NodeDesignator designator = null;
    Token t = null;
    List<NodeStatement> statements = new ArrayList<NodeStatement>();
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case IDENT:{
      statement1 = DesignatorStatement();
      jj_consume_token(46);
{if ("" != null) return statement1;}
      break;
      }
    case IF:{
      jj_consume_token(IF);
      jj_consume_token(49);
      condition = Condition();
      jj_consume_token(50);
      statement1 = Statement();
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case ELSE:{
        jj_consume_token(ELSE);
        statement2 = Statement();
        break;
        }
      default:
        jj_la1[28] = jj_gen;
        ;
      }
{if ("" != null) return new NodeStatementIf(condition, statement1, statement2);}
      break;
      }
    case FOR:{
      jj_consume_token(FOR);
      jj_consume_token(49);
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case IDENT:{
        statement1 = DesignatorStatement();
        break;
        }
      default:
        jj_la1[29] = jj_gen;
        ;
      }
      jj_consume_token(46);
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case NEW:
      case MINUS:
      case NUM:
      case BOOL:
      case CHAR:
      case IDENT:{
        condition = Condition();
        break;
        }
      default:
        jj_la1[30] = jj_gen;
        ;
      }
      jj_consume_token(46);
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case IDENT:{
        statement2 = DesignatorStatement();
        break;
        }
      default:
        jj_la1[31] = jj_gen;
        ;
      }
      jj_consume_token(50);
      statement3 = Statement();
{if ("" != null) return new NodeStatementFor(statement1, condition, statement2, statement3);}
      break;
      }
    case BREAK:{
      jj_consume_token(BREAK);
      jj_consume_token(46);
{if ("" != null) return new NodeStatementNoOp();}
      break;
      }
    case CONTINUE:{
      jj_consume_token(CONTINUE);
      jj_consume_token(46);
{if ("" != null) return new NodeStatementNoOp();}
      break;
      }
    case RETURN:{
      jj_consume_token(RETURN);
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case NEW:
      case MINUS:
      case NUM:
      case BOOL:
      case CHAR:
      case IDENT:{
        expression = Expr();
        break;
        }
      default:
        jj_la1[32] = jj_gen;
        ;
      }
      jj_consume_token(46);
{if ("" != null) return new NodeStatementReturn(expression);}
      break;
      }
    case READ:{
      jj_consume_token(READ);
      jj_consume_token(49);
      designator = Designator();
      jj_consume_token(50);
      jj_consume_token(46);
{if ("" != null) return new NodeStatementRead(designator);}
      break;
      }
    case PRINT:{
      jj_consume_token(PRINT);
      jj_consume_token(49);
      expression = Expr();
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case 45:{
        jj_consume_token(45);
        t = jj_consume_token(NUM);
        break;
        }
      default:
        jj_la1[33] = jj_gen;
        ;
      }
      jj_consume_token(50);
      jj_consume_token(46);
{if ("" != null) return new NodeStatementPrint(expression, (t == null ? null : t.image));}
      break;
      }
    case 43:{
      jj_consume_token(43);
      label_13:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case IF:
        case FOR:
        case BREAK:
        case CONTINUE:
        case RETURN:
        case READ:
        case PRINT:
        case IDENT:
        case 43:{
          ;
          break;
          }
        default:
          jj_la1[34] = jj_gen;
          break label_13;
        }
        statement1 = Statement();
statements.add(statement1);
      }
      jj_consume_token(44);
{if ("" != null) return new NodeStatementNested(statements);}
{if ("" != null) return statement1;}
      break;
      }
    default:
      jj_la1[35] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
}

  static final public NodeStatementDesignator DesignatorStatement() throws ParseException {NodeDesignator designator;
    NodeExpr expr = null;
    NodeActPars actPars = null;
    designator = Designator();
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case EQ:{
      Assignop();
      expr = Expr();
      break;
      }
    case 49:{
      jj_consume_token(49);
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case NEW:
      case MINUS:
      case NUM:
      case BOOL:
      case CHAR:
      case IDENT:{
        actPars = ActPars();
        break;
        }
      default:
        jj_la1[36] = jj_gen;
        ;
      }
      jj_consume_token(50);
      break;
      }
    case PLUSx2:{
      jj_consume_token(PLUSx2);
      break;
      }
    case MINUSx2:{
      jj_consume_token(MINUSx2);
      break;
      }
    default:
      jj_la1[37] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
{if ("" != null) return new NodeStatementDesignator(designator, expr, actPars);}
    throw new Error("Missing return statement in function");
}

  static final public NodeDesignator Designator() throws ParseException {Token id;
    List<String> ids = new ArrayList<String>();
    List<NodeExpr> exprs = new ArrayList<NodeExpr>();
    NodeExpr expr = null;
    id = jj_consume_token(IDENT);
ids.add(id.image);
    label_14:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case 47:
      case 51:{
        ;
        break;
        }
      default:
        jj_la1[38] = jj_gen;
        break label_14;
      }
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case 51:{
        jj_consume_token(51);
        id = jj_consume_token(IDENT);
ids.add(id.image);
        break;
        }
      case 47:{
        jj_consume_token(47);
        expr = Expr();
exprs.add(expr);
        jj_consume_token(48);
        break;
        }
      default:
        jj_la1[39] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
{if ("" != null) return new NodeDesignator(ids, exprs);}
    throw new Error("Missing return statement in function");
}

  static final public NodeCondition Condition() throws ParseException {List<NodeCondTerm> terms = new ArrayList<NodeCondTerm>();
    NodeCondTerm term = null;
    term = CondTerm();
terms.add(term);
    label_15:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case OR:{
        ;
        break;
        }
      default:
        jj_la1[40] = jj_gen;
        break label_15;
      }
      jj_consume_token(OR);
      term = CondTerm();
terms.add(term);
    }
{if ("" != null) return new NodeCondition(terms);}
    throw new Error("Missing return statement in function");
}

  static final public NodeCondTerm CondTerm() throws ParseException {List<NodeCondFact> facts = new ArrayList<NodeCondFact>();
    NodeCondFact fact = null;
    fact = CondFact();
facts.add(fact);
    label_16:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case AND:{
        ;
        break;
        }
      default:
        jj_la1[41] = jj_gen;
        break label_16;
      }
      jj_consume_token(AND);
      fact = CondFact();
facts.add(fact);
    }
{if ("" != null) return new NodeCondTerm(facts);}
    throw new Error("Missing return statement in function");
}

  static final public NodeCondFact CondFact() throws ParseException {NodeExpr expr1 = null;
    NodeExpr expr2 = null;
    expr1 = Expr();
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case EQEQ:
    case NOTEQ:
    case GR:
    case GRE:
    case LE:
    case LEQ:{
      Relop();
      expr2 = Expr();
      break;
      }
    default:
      jj_la1[42] = jj_gen;
      ;
    }
{if ("" != null) return new NodeCondFact(expr1, expr2);}
    throw new Error("Missing return statement in function");
}

  static final public NodeExpr Expr() throws ParseException {List<NodeTerm> terms = new ArrayList<NodeTerm>();
    NodeTerm term = null;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case MINUS:{
      jj_consume_token(MINUS);
      break;
      }
    default:
      jj_la1[43] = jj_gen;
      ;
    }
    term = Term();
terms.add(term);
    label_17:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case PLUS:
      case MINUS:{
        ;
        break;
        }
      default:
        jj_la1[44] = jj_gen;
        break label_17;
      }
      Addop();
      term = Term();
terms.add(term);
    }
{if ("" != null) return new NodeExpr(terms);}
    throw new Error("Missing return statement in function");
}

  static final public NodeTerm Term() throws ParseException {List<NodeFactor> factors = new ArrayList<NodeFactor>();
    NodeFactor factor = null;
    factor = Factor();
factors.add(factor);
    label_18:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case TIMES:
      case DIV:
      case MOD:{
        ;
        break;
        }
      default:
        jj_la1[45] = jj_gen;
        break label_18;
      }
      Mulop();
      factor = Factor();
factors.add(factor);
    }
{if ("" != null) return new NodeTerm(factors);}
    throw new Error("Missing return statement in function");
}

  static final public NodeFactor Factor() throws ParseException {NodeFactor factor = null;
    NodeDesignator designator = null;
    NodeActPars actPars = null;
    Token t = null;
    String type;
    NodeExpr expr = null;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case IDENT:{
      designator = Designator();
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case 49:{
        jj_consume_token(49);
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case NEW:
        case MINUS:
        case NUM:
        case BOOL:
        case CHAR:
        case IDENT:{
          actPars = ActPars();
          break;
          }
        default:
          jj_la1[46] = jj_gen;
          ;
        }
        jj_consume_token(50);
        break;
        }
      default:
        jj_la1[47] = jj_gen;
        ;
      }
factor = new NodeFactorDesignator(designator, actPars);
      break;
      }
    case NUM:{
      t = jj_consume_token(NUM);
factor = new NodeFactorPrimitive(t.image);
      break;
      }
    case CHAR:{
      t = jj_consume_token(CHAR);
factor = new NodeFactorPrimitive(t.image);
      break;
      }
    case BOOL:{
      t = jj_consume_token(BOOL);
factor = new NodeFactorPrimitive(t.image);
      break;
      }
    case NEW:{
      jj_consume_token(NEW);
      type = Type();
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case 47:{
        jj_consume_token(47);
        expr = Expr();
        jj_consume_token(48);
        break;
        }
      default:
        jj_la1[48] = jj_gen;
        ;
      }
factor = new NodeFactorNew(type, expr);
      break;
      }
    default:
      jj_la1[49] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
{if ("" != null) return factor;}
    throw new Error("Missing return statement in function");
}

  static final public NodeActPars ActPars() throws ParseException {List<NodeExpr> exprs = new ArrayList<NodeExpr>();
    NodeExpr expr = null;
    expr = Expr();
exprs.add(expr);
    label_19:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case 45:{
        ;
        break;
        }
      default:
        jj_la1[50] = jj_gen;
        break label_19;
      }
      jj_consume_token(45);
      expr = Expr();
exprs.add(expr);
    }
{if ("" != null) return new NodeActPars(exprs);}
    throw new Error("Missing return statement in function");
}

  static final public String Assignop() throws ParseException {Token t;
    t = jj_consume_token(EQ);
{if ("" != null) return t.image;}
    throw new Error("Missing return statement in function");
}

  static final public String Relop() throws ParseException {Token t;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case EQEQ:{
      t = jj_consume_token(EQEQ);
      break;
      }
    case NOTEQ:{
      t = jj_consume_token(NOTEQ);
      break;
      }
    case GR:{
      t = jj_consume_token(GR);
      break;
      }
    case GRE:{
      t = jj_consume_token(GRE);
      break;
      }
    case LE:{
      t = jj_consume_token(LE);
      break;
      }
    case LEQ:{
      t = jj_consume_token(LEQ);
      break;
      }
    default:
      jj_la1[51] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
{if ("" != null) return t.image;}
    throw new Error("Missing return statement in function");
}

  static final public String Addop() throws ParseException {Token t;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case PLUS:{
      t = jj_consume_token(PLUS);
      break;
      }
    case MINUS:{
      t = jj_consume_token(MINUS);
      break;
      }
    default:
      jj_la1[52] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
{if ("" != null) return t.image;}
    throw new Error("Missing return statement in function");
}

  static final public String Mulop() throws ParseException {Token t;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case TIMES:{
      t = jj_consume_token(TIMES);
      break;
      }
    case DIV:{
      t = jj_consume_token(DIV);
      break;
      }
    case MOD:{
      t = jj_consume_token(MOD);
      break;
      }
    default:
      jj_la1[53] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
{if ("" != null) return t.image;}
    throw new Error("Missing return statement in function");
}

  static private boolean jj_initialized_once = false;
  /** Generated Token Manager. */
  static public MyParserTokenManager token_source;
  static SimpleCharStream jj_input_stream;
  /** Current token. */
  static public Token token;
  /** Next token. */
  static public Token jj_nt;
  static private int jj_ntk;
  static private int jj_gen;
  static final private int[] jj_la1 = new int[54];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static {
	   jj_la1_init_0();
	   jj_la1_init_1();
	}
	private static void jj_la1_init_0() {
	   jj_la1_0 = new int[] {0x780,0x780,0x2000,0x0,0x0,0x0,0x10000000,0x0,0x10000000,0x0,0x0,0x0,0x2000,0x2000,0x0,0x0,0x0,0x0,0x800,0x0,0x1000,0x0,0x2000,0x0,0x2000,0x0,0x0,0x7e8000,0x10000,0x0,0x1004000,0x0,0x1004000,0x0,0x7e8000,0x7e8000,0x1004000,0x10000000,0x0,0x0,0x0,0x0,0xe0000000,0x1000000,0x1800000,0xe000000,0x1004000,0x0,0x0,0x4000,0x0,0xe0000000,0x1800000,0xe000000,};
	}
	private static void jj_la1_init_1() {
	   jj_la1_1 = new int[] {0x400,0x400,0x400,0x380,0x2000,0x380,0x0,0x2000,0x0,0x8000,0x2000,0x8000,0x400,0x400,0x400,0x8000,0x2000,0x8000,0x0,0x2000,0x0,0x400,0x400,0x800,0x400,0x400,0x400,0xc00,0x0,0x400,0x780,0x400,0x780,0x2000,0xc00,0xc00,0x780,0x20060,0x88000,0x88000,0x10,0x8,0x7,0x0,0x0,0x0,0x780,0x20000,0x8000,0x780,0x2000,0x7,0x0,0x0,};
	}

  /** Constructor with InputStream. */
  public MyParser(java.io.InputStream stream) {
	  this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public MyParser(java.io.InputStream stream, String encoding) {
	 if (jj_initialized_once) {
	   System.out.println("ERROR: Second call to constructor of static parser.  ");
	   System.out.println("	   You must either use ReInit() or set the JavaCC option STATIC to false");
	   System.out.println("	   during parser generation.");
	   throw new Error();
	 }
	 jj_initialized_once = true;
	 try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
	 token_source = new MyParserTokenManager(jj_input_stream);
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 54; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream) {
	  ReInit(stream, null);
  }
  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream, String encoding) {
	 try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
	 token_source.ReInit(jj_input_stream);
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 54; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public MyParser(java.io.Reader stream) {
	 if (jj_initialized_once) {
	   System.out.println("ERROR: Second call to constructor of static parser. ");
	   System.out.println("	   You must either use ReInit() or set the JavaCC option STATIC to false");
	   System.out.println("	   during parser generation.");
	   throw new Error();
	 }
	 jj_initialized_once = true;
	 jj_input_stream = new SimpleCharStream(stream, 1, 1);
	 token_source = new MyParserTokenManager(jj_input_stream);
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 54; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  static public void ReInit(java.io.Reader stream) {
	if (jj_input_stream == null) {
	   jj_input_stream = new SimpleCharStream(stream, 1, 1);
	} else {
	   jj_input_stream.ReInit(stream, 1, 1);
	}
	if (token_source == null) {
 token_source = new MyParserTokenManager(jj_input_stream);
	}

	 token_source.ReInit(jj_input_stream);
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 54; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public MyParser(MyParserTokenManager tm) {
	 if (jj_initialized_once) {
	   System.out.println("ERROR: Second call to constructor of static parser. ");
	   System.out.println("	   You must either use ReInit() or set the JavaCC option STATIC to false");
	   System.out.println("	   during parser generation.");
	   throw new Error();
	 }
	 jj_initialized_once = true;
	 token_source = tm;
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 54; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(MyParserTokenManager tm) {
	 token_source = tm;
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 54; i++) jj_la1[i] = -1;
  }

  static private Token jj_consume_token(int kind) throws ParseException {
	 Token oldToken;
	 if ((oldToken = token).next != null) token = token.next;
	 else token = token.next = token_source.getNextToken();
	 jj_ntk = -1;
	 if (token.kind == kind) {
	   jj_gen++;
	   return token;
	 }
	 token = oldToken;
	 jj_kind = kind;
	 throw generateParseException();
  }


/** Get the next Token. */
  static final public Token getNextToken() {
	 if (token.next != null) token = token.next;
	 else token = token.next = token_source.getNextToken();
	 jj_ntk = -1;
	 jj_gen++;
	 return token;
  }

/** Get the specific Token. */
  static final public Token getToken(int index) {
	 Token t = token;
	 for (int i = 0; i < index; i++) {
	   if (t.next != null) t = t.next;
	   else t = t.next = token_source.getNextToken();
	 }
	 return t;
  }

  static private int jj_ntk_f() {
	 if ((jj_nt=token.next) == null)
	   return (jj_ntk = (token.next=token_source.getNextToken()).kind);
	 else
	   return (jj_ntk = jj_nt.kind);
  }

  static private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  static private int[] jj_expentry;
  static private int jj_kind = -1;

  /** Generate ParseException. */
  static public ParseException generateParseException() {
	 jj_expentries.clear();
	 boolean[] la1tokens = new boolean[52];
	 if (jj_kind >= 0) {
	   la1tokens[jj_kind] = true;
	   jj_kind = -1;
	 }
	 for (int i = 0; i < 54; i++) {
	   if (jj_la1[i] == jj_gen) {
		 for (int j = 0; j < 32; j++) {
		   if ((jj_la1_0[i] & (1<<j)) != 0) {
			 la1tokens[j] = true;
		   }
		   if ((jj_la1_1[i] & (1<<j)) != 0) {
			 la1tokens[32+j] = true;
		   }
		 }
	   }
	 }
	 for (int i = 0; i < 52; i++) {
	   if (la1tokens[i]) {
		 jj_expentry = new int[1];
		 jj_expentry[0] = i;
		 jj_expentries.add(jj_expentry);
	   }
	 }
	 int[][] exptokseq = new int[jj_expentries.size()][];
	 for (int i = 0; i < jj_expentries.size(); i++) {
	   exptokseq[i] = jj_expentries.get(i);
	 }
	 return new ParseException(token, exptokseq, tokenImage);
  }

  static private int trace_indent = 0;
  static private boolean trace_enabled;

/** Trace enabled. */
  static final public boolean trace_enabled() {
	 return trace_enabled;
  }

  /** Enable tracing. */
  static final public void enable_tracing() {
  }

  /** Disable tracing. */
  static final public void disable_tracing() {
  }

}
