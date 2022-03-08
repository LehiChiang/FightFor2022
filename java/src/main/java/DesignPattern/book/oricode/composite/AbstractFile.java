//DesignPattern.book_java_design_pattern.oricode.composite.AbstractFile.java
package designpattern.book.oricode.composite;

public abstract class AbstractFile {
    public abstract void add(AbstractFile file);

    public abstract void remove(AbstractFile file);

    public abstract AbstractFile getChild(int i);

    public abstract void killVirus();
}