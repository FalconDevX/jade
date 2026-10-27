import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;

public class klasa_2 extends Agent {

    @Override
    protected void setup() {
        System.out.println("startuję");

        addBehaviour(new OneShotBehaviour() {
            @Override
            public void action() {
                System.out.println("wykonuję");
                myAgent.doDelete();
            }
        });
    }

    @Override
    protected void takeDown() {
        System.out.println("zaraz się usunę");
    }
}
