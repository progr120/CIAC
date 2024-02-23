package hello;

import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class GestioneConto {
    static ArrayList<Float> movimenti = new ArrayList<Float>();
    // formato data standard mondiale "2024-02-22 12:00:00"
    static ArrayList<String> datemovimenti = new ArrayList<String>();

    static ArrayList<Movimento> listaMovimenti = new ArrayList<>();
    
    static String contoCorrente = "";
    static Util helper = new Util();
        

    public static void main(String[] args) {
        Scanner readMenu = new Scanner(System.in);
        setContoCorrente();
        leggiCC();
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
        readMenu.close();
    }

    static void setContoCorrente() {
        Scanner readCC = new Scanner(System.in);
        String cc = "";
        while (true) {
            System.out.println("Inserire conto corrente");
            cc = readCC.nextLine();
            cc = cc.replaceAll(" ", "_");
            if (cc.length() > 0)
                break;
            else
                System.out.println("conto errato!!");
        }
        contoCorrente = cc;
    }

    public static float chiediFloat(String domanda) {
        float ris = 0;
        while (true) {
            Scanner getFloat = new Scanner(System.in);
            System.out.println(domanda);
            try {
                ris = getFloat.nextFloat();
                break;
            } catch (Exception err) {
                System.out.println("!!!attenzione inserire solo importi con eventuali decimali [es. 123.00]");
                System.out.println("per annullare il versamento inserire importo zero [es. 0]");
            }
        }
        return ris;
    }

    public static void printMenu() {
        System.out.println("\n           --- MENU  BANCA ---");
        System.out.println("[S] saldo attuale   --- [L] lista movimenti");
        System.out.println("[V] versamento      --- [P] prelievo");
        System.out.println("[E] termina esci");
    }

    private static void insPrelievo() {
        float prelievo = chiediFloat("ins importo versamento");
        if (prelievo < 0) {
            System.out.println("importo non valido!");
            return;
        }
        if (prelievo == 0) {
            System.out.println("operazione annullata!");
            return;
        }
        if (prelievo > getSaldoValue()) {
            System.out.println("credito insufficiente");
            return;
        }
        // pronti per inserire prelievo in movimenti
        // creo una data e la inserisco in datemovimenti
        LocalDateTime dateTime = LocalDateTime.now();
        // creo un formattatore dtf
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        // creo testo da data con formattatore
        String txData = dateTime.format(dtf);
        datemovimenti.add(txData);
        movimenti.add(-prelievo);
        // creo un nuovo oggetto di tipo movimento e lo add a listamovimenti
        Movimento mov = new Movimento();
        mov.data=dateTime;
        mov.txdata=txData;
        mov.importo=-prelievo;
        listaMovimenti.add(mov);
        aggiornaCC();
        System.out.println("prelievo effettuato grazie!");
    }

    private static void insVersamento() {
        float versamento = chiediFloat("Ins. importo da VERSARE [es: 123.50]");
        if (versamento < 0) {
            System.out.println("importo non valido!");
            return;
        }
        if (versamento == 0) {
            System.out.println("operazione annullata!");
            return;
        }
        // creo una data e la inserisco in datemovimenti
        LocalDateTime dateTime = LocalDateTime.now();
        // creo un formattatore dtf
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        // creo testo da data con formattatore
        String txData = dateTime.format(dtf);
        datemovimenti.add(txData);
        movimenti.add(versamento);
        Movimento mov = new Movimento();
        mov.data=dateTime;
        mov.txdata=txData;
        mov.importo=versamento;
        listaMovimenti.add(mov);
        
        aggiornaCC();
        System.out.println("versamento effettuato grazie!");

    }

    static void leggiCC() {
        try {
            File f = new File(contoCorrente + ".csv");
            Scanner readMov = new Scanner(f);
            movimenti.clear();
            datemovimenti.clear();
            String newmov = readMov.nextLine();
            while (readMov.hasNextLine()) {
                newmov = readMov.nextLine();
                // es newmov e'  "123.00,2024-02-02 12:00:00"
                // miserve un array ["123.00","2024-02-02 12:00:00"]
                String[] itemsMov=newmov.split(",");
                float importo = Float.parseFloat(itemsMov[0]);
                movimenti.add(importo);
                datemovimenti.add(itemsMov[1]);
            }

        } catch (Exception e) {
            System.out.println("errore nel carimento dati");
            movimenti.clear();
        }
    }

    static void aggiornaCC() {
        String txfile = new Movimento().getHeadCSV();
        for (Movimento mov : listaMovimenti)
            txfile+=mov.getRigaCSV();
        helper.salvaFileTesto(contoCorrente+".csv", txfile);

        /*
        int i = 0;
        for (float mov : movimenti) {
            String data = datemovimenti.get(i);
            // txfile = txfile + mov + "\n";
            txfile += mov + "," + data + "\n";
            i++;
        }
        

        try {
            FileWriter fw = new FileWriter(contoCorrente + ".csv");
            fw.write(txfile);
            fw.close();
        } catch (Exception err) {
            System.out.println("impossibile creare il file");
        }
        */
    }

    private static void getListaMovimenti() {
        System.out.println("Lista movimenti: ");
        int i = 1;
        for (float mov : movimenti) {
            String riga = i + ") " + mov +" - il " + datemovimenti.get(i-1) ;
            System.out.println(riga);
            i++;
        }
        getSaldo();
    }

    private static void getSaldo() {
        System.out.println("Il tuo saldo corrente e' €" + getSaldoValue());
    }

    private static float getSaldoValue() {
        // fare ciclo for che scorra uno per uno tutt i movimenti sommandoli
        float saldo = 0;
        // scorro tutto movimenti e aggiungo uno per uno i valori che trovo
        for (int i = 0; i < movimenti.size(); i++) {
            saldo = saldo + movimenti.get(i);
        }
        return saldo;
    }
}
