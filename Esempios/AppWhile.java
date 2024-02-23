import java.util.Scanner;

public class AppWhile {
    public static void printWelcome() {
        System.out.println("\n----------- Welcome !!! ---------\n");

    }

    public static int askNumber(String question) {
        System.out.println("---richiesta numero intero---");
        int result = -1;
        boolean ko = true;
        do {
            System.out.println(question);
            try {
                Scanner in = new Scanner(System.in);

                result = in.nextInt();
                ko = false;
            } catch (Exception errore) {

                System.out.println(errore.toString());
                ko = true;
                System.out.println("attenzione inserire solo numeri interi [ad es. 12]");

            }
        } while (ko == true);

        return result;
    }

    public static void main(String[] args) {
        printWelcome();
        Scanner in = new Scanner(System.in);
        Scanner inl = new Scanner(System.in);
        // chiedere la domanda e valtare la risposta se corretta o no
        boolean ok = false;
        int cont = 0;
        do {
            boolean ok1 = false;
            boolean ok2 = false;

            if (cont == 4) {
                System.out.println("Raggiunto limite massimo di risposte!!!\nArrivederci CAPRA CAPRA CAPRA!!!");
                // ok=true;
                break;
            }
            cont++;
            // domanda1
            if (ok1 == false) {
                String dom = "Quanti giorni ha maggio? ";
                int ng_maggio = askNumber(dom);
                if (ng_maggio == 31) {
                    System.out.println("bravo 31 giorni maggio");
                    ok1 = true;
                } else {
                    System.out.println(ng_maggio + " giorni!!! risposta errata!!!! RITENTA");
                }
            }
            // domanda2
            if (ok2 == false) {
                System.out.println("Mese di natale? ");
                // in=new Scanner(System.in);
                String mese_natale = inl.nextLine();
                mese_natale = mese_natale.toLowerCase();
                mese_natale = mese_natale.trim();
                if (mese_natale.equals("dicembre")) {
                    System.out.println("bravo dicembre natale");
                    ok2 = true;
                } else {
                    System.out.println(mese_natale + " non ha natale!!! risposta errata!!!! RITENTA");
                }
            }
            if (ok1 == true && ok2 == true)
                ok = true;
        } while (ok == false); // !ok
    }
}