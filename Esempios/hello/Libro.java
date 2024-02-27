package hello;

import java.time.LocalDateTime;
import java.time.Period;

public class Libro {
    // elenco attributi o proprieta
    String author;
    String tittle;
    String edition;
    int numpages;
    String fascia;
    LocalDateTime dataOut;
    LocalDateTime dataBack;

    // costruttore libro
    public Libro(String author, String tittle) {
        this.author = author.toUpperCase().trim();
        this.tittle = tittle.toUpperCase().trim();
    }

    // elenco metodi classe
    public void inPrestito() {
        dataBack = null;
        dataOut = LocalDateTime.now();
    }

    public void ristituzioneLibro() {
        dataBack = LocalDateTime.now();
    }

    public int getDaysOut() {
        int ris = 0;
        LocalDateTime adesso = LocalDateTime.now();
        Period per = Period.between(dataOut.toLocalDate(), adesso.toLocalDate());
        ris = per.getDays();
        return ris;
    }
}
