import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;

public class klasa_2_5 extends Agent {

    private static final int POWTORZENIA = 10;

    private long startTime;
    private int zakonczone = 0;

    @Override
    protected void setup() {
        System.out.println("startuję");
        startTime = System.currentTimeMillis();

        addBehaviour(new Cykliczne("cyclic 1", 1000));
        addBehaviour(new Cykliczne("cyclic 2", 2000));
    }

    @Override
    protected void takeDown() {
        System.out.println("zaraz się usunę");
    }

    private long czas() {
        return System.currentTimeMillis() - startTime;
    }

    private class Cykliczne extends TickerBehaviour {
        private final String nazwa;

        Cykliczne(String nazwa, long okres) {
            super(klasa_2_5.this, okres);
            this.nazwa = nazwa;
        }

        @Override
        protected void onTick() {
            System.out.println("[" + czas() + " ms] " + nazwa + " (" + getTickCount() + "/" + POWTORZENIA + ")");
            if (getTickCount() >= POWTORZENIA) {
                stop();
            }
        }

        @Override
        public int onEnd() {
            System.out.println("[" + czas() + " ms] " + nazwa + " zakończone");
            zakonczone++;
            if (zakonczone == 2) {
                myAgent.doDelete();
            }
            return super.onEnd();
        }
    }
}
