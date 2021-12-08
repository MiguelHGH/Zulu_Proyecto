package Cliente;

public class ValidadorComandoEstructura extends Validador{
    private ComandosLista comandosLista = new ComandosLista();
    @Override
    public boolean validar(String comando) {
        comandosLista.ObtenerEstructuraComando(comando);
        return false;
    }
}
