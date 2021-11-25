//DesignPattern.book_java_design_pattern.oricode.abstractfactory.SkinFactory.java
package DesignPattern.book_java_design_pattern.oricode.abstractfactory;

public interface SkinFactory {
    public Button createButton();

    public TextField createTextField();

    public ComboBox createComboBox();
}