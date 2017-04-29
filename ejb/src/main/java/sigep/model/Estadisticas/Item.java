package sigep.model.Estadisticas;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Item{

    @Id
    private String nombre;
    private Integer cantidad;

    public Item() {
    }

    public Item(String nombre){
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
}
