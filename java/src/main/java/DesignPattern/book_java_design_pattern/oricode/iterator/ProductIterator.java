//DesignPattern.book_java_design_pattern.oricode.iterator.ProductIterator.java
package DesignPattern.book_java_design_pattern.oricode.iterator;

import java.util.List;

//��Ʒ�����������������
public class ProductIterator implements AbstractIterator {
	private List<Object> products;
	private int cursor1; //����һ���α꣬���ڼ�¼���������λ��
	private int cursor2; //����һ���α꣬���ڼ�¼���������λ��

	public ProductIterator(ProductList list) {
		this.products = list.getObjects(); //��ȡ���϶���
		cursor1 = 0; //������������α�ĳ�ʼֵ
		cursor2 = products.size() - 1; //������������α�ĳ�ʼֵ
	}

	public void next() {
		if (cursor1 < products.size()) {
			cursor1++;
		}
	}

	public boolean isLast() {
		return (cursor1 == products.size());
	}

	public void previous() {
		if (cursor2 > -1) {
			cursor2--;
		}
	}

	public boolean isFirst() {
		return (cursor2 == -1);
	}

	public Object getNextItem() {
		return products.get(cursor1);
	}

	public Object getPreviousItem() {
		return products.get(cursor2);
	}
}
