package datastructure;

import java.util.ArrayList;

public class Heap <E extends Comparable> {

    private ArrayList<E> list = new ArrayList<>();

    public Heap() {}

    public Heap(E[] list) {
        for (E elem : list) {
            add(elem);
        }
    }

    public void add(E elem) {
        list.add(elem);
        int currentIndex = getSize() - 1;

        while (currentIndex > 0) {
            int parentIndex = (currentIndex - 1) / 2;
            if (list.get(parentIndex).compareTo(list.get(currentIndex)) > 0) {
                E temp = list.get(parentIndex);
                list.set(parentIndex, list.get(currentIndex));
                list.set(currentIndex, temp);
            } else {
                break;
            }
            currentIndex = parentIndex;
        }
    }

    public E remove() {
        if (getSize() == 0)
            return null;

        E elem = list.get(0);
        list.set(0, list.get(getSize() - 1));
        list.remove(getSize() - 1);

        int currentIndex = 0;
        while (currentIndex < getSize()) {
            int leftIndex = 2 * currentIndex + 1;
            int rightIndex = 2 * currentIndex + 2;
            if (leftIndex >= getSize())
                break;
            int maxIndex = leftIndex;
            if (rightIndex < getSize()) {
                if (list.get(maxIndex).compareTo(list.get(rightIndex)) > 0)
                    maxIndex = rightIndex;
            }
            if (list.get(maxIndex).compareTo(list.get(currentIndex)) < 0) {
                E temp = list.get(currentIndex);
                list.set(currentIndex, list.get(maxIndex));
                list.set(maxIndex, temp);
                currentIndex = maxIndex;
            } else {
                break;
            }
        }
        return elem;
    }

    public int getSize() {
        return list.size();
    }

    public E get (int index) {
        return list.get(index);
    }
}
