package sigep.beans.Ivent;

public class VotacionLugarBean {

    private Long id;
    private UsuarioBean usuario;
    private LugarBean lugar;
    private String puntuacion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UsuarioBean getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioBean usuario) {
        this.usuario = usuario;
    }

    public LugarBean getLugar() {
        return lugar;
    }

    public void setLugar(LugarBean lugar) {
        this.lugar = lugar;
    }

    public String getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(String puntuacion) {
        this.puntuacion = puntuacion;
    }
}