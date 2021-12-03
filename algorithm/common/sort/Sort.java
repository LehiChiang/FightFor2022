package common.sort;

public class Sort implements Sortable{

    public void printInfo() {
        String className = '[' + this.getClass().getName() + ']';
        System.out.println("***** " + className + " *****");
    }

    @Override
    public void sort(int[] list) {
        printInfo();
    }
}
