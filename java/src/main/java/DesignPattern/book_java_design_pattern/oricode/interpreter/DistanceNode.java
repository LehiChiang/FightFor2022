//DesignPattern.book_java_design_pattern.oricode.interpreter.DistanceNode.java
package DesignPattern.book_java_design_pattern.oricode.interpreter;

//������ͣ��ս�����ʽ
public class DistanceNode extends AbstractNode {
    private String distance;

    public DistanceNode(String distance) {
        this.distance = distance;
    }

    //������ʽ�Ľ��Ͳ���
    public String interpret() {
        return this.distance;
    }
}