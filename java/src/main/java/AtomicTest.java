import java.util.concurrent.atomic.AtomicInteger;

public class AtomicTest {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(6);
        int res = atomicInteger.incrementAndGet();
        System.out.println(res);
    }
}
