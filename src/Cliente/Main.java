package Cliente;

import ConfigurationFile.ConfigReader;
import ConfigurationFile.MissingContentError;
import Models.roomsMod;

import java.util.HashMap;

public class Main {


    public static void main(String[] args) {
        HashMap<String, roomsMod> RoomsHs = new HashMap<String, roomsMod>();
        ConfigReader reader = new ConfigReader();


            HashMap<String, String[]> rooms = reader.leerConfiguracion();
            for (String crearCuartos : rooms.keySet()) {
                RoomsHs.put(crearCuartos, new roomsMod(crearCuartos));
            }

            for (roomsMod cuartos : RoomsHs.values()) {
                int i = 1;
                for (String datos : rooms.get(cuartos.getRoomName())) {
                    if(i == 1){
                        cuartos.addDescription(datos);
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
                    }
                    if (datos.equals("null")) {
                        System.out.println(cuartos.getRoomName() + ":" + direccion + " " + null);
                        cuartos.addRoom(direccion, null);
                    } else {
                        System.out.println(cuartos.getRoomName() + ":" + direccion + " " + RoomsHs.get(datos).getRoomName());
                        cuartos.addRoom(direccion, RoomsHs.get(datos));
                    }
                    i++;
                }
            }

            roomsMod CuartoActual = RoomsHs.get(reader.getCuartoInicial());
            CuartoActual.CheckRooms();
            ComandoProceso cmd = new ComandoProceso();


            while (true) {
                System.out.println(CuartoActual.getRoomName());
                System.out.println("Enter Direction");

                String[] cmdparsed = cmd.ObtenerComandoParseo();

                if (CuartoActual.getCuartos(cmdparsed[1]) != null) {
                    CuartoActual = CuartoActual.getCuartos(cmdparsed[1]);
                } else {
                    System.out.println("You can walk to that direction");
                }
            }




    }
}
