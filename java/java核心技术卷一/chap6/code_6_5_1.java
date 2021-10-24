package java核心技术卷一.chap6;

import java.util.Date;

interface PrintService {
    void print();
}

/**
 * 原始的PrintService接口实现类，但我们想对这里的功能进行扩展，并且想保持
 * 这个实现类的封装性，所以我们使用静态代理类来扩展这个方法。
 */
class PrintServiceImpl implements PrintService {

    @Override
    public void print() {
        System.out.println("Start printing!");
    }
}

/**
 * 代理类，对PrintServiceImpl类的封装和增强。代理类是对外开放的
 */
class PrintServiceProxy implements PrintService {

    private final PrintService printService;

    public PrintServiceProxy() {
        this.printService = new PrintServiceImpl();
    }

    @Override
    public void print() {
        System.out.print(new Date() + ": ");
        printService.print();
    }
}

public class code_6_5_1 {
    public static void main(String[] args) {
        PrintServiceProxy printServiceProxy = new PrintServiceProxy();
        printServiceProxy.print();
    }
}


