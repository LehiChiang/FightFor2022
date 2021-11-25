//DesignPattern.book_java_design_pattern.oricode.builder.DevilBuilder.java
package DesignPattern.book_java_design_pattern.oricode.builder;

public class DevilBuilder extends ActorBuilder {
    public void buildType() {
        actor.setType("��ħ");
    }

    public void buildSex() {
        actor.setSex("��");
    }

    public void buildFace() {
        actor.setFace("��ª");
    }

    public void buildCostume() {
        actor.setCostume("����");
    }

    public void buildHairstyle() {
        actor.setHairstyle("��ͷ");
    }
}