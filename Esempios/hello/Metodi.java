package hello;

import java.util.Date;

public class Metodi {

    public static void main(String[] args) {
        // richiamo metodo ora
        stampaDataOra();
        int num1, num2, num3, num4;
        num1 = 12;
        num2 = 8;
        num3 = 22;
        num4 = 22;
        System.out.println(getMaggiore(num1, num2));
        System.out.println(getMaggiore(num1, num2, num3));
        System.out.println(getMaggiore(num1, num2, num3, num4));
    }

    // metodo senza parametri
    // stampa l'ora corrente
    public static void stampaDataOra() {
        Date orologio = new Date();
        String dataora = orologio.toLocaleString();
        System.out.println(dataora);
    }

    // metodo per con parametri numeri per ritornare il valore maggiore
    public static int getMaggiore(int n1, int n2) {
        int ris = 0;
        if (n1 > n2) {
            ris = n1;
        }
        if (n1 < n2) {
            ris = n2;
        }
        return ris;
    }

    public static int getMaggiore(int n1, int n2, int n3) {
        int ris = 0;
        if (n1 > n2) {
            ris = n1;
        } else {
            ris = n2;
        }
        return ris;
    }

    public static int getMaggiore(int n1, int n2, int n3, int n4) {
        int ris = 0;
        if (n1 >= n2 && n1 >= n3 && n1 >= n4)
            ris = n1;
        if (n2 >= n1 && n2 >= n3 && n2 >= n4)
            ris = n2;
        if (n3 >= n1 && n3 >= n2 && n3 >= n4)
            ris = n3;
        if (n4 >= n1 && n4 >= n2 && n4 >= n3)
            ris = n4;

        return ris;
    }
}
