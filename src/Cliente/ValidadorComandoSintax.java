package Cliente;
import java.util.regex.Pattern;

public class ValidadorComandoSintax extends Validador{
    private final String Sintaxis = "(?=^[a-zA-Z]+/*[a-zA-Z]+$)[a-zA-Z/]+";

    @Override
    public boolean validar(String comando) {
        verificador = Pattern.compile(Sintaxis);
        return verificador.matcher(comando).matches();
    }
}
