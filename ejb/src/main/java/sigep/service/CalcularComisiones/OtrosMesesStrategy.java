package sigep.service.CalcularComisiones;

import sigep.model.sigep.Comision;

public class OtrosMesesStrategy {
    private static final int PORCENTAJE_COMERCIALIZADORA = 10;
    private static final int PORCENTAJE_PRODUCTOR = 90;
    private static final int PORCENTAJE_VENDEDOR = 0;

    public static void calcularComisiones(Comision comision, Double monto){
        comision.setComisionComercializadora(monto * PORCENTAJE_COMERCIALIZADORA / 100);
        comision.setComisionVendedor(monto * PORCENTAJE_VENDEDOR / 100);
        comision.setComisionProductor(monto * PORCENTAJE_PRODUCTOR / 100);
    }
}
