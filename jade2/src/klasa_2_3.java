import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.ParallelBehaviour;
import jade.core.behaviours.ThreadedBehaviourFactory;

public class klasa_2_3 extends Agent {

    private long startTime;

    @Override
    protected void setup() {
        System.out.println("startuję");

        ParallelBehaviour rownolegle = new ParallelBehaviour(this, ParallelBehaviour.WHEN_ALL) {
            @Override
            public int onEnd() {
                System.out.println("[" + czas() + " ms] wszystkie 3 zachowania zakończone");
                myAgent.doDelete();
                return super.onEnd();
            }
        };

        ThreadedBehaviourFactory tbf = new ThreadedBehaviourFactory();
        rownolegle.addSubBehaviour(tbf.wrap(new Zachowanie(1)));
        rownolegle.addSubBehaviour(tbf.wrap(new Zachowanie(2)));
        rownolegle.addSubBehaviour(tbf.wrap(new Zachowanie(3)));

        startTime = System.currentTimeMillis();
        addBehaviour(rownolegle);
    }

    @Override
    protected void takeDown() {
        System.out.println("zaraz się usunę");
    }

    private long czas() {
        return System.currentTimeMillis() - startTime;
    }

    private class Zachowanie extends Behaviour {
        private final int numer;
        private int krok = 1;

        Zachowanie(int numer) {
            super(klasa_2_3.this);
            this.numer = numer;
        }

        @Override
        public void action() {
            String tekst;
            switch (krok) {
                case 1:
                    tekst = "pierwszy krok";
                    break;
                case 2:
                    tekst = "drugi krok";
                    break;
                default:
                    tekst = "trzeci krok";
                    break;
            }
            System.out.println("[" + czas() + " ms] zachowanie " + numer + ": " + tekst);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            krok++;
        }

        @Override
        public boolean done() {
            return krok > 3;
        }
    }
}
