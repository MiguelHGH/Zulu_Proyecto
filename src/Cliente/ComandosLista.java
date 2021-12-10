package Cliente;
import java.util.HashMap;
import java.util.regex.Pattern;

public class ComandosLista {
    private HashMap<String, String> comandoLista;
    ComandosLista(){
        comandoLista = new HashMap<>();
        comandoLista.put("Go", "(?=^Go/[a-zA-Z]+$)[a-zA-Z/]+");
        comandoLista.put("Help", "(?=^Help$)[a-zA-Z]+");
        comandoLista.put("Exit", "(?=^Exit$)[a-zA-Z]+");
        comandoLista.put("Error", "ERROR");
    }

    public String ObtenerEstructuraComando(String comando){
        String comandoParseado = comando.split("/")[0];
        if(ExisteComando(comandoParseado)){
            return comandoLista.get(comandoParseado);
        }
        return comandoLista.get("Error");
    }

    private boolean ExisteComando(String comando){
        return (comandoLista.get(comando) != null);
    }
}
