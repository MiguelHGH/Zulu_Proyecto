package Cliente;

import ConfigurationFile.ConfigReader;
import ConfigurationFile.MissingContentError;

import java.io.IOException;
import java.util.HashMap;

public class Main {
    public static void main(String[] args)  {
        ConfigReader reader = new ConfigReader();
        try {
            HashMap rooms = reader.leerArchivo();
        }
        catch (MissingContentError missingContentError) {
            System.out.println(missingContentError);
        }

    }
}
