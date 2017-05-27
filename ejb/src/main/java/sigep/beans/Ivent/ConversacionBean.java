package sigep.beans.Ivent;

public class ConversacionBean {

    private Long id;
    private UsuarioBean usuario1;
    private UsuarioBean usuario2;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UsuarioBean getUsuario1() {
        return usuario1;
    }

    public void setUsuario1(UsuarioBean usuario1) {
        this.usuario1 = usuario1;
    }

    public UsuarioBean getUsuario2() {
        return usuario2;
    }

    public void setUsuario2(UsuarioBean usuario2) {
        this.usuario2 = usuario2;
    }
}
