import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.OneShotBehaviour;

public class klasa_7 extends Agent {

    @Override
    protected void setup() {
        System.out.println("startuję");

        addBehaviour(new Behaviour() {
            private int krok = 1;

            @Override
            public void action() {
                switch (krok) {
                    case 1:
                        System.out.println("pierwszy krok");
                        myAgent.addBehaviour(new OneShotBehaviour() {
                            @Override
                            public void action() {
                                System.out.println("drugie");
                            }
                        });
                        break;
                    case 2:
                        System.out.println("drugi krok");
                        break;
                    case 3:
                        System.out.println("trzeci krok");
                        break;
                }
                krok++;
            }

            @Override
            public boolean done() {
                return krok > 3;
            }
        });

        addBehaviour(new OneShotBehaviour() {
            @Override
            public void action() {
                System.out.println("pierwsze");
            }
        });
    }

    @Override
    protected void takeDown() {
        System.out.println("zaraz się usunę");
    }
}
