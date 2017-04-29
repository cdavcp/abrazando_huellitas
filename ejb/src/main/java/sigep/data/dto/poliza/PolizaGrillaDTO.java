package sigep.data.dto.poliza;

import sigep.model.sigep.Poliza;

import java.util.Date;

public class PolizaGrillaDTO {
    private Long id;
    private String aseguradora;
    private Long aseguradoraId;
    private String rubro;
    private Long rubroId;
    private String numero;
    private Date fecha;
    private Long clienteId;
    private String nombreCliente;
    private String apellidoCliente;
    private Long origenId;
    private String origen;

    //Se setea en el mapeo
    private Integer estadoId;

    public PolizaGrillaDTO() {
    }

    public PolizaGrillaDTO(Poliza entity) {
        setId(entity.getId());
        setApellidoCliente(entity.getCliente().getApellido());
        setAseguradora(entity.getCobertura().getAseguradora().getNombre());
        setAseguradoraId(entity.getCobertura().getAseguradora().getId());
        setClienteId(entity.getCliente().getId());
        setFecha(entity.getFecha());
        setNombreCliente(entity.getCliente().getNombre());
        setNumero(entity.getNumero());
        setOrigen(entity.getOrigen().getNombre());
        setOrigenId(entity.getOrigen().getId());
        setRubro(entity.getCobertura().getRubro().getNombre());
        setRubroId(entity.getCobertura().getRubro().getId());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAseguradora() {
        return aseguradora;
    }

    public void setAseguradora(String aseguradora) {
        this.aseguradora = aseguradora;
    }

    public Long getAseguradoraId() {
        return aseguradoraId;
    }

    public void setAseguradoraId(Long aseguradoraId) {
        this.aseguradoraId = aseguradoraId;
    }

    public String getRubro() {
        return rubro;
    }

    public void setRubro(String rubro) {
        this.rubro = rubro;
    }

    public Long getRubroId() {
        return rubroId;
    }

    public void setRubroId(Long rubroId) {
        this.rubroId = rubroId;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getApellidoCliente() {
        return apellidoCliente;
    }

    public void setApellidoCliente(String apellidoCliente) {
        this.apellidoCliente = apellidoCliente;
    }

    public Integer getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(Integer estadoId) {
        this.estadoId = estadoId;
    }

    public Long getOrigenId() {
        return origenId;
    }

    public void setOrigenId(Long origenId) {
        this.origenId = origenId;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }
}
