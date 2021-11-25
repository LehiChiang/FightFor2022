//DesignPattern.book_java_design_pattern.oricode.iterator.Client.java
package DesignPattern.book_java_design_pattern.oricode.iterator;

import java.util.ArrayList;
import java.util.List;

public class Client {
	public static void main(String args[]) {
		List<Object> products = new ArrayList<Object>();
		products.add("���콣");
		products.add("������");
		products.add("�ϳ���");
		products.add("��������");
		products.add("��ʮ���¾�");

		AbstractObjectList list;
		AbstractIterator iterator;

		list = new ProductList(products); //�����ۺ϶���
		iterator = list.createIterator();    //��������������

		System.out.println("���������");
		while (!iterator.isLast()) {
			System.out.print(iterator.getNextItem() + "��");
			iterator.next();
		}
		System.out.println();
		System.out.println("-----------------------------");
		System.out.println("���������");
		while (!iterator.isFirst()) {
			System.out.print(iterator.getPreviousItem() + "��");
			iterator.previous();
		}
	}
}
