package sigep.service.sigep;

import sigep.beans.sigep.CoberturaBean;
import sigep.beans.sigep.RiesgoBean;
import sigep.data.dto.SimulacionDTO;
//import sigep.data.dto.sigep.SimulacionDTO;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.*;

@Stateless
public class SimulacionService {

    @Inject
    CoberturaService coberturaService;
    @Inject
    AseguradoraService aseguradoraService;

    public static final String MONEDA_DEFAULT = "$";

    public SimulacionDTO simular(Long rubroId, Long tipoCoberturaId, String valorAuto){
        SimulacionDTO result = new SimulacionDTO();
        List<CoberturaBean> coberturas = coberturaService.findCompletoByRubroYTipoCoberturaConRiesgos(rubroId, tipoCoberturaId);
        setearLogosAseguradoras(coberturas);

        //SET PARA EVITAR RIESGOS REPETIDOS.
        Set<RiesgoBean> allRiesgos = new LinkedHashSet<RiesgoBean>();
        for(CoberturaBean cobertura : coberturas){
            allRiesgos.addAll(cobertura.getRiesgos());
        }

        setearInclusiones(allRiesgos, coberturas);

        if(valorAuto != null){
            result.setMoneda(valorAuto.replaceAll("[!^0-9\\.]",""));
            calcularPrecios(coberturas, new Integer(valorAuto.replaceAll("[^0-9]","")));
        }else{
            result.setMoneda(MONEDA_DEFAULT);
        }

        List<RiesgoBean> riesgosSorted = new ArrayList<RiesgoBean>(allRiesgos);
        Collections.sort(riesgosSorted);
        result.setRiesgos(riesgosSorted);
        result.setCoberturas(coberturas);
        return result;
    }

    private List<CoberturaBean> calcularPrecios(List<CoberturaBean> coberturas, Integer valorAuto){
        double precio;
        for(CoberturaBean cobertura : coberturas){
            precio = roundTwoDecimals(valorAuto * cobertura.getPorcentajePrecio() / 100);
            cobertura.setPrecio(precio);
        }
        return coberturas;
    }

    private double roundTwoDecimals(double d) {
//        DecimalFormat twoDForm = new DecimalFormat("#.##");
//        return Double.valueOf(twoDForm.format(d));
        return Math.round(d*1e2)/1e2;
    }

    private void setearInclusiones(Set<RiesgoBean> riesgos, List<CoberturaBean> coberturas){
        ArrayList<Boolean> incluidos;
        for(RiesgoBean riesgo : riesgos){
            incluidos = new ArrayList<Boolean>(coberturas.size());
            for(CoberturaBean cobertura : coberturas){
                incluidos.add(cobertura.getRiesgos().contains(riesgo));
            }
            riesgo.setIncluidos(incluidos);
        }
    }

    private void setearLogosAseguradoras(List<CoberturaBean> coberturas){
        for(CoberturaBean cobertura: coberturas){
            aseguradoraService.setearLogo(cobertura.getAseguradora());
        }
    }

}
