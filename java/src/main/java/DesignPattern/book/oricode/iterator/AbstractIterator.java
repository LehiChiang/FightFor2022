//DesignPattern.book_java_design_pattern.oricode.iterator.AbstractIterator.java
package designpattern.book.oricode.iterator;

//���������
public interface AbstractIterator {
    public void next(); //������һ��Ԫ��

    public boolean isLast(); //�ж��Ƿ�Ϊ���һ��Ԫ��

    public void previous(); //������һ��Ԫ��

    public boolean isFirst(); //�ж��Ƿ�Ϊ��һ��Ԫ��

    public Object getNextItem(); //��ȡ��һ��Ԫ��

    public Object getPreviousItem(); //��ȡ��һ��Ԫ��
}