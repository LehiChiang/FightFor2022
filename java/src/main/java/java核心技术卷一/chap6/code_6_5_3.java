package java核心技术卷一.chap6;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Random;

class TraceHandler implements InvocationHandler {

    private final Object proxyObject;

    public TraceHandler(Object proxyObject) {
        this.proxyObject = proxyObject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.print(proxyObject);
        System.out.print("." + method.getName() + "(");
        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                System.out.print(args[0]);
                if (i < args.length - 1)
                    System.out.print(",");
            }
        }
        System.out.println(")");
        return method.invoke(proxyObject, args);
    }
}

public class code_6_5_3 {
    public static void main(String[] args) {
        var array = new Object[100];
        for (int i = 0; i < array.length; i++) {
            Integer number = i + 1;
            TraceHandler proxyHandler = new TraceHandler(number);
            array[i] = Proxy.newProxyInstance(number.getClass().getClassLoader(), number.getClass().getInterfaces(), proxyHandler);
        }

        Integer key = new Random().nextInt(array.length) + 1;
        int result = Arrays.binarySearch(array, key);
        if (result >= 0)
            System.out.println(array[result]);
    }
}
