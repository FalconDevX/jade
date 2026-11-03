import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.SequentialBehaviour;

public class klasa_2_4 extends Agent {

    private long startTime;

    @Override
    protected void setup() {
        System.out.println("startuję");
        addBehaviour(new PomiarCzasu());
    }

    @Override
    protected void takeDown() {
        System.out.println("zaraz się usunę");
    }

    private long czas() {
        return System.currentTimeMillis() - startTime;
    }

    private class PomiarCzasu extends Behaviour {
        private boolean sekwencjaDodana = false;
        private boolean koniec = false;

        PomiarCzasu() {
            super(klasa_2_4.this);
        }

        @Override
        public void action() {
            if (!sekwencjaDodana) {
                SequentialBehaviour sekwencja = new SequentialBehaviour(myAgent) {
                    @Override
                    public int onEnd() {
                        PomiarCzasu.this.restart();
                        return super.onEnd();
                    }
                };
                sekwencja.addSubBehaviour(new Zachowanie(1));
                sekwencja.addSubBehaviour(new Zachowanie(2));
                sekwencja.addSubBehaviour(new Zachowanie(3));

                startTime = System.currentTimeMillis();
                myAgent.addBehaviour(sekwencja);
                sekwencjaDodana = true;
                block();
            } else {
                long endTime = System.currentTimeMillis();
                System.out.println("[" + czas() + " ms] wszystkie 3 zachowania zakończone, czas: "
                        + (endTime - startTime) + " ms");
                koniec = true;
                myAgent.doDelete();
            }
        }

        @Override
        public boolean done() {
            return koniec;
        }
    }

    private class Zachowanie extends Behaviour {
        private final int numer;
        private int krok = 1;

        Zachowanie(int numer) {
            super(klasa_2_4.this);
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
                Thread.sleep(1000);
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
