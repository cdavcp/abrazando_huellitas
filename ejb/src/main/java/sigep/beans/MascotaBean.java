package sigep.beans;

public class MascotaBean {

    private Long id;
    private TipoMascotaBean tipoMascota;
    private RazaMascotaBean razaMascota;
    private String nombre;
    private char sexo;
    private EdadMascotaBean edadMascota;
    private TamanioMascotaBean tamanioMascota;
    private boolean estaCastrada;
    private boolean estaVacunada;
    private String descripcion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoMascotaBean getTipoMascota() {
        return tipoMascota;
    }

    public void setTipoMascota(TipoMascotaBean tipoMascota) {
        this.tipoMascota = tipoMascota;
    }

    public RazaMascotaBean getRazaMascota() {
        return razaMascota;
    }

    public void setRazaMascota(RazaMascotaBean razaMascota) {
        this.razaMascota = razaMascota;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public EdadMascotaBean getEdadMascota() {
        return edadMascota;
    }

    public void setEdadMascota(EdadMascotaBean edadMascota) {
        this.edadMascota = edadMascota;
    }

    public TamanioMascotaBean getTamanioMascota() {
        return tamanioMascota;
    }

    public void setTamanioMascota(TamanioMascotaBean tamanioMascota) {
        this.tamanioMascota = tamanioMascota;
    }

    public boolean isEstaCastrada() {
        return estaCastrada;
    }

    public void setEstaCastrada(boolean estaCastrada) {
        this.estaCastrada = estaCastrada;
    }

    public boolean isEstaVacunada() {
        return estaVacunada;
    }

    public void setEstaVacunada(boolean estaVacunada) {
        this.estaVacunada = estaVacunada;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) { this.descripcion = descripcion;}

}
