package Cliente;
import java.util.Scanner;

public class ComandoProceso {
    private Scanner comandoLector;
    private final String[] comandoDefault = new String[]{"Help", " "};

    public ComandoProceso() {

    }

    public String[] ObtenerComandoParseo() {
        System.out.println("Introduzca el comando");
        comandoLector = new Scanner(System.in);
        String comandoSinParseo = comandoLector.nextLine();
        if(new ValidadorComandoSintax().validar(comandoSinParseo) && new ValidadorComandoEstructura().validar(comandoSinParseo)){
            return comandoSinParseo.split("/");
        }
        return comandoDefault;
    }
}