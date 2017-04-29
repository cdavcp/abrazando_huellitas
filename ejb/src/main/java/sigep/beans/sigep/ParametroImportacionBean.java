package sigep.beans.sigep;

public class ParametroImportacionBean {
    private Long id;
    private Integer filaInicio;
    private Integer nroColumnaComision;
    private Integer nroColumnaFech;
    private Integer nroColumnaNroPoliza;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getNroColumnaFech() {
        return nroColumnaFech;
    }

    public void setNroColumnaFech(Integer nroColumnaFech) {
        this.nroColumnaFech = nroColumnaFech;
    }

    public Integer getNroColumnaNroPoliza() {
        return nroColumnaNroPoliza;
    }

    public void setNroColumnaNroPoliza(Integer nroColumnaNroPoliza) {
        this.nroColumnaNroPoliza = nroColumnaNroPoliza;
    }
}
