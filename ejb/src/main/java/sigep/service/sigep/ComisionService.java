package sigep.service.sigep;

import sigep.beans.sigep.*;
import sigep.data.dao.sigep.ComisionDao;
import sigep.exceptions.SIGEPException;
import sigep.exceptions.SIGEPValidationException;
import sigep.model.sigep.*;
import sigep.service.CalcularComisiones.CalcularComisionService;
import sigep.util.DateUtil;
import sigep.util.StringUtil;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class ComisionService extends BaseService<Comision, ComisionBean> {
    @Inject
    private ComisionDao dao;
    @Inject
    private LoteService loteService;
    @Inject
    private AseguradoraService aseguradoraService;
    @Inject
    private PolizaService polizaService;
    @Inject
    private EstadoLoteService estadoLoteService;
    @Inject
    private ComisionIncompletaService comisionIncompletaService;

    enum ErrorImportacion{
        FORMATO("Error de formato de fila"),
        COMISION("No se pudo obtener comisión"),
        COMISION_REPETIDA("La comisión ya se encuentra procesada en otro lote."),
        FECHA("No se pudo obtener fecha"),
        NRO_POLIZA("No se pudo obtener el Nro de póliza"),
        POLIZA_INEXISTENTE("La póliza no se encuentra cargada en el sistema"),
        ;

        private final String error;

        private ErrorImportacion(final String error){
            this.error = error;
        }

        public String getMessage(){
            return error;
        }
    }

    @Override
    public ComisionBean findById(Long idEntity) {
        return dao.findById(idEntity).getBean();
    }

    @Override
    public ComisionBean create(ComisionBean bean) throws SIGEPException {
        return dao.create(new Comision(bean)).getBean();
    }

    @Override
    public void update(ComisionBean bean) throws SIGEPException {
        dao.update(new Comision(bean));
    }

    @Override
    public void bussinessValidation(Comision entity) throws SIGEPValidationException {

    }

    public boolean existeComision(Date fecha, Long polizaId){
        return (dao.findByFechaYPoliza((DateUtil.getMonth(fecha) + 1), DateUtil.getYear(fecha), polizaId) != null);
    }

    public ComisionBean findUltimaComisionPoliza(Long idPoliza){
        return dao.findUltimaComisionPoliza(idPoliza).getBean();
    }

    public void deleteAllByLote(Long loteId){
        dao.deleteByLote(loteId);
    }

    public boolean loteValido(LoteBean loteBean){
        return loteService.findByMesYAseguradora((DateUtil.getMonth(loteBean.getFecha()) + 1), DateUtil.getYear(loteBean.getFecha()), loteBean.getAseguradora().getId(), loteBean.getProductor().getId()) == null;
    }

    public boolean procesarLoteManual(LoteBean loteBean, List<ComisionIncompletaBean> comisiones) throws SIGEPException {
        if(loteValido(loteBean))
        {
            boolean hayErrorComisionRepetida = false;
            boolean seProcesoAlMenosUna = false;
            loteBean.setEstado(new EstadoLoteBean());
            loteBean.getEstado().setId(EstadoLote.ID_ESTADO_PROCESADO);
            Lote lote = new Lote(loteService.create(loteBean));

            for(ComisionIncompletaBean comisionInc : comisiones)
            {
                Poliza poliza = polizaService.findBOByNumeroYAseguradora(comisionInc.getNumeroPoliza(), lote.getAseguradora().getId());
                if(!existeComision(comisionInc.getFecha(), poliza.getId()))
                {
                    poliza.setFechaUltimaComision(comisionInc.getFecha());
                    Comision comision = new Comision();
                    comision.setLote(lote);
                    comision.setFecha(comisionInc.getFecha());
                    comision.setPoliza(poliza);
                    CalcularComisionService.calcularComisiones(comision, comisionInc.getMontoTotal());
                    create(comision.getBean());
                    seProcesoAlMenosUna = true;
                } else {
                    comisionInc.setError(ErrorImportacion.COMISION_REPETIDA.getMessage());
                    comisionInc.setLote(lote.getBean());
                    comisionIncompletaService.update(comisionInc);
                    loteBean.addComisionIncompleta(comisionInc);
                    hayErrorComisionRepetida = true;
                }
            }
            if(hayErrorComisionRepetida) {
                if (seProcesoAlMenosUna) {
                    lote.getEstado().setId(EstadoLote.ID_ESTADO_PROCESADO_PARCIAL);
                } else {
                    lote.getEstado().setId(EstadoLote.ID_ESTADO_RECHAZADO);
                }
                loteService.updateBO(lote);
            }
            return true;
        }
        return false;
    }

    //Se llama cuando se corrigieron las comisiones que dieron error por la importacion por archivo
    public void procesarActualizacion(LoteBean loteBean, List<ComisionIncompletaBean> comisiones, List<Long> borradas) throws SIGEPException {
        Lote lote = new Lote(loteBean);
        boolean seArregloAlMenosUna = false;
        boolean haySoloBorradas = (comisiones.size() == 0);
        for(ComisionIncompletaBean comisionInc : comisiones)
        {
            Poliza poliza = polizaService.findBOByNumeroYAseguradora(comisionInc.getNumeroPoliza(), lote.getAseguradora().getId());
            if(!existeComision(comisionInc.getFecha(), poliza.getId()))
            {
                poliza.setFechaUltimaComision(comisionInc.getFecha());
                Comision comision = new Comision();
                comision.setLote(lote);
                comision.setFecha(comisionInc.getFecha());
                comision.setPoliza(poliza);
                CalcularComisionService.calcularComisiones(comision, comisionInc.getMontoTotal());
                create(comision.getBean());
                borradas.add(comisionInc.getId());
                seArregloAlMenosUna = true;
            } else {
                comisionInc.setError(ErrorImportacion.COMISION_REPETIDA.getMessage());
                comisionIncompletaService.update(comisionInc);
                lote.addComisionIncompleta(new ComisionIncompleta(comisionInc));
            }
        }

        comisionIncompletaService.deleteActualizadas(borradas);
        if(seArregloAlMenosUna || haySoloBorradas){
            if (lote.getComisionesIncompletas() == null || lote.getComisionesIncompletas().size() == 0) {
                lote.getEstado().setId(EstadoLote.ID_ESTADO_PROCESADO);
            } else {
                lote.getEstado().setId(EstadoLote.ID_ESTADO_PROCESADO_PARCIAL);
            }
        }
        loteService.updateBO(lote);
    }

    public Long procesarLoteArchivo(LoteBean loteBean, File file) throws SIGEPException {
        if(!loteValido(loteBean))
            return null;

        ParametroImportacionBean parametros = aseguradoraService.findById(loteBean.getAseguradora().getId()).getParametroImportacion();
        Long loteId = null;
        try {
            loteBean.setEstado(new EstadoLoteBean());
            loteBean.getEstado().setId(EstadoLote.ID_ESTADO_IMPORTADO);
            Lote lote = new Lote(loteService.create(loteBean));
            loteId = lote.getId();
            lote.setComisiones(new ArrayList<Comision>());
            lote.setComisionesIncompletas(new ArrayList<ComisionIncompleta>());

            InputStream archivo = new FileInputStream(file);
            LineNumberReader reader = new LineNumberReader(new InputStreamReader(archivo));
            String line;
            String[] array;

            //SALTEAR TITULOS
            for (int i = 0; i < parametros.getFilaInicio() - 1; i++) {
                reader.readLine();
            }

            while ((line = reader.readLine()) != null) {
                array = line.split(";");
                if (generarComision(array, parametros, lote, reader.getLineNumber()))
                    break;
            }
            reader.close();
            actualizarEstadoLote(lote);

        } catch (Exception e) {
            Logger.getLogger(ComisionService.class.getName()).log(Level.INFO, null, e);
            e.printStackTrace();
            throw new SIGEPException(e);
        }
        return loteId;
    }


    private boolean generarComision(String[] array, ParametroImportacionBean parametros, Lote lote, int numeroFila) throws SIGEPException {
        Double montoComision = null;
        Date fecha = null;
        Poliza poliza = null;

        String montoComisionStr = null;
        String fechaStr = null;
        String nroPoliza = null;

        boolean filaErronea = false;
        List<ErrorImportacion> errores = new ArrayList<ErrorImportacion>();

        try {
            montoComisionStr = StringUtil.estaVacio(array[parametros.getNroColumnaComision() - 1]) ? null : array[parametros.getNroColumnaComision() - 1];
            fechaStr = StringUtil.estaVacio(array[parametros.getNroColumnaFech() - 1]) ? null : array[parametros.getNroColumnaFech() - 1];
            nroPoliza = StringUtil.estaVacio(array[parametros.getNroColumnaNroPoliza() - 1]) ? null : array[parametros.getNroColumnaNroPoliza() - 1];
        } catch (Exception e) {
            System.out.println("Error de formato en la fila: " + numeroFila);
            errores.add(ErrorImportacion.FORMATO);
            filaErronea = true;
        }

        if (!filaErronea) {
            try {
                montoComision = new Double(StringUtil.excelToDoubleNumberFormat(montoComisionStr));
                if(montoComision == null)
                    errores.add(ErrorImportacion.COMISION);
            } catch (Exception e) {
                System.out.println("Error al obtener monto comisión en fila: " + numeroFila);
                errores.add(ErrorImportacion.COMISION);
            }

            try {
                fecha = DateUtil.format(fechaStr, "dd/MM/yy");
                if(fecha == null)
                    errores.add(ErrorImportacion.FECHA);
            } catch (Exception e) {
                System.out.println("Error al obtener fecha en fila: " + numeroFila);
                errores.add(ErrorImportacion.FECHA);
            }

            try {
                poliza = polizaService.findBOByNumeroYAseguradora(nroPoliza, lote.getAseguradora().getId());
                if (poliza == null)
                    errores.add(ErrorImportacion.POLIZA_INEXISTENTE);
            } catch (Exception e) {
                System.out.println("Error al obtener póliza en fila: " + numeroFila);
                errores.add(ErrorImportacion.NRO_POLIZA);
            }

            if(fecha != null && poliza != null && existeComision(fecha ,poliza.getId())){
                errores.add(ErrorImportacion.COMISION_REPETIDA);
            }

            if (errores.size() == 0) {
                Comision comision = new Comision();
                comision.setLote(lote);
                comision.setFecha(fecha);
                poliza.setFechaUltimaComision(fecha);
                comision.setPoliza(poliza);
                CalcularComisionService.calcularComisiones(comision, montoComision);
                lote.getComisiones().add(comision);
                create(comision.getBean());
            } else {
                ComisionIncompleta comisionIncompleta = new ComisionIncompleta();
                comisionIncompleta.setLote(lote);
                comisionIncompleta.setNumeroFila(numeroFila);

                comisionIncompleta.setError(obtenerMensajeError(errores));
                comisionIncompleta.setFecha(fecha);
                comisionIncompleta.setNumeroPoliza(nroPoliza);
                comisionIncompleta.setMontoTotal(montoComision);
                lote.getComisionesIncompletas().add(comisionIncompleta);
                comisionIncompletaService.create(comisionIncompleta.getBean());
            }
        }
        return filaErronea;
    }

    private String obtenerMensajeError(List<ErrorImportacion> errores){
        StringBuilder errorBuilder = new StringBuilder(errores.get(0).getMessage());
        if(errores.size() != 1) {
            for (int i = 1; i <= errores.size() - 1; i++) {
                errorBuilder.append(", ");
                errorBuilder.append(errores.get(i).getMessage());
            }
        }
        return errorBuilder.toString();
    }

    private void actualizarEstadoLote(Lote lote) throws SIGEPException {
        if (lote.getComisiones().size() == 0) {
            lote.getEstado().setId(EstadoLote.ID_ESTADO_RECHAZADO);
        } else {
            if (lote.getComisionesIncompletas().size() == 0) {
                lote.getEstado().setId(EstadoLote.ID_ESTADO_PROCESADO);
            } else {
                lote.getEstado().setId(EstadoLote.ID_ESTADO_PROCESADO_PARCIAL);
            }
        }
        loteService.updateBO(lote);
    }
}
