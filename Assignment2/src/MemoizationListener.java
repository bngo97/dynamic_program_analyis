import gov.nasa.jpf.ListenerAdapter;
import gov.nasa.jpf.vm.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MemoizationListener extends ListenerAdapter {
    // Map from className/methodName concatenation, to input values to return value
    // Map<ClassName+MethodName, Map<Inputs, Value>>>
    Map<String, Map<String, Integer>> intValues;
    Map<String, Map<String, Double>> doubleValues;
    Map<String, Map<String, Long>> longValues;
    Map<String, Map<String, Float>> floatValues;
    Map<String, Map<String, Boolean>> boolValues;
    Map<String, Map<String, Character>> charValues;

    Set<String> primitiveReturnTypes;

    String argumentKey;

    public MemoizationListener() {
        primitiveReturnTypes = new HashSet<>();

        // Integer
        intValues = new HashMap<>();
        primitiveReturnTypes.add("I");

        // Double
        doubleValues = new HashMap<>();
        primitiveReturnTypes.add("D");

        // Long
        longValues = new HashMap<>();
        primitiveReturnTypes.add("J");

        floatValues = new HashMap<>();
        primitiveReturnTypes.add("F");

        // Boolean
        boolValues = new HashMap<>();
        primitiveReturnTypes.add("Z");

        charValues = new HashMap<>();
        primitiveReturnTypes.add("C");

    }

    @Override
    public void methodEntered (VM vm, ThreadInfo currentThread, MethodInfo enteredMethod) {
        if(!isMemoizedFunction(enteredMethod)) {
            return;
        }

        argumentKey = "";
        ElementInfo e = currentThread.getHeap().get(currentThread.getThis());
        argumentKey = dfs(e, currentThread, argumentKey);
        Object[] args = currentThread.getTopFrame().getArgumentValues(currentThread);
        for(Object o : args) {
            if(o instanceof DynamicElementInfo) {
                DynamicElementInfo element = (DynamicElementInfo) o;
                argumentKey = dfs(element, currentThread, argumentKey);
            } else {
                argumentKey += o + ",";
            }
        }
        String className = enteredMethod.getClassName();
        String methodName = enteredMethod.getName();
        String classMethodKey = className + "," + methodName;

//        System.out.println("KEY = "+ argumentKey);

        switch (enteredMethod.getReturnType()) {
            // int
            case("I"):
                if(intValues.containsKey(classMethodKey)) {
                    Map<String, Integer> methodValues = intValues.get(classMethodKey);
                    if(methodValues.containsKey(argumentKey)) {
                        currentThread.getTopFrame().setResult(methodValues.get(argumentKey), null);
                        currentThread.setPC(enteredMethod.getLastInsn());
                    }
                }
                break;
            // double
            case("D"):
                if(doubleValues.containsKey(classMethodKey)) {
                    Map<String, Double> methodValues = doubleValues.get(classMethodKey);
                    if(methodValues.containsKey(argumentKey)) {
                        currentThread.getTopFrame().setResult(Double.doubleToLongBits(methodValues.get(argumentKey)), null);
                        currentThread.setPC(enteredMethod.getLastInsn());
                    }
                }
                break;
            // boolean
            case("Z"):
                if(boolValues.containsKey(classMethodKey)) {
                    Map<String, Boolean> methodValues = boolValues.get(classMethodKey);
                    if(methodValues.containsKey(argumentKey)) {
                        currentThread.getTopFrame().setResult(methodValues.get(argumentKey).booleanValue() ? 1 : 0, null);
                        currentThread.setPC(enteredMethod.getLastInsn());
                    }
                }
                break;
            // long
            case("J"):
                if(longValues.containsKey(classMethodKey)) {
                    Map<String, Long> methodValues = longValues.get(classMethodKey);
                    if(methodValues.containsKey(argumentKey)) {
                        currentThread.getTopFrame().setResult(methodValues.get(argumentKey), null);
                        currentThread.setPC(enteredMethod.getLastInsn());
                    }
                }
                break;
            // float
            case("F"):
                if(floatValues.containsKey(classMethodKey)) {
                    Map<String, Float> methodValues = floatValues.get(classMethodKey);
                    if(methodValues.containsKey(argumentKey)) {
                        currentThread.getTopFrame().setResult(Float.floatToIntBits(methodValues.get(argumentKey)), null);
                        currentThread.setPC(enteredMethod.getLastInsn());
                    }
                }
                break;
            // char
            case("C"):
                if(charValues.containsKey(classMethodKey)) {
                    Map<String, Character> methodValues = charValues.get(classMethodKey);
                    if(methodValues.containsKey(argumentKey)) {
                        currentThread.getTopFrame().setResult(methodValues.get(argumentKey).charValue(), null);
                        currentThread.setPC(enteredMethod.getLastInsn());
                    }
                }
                break;
        }

    }

    private String dfs(ElementInfo element, ThreadInfo currentThread, String key) {
        if(element == null) {
            return key;
        }
        if(element.isStringObject()) {
            return key + element.asString() + ",";
        }
        for(int i = 0; i < element.getNumberOfFields(); i++) {
            FieldInfo info = element.getFieldInfo(i);
            if(info.isReference()) {
                int ref = element.getReferenceField(info);
                ElementInfo e = currentThread.getHeap().get(ref);
                key = dfs(e, currentThread, key);
            } else {
                if(info.isIntField()) {
                    key += info.getFullName() + "=" + element.getIntField(info) + ",";
                } else if(info.isDoubleField()) {
                    key += info.getFullName() + "=" + element.getDoubleField(info) + ",";
                } else if(info.isBooleanField()) {
                    key += info.getFullName() + "=" + element.getBooleanField(info) + ",";
                } else if(info.isFloatField()) {
                    key += info.getFullName() + "=" + element.getFloatField(info) + ",";
                } else if(info.isLongField()) {
                    key += info.getFullName() + "=" + element.getLongField(info) + ",";
                } else if(info.isCharField()) {
                    key += info.getFullName() + "=" + element.getCharField(info) + ",";
                }
            }
        }
        return key;
    }

    @Override
    public void methodExited (VM vm, ThreadInfo currentThread, MethodInfo exitedMethod) {
        if(isMemoizedFunction(exitedMethod)) {
//            System.out.println("EXIT KEY = " + argumentKey);
            String classMethodKey = exitedMethod.getClassName() + "," + exitedMethod.getName();
            switch(exitedMethod.getReturnType()) {
                // int
                case("I"):
                    Map<String, Integer> intMethodValues = intValues.getOrDefault(classMethodKey, new HashMap<>());
                    int intResult = currentThread.getTopFrame().getResult();
                    currentThread.getTopFrame().setResult(intResult, null);
                    if(!intMethodValues.containsKey(argumentKey)) {
                        intMethodValues.put(argumentKey, intResult);
                        intValues.put(classMethodKey, intMethodValues);
                    }
                    break;
                // double
                case("D"):
                    Map<String, Double> doubleMethodValues = doubleValues.getOrDefault(classMethodKey, new HashMap<>());
                    double doubleResult = currentThread.getTopFrame().getDoubleResult();
                    currentThread.getTopFrame().setResult(Double.doubleToLongBits(doubleResult), null);
                    if(!doubleMethodValues.containsKey(argumentKey)) {
                        doubleMethodValues.put(argumentKey, doubleResult);
                        doubleValues.put(classMethodKey, doubleMethodValues);
                    }
                    break;
                // boolean
                case("Z"):
                    Map<String, Boolean> boolMethodValues = boolValues.getOrDefault(classMethodKey, new HashMap<>());
                    boolean boolResult = currentThread.getTopFrame().getResult() == 1;
                    currentThread.getTopFrame().setResult(boolResult ? 1 : 0, null);
                    if(!boolMethodValues.containsKey(argumentKey)) {
                        boolMethodValues.put(argumentKey, boolResult);
                        boolValues.put(classMethodKey, boolMethodValues);
                    }
                    break;
                // long
                case("J"):
                    Map<String, Long> longMethodValues = longValues.getOrDefault(classMethodKey, new HashMap<>());
                    long longResult = currentThread.getTopFrame().getLongResult();
                    currentThread.getTopFrame().setResult(longResult, null);
                    if(!longMethodValues.containsKey(argumentKey)) {
                        longMethodValues.put(argumentKey, longResult);
                        longValues.put(classMethodKey, longMethodValues);
                    }
                    break;
                // float
                case("F"):
                    Map<String, Float> floatMethodValues = floatValues.getOrDefault(classMethodKey, new HashMap<>());
                    float floatResult = currentThread.getTopFrame().getFloatResult();
                    currentThread.getTopFrame().setResult(Float.floatToIntBits(floatResult), null);
                    if(!floatMethodValues.containsKey(argumentKey)) {
                        floatMethodValues.put(argumentKey, floatResult);
                        floatValues.put(classMethodKey, floatMethodValues);
                    }
                    break;
                // char
                case("C"):
                    Map<String, Character> charMethodValues = charValues.getOrDefault(classMethodKey, new HashMap<>());
                    char charResult = (char) currentThread.getTopFrame().getResult();
                    currentThread.getTopFrame().setResult(charResult, null);
                    if(!charMethodValues.containsKey(argumentKey)) {
                        charMethodValues.put(argumentKey, charResult);
                        charValues.put(classMethodKey, charMethodValues);
                    }
                    break;
            }
        }
    }

    private boolean isMemoizedFunction(MethodInfo method) {
        String packageName = method.getClassInfo().getPackageName();
        String returnType = method.getReturnType();
        return !packageName.startsWith("java.") &&
                !packageName.startsWith("sun.") &&
                !packageName.startsWith("gov.") &&
                primitiveReturnTypes.contains(returnType);
    }

}
