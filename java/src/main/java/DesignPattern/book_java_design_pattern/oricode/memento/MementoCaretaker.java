//DesignPattern.book_java_design_pattern.oricode.memento.MementoCaretaker.java
package DesignPattern.book_java_design_pattern.oricode.memento;

import java.util.ArrayList;
/*
//�������ӱ���¼�����ࣺ������
public class MementoCaretaker {
	private ChessmanMemento memento;

	public ChessmanMemento getMemento() {
		return memento;
	}

	public void setMemento(ChessmanMemento memento) {
		this.memento = memento;
	}
}
*/

public class MementoCaretaker {
    //����һ���������洢�������¼
    private ArrayList<ChessmanMemento> mementolist = new ArrayList<ChessmanMemento>();

    public ChessmanMemento getMemento(int i) {
        return (ChessmanMemento) mementolist.get(i);
    }

    public void setMemento(ChessmanMemento memento) {
        mementolist.add(memento);
    }
}