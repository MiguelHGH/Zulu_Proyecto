package Cliente;

public class SintaxError extends Exception{
    public SintaxError(){}
    public SintaxError(String msj){
        super(msj);
    }
}
