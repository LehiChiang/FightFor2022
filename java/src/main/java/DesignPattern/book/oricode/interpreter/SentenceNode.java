//DesignPattern.book_java_design_pattern.oricode.interpreter.SentenceNode.java
package designpattern.book.oricode.interpreter;

//�򵥾��ӽ��ͣ����ս�����ʽ
public class SentenceNode extends AbstractNode {
    private AbstractNode direction;
    private AbstractNode action;
    private AbstractNode distance;

    public SentenceNode(AbstractNode direction, AbstractNode action, AbstractNode distance) {
        this.direction = direction;
        this.action = action;
        this.distance = distance;
    }

    //�򵥾��ӵĽ��Ͳ���
    public String interpret() {
        return direction.interpret() + action.interpret() + distance.interpret();
    }
}