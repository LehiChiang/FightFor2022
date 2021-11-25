//DesignPattern.book_java_design_pattern.oricode.builder.HeroBuilder.java
package DesignPattern.book_java_design_pattern.oricode.builder;

public class HeroBuilder extends ActorBuilder {
    public void buildType() {
        actor.setType("Ӣ��");
    }

    public void buildSex() {
        actor.setSex("��");
    }

    public void buildFace() {
        actor.setFace("Ӣ��");
    }

    public void buildCostume() {
        actor.setCostume("����");
    }

    public void buildHairstyle() {
        actor.setHairstyle("Ʈ��");
    }
}