package Models;


import java.util.ArrayList;
import java.util.HashMap;

public class roomsMod {
    String roomName;
    String descripcion;
    HashMap<String, roomsMod> cuartos;


    public roomsMod(String roomName) {
        this.roomName = roomName;
        cuartos = new HashMap<String, roomsMod>();
    }


    public String getRoomName() {
        return roomName;
    }

    public void addDescription(String descripcion) {
        this.descripcion = descripcion;
    }

    public void addRoom(String Direccion, roomsMod room) {
        cuartos.put(Direccion, room);
    }

    public roomsMod getCuartos(String direccion) {
        return cuartos.get(direccion);
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
