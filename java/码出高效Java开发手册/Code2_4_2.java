package 码出高效Java开发手册;

public class Code2_4_2 {
    public static int num = 10;
    public static String word = "love";
    private static final StringBuilder stringBuilder = new StringBuilder("hello");

    public static void method(int nums) {
        nums = 1000;
    }

    public static void method() {
        num = 1000;
    }

    public static void method(String word) {
        word = "hate";
    }

    public static void method(StringBuilder sb1, StringBuilder sb2) {
        sb1.append("world");
        sb2.append("!");

        sb1 = new StringBuilder("yyds");
        sb1.append("java");
    }
}

class Father{
    protected void doSometing(){
        System.out.println("Father");
    }

    public static void main(String[] args) {
        Father father = new Son();
        father.doSometing();
    }
}

class Son extends Father{

    @Override
    protected void doSometing(){
        System.out.println("Son");
        super.doSometing();
    }
}
