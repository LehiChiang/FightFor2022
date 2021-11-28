//DesignPattern.book_java_design_pattern.oricode.cor.PurchaseRequest.java
package designpattern.book.oricode.cor;

//�ɹ�����������
public class PurchaseRequest {
    private double amount;  //�ɹ����
    private int number;  //�ɹ������
    private String purpose;  //�ɹ�Ŀ��

    public PurchaseRequest(double amount, int number, String purpose) {
        this.amount = amount;
        this.number = number;
        this.purpose = purpose;
    }

    public double getAmount() {
        return this.amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getNumber() {
        return this.number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getPurpose() {
        return this.purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }
}
