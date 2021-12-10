package Cliente;

import ConfigurationFile.ConfigReader;
import ConfigurationFile.MissingContentError;
import Models.cuartosModelado;

import java.util.HashMap;
import java.util.Scanner;


//Creamos interfaces que nos serviran para determinar las acciones de nuestros estados
interface State {
    abstract void help();
    cuartosModelado cargarCasa();
    void   jugar(cuartosModelado cuartos);
    void  exit();
    default void cambiarEstado() {
        // Only some states will need this
    }

}

public class ManejadorEstados  implements State {

    //////////////////////////////////////////////
    // Iniciamos las operaciones para definir nuestro estado actual
    //////////////////////////////////////////////
    public void cambiarEstado() {
        estadoActual.cambiarEstado();
    }

    public void help() {


        estadoActual.help();
        System.out.println("Cambiamos al estado de Ayuda!");
        ComandoProceso cmd = new ComandoProceso();


        System.out.println("Bienvenido a la ayuda de Zuul");
        System.out.println("Tienes 4 direcciones");
        System.out.println("Norte");
        System.out.println("Sur");
        System.out.println("Este");
        System.out.println("Oeste");
        System.out.println("Para moverte necesitaras usar el comando");
        System.out.println("Go/Dirreccion");
        System.out.println("Ejemplo : Go/Norte");
        System.out.println("Escribe : 'Adios' para salir del juego! ");
        System.out.println("Es todo!, de vuelta al juego!");
        System.out.println("De lo contrario repetire lo mismo otra vez!");

        Scanner myObj = new Scanner(System.in);
        String userInput = myObj.nextLine();
        cargarCasa();


    }


    public void exit() {

        System.out.println("Adiós!");
        System.exit(0);
    }
    //////////////////////////////////////////////
    // Definimos el proceso para cargar los datos del archivo y almacenarlos en un objeto
    // retornando el objeto con los Cuartos Cargados
    //////////////////////////////////////////////

    public cuartosModelado cargarCasa() {
        estadoActual.cargarCasa();
        HashMap<String, cuartosModelado> RoomsHs = new HashMap<String, cuartosModelado>();
        ConfigReader reader = new ConfigReader();
        HashMap<String, String[]> rooms = reader.leerConfiguracion();
        for (String crearCuartos : rooms.keySet()) {
            RoomsHs.put(crearCuartos, new cuartosModelado(crearCuartos));
        }

        for (cuartosModelado cuartos : RoomsHs.values()) {
            int i = 1;
            for (String datos : rooms.get(cuartos.getnombreCuarto())) {
                if (i == 1) {
                    cuartos.addDescipcion(datos);
                    i++;
                    continue;
                }
                String direccion = "";
                switch (i) {
                    case 2:
                        direccion = "Norte";
                        break;
                    case 3:
                        direccion = "Este";
                        break;
                    case 4:
                        direccion = "Sur";
                        break;
                    case 5:
                        direccion = "Oeste";
                        break;
                    default:
                        System.out.println("Sin direccion");
                        break;
                }
                if (datos.equals("null")) {
                    cuartos.addCuarto(direccion, null);
                } else {
                    cuartos.addCuarto(direccion, RoomsHs.get(datos));
                }
                i++;
            }
        }
        cuartosModelado CuartoActual = RoomsHs.get(reader.getCuartoInicial());
        return CuartoActual;

    }


    //////////////////////////////////////////////
    // tomamos los objetos cargados e iniciamos el juego
    //////////////////////////////////////////////
    @Override
    public void jugar(cuartosModelado cuartos) {
        estadoActual.jugar(cuartos);
        ComandoProceso cmd = new ComandoProceso();
        while (true) {
            System.out.println("Te encuentras en " + cuartos.getnombreCuarto());
            System.out.println("Selecciona una dirección");


            String[] cmdparsed = cmd.ObtenerComandoParseo();

            if (cmdparsed[0].equals("Help")){
                help();
                jugar(cuartos);

            }if (cmdparsed[0].equals("Exit")){
                exit();

            }else
            if (cuartos.getCuartos(cmdparsed[1]) != null) {
                cuartos = cuartos.getCuartos(cmdparsed[1]);
            } else {
                System.out.println("No puedes caminar a esa direccion");
            }
        }


    }


    ////////////////////////////////
    // Definimos los cambios de estado
    ////////////////////////////////

    State estadoCargando = new State() {
        @Override
        public void cambiarEstado() {
            help();
        }
        @Override
        public void help() {
            // Nada
        }

        @Override
        public cuartosModelado cargarCasa() {
            // nada ya esta cargando
            return null;
        }

        @Override
        public void jugar(cuartosModelado cuartos) {
            estadoActual = estadoJugando;
            estadoActual.cambiarEstado();
        }

        @Override
        public void exit() {
            exit();
        }


    };

    ////////////////////////////////
    // Definimos el estado para cuando estamos jugando
    ////////////////////////////////

    State estadoJugando = new State() {

        public void cambiarEstado() {
            jugar(cargarCasa());
        }

        @Override
        public void help() {
            estadoActual=estadoPausado;
            estadoActual.cambiarEstado();
        }

        @Override
        public void jugar(cuartosModelado cuartos) {
            // Ya estando jugando
        }

        @Override
        public void exit() {
            exit();
        }

        @Override
        public cuartosModelado cargarCasa() {
            estadoActual = estadoCargando;
            estadoActual.cambiarEstado();
            return null;
        }




    };

    ////////////////////////////////
    // Definimos el estado de pausa por si llegase a ser necesario en futuro
    ////////////////////////////////

    State estadoPausado = new State() {

        @Override
        public void help() {
            //Nada
        }

        @Override
        public cuartosModelado cargarCasa() {
            estadoActual = estadoCargando;
            estadoActual.cambiarEstado();
            return cargarCasa();
        }

        @Override
        public void jugar(cuartosModelado cuartos) {
            estadoActual = estadoJugando;
            estadoActual.cambiarEstado();

        }

        @Override
        public void exit() {
            exit();
        }


    };



    State estadoActual = estadoCargando;


    public String getEstado() {
        return estadoActual.getClass().getName();
    }


    // Definimos una manera sencilla de cargar los estados
    enum StateName { CARGANDO, JUGANDO, PAUSADO}

    StateName nombreEstadoActual;
    public void unmaintainableStart() {
        if (nombreEstadoActual == StateName.CARGANDO) {
            nombreEstadoActual = StateName.JUGANDO;
            iniciarJuego();
        } else if (nombreEstadoActual == StateName.PAUSADO) {
            nombreEstadoActual = StateName.JUGANDO;
            seguirJugando();
        }
    }

    // y una manera sencilla de poder llamar a las funciones
    void iniciarJuego() {}
    void seguirJugando() {}


}



