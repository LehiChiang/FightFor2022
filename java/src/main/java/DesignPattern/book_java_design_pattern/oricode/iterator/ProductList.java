//DesignPattern.book_java_design_pattern.oricode.iterator.ProductList.java
package DesignPattern.book_java_design_pattern.oricode.iterator;

import java.util.List;

//��Ʒ�����ࣺ����ۺ���
public class ProductList extends AbstractObjectList {
	public ProductList(List<Object> products) {
		super(products);
	}

	//ʵ�ִ�������������ľ��幤������
	public AbstractIterator createIterator() {
		return new ProductIterator(this);
	}
} 
