package Cliente;
import java.util.regex.Pattern;

public abstract class Validador {
    protected Pattern verificador;
    public abstract boolean validar(String comando);
}
