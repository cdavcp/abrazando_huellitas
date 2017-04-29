package sigep.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Asus PC on 14/07/2015.
 */

@Entity
public class Archivo extends EntityBase {

    @NotNull
    @Column(columnDefinition = "LONGBLOB")
    private byte[] archivo;

    private String nombre;

    public byte[] getArchivo() {
        return archivo;
    }

    public void setArchivo(byte[] archivo) {
        this.archivo = archivo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
