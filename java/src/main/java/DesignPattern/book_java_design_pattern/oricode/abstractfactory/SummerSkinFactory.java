//DesignPattern.book_java_design_pattern.oricode.abstractfactory.SummerSkinFactory.java
package DesignPattern.book_java_design_pattern.oricode.abstractfactory;

public class SummerSkinFactory implements SkinFactory {
    public Button createButton() {
        return new SummerButton();
    }

    public TextField createTextField() {
        return new SummerTextField();
    }

    public ComboBox createComboBox() {
        return new SummerComboBox();
    }
}