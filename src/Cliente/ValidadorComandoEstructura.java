package Cliente;

import java.util.regex.Pattern;

public class ValidadorComandoEstructura extends Validador{
    private ComandosLista comandosLista = new ComandosLista();
    @Override
    public boolean validar(String comando) {
        verificador = Pattern.compile(comandosLista.ObtenerEstructuraComando(comando));
        return verificador.matcher(comando).matches();
    }
}
