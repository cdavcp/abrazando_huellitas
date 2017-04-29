package sigep.model;

import sigep.beans.PersonaBean;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
public class Persona extends EntityBase {

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Date getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

//    public Date getFechaNacimiento() {
//        return fechaNacimiento;
//    }
//
//    public void setFechaNacimiento(Date fechaNacimiento) {
//        this.fechaNacimiento = fechaNacimiento;
//    }

    @Size( max = 50)
    @NotNull(message = "Ingrese un nombre")
    private String nombre;

    @Size( max = 50)
    @NotNull(message = "Ingrese un apellido")
    private String apellido;

    @Size( max = 25)
    private String telefono;

    @Size( max = 100)
    @NotNull(message = "Ingrese una direccion de mail")
    private String mail;

    @OneToOne
    private Usuario usuario;

//    @NotNull(message = "Ingrese una fecha de alta")
    private Date fechaAlta;

    private Date fechaBaja;

//    @NotNull(message = "Ingrese una fecha de nacimiento")
//    private Date fechaNacimiento;

    public Persona () {}

    public PersonaBean getBeanCompleto(){
        PersonaBean p = new PersonaBean();
        p.setId(getId());
        p.setApellido(getApellido());
        p.setMail(getMail());
        p.setNombre(getNombre());
        p.setTelefono(getTelefono());
        p.setFechaAlta(getFechaAlta());
        p.setFechaBaja(getFechaBaja());
        p.setUsuario((getUsuario() == null) ? null : getUsuario().getBean());
        return p;
    }





    public Persona (PersonaBean p){

        setId(p.getId());
        nombre = p.getNombre();
        apellido = p.getApellido();
         telefono = p.getTelefono();
         mail = p.getMail();
         fechaAlta = p.getFechaAlta();
        fechaBaja = p.getFechaBaja();
//        fechaNacimiento = p.getFechaNacimiento();
        usuario = (p.getUsuario() == null) ? null : new Usuario(p.getUsuario());

    }

}
