package sigep.data.dto;

import sigep.beans.*;
import sigep.model.TipoUsuario;
import sigep.model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class EventoDTO {
    private List<EventoBean> recibidas = new ArrayList<EventoBean>();
    private List<EventoBean> enviadas = new ArrayList<EventoBean>();
    private List<EventoBean> rechazadas = new ArrayList<EventoBean>();
    private List<EventoBean> aceptadas = new ArrayList<EventoBean>();

    public void build(List<EventoBean> originalList, UsuarioBean usuario){
        for (EventoBean evento : originalList) {
            if (evento.getAceptadoPorArtista() && evento.getAceptadoPorLugar()) {
                addAceptada(evento);
            }
            if (!evento.getAceptadoPorArtista() && !evento.getAceptadoPorLugar()){
                addRechazada(evento);
            }
            if (evento.getAceptadoPorArtista() && !evento.getAceptadoPorLugar()){
                if (usuario.getTipoUsuario().getId().equals(TipoUsuario.ID_MANAGER))
                    addEnviada(evento);
                else
                    addRecibida(evento);
            }
            if (!evento.getAceptadoPorArtista() && evento.getAceptadoPorLugar()){
                if (usuario.getTipoUsuario().getId().equals(TipoUsuario.ID_ANFITRION))
                    addEnviada(evento);
                else
                    addRecibida(evento);
            }
        }
    }

    public void build(List<EventoBean> originalList, ArtistaBean artista){
        for (EventoBean evento : originalList) {
            if (evento.getAceptadoPorArtista() && evento.getAceptadoPorLugar()) {
                addAceptada(evento);
            }
        }
    }


    public void build(List<EventoBean> originalList, LugarBean lugar){
        for (EventoBean evento : originalList) {
            if (evento.getAceptadoPorArtista() && evento.getAceptadoPorLugar()) {
                addAceptada(evento);
            }
        }
    }

    public void build(List<EventoBean> originalList){
        for (EventoBean evento : originalList) {
            if (evento.getAceptadoPorArtista() && evento.getAceptadoPorLugar()) {
                addAceptada(evento);
            }
        }
    }


    public List<EventoBean> getRecibidas() {
        return recibidas;
    }

    public void setRecibidas(List<EventoBean> recibidas) {
        this.recibidas = recibidas;
    }

    public List<EventoBean> getEnviadas() {
        return enviadas;
    }

    public void setEnviadas(List<EventoBean> enviadas) {
        this.enviadas = enviadas;
    }

    public List<EventoBean> getRechazadas() {
        return rechazadas;
    }

    public void setRechazadas(List<EventoBean> rechazadas) {
        this.rechazadas = rechazadas;
    }

    public List<EventoBean> getAceptadas() {
        return aceptadas;
    }

    public void setAceptadas(List<EventoBean> aceptadas) {
        this.aceptadas = aceptadas;
    }

    public void addRechazada(EventoBean b){
        rechazadas.add(b);
    }

    public void addEnviada(EventoBean b){
        enviadas.add(b);
    }

    public void addRecibida(EventoBean b){
        recibidas.add(b);
    }

    public void addAceptada(EventoBean b){
        aceptadas.add(b);
    }
}
