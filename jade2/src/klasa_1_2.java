import jade.core.Agent;
import jade.core.behaviours.FSMBehaviour;
import jade.core.behaviours.OneShotBehaviour;

import java.util.Random;

public class klasa_1_2 extends Agent {

    private static final String stan_a = "A";
    private static final String stan_b = "B";
    private static final String stan_c = "C";
    private static final String stan_d = "D";
    private static final String stan_e = "E";

    private final Random random = new Random();

    @Override
    protected void setup() {
        System.out.println("startuję");

        FSMBehaviour fsm = new FSMBehaviour(this) {
            @Override
            public int onEnd() {
                System.out.println("koniec dzialania stanow");
                myAgent.doDelete();
                return super.onEnd();
            }
        };

        fsm.registerFirstState(new StanZwykly(stan_a), stan_a);
        fsm.registerState(new StanLosujacy(stan_b), stan_b);
        fsm.registerState(new StanZwykly(stan_c), stan_c);
        fsm.registerState(new StanLosujacy(stan_d), stan_d);
        fsm.registerLastState(new StanZwykly(stan_e), stan_e);

        fsm.registerDefaultTransition(stan_a, stan_b);
        fsm.registerTransition(stan_b, stan_c, 1);
        fsm.registerTransition(stan_b, stan_d, 0);
        fsm.registerDefaultTransition(stan_c, stan_d);
        fsm.registerTransition(stan_d, stan_a, 1, new String[]{stan_a, stan_b, stan_c, stan_d});
        fsm.registerTransition(stan_d, stan_e, 0);

        addBehaviour(fsm);
    }

    @Override
    protected void takeDown() {
        System.out.println("zaraz się usunę");
    }

    private class StanZwykly extends OneShotBehaviour {
        private final String nazwa;

        StanZwykly(String nazwa) {
            super(klasa_1_2.this);
            this.nazwa = nazwa;
        }

        @Override
        public void action() {
            System.out.println("stan " + nazwa);
        }
    }

    private class StanLosujacy extends OneShotBehaviour {
        private final String nazwa;
        private int wynik;

        StanLosujacy(String nazwa) {
            super(klasa_1_2.this);
            this.nazwa = nazwa;
        }

        @Override
        public void action() {
            wynik = random.nextInt(2);
            System.out.println("stan " + nazwa + " - wylosowano " + wynik);
        }

        @Override
        public int onEnd() {
            return wynik;
        }
    }
}
