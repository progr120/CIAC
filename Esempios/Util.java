
import java.io.FileWriter;

public class Util {
    void salvaFileTesto(String nomefile,String testofile){
                try {
            FileWriter fw = new FileWriter(nomefile);
            fw.write(testofile);
            fw.close();
        } catch (Exception err) {
            System.out.println("impossibile creare il file");
        }
    }
}
