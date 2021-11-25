//DesignPattern.book_java_design_pattern.oricode.memento.Chessman.java
package DesignPattern.book_java_design_pattern.oricode.memento;

//���������ࣺԭ����
public class Chessman {
    private String label;
    private int x;
    private int y;

    public Chessman(String label, int x, int y) {
        this.label = label;
        this.x = x;
        this.y = y;
    }

    public String getLabel() {
        return (this.label);
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getX() {
        return (this.x);
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return (this.y);
    }

    public void setY(int y) {
        this.y = y;
    }

    //����״̬
    public ChessmanMemento save() {
        return new ChessmanMemento(this.label, this.x, this.y);
    }

    //�ָ�״̬
    public void restore(ChessmanMemento memento) {
        this.label = memento.getLabel();
        this.x = memento.getX();
        this.y = memento.getY();
    }
}