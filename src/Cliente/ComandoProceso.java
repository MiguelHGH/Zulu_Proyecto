package Cliente;
import java.util.Scanner;

public class ComandoProceso {
    private final Scanner comandoLector;
    private final String[] comandoDefault = new String[]{"Help"};
    public ComandoProceso() {
        comandoLector = new Scanner(System.in);
    }

    public String[] ObtenerComandoParseo() {
        System.out.println("Introduzca el comando");
        String comandoSinParseo = comandoLector.nextLine();
        if(new ValidadorComandoSintax().validar(comandoSinParseo) && new ValidadorComandoEstructura().validar(comandoSinParseo)){
            return comandoSinParseo.split("/");
        }
        return comandoDefault;
    }
}