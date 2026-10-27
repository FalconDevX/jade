import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;

public class klasa_3 extends Agent {

    @Override
    protected void setup() {
        System.out.println("startuję");

        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                System.out.println("wykonuję");
                block(1000);
            }
        });
    }

    @Override
    protected void takeDown() {
        System.out.println("zaraz się usunę");
    }
}
