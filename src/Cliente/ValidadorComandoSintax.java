package Cliente;
import java.util.regex.Pattern;

public class ValidadorComandoSintax extends Validador{
    private final String Sintaxis = "(?=^[a-zA-Z]+/*[a-zA-Z]+$)[a-zA-Z/]+";
    private final Pattern verificador = Pattern.compile(Sintaxis);

    @Override
    public boolean validar(String comando) {
        return verificador.matcher(comando).matches();
    }
}
