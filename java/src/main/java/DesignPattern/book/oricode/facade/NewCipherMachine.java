//DesignPattern.book_java_design_pattern.oricode.facade.NewCipherMachine.java
package designpattern.book.oricode.facade;

public class NewCipherMachine {
    public String encrypt(String plainText) {
        System.out.print("���ݼ��ܣ�������ת��Ϊ���ģ�");
        String es = "";
        int key = 10;//������Կ����λ��Ϊ10
        for (int i = 0; i < plainText.length(); i++) {
            char c = plainText.charAt(i);
            //Сд��ĸ��λ
            if (c >= 'a' && c <= 'z') {
                c += key % 26;
                if (c > 'z') c -= 26;
                if (c < 'a') c += 26;
            }
            //��д��ĸ��λ
            if (c >= 'A' && c <= 'Z') {
                c += key % 26;
                if (c > 'Z') c -= 26;
                if (c < 'A') c += 26;
            }
            es += c;
        }
        System.out.println(es);
        return es;
    }
}
