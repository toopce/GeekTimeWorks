import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class HelloClassLoader extends ClassLoader{
    public static void main(String[] args) {
        try {
            Class<?> hello = new HelloClassLoader().findClass("Hello");
            Method hello1 = hello.getMethod("hello");
            hello1.invoke(hello.newInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
//        String helloBase64 = "yv66vgAAADQAHAoABgAOCQAPABAIABEKABIAEwcAFAcAFQEABjxpbml0PgEAAygpVgEABENvZGUBAA9MaW5lTnVtYmVyVGFibGUBAAg8Y2xpbml0PgEAClNvdXJjZUZpbGUBAApIZWxsby5qYXZhDAAHAAgHABYMABcAGAEACmhlbGxvISEhISEHABkMABoAGwEADUpWTTA5MTQvSGVsbG8BABBqYXZhL2xhbmcvT2JqZWN0AQAQamF2YS9sYW5nL1N5c3RlbQEAA291dAEAFUxqYXZhL2lvL1ByaW50U3RyZWFtOwEAE2phdmEvaW8vUHJpbnRTdHJlYW0BAAdwcmludGxuAQAVKExqYXZhL2xhbmcvU3RyaW5nOylWACEABQAGAAAAAAACAAEABwAIAAEACQAAAB0AAQABAAAABSq3AAGxAAAAAQAKAAAABgABAAAAAwAIAAsACAABAAkAAAAlAAIAAAAAAAmyAAISA7YABLEAAAABAAoAAAAKAAIAAAAFAAgABgABAAwAAAACAA0=";
//        byte[] bytes = decode(helloBase64);
        byte[] bytes = new byte[]{};
        try {
            bytes = FileAnalyze();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return defineClass(name,bytes,0,bytes.length);
    }


    public byte[] FileAnalyze() throws IOException {
        String filepath = "C:\\Users\\wmj\\Desktop\\资料\\第一周-jvm\\Hello.xlass";
        Path path = Paths.get(filepath);
        byte[] bytes = Files.readAllBytes(path);
        for (int i = 0;i<bytes.length;i++){
            bytes[i] = (byte) (255-bytes[i]);
        }
        return bytes;
    }
}
