//DesignPattern.book_java_design_pattern.oricode.abstractfactory.SpringSkinFactory.java
package DesignPattern.book_java_design_pattern.oricode.abstractfactory;

public class SpringSkinFactory implements SkinFactory {
    public Button createButton() {
        return new SpringButton();
    }

    public TextField createTextField() {
        return new SpringTextField();
    }

    public ComboBox createComboBox() {
        return new SpringComboBox();
    }
}