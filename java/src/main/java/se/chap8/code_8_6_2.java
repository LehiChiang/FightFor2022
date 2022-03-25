package se.chap8;

public class code_8_6_2 {
    public static void main(String[] args) {
        var basket = new Basket<?>[10];
    }
}

class Basket<T extends Comparable> {
    private final T item;

    public Basket(T t) {
        item = t;
    }
}
