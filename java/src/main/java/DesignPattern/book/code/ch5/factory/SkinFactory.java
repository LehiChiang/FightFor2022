package designpattern.book.code.ch5.factory;

import designpattern.book.code.ch5.widget.Button;
import designpattern.book.code.ch5.widget.ComboBox;
import designpattern.book.code.ch5.widget.TextField;

public interface SkinFactory {
    public Button createButton();

    public TextField createTextField();

    public ComboBox createComboBox();
}
