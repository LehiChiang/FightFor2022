//DesignPattern.book_java_design_pattern.oricode.cor.Congress.java
package designpattern.book.oricode.cor;

//���»��ࣺ���崦����
public class Congress extends Approver {
    public Congress(String name) {
        super(name);
    }

    //������������
    public void processRequest(PurchaseRequest request) {
        System.out.println("�ٿ����»������ɹ�����" + request.getNumber() + "����" + request.getAmount() + "Ԫ���ɹ�Ŀ�ģ�" + request.getPurpose() + "��");        //��������
    }
}