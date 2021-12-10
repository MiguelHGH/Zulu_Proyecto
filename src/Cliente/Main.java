package Cliente;

public class Main {


    public static void main(String[] args) {
        ManejadorEstados context = new ManejadorEstados();
        // Pasamos al estado de Cargar juego
        context.cargarCasa();
        // Pasamos al estado de poder Jugar pasando el objeto con los cuartos
        context.jugar(context.cargarCasa());

    }
}
