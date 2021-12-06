package Cliente;

import java.util.Scanner;

public class Comando {
    private final Scanner cmdscan;
    public Comando(){
        cmdscan = new Scanner(System.in);
    }

    public String RecibirCmd(){
        System.out.println("Introduzca el comando");
        return cmdscan.nextLine();
    }
}