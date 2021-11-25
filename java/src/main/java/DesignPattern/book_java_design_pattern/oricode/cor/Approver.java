//DesignPattern.book_java_design_pattern.oricode.cor.Approver.java
package DesignPattern.book_java_design_pattern.oricode.cor;

//�������ࣺ��������
public abstract class Approver {
    protected Approver successor; //�����̶���
    protected String name; //����������

    public Approver(String name) {
        this.name = name;
    }

    //���ú����
    public void setSuccessor(Approver successor) {
        this.successor = successor;
    }

    //������������
    public abstract void processRequest(PurchaseRequest request);
}
