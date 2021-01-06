package tasks.classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class CustomClassLoader extends ClassLoader {

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] b = loadClassFromFile(name);
        return defineClass(name, b, 0, b.length);
    }

    private byte[] loadClassFromFile(String name) {
        InputStream inputStream = getClass().getClassLoader()
                .getResourceAsStream(name.replace('.', File.separatorChar) + ".class");
        byte[] buffer;
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        int nextValue;
        try {
            assert inputStream != null;
            while ((nextValue = inputStream.read()) != -1) {
                stream.write(nextValue);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        buffer = stream.toByteArray();
        return buffer;
    }
}

