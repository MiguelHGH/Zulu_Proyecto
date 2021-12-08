package ConfigurationFile;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Iterator;


public class ConfigReader {
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
        if (cuarto.get("Name") == null) {
            String error = "Name not found in object number " + posicion;
            throw new MissingContentError(error);
        }
        if (cuarto.get("Description") == null) {
            String error = "Description not found in object number " + posicion;
            throw new MissingContentError(error);
        }
        if (cuarto.get("Exits") == null) {
            String error = "Exit not found in object number " + posicion;
            throw new MissingContentError(error);
        }
    }

    private void validarValores(JSONObject cuarto, int posicion) throws MissingContentError {
        final String regex = ".*[a-zA-Z0-9_].*";
        String name = (String) cuarto.get("Name");

        if (validarRegex(name, regex)) {
            String error = "Empty name in object number " + posicion;
            throw new MissingContentError(error);
        }
        String description = (String) cuarto.get("Description");
        if (validarRegex(description, regex)) {
            String error = "Empty description in object number " + posicion;
            throw new MissingContentError(error);
        }
        JSONArray exits = (JSONArray) cuarto.get("Exits");
        if (exits.size() != 4) {
            String error = "Must be four exits in object number " + posicion;
            throw new MissingContentError(error);
        }
        for (int j = 0; j < exits.size(); j++) {
            if (validarRegex((String) exits.get(j), regex)) {
                String error = "Empty exit in object number " + posicion + ", exit " + (j + 1);
                throw new MissingContentError(error);
            }
        }
    }

    private boolean validarRegex(String valor, String regex) {
        return !valor.matches(regex);
    }
}
