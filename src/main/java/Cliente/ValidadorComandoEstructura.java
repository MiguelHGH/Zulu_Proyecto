package Cliente;

import java.util.regex.Pattern;

public class ValidadorComandoEstructura extends Validador {
    private final ComandosLista comandosLista = new ComandosLista();

    @Override
    public boolean validar(String comando) {
        //Se obtine la estructura especifica de un comando introducido,obteniendo dicho comando de la cadena <Comando> / <Opcion>
        System.out.println(comando.split("/")[0]);
        verificador = Pattern.compile(comandosLista.ObtenerEstructuraComando(comando.split("/")[0]));
        System.out.println(verificador.matcher(comando).matches());
        return verificador.matcher(comando).matches();
    }
}
