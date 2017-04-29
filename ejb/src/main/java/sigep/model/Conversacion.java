package sigep.model;

import sigep.beans.ConversacionBean;
import sigep.beans.EventoBean;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Time;
import java.util.Date;

@Entity
public class Conversacion extends EntityBase  {


    @OneToOne
    private Usuario usuario1;

    @OneToOne
    private Usuario usuario2;




    public Conversacion() {
    }

 public Conversacion(ConversacionBean c){
     setId(c.getId());
     this.usuario1 =  (c.getUsuario1() == null)? null: new Usuario(c.getUsuario1());
     this.usuario2 =  (c.getUsuario2() == null)? null: new Usuario(c.getUsuario2());

 }

    public ConversacionBean getBeanCompleto(){
            ConversacionBean c = new ConversacionBean();
        c.setId(getId());
        c.setUsuario1((getUsuario1() == null) ? null : getUsuario1().getBean());
        c.setUsuario2((getUsuario2() == null) ? null : getUsuario2().getBean());
        return c;
    }

    public Usuario getUsuario2() {
        return usuario2;
    }

    public void setUsuario2(Usuario usuario2) {
        this.usuario2 = usuario2;
    }

    public Usuario getUsuario1() {
        return usuario1;
    }

    public void setUsuario1(Usuario usuario1) {
        this.usuario1 = usuario1;
    }
}
