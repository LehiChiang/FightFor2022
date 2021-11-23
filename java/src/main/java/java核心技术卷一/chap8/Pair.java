package java核心技术卷一.chap8;

import java.time.LocalDate;

public class Pair<T> {
    private T firstNumber;
    private T secondNumber;

    public Pair(T firstNumber, T secondNumber) {
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
    }

    public static <T extends Comparable> Pair<T> minmax(T[] a) {
        if (a == null || a.length == 0) return null;
        T min = a[0];
        T max = a[0];
        for (int i = 1; i < a.length; i++) {
            if (max.compareTo(a[i]) < 0) max = a[i];
            if (min.compareTo(a[i]) > 0) min = a[i];
        }
        return new Pair<>(min, max);
    }

    public T getFirstNumber() {
        return firstNumber;
    }

    public void setFirstNumber(T firstNumber) {
        this.firstNumber = firstNumber;
    }

    public T getSecondNumber() {
        return secondNumber;
    }

    public void setSecondNumber(T secondNumber) {
        this.secondNumber = secondNumber;
    }
}

class DateInterval extends Pair<LocalDate> {

    public DateInterval(LocalDate firstNumber, LocalDate secondNumber) {
        super(firstNumber, secondNumber);
    }

    public LocalDate getSecondNumber() {
        return super.getSecondNumber();
    }
}