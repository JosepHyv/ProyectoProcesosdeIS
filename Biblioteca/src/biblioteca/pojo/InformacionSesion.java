package biblioteca.pojo;


import java.util.Date;

public class  InformacionSesion {
    private String TipoContratacionDelEmpleado;
    private static InformacionSesion instancia;
    
    private InformacionSesion() {
    }

    public static InformacionSesion getInformacionSesion(){
        if(instancia == null){
            instancia = new InformacionSesion();
        }
        return instancia;
    }
    public String getGlobalTipoContratacionDelEmpleado() {
        return this.TipoContratacionDelEmpleado;
    }

    public void setTipoContratacionDelEmpleado(String TipoContratacionDelEmpleado) {
        this.TipoContratacionDelEmpleado = TipoContratacionDelEmpleado;
    }
    
}
