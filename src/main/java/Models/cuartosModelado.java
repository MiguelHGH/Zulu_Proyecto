package Models;


import java.util.HashMap;

public class cuartosModelado {
    String nombreCuarto;
    String descripcion;
    HashMap<String, cuartosModelado> cuartos;

    public cuartosModelado(String nombreCuarto ) {
        this.nombreCuarto = nombreCuarto;
        cuartos = new HashMap<String, cuartosModelado>();
    }



    public void ChecarCuartos(){
        for (String llaves : cuartos.keySet()) {
            String cuartoNombre;
            if (cuartos.get(llaves) == null) {
                cuartoNombre = null;
            } else {
                cuartoNombre = cuartos.get(llaves).nombreCuarto;
            }
            System.out.println(llaves + " " + cuartoNombre);
        }
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
