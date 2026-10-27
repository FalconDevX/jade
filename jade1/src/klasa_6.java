import jade.core.Agent;
import jade.core.behaviours.Behaviour;

import java.util.Scanner;

public class klasa_6 extends Agent {

    @Override
    protected void setup() {
        System.out.println("startuję");

        addBehaviour(new Behaviour() {
            private final Scanner scanner = new Scanner(System.in);
            private boolean koniec = false;

            @Override
            public void onStart() {
                System.out.println("zachowanie startuje");
            }

            @Override
            public void action() {
                System.out.print("Podaj liczbę całkowitą: ");
                if (!scanner.hasNextInt()) {
                    System.out.println("To nie jest liczba całkowita.");
                    scanner.next();
                    return;
                }
                int liczba = scanner.nextInt();
                System.out.println("Podano: " + liczba);
                if (liczba < 0) {
                    koniec = true;
                }
            }

            @Override
            public boolean done() {
                return koniec;
            }

            @Override
            public int onEnd() {
                System.out.println("zachowanie zakończone");
                return 0;
            }
        });
    }

    @Override
    protected void takeDown() {
        System.out.println("zaraz się usunę");
    }
}
