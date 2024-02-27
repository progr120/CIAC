package hello;

import java.util.ArrayList;

public class BiblioAsilo {
    String nomeasilo;
    static ArrayList<Libro> elencoLibri = new ArrayList<Libro>();

    public static void main(String[] args) {
        Libro book1 = new Libro("manzoni alessandro", "promessi sposi");
        Libro book2 = new Libro("jane austen", "orgoglio e pregiudizio");
        book1.numpages = 120;
        book2.numpages = 200;
        elencoLibri.add(book1);
        elencoLibri.add(book2);

        Libro b0 = elencoLibri.get(0);
        b0.inPrestito();
    }
}
