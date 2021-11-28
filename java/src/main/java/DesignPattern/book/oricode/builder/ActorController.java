//DesignPattern.book_java_design_pattern.oricode.builder.ActorController.java
package designpattern.book.oricode.builder;

public class ActorController {
    //�𲽹������Ӳ�Ʒ����
    public Actor construct(ActorBuilder ab) {
        Actor actor;
        ab.buildType();
        ab.buildSex();
        ab.buildFace();
        ab.buildCostume();
        ab.buildHairstyle();
        actor = ab.createActor();
        return actor;
    }
}