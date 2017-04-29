package sigep.service.CalcularComisiones;

import sigep.model.sigep.Comision;
import sigep.util.DateUtil;

public class CalcularComisionService {

    /**Setea las comisiones de cada parte en el objeto comision pasado como parametro,
     * dicho objeto debe tener seteado previamente la fecha y la poliza de la comision.
     * */
    public static void calcularComisiones(Comision comision, Double monto){
        int mesPoliza = DateUtil.getMonth(comision.getPoliza().getFecha());
        int mesComision = DateUtil.getMonth(comision.getFecha());
        int anioPoliza = DateUtil.getYear(comision.getPoliza().getFecha());
        int anioComision = DateUtil.getYear(comision.getFecha());

        if((mesPoliza == mesComision) && (anioPoliza == anioComision)){
            PrimerMesStrategy.calcularComisiones(comision, monto);
        }
        else {
            OtrosMesesStrategy.calcularComisiones(comision, monto);
        }
    }
}
