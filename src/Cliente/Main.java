package Cliente;

import ConfigurationFile.ConfigReader;
import ConfigurationFile.MissingContentError;
import Models.roomsMod;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {


    public static void main(String[] args) {
        HashMap<String, roomsMod> RoomsHs = new HashMap<String, roomsMod>();
        ConfigReader reader = new ConfigReader();

        try {

            HashMap<String, String[]> rooms = reader.leerArchivo();


            for (String CrearCuartos : rooms.keySet()) {

                RoomsHs.put(CrearCuartos, new roomsMod(CrearCuartos));

            }

            for (String cuartos : RoomsHs.keySet()) {
                for (String[] dirrecion_array : rooms.values()) {
                    int i = 1;
                    for (String Direccion : dirrecion_array) {
                        String dirreccionCard;
                        switch (i) {
                            case 2:
                                dirreccionCard = "Norht";
                                break;
                            case 3:
                                dirreccionCard = "East";
                                break;
                            case 4:
                                dirreccionCard = "South";
                                break;
                            case 5:
                                dirreccionCard = "West";
                                break;
                            default:
                                dirreccionCard = "";
                                break;
                        }
                        if (i == 1) {
                            RoomsHs.get(cuartos).addDescription(Direccion);
                            continue;
                        } else if (Direccion.equals("null")) {
                            RoomsHs.get(cuartos).addRoom(dirreccionCard, RoomsHs.get(Direccion));
                        } else {
                            RoomsHs.get(cuartos).addRoom(dirreccionCard, RoomsHs.get(Direccion));
                        }
                        i++;
                    }
                }
            }




        } catch (MissingContentError missingContentError) {
            System.out.println(missingContentError);
        }


    }
}
