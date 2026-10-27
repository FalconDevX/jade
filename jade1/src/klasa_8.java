import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.core.behaviours.WakerBehaviour;

public class klasa_8 extends Agent {

    @Override
    protected void setup() {
        System.out.println("startuję");

        addBehaviour(new TickerBehaviour(this, 2000) {
            @Override
            protected void onTick() {
                System.out.println("mały tick");
            }
        });

        TickerBehaviour duzyTick = new TickerBehaviour(this, 5000) {
            @Override
            protected void onTick() {
                System.out.println("duży tick");
            }
        };
        addBehaviour(duzyTick);

        addBehaviour(new WakerBehaviour(this, 50000) {
            @Override
            protected void onWake() {
                System.out.println("usuwam zachowanie „duży tick”");
                myAgent.removeBehaviour(duzyTick);
            }
        });

        addBehaviour(new WakerBehaviour(this, 100000) {
            @Override
            protected void onWake() {
                myAgent.doDelete();
            }
        });
    }

    @Override
    protected void takeDown() {
        System.out.println("zaraz się usunę");
    }
}
