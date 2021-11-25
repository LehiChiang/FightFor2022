//DesignPattern.book_java_design_pattern.oricode.interpreter.AndNode.java
package DesignPattern.book_java_design_pattern.oricode.interpreter;

//And���ͣ����ս�����ʽ
public class AndNode extends AbstractNode {
    private AbstractNode left; //And������ʽ
    private AbstractNode right; //And���ұ��ʽ

    public AndNode(AbstractNode left, AbstractNode right) {
        this.left = left;
        this.right = right;
    }

    //And���ʽ���Ͳ���
    public String interpret() {
        return left.interpret() + "��" + right.interpret();
    }
}
