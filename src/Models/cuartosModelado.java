package Models;


import java.util.HashMap;

public class cuartosModelado {
    private String nombreCuarto;
    private String descripcion;
    private HashMap<String, cuartosModelado> cuartos;

    public cuartosModelado(String nombreCuarto ) {
        this.nombreCuarto = nombreCuarto;
        cuartos = new HashMap<String, cuartosModelado>();
    }

    public String getDescripcion(){
        return descripcion;
    }

    public String getnombreCuarto() {
        return nombreCuarto;
    }


    public void addDescipcion(String descripcion) {
        this.descripcion = descripcion;
    }


    public void addCuarto(String Direccion, cuartosModelado cuarto) {
        cuartos.put(Direccion, cuarto);
    }

    public cuartosModelado getCuartos(String direccion) {
        return cuartos.get(direccion);
    }

}
