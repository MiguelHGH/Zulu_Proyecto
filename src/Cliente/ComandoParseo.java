package Cliente;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ComandoParseo {

    public ComandoParseo(){

    }

    public String[] ParseoCmd(String cmd){
        String[] cmdParsed = null;
        try{
            System.out.println(cmd);
            EstrucCmd(cmd);
            cmdParsed = cmd.split(".");;
            System.out.println(cmdParsed[0]+ " "+ cmdParsed[1]);
        }catch (SintaxError e){
            e.printStackTrace();
        }
        return cmdParsed;
    }

    private void EstrucCmd(String cmd) throws SintaxError {
        Pattern estruc = Pattern.compile("(?=[a-zA-Z]\\.*[a-zA-Z]*)[a-zA-Z\\.]+");
        Matcher matcher = estruc.matcher(cmd);
        if(!matcher.matches()) {
            throw new SintaxError("El comando no tiene la estructura <Cliente.Comando>.<Opcion>");
        }
    }
}
