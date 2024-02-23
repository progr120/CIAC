package hello;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class contoArrayList {
    static ArrayList<Float> movimenti = new ArrayList<Float>();
    static String contoCorrente = "";

    public static void main(String[] args) { // config do menu
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

    static void setContoCorrente() { // historico da conta corrente
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

    public static float chiediFloat(String domanda) { // cancelamento de operacao
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

    public static void printMenu() { // menu de entrada
        System.out.println("\n           --- MENU  BANCA ---");
        System.out.println("[S] saldo attuale   --- [L] lista movimenti");
        System.out.println("[V] versamento      --- [P] prelievo");
        System.out.println("[E] termina esci");
    }

    private static void insPrelievo() { // remover dinheiro
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
        movimenti.add(-prelievo);
        aggiornaCC();
        System.out.println("prelievo effettuato grazie!");
    }

    private static void insVersamento() { // adicionar dinheiro
        float versamento = chiediFloat("Ins. importo da VERSARE [es: 123.50]");
        if (versamento < 0) {
            System.out.println("importo non valido!");
            return;
        }
        if (versamento == 0) {
            System.out.println("operazione annullata!");
            return;
        }
        movimenti.add(versamento);
        aggiornaCC();
        System.out.println("versamento effettuato grazie!");

    }

        static void leggiCC(){ // leitor da conta corrente
            try {
            File f = new File(contoCorrente + ".txt");
            Scanner readMov = new Scanner(f);
            movimenti.clear();
            while(readMov.hasNextLine()){
                String newmov = readMov.nextLine();
                float importo = Float.parseFloat(newmov);
                movimenti.add(importo);
            }
            }
            catch(Exception e){
                System.out.println("errore nel caricamento dati");
                movimenti.clear();
            }   
        }

    static void aggiornaCC() { // criacao da conta corrente
        String txfile=""    ;
        for(float mov :movimenti){
            txfile= txfile + mov + "\n";

        }
        try {
            FileWriter fw = new FileWriter(contoCorrente + ".txt");
            fw.write(txfile);
            fw.close();
        }
        catch(Exception err){
            System.out.println("impossibile creare il file");
        }
    }

    private static void getListaMovimenti() { // lista de movimentos feitos
        System.out.println("Lista movimenti: ");
        int i = 1;
        for (float mov : movimenti) {
            String riga = i + ") " + mov;
            System.out.println(riga);
            i++;
        }
        getSaldo();
    }

    private static void getSaldo() { // saldo da conta
        System.out.println("Il tuo saldo corrente e' £: " + getSaldoValue());
    }

    private static float getSaldoValue() { // variavel para saldo
        // fare ciclo for che scorra uno per uno tutt i movimenti sommandoli
        float saldo = 0;
        // scorro tutto movimenti e aggiungo uno per uno i valori che trovo
        for (int i = 0; i < movimenti.size(); i++) {
            saldo = saldo + movimenti.get(i);
        }
        return saldo;
    }
}
