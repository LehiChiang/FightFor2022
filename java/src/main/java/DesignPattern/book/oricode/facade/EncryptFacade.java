//DesignPattern.book_java_design_pattern.oricode.facade.EncryptFacade.java
package designpattern.book.oricode.facade;

import java.io.FileReader;
import java.io.FileWriter;

//��������ࣺ�����
public class EncryptFacade {
    //ά�ֶ��������������
    private FileReader reader;
    private CipherMachine cipher;
    private FileWriter writer;

    public EncryptFacade() {
        reader = new FileReader();
        cipher = new CipherMachine();
        writer = new FileWriter();
    }

    //�������������ҵ�񷽷�
    public void fileEncrypt(String fileNameSrc, String fileNameDes) {
        String plainStr = reader.read(fileNameSrc);
        String encryptStr = cipher.encrypt(plainStr);
        writer.write(encryptStr, fileNameDes);
    }
}