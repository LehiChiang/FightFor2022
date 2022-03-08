//DesignPattern.book_java_design_pattern.oricode.memento.Client.java
package designpattern.book.oricode.memento;

/*
public class Client {
	public static void main(String args[]) {
		MementoCaretaker mc = new MementoCaretaker();
		Chessman chess = new Chessman("��",1,1);
		display(chess);
		mc.setMemento(chess.save()); //����״̬		
		chess.setY(4);
		display(chess);
		mc.setMemento(chess.save()); //����״̬
		chess.setX(5);
		display(chess);
		System.out.println("******����******");	
		chess.restore(mc.getMemento()); //�ָ�״̬
		display(chess);
	}
	
	public static void display(Chessman chess) {
		System.out.println("����" + chess.getLabel() + "��ǰλ��Ϊ��" + "��" + chess.getX() + "��" + "��" + chess.getY() + "�С�");
	}
}
*/

public class Client {
    private static int index = -1; //����һ����������¼��ǰ״̬����λ��
    private static MementoCaretaker mc = new MementoCaretaker();

    public static void main(String args[]) {
        Chessman chess = new Chessman("��", 1, 1);
        play(chess);
        chess.setY(4);
        play(chess);
        chess.setX(5);
        play(chess);
        undo(chess, index);
        undo(chess, index);
        redo(chess, index);
        redo(chess, index);
    }

    //����
    public static void play(Chessman chess) {
        mc.setMemento(chess.save()); //���汸��¼
        index++;
        System.out.println("����" + chess.getLabel() + "��ǰλ��Ϊ��" + "��" + chess.getX() + "��" + "��" + chess.getY() + "�С�");
    }

    //����
    public static void undo(Chessman chess, int i) {
        System.out.println("******����******");
        index--;
        chess.restore(mc.getMemento(i - 1)); //��������һ������¼
        System.out.println("����" + chess.getLabel() + "��ǰλ��Ϊ��" + "��" + chess.getX() + "��" + "��" + chess.getY() + "�С�");
    }

    //��������
    public static void redo(Chessman chess, int i) {
        System.out.println("******��������******");
        index++;
        chess.restore(mc.getMemento(i + 1)); //�ָ�����һ������¼
        System.out.println("����" + chess.getLabel() + "��ǰλ��Ϊ��" + "��" + chess.getX() + "��" + "��" + chess.getY() + "�С�");
    }
} 