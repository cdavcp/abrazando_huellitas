package sigep.model.sigep;

import sigep.beans.sigep.ParametroImportacionBean;
import sigep.model.EntityBase;

import javax.persistence.Entity;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

@Entity
public class ParametroImportacion extends EntityBase {

    @Max(1000)
    @NotNull(message = "Ingrese una fila de inicio")
    private Integer filaInicio;

    @Max(1000)
    @NotNull(message = "Ingrese un número de columna de comisión")
    private Integer nroColumnaComision;

    @Max(1000)
    @NotNull(message = "Ingrese un número de columna de fecha")
    private Integer nroColumnaFecha;

    @Max(1000)
    @NotNull(message = "Ingrese una columna de número de póliza")
    private Integer nroColumnaNroPoliza;

    public ParametroImportacion() {
    }

    public ParametroImportacion(ParametroImportacionBean bean){
        setId(bean.getId());
        setNroColumnaNroPoliza(bean.getNroColumnaNroPoliza());
        setNroColumnaFecha(bean.getNroColumnaFech());
        setNroColumnaComision(bean.getNroColumnaComision());
        setFilaInicio(bean.getFilaInicio());
    }

    public ParametroImportacionBean getBean(){
        ParametroImportacionBean bean = new ParametroImportacionBean();
        bean.setId(getId());
        bean.setFilaInicio(getFilaInicio());
        bean.setNroColumnaComision(getNroColumnaComision());
        bean.setNroColumnaFech(getNroColumnaFecha());
        bean.setNroColumnaNroPoliza(getNroColumnaNroPoliza());
        return bean;
    }

    public Integer getFilaInicio() {
        return filaInicio;
    }

    public void setFilaInicio(Integer filaInicio) {
        this.filaInicio = filaInicio;
    }

    public Integer getNroColumnaComision() {
        return nroColumnaComision;
    }

    public void setNroColumnaComision(Integer nroColumnaComision) {
        this.nroColumnaComision = nroColumnaComision;
    }

    public Integer getNroColumnaFecha() {
        return nroColumnaFecha;
    }

    public void setNroColumnaFecha(Integer nroColumnaFecha) {
        this.nroColumnaFecha = nroColumnaFecha;
    }

    public Integer getNroColumnaNroPoliza() {
        return nroColumnaNroPoliza;
    }

    public void setNroColumnaNroPoliza(Integer nroColumnaNroPoliza) {
        this.nroColumnaNroPoliza = nroColumnaNroPoliza;
    }
}
