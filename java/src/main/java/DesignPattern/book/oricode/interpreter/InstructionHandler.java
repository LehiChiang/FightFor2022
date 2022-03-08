//DesignPattern.book_java_design_pattern.oricode.interpreter.InstructionHandler.java
package designpattern.book.oricode.interpreter;

import java.util.Stack;

//ָ����ࣺ������
public class InstructionHandler {
    private AbstractNode node;

    public void handle(String instruction) {
        AbstractNode left = null, right = null;
        AbstractNode direction = null, action = null, distance = null;
        Stack<AbstractNode> stack = new Stack<AbstractNode>(); //����һ��ջ�������ڴ洢�����﷨��
        String[] words = instruction.split(" "); //�Կո�ָ�ָ���ַ���
        for (int i = 0; i < words.length; i++) {
            //��ʵ������ջ�ķ�ʽ������ָ����������and��������������������Ϊ�����ս�����ʽ����һ���򵥾���SentenceNode��Ϊ��and�����ұ��ʽ��������ջ�������ı��ʽ��Ϊ��and��������ʽ������µġ�and�����ʽѹ��ջ�С�
            if (words[i].equalsIgnoreCase("and")) {
                left = (AbstractNode) stack.pop(); //����ջ�����ʽ��Ϊ����ʽ
                String word1 = words[++i];
                direction = new DirectionNode(word1);
                String word2 = words[++i];
                action = new ActionNode(word2);
                String word3 = words[++i];
                distance = new DistanceNode(word3);
                right = new SentenceNode(direction, action, distance); //�ұ��ʽ
                stack.push(new AndNode(left, right)); //���±��ʽѹ��ջ��
            }
            //����Ǵ�ͷ��ʼ���н��ͣ���ǰ�����������һ���򵥾���SentenceNode�����þ���ѹ��ջ��
            else {
                String word1 = words[i];
                direction = new DirectionNode(word1);
                String word2 = words[++i];
                action = new ActionNode(word2);
                String word3 = words[++i];
                distance = new DistanceNode(word3);
                left = new SentenceNode(direction, action, distance);
                stack.push(left); //���±��ʽѹ��ջ��
            }
        }
        this.node = (AbstractNode) stack.pop(); //��ȫ�����ʽ��ջ�е���
    }

    public String output() {
        String result = node.interpret(); //���ͱ��ʽ
        return result;
    }
}