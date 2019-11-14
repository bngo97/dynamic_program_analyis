import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class DynamicVisitor {

    public static void main(String[] args) throws IOException {
        byte[] code = Files.readAllBytes(new File("src/Input.java").toPath());
        ClassReader cr = new ClassReader(code);
        ClassWriter cw = new ClassWriter(cr, 0);
    }
}
