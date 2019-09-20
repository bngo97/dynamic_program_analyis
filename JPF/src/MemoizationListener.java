import gov.nasa.jpf.ListenerAdapter;
import gov.nasa.jpf.jvm.bytecode.JVMInvokeInstruction;
import gov.nasa.jpf.vm.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MemoizationListener extends ListenerAdapter {

    Map<String, Object> values;

    public MemoizationListener() {
        values = new HashMap<>();
    }

    // ((JVMInvokeInstruction) instructionToExecute).getArgumentValues(currentThread);
    @Override
    public void executeInstruction(VM vm, ThreadInfo currentThread, Instruction instructionToExecute) {
        Object[] args = ((JVMInvokeInstruction) instructionToExecute).getArgumentValues(currentThread);
    }

    @Override
    public void methodEntered (VM vm, ThreadInfo currentThread, MethodInfo enteredMethod) {
        String packageName = enteredMethod.getClassInfo().getPackageName();
        if(packageName.equals("")) {
            String inputs = "";
            StackFrame frame = currentThread.getTopFrame();
            Object[] args = frame.getArgumentValues(currentThread);
            System.out.println(enteredMethod.getName() + ": " + Arrays.toString(args));
        }
    }


    @Override
    public void methodExited (VM vm, ThreadInfo currentThread, MethodInfo exitedMethod) {
        //StackFrame frame = currentThread.getTopFrame();

        //System.out.println(exitedMethod.getName() + ": " + frame.getResult());

    }

}
