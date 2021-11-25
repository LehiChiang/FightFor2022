//DesignPattern.book_java_design_pattern.oricode.observer.AllyControlCenter.java
package DesignPattern.book_java_design_pattern.oricode.observer;

import java.util.ArrayList;

//ս�ӿ��������ࣺĿ����
public abstract class AllyControlCenter {
    protected String allyName; //ս������
    protected ArrayList<Observer> players = new ArrayList<Observer>(); //����һ���������ڴ洢ս�ӳ�Ա

    public String getAllyName() {
        return this.allyName;
    }

    public void setAllyName(String allyName) {
        this.allyName = allyName;
    }

    //ע�᷽��
    public void join(Observer obs) {
        System.out.println(obs.getName() + "����" + this.allyName + "ս�ӣ�");
        players.add(obs);
    }

    //ע������
    public void quit(Observer obs) {
        System.out.println(obs.getName() + "�˳�" + this.allyName + "ս�ӣ�");
        players.remove(obs);
    }

    //��������֪ͨ����
    public abstract void notifyObserver(String name);
}