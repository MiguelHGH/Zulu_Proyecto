package Cliente;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static org.junit.runners.Parameterized.*;

@RunWith(value = Parameterized.class)
public class ComandoProcesoTest {

    @Parameters
    public static Iterable<Object[]> getData(){
        List<Object[]> parametros = new ArrayList<>();
        parametros.add(new Object[] {"Go/Norte", "Go", "Norte"});
        parametros.add(new Object[] {"Go/Sur", "Go", "Sur"});
        parametros.add(new Object[] {"Go/Este", "Go", "Este"});
        parametros.add(new Object[] {"Go/Oeste", "Go", "Oeste"});
        parametros.add(new Object[] {"Go/90", "Help", " "});
        parametros.add(new Object[] {"Go80Oeste", "Help", " "});
        parametros.add(new Object[] {" ", "Help", " "});
        parametros.add(new Object[] {"Exit", "Exit", ""});
        parametros.add(new Object[] {"9304953", "Help", " "});
        return parametros;
    }

    private String input;
    private String[] salidaEsperada;

    public ComandoProcesoTest(String comandoEntrada, String comandoAccion, String opcion){
        this.input = comandoEntrada;
        this.salidaEsperada = new String[]{comandoAccion, opcion};
    }

    @Test
    public void TestComando(){
        ComandoProceso comandoProceso = new ComandoProceso();
        InputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        System.setIn(in);
        Assertions.assertArrayEquals(salidaEsperada, comandoProceso.ObtenerComandoParseo());
    }

}