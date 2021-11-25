//DesignPattern.book_java_design_pattern.oricode.state.screen.Screen.java
package DesignPattern.book_java_design_pattern.oricode.state.screen;

//��Ļ�ࣺ������
public class Screen {
    //ö�����е�״̬��currentState��ʾ��ǰ״̬
    private ScreenState currentState, normalState, largerState, largestState;

    public Screen() {
        this.normalState = new NormalState(); //��������״̬����
        this.largerState = new LargerState(); //���������Ŵ�״̬����
        this.largestState = new LargestState(); //�����ı��Ŵ�״̬����
        this.currentState = normalState; //���ó�ʼ״̬
        this.currentState.display();
    }

    public void setState(ScreenState state) {
        this.currentState = state;
    }

    //�����¼�����������ת�˶�״̬����ҵ�񷽷��ĵ��ú�״̬��ת��
    public void onClick() {
        if (this.currentState == normalState) {
            this.setState(largerState);
            this.currentState.display();
        } else if (this.currentState == largerState) {
            this.setState(largestState);
            this.currentState.display();
        } else if (this.currentState == largestState) {
            this.setState(normalState);
            this.currentState.display();
        }
    }
}
