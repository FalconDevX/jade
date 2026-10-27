import jade.core.Agent;

public class klasa_1 extends Agent {

    @Override
    protected void setup() {
        System.out.println("startuję");
        doDelete();
    }

    @Override
    protected void takeDown() {
        System.out.println("zaraz się usunę");
    }
}
