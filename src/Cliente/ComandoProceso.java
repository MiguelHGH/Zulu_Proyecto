package Cliente;

import java.util.Scanner;

public class ComandoLector {
    private final Scanner cmdscan;
    private final ComandoParseo parser;

    public ComandoLector() {
        cmdscan = new Scanner(System.in);
        parser = new ComandoParseo();
    }

    public String RecibirCmd() {
        System.out.println("Introduzca el comando");
        return cmdscan.nextLine();
    }
}