//DesignPattern.book_java_design_pattern.oricode.mediator.Client.java
package designpattern.book.oricode.mediator;

import designpattern.book.code.ch5.widget.ComboBox;

public class Client {
    public static void main(String args[]) {
		/*
        //�����н��߶���
		ConcreteMediator mediator;
		mediator = new ConcreteMediator();
		
        //����ͬ�¶���
		Button addBT = new Button();
		List list = new List();
	    ComboBox cb = new ComboBox();
	    TextBox userNameTB = new TextBox();

		addBT.setMediator(mediator);
		list.setMediator(mediator);
		cb.setMediator(mediator);
		userNameTB.setMediator(mediator);

		mediator.addButton = addBT;
		mediator.list = list;
		mediator.cb = cb;
		mediator.userNameTextBox = userNameTB;
		
		addBT.changed();
		System.out.println("-----------------------------");
		list.changed();
		*/
        //�����������н��߶����н��߶���
        SubConcreteMediator mediator;
        mediator = new SubConcreteMediator();

        Button addBT = new Button();
        List list = new List();
        ComboBox cb = new ComboBox();
        TextBox userNameTB = new TextBox();
        Label label = new Label();

        addBT.setMediator(mediator);
        list.setMediator(mediator);
        cb.setMediator(mediator);
        userNameTB.setMediator(mediator);
        label.setMediator(mediator);

        mediator.addButton = addBT;
        mediator.list = list;
        mediator.cb = cb;
        mediator.userNameTextBox = userNameTB;
        mediator.label = label;

        addBT.changed();
        System.out.println("-----------------------------");
        list.changed();
    }
}
