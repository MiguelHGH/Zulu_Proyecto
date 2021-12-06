package Models;


import java.util.ArrayList;
import java.util.HashMap;

public class roomsMod {
    String roomName;
    String descripcion;
    HashMap<String, roomsMod> cuartos = new HashMap<String, roomsMod>();


    public roomsMod(String roomName) {
        this.roomName = roomName;
    }


    public String getRoomName() {
        return roomName;
    }

    public void addDescription(String descripcion) {

    }

    public void addRoom(String Direccion, roomsMod room) {

        cuartos.put(Direccion, room);

    }

    public boolean isWalkable(roomsMod room){
        if (room == null){
            return false;
        }
        else {
            return true;
        }
    }
}
