package ConfigurationFile;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Iterator;


public class ConfigReader {
    private ComprobadorDatos comprobadorDatos;

    public ConfigReader() {
        comprobadorDatos = new ComprobadorDatos();
    }

    public HashMap<String, String[]> leerConfiguracion() {
        final String ubicacionArchivo = "src\\ConfigurationFile\\Config.txt";
        JSONArray cuartosArray = leerArchivo(ubicacionArchivo);
        return obtenerDatos(cuartosArray);
    }

    private JSONArray leerArchivo(String ubicacion) {
        JSONParser parser = new JSONParser();
        JSONArray cuartos = new JSONArray();
        try {
            FileReader fileReader = new FileReader(ubicacion);
            Object datosArchivo = parser.parse(fileReader);
            cuartos = (JSONArray) datosArchivo;
        } catch (FileNotFoundException e) {
            System.out.println("src/ConfigurationFile/Config.txt not found.");
        } catch (Exception e) {
            System.out.println("Unexpected content in configuration file.");
        }
        return cuartos;
    }

    private HashMap obtenerDatos(JSONArray cuartosArray) {
        HashMap<String, String[]> cuartos = new HashMap<String, String[]>();
        Iterator iterator = cuartosArray.iterator();
        int posicion = 1;
        while (iterator.hasNext()) {
            JSONObject cuarto = (JSONObject) iterator.next();
            try {
                validarPropiedades(cuarto, posicion);
                validarValores(cuarto, posicion);
            } catch (MissingContentError e) {
                System.out.println(e);
                System.exit(0);
            }
            String name = (String) cuarto.get("Name");
            String description = (String) cuarto.get("Description");
            JSONArray exits = (JSONArray) cuarto.get("Exits");
            String[] datos = {description, (String) exits.get(0), (String) exits.get(1), (String) exits.get(2), (String) exits.get(3)};
            cuartos.put(name, datos);
            posicion++;
        }
        return cuartos;
    }

    private void validarPropiedades(JSONObject cuarto, int posicion) throws MissingContentError {
        this.comprobadorDatos.validarPropiedades(cuarto, posicion);
    }

    private void validarValores(JSONObject cuarto, int posicion) throws MissingContentError {
        this.comprobadorDatos.validarValores(cuarto, posicion);
    }
}
