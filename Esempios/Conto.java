import java.util.Scanner;

public class Conto {
    static float movimenti[] = new float[100];

    public static void printMenu() {
        System.out.println("\n            --- MENU BANCA ---");
        System.out.println("[S] saldo attaule   --- [L]     lista movimenti");
        System.out.println("[V] versamento      --- [P] prelievo");
        System.out.println("[E] termina esci");
    }

    public static void main(String[] args) {
        Scanner readMenu = new Scanner(System.in);

        while (true) {
            printMenu();
            String choice = readMenu.nextLine();
            choice = choice.toLowerCase();
            // caso saldo attuale
            if (choice.equals("s"))
                getSaldo();

            if (choice.equals("l"))
                getListaMovimenti();

            if (choice.equals("v"))
                insVersamento();

            if (choice.equals("p"))
                insPrelievo();

            if (choice.equals("e"))
                break;
        }
    }

    private static void insPrelievo() {
        Scanner getfloat = new Scanner(System.in);
        System.out.println("Ins importo da prelevare [es: 88.99]");
        float prelievo = getfloat.nextFloat();
        if (prelievo < 0) {
            System.out.println("importo non valido!");
            return;

        }
        
        if (prelievo > getSaldoValue()) {
            System.out.println("credito insufficiente");
            return;
        } else {
            for (int i = 0; i < movimenti.length; i++) {
                if (movimenti[i] == 0) {
                    movimenti[i] = -prelievo;
                    break;
                }
            } // fine ciclo for
            System.out.println("prelievo effettuato, grazie!");
        }
    }

    private static void insVersamento() {
        Scanner getfloat = new Scanner(System.in);
        System.out.println("Ins importo da VERSARE [es: 88.99]");
        float versamento = getfloat.nextFloat();
        if (versamento <= 0) {
            System.out.println("importo non valido!");
        } else {
            for (int i = 0; i < movimenti.length; i++) {
                if (movimenti[i] == 0) {
                    movimenti[i] = versamento;
                    break;
                }
            } // fine ciclo for
            System.out.println("versamento effettuato, grazie!");
        }
    }

    private static void getListaMovimenti() {
        System.out.println("Lista movimenti: ");
        for (int i = 0; i < movimenti.length; i++) {
            if (movimenti[i] == 0)
                break;

            String riga = (i + 1) + ") " + movimenti[i];
            System.out.println(riga);
        }
        getSaldo();
    }

    private static void getSaldo() {
        System.out.println("il tuo saldo corrente e' £: " + getSaldoValue());
    }

    private static float getSaldoValue() {
        // fare ciclo for che scorra uno per uno tutti i movimenti
        float saldo = 0;
        // scorro tutti movimenti e aggiungo uno per uno i valori
        for (int i = 0; i < 100; i++) {
            saldo = saldo + movimenti[i];
        }
        return saldo;

    }
}