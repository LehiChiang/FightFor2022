package designpattern.book.code.ch5;

import designpattern.book.code.ch5.factory.SkinFactory;
import designpattern.book.code.ch5.widget.Button;
import designpattern.book.code.ch5.widget.ComboBox;
import designpattern.book.code.ch5.widget.TextField;

public class Client {
    public static void main(String[] args) {
        SkinFactory skinFactory = (SkinFactory) BeanlUtils.getBean();
        Button button = skinFactory.createButton();
        ComboBox comboBox = skinFactory.createComboBox();
        TextField textField = skinFactory.createTextField();
        button.display();
        comboBox.display();
        textField.display();
    }
}
