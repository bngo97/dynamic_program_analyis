import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;

public class CFT implements ClassFileTransformer {

    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) {
        // Only modify non library classes
        if (!className.contains("/") && !className.equals("MethodCounter")) {
            ClassReader cr = new ClassReader(classfileBuffer);
            ClassWriter cw = new ClassWriter(cr, ClassWriter.COMPUTE_MAXS);
            ClassMethodVisitor tv = new ClassMethodVisitor(className, cw);
            cr.accept(tv, 0);
            return cw.toByteArray();
        } else {
            return null;
        }
    }

}
