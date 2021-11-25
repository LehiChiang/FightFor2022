//DesignPattern.book_java_design_pattern.oricode.cor.Director.java
package DesignPattern.book_java_design_pattern.oricode.cor;

//�����ࣺ���崦����
public class Director extends Approver {
    public Director(String name) {
        super(name);
    }

    //������������
    public void processRequest(PurchaseRequest request) {
        if (request.getAmount() < 50000) {
            System.out.println("����" + this.name + "�����ɹ�����" + request.getNumber() + "����" + request.getAmount() + "Ԫ���ɹ�Ŀ�ģ�" + request.getPurpose() + "��");  //��������
        } else {
            this.successor.processRequest(request);  //ת������
        }
    }
}
