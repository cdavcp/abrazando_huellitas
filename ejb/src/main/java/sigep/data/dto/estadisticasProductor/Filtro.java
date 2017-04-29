package sigep.data.dto.estadisticasProductor;

/**
 * Created by Asus PC on 29/9/2015.
 */
public class Filtro {

    public static final int FILTRO_ASEGURADORA_ID = 1;
    public static final int FILTRO_ORIGEN_ID = 2;
    public static final int FILTRO_ESTADO_ID = 3;
    public static final int FILTRO_RUBRO_ID = 4;

    private Integer id;
    private String nombre;

    public Filtro(){}

    public Filtro(Integer id, String nombre){
        setId(id);
        setNombre(nombre);
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
}
