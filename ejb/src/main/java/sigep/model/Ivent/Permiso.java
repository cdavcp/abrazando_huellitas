package sigep.model.Ivent;

import sigep.beans.Ivent.PermisoBean;
import sigep.model.EntityBase;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Permiso extends EntityBase {

    @NotNull
    @Size(max = 200)
    private String title;

    @NotNull
    @Size(max = 200)
    private String state;

    @NotNull
    @Size(max = 200)
    private String icon;

    public Permiso() {}

    public Permiso(PermisoBean b) {
        setId(b.getId());
        this.title = b.getTitle();
        this.state = b.getState();
        this.icon = b.getIcon();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public PermisoBean getBean()
    {
        PermisoBean b = new PermisoBean();
        b.setId(this.getId());
        b.setIcon(this.getIcon());
        b.setState(this.getState());
        b.setTitle(this.getTitle());
        return b;
    }
}
