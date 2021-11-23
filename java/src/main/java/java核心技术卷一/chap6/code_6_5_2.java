package java核心技术卷一.chap6;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Date;

class PrintServiceProxyHandler implements InvocationHandler {
    private final Object proxyObject;

    public PrintServiceProxyHandler(Object proxyObject) {
        this.proxyObject = proxyObject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.print(new Date() + ": ");
        return method.invoke(proxyObject, args);
    }
}

/**
 * 动态代理示例，自动生成代理类！
 */
public class code_6_5_2 {
    public static void main(String[] args) {
        // 被代理对象
        PrintService printService = new PrintServiceImpl();
        // 该类用来创建代理对象
        PrintServiceProxyHandler proxyServiceHandler = new PrintServiceProxyHandler(printService);
        // 返回一个代理对象
        PrintService proxyService = (PrintService) Proxy.newProxyInstance(printService.getClass().getClassLoader(),
                printService.getClass().getInterfaces(),
                proxyServiceHandler);
        proxyService.print();
    }
}


