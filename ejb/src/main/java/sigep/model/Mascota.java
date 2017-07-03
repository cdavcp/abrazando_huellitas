package sigep.model;

import sigep.beans.MascotaBean;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Mascota extends EntityBase {

    @NotNull
    private TipoMascota tipoMascota;

    private RazaMascota razaMascota;

    @Size(max = 50)
    private String nombre;

    private char sexo;

    private EdadMascota edadMascota;

    private TamanioMascota tamanioMascota;

    private boolean estaCastrada;

    private boolean estaVacunada;

    @Size(max = 300)
    private String descripcion;

    public Mascota() {
    }

    public Mascota(MascotaBean ma) {
        setId(ma.getId());
        this.tipoMascota = new TipoMascota(ma.getTipoMascota());
        this.razaMascota = (ma.getRazaMascota() == null) ? null : new RazaMascota(ma.getRazaMascota());
        this.nombre = ma.getNombre();
        this.sexo = ma.getSexo();
        this.edadMascota = (ma.getEdadMascota() == null) ? null : new EdadMascota(ma.getEdadMascota());
        this.tamanioMascota = (ma.getTamanioMascota() == null) ? null : new TamanioMascota( ma.getTamanioMascota());
        this.estaCastrada = ma.isEstaCastrada();
        this.estaVacunada = ma.isEstaVacunada();
        this.descripcion = ma.getDescripcion();
    }

    public MascotaBean getBeanCompleto() {
        MascotaBean ma = new MascotaBean();
        ma.setId(getId());
        ma.setTipoMascota(getTipoMascota().getBeanCompleto());
        ma.setRazaMascota(getRazaMascota().getBeanCompleto());
        ma.setNombre(getNombre());
        ma.setSexo(getSexo());
        ma.setEdadMascota(getEdadMascota().getBeanCompleto());
        ma.setTamanioMascota(getTamanioMascota().getBeanCompleto());
        ma.setEstaCastrada(EstaCastrada());
        ma.setEstaVacunada(EstaVacunada());
        ma.setDescripcion(getDescripcion());

        return ma;
    }

    public TipoMascota getTipoMascota() {
        return tipoMascota;
    }

    public void setTipoMascota(TipoMascota tipoMascota) {
        this.tipoMascota = tipoMascota;
    }

    public RazaMascota getRazaMascota() {
        return razaMascota;
    }

    public void setRazaMascota(RazaMascota razaMascota) {
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

    public EdadMascota getEdadMascota() {
        return edadMascota;
    }

    public void setEdadMascota(EdadMascota edadMascota) {
        this.edadMascota = edadMascota;
    }

    public TamanioMascota getTamanioMascota() {
        return tamanioMascota;
    }

    public void setTamanioMascota(TamanioMascota tamanioMascota) {
        this.tamanioMascota = tamanioMascota;
    }

    public boolean EstaCastrada() {
        return estaCastrada;
    }

    public void setEstaCastrada(boolean estaCastrada) {
        this.estaCastrada = estaCastrada;
    }

    public boolean EstaVacunada() {
        return estaVacunada;
    }

    public void setEstaVacunada(boolean estaVacunada) {
        this.estaVacunada = estaVacunada;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
