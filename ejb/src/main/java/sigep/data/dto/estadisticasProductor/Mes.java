package sigep.data.dto.estadisticasProductor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Asus PC on 4/10/2015.
 */
public class Mes {

    private Integer id;
    private String nombre;
    private List<Integer> lista;

    public Mes() {
    }

    public Mes(Integer id, String nombre, List<Integer> lista) {
        this.id = id;
        this.nombre = nombre;
        this.lista = lista;
    }

    public Mes(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public void agregarALista(Integer num){
        if(lista == null)
            lista = new ArrayList<Integer>(12);
        lista.add(num);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Integer> getLista() {
        return lista;
    }

    public void setLista(List<Integer> lista) {
        this.lista = lista;
    }
}
