import gov.nasa.jpf.ListenerAdapter;
import gov.nasa.jpf.jvm.bytecode.IRETURN;
import gov.nasa.jpf.jvm.bytecode.JVMInvokeInstruction;
import gov.nasa.jpf.jvm.bytecode.RETURN;
import gov.nasa.jpf.vm.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MemoizationListener extends ListenerAdapter {

    Map<String, Map<String, Integer>> values;

    public MemoizationListener() {
        values = new HashMap<>();
    }

    // CLASS LOADED

    @Override
    public void methodEntered (VM vm, ThreadInfo currentThread, MethodInfo enteredMethod) {
        System.out.println(enteredMethod.getClassInfo().getPackageName());
        String packageName = enteredMethod.getClassInfo().getPackageName();
        if(packageName.equals("")) {
            String inputs = "";
            StackFrame frame = currentThread.getTopFrame();
            Object[] args = frame.getArgumentValues(currentThread);
//            System.out.println(enteredMethod.getName() + ": " + Arrays.toString(args));
            String hash = "";
            for(Object o : args) {
                if(o instanceof DynamicElementInfo) {
                    DynamicElementInfo element = (DynamicElementInfo) o;
                    System.out.println(o);
                    dfs(element, currentThread, hash);
                } else {
                   //System.out.println(o);
                    // APPEND HASH
                }
            }
            // hash == key
            if(values.containsKey(hash)) {

            }
            System.out.println(enteredMethod.getName());
            if(enteredMethod.getName().equals("test")) {
                currentThread.getTopFrame().setResult(69, null);
                currentThread.setPC(enteredMethod.getLastInsn());
            }
        }
    }

    private void dfs(ElementInfo element, ThreadInfo currentThread, String hash) {
        for(int i = 0; i < element.getNumberOfFields(); i++) {
            FieldInfo info = element.getFieldInfo(i);
            System.out.println(info.getName());
            if(info.isReference()) {
                int ref = element.getReferenceField(info);
                ElementInfo e = currentThread.getHeap().get(ref);
                dfs(e, currentThread, hash);
            } else {
                System.out.println(element.getIntField(info));
                //hash += "VALUE";
            }
        }
    }

    @Override
    public void methodExited (VM vm, ThreadInfo currentThread, MethodInfo exitedMethod) {
        if(exitedMethod.getClassInfo().getPackageName().equals("")) {
            if (exitedMethod.getReturnType().equals("I")) {
                System.out.println(exitedMethod.getName());
                System.out.println(currentThread.getTopFrame().getResult());
            }
        }
    }

}
