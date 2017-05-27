package sigep.beans.Ivent;

public class MeGustaXEventoBean {

    private Long id;
    private EventoBean evento;
    private UsuarioBean usuario;

    public Long     getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EventoBean getEvento() {
        return evento;
    }

    public void setEvento(EventoBean evento) {
        this.evento = evento;
    }

    public UsuarioBean getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioBean usuario) {
        this.usuario = usuario;
    }
}
