package Cliente;

import ConfigurationFile.ConfigReader;
import ConfigurationFile.MissingContentError;
import Models.roomsMod;

import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {


    public static void main(String[] args) {
        HashMap<String, roomsMod> RoomsHs = new HashMap<String, roomsMod>();
        ConfigReader reader = new ConfigReader();

        try {

            HashMap<String, String[]> rooms = reader.leerArchivo();
            for (String crearCuartos : rooms.keySet()) {

                RoomsHs.put(crearCuartos, new roomsMod(crearCuartos));
            }

            for (String cuartos : RoomsHs.keySet()) {
                for (String[] direcion_array : rooms.values()) {
                    int indice_dato = 1;
                    for (String data_room : direcion_array) {
                        String direccionCard;
                        switch (indice_dato) {
                            case 2:
                                direccionCard = "North";
                                break;
                            case 3:
                                direccionCard = "East";
                                break;
                            case 4:
                                direccionCard = "South";
                                break;
                            case 5:
                                direccionCard = "West";
                                break;
                            default:
                                direccionCard = "";
                                break;
                        }
                        if (indice_dato == 1) {
                            RoomsHs.get(cuartos).addDescription(data_room);
                        } else if (data_room.equals("null")) {
                            RoomsHs.get(cuartos).addRoom(direccionCard, null);
                        } else {
                            RoomsHs.get(cuartos).addRoom(direccionCard, RoomsHs.get(data_room));
                        }
                        indice_dato++;
                    }
                }
            }


            roomsMod CuartoActual = RoomsHs.get("office");

            while (true){
                System.out.println(CuartoActual.getRoomName());
                Scanner myObj = new Scanner(System.in);
                System.out.println("Enter Direction");

                String direction = myObj.nextLine();
                System.out.println(direction);

                   if (CuartoActual.getCuartos(direction) != null){
                        CuartoActual = CuartoActual.getCuartos(direction);
                    }else {
                        System.out.println("You cannot walk to that direction");
                    }
            }


        } catch (MissingContentError missingContentError) {
            System.out.println(missingContentError);
        }


    }
}
