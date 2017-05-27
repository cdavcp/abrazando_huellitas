package sigep.model.Ivent;

import sigep.beans.Ivent.TipoDocumentoBean;
import sigep.model.EntityBase;

import javax.persistence.Entity;
import javax.validation.constraints.Size;

@Entity
public class TipoDocumento extends EntityBase {

    @Size( max = 50)
    private String nombre;

    public TipoDocumento() {
    }

    public TipoDocumento(TipoDocumentoBean b) {
        setNombre(b.getNombre());
        setId(b.getId());
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoDocumentoBean getBean(){
        TipoDocumentoBean b = new TipoDocumentoBean();
        b.setId(this.getId());
        b.setNombre(this.getNombre());
        return b;
    }
}
