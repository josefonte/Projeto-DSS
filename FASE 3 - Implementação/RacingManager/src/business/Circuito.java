package business;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Circuito {
    private float distancia;
    private String nomeCircuito;
    private List<SegmentoDePista> segmentosdepista = new ArrayList<>();

    public Circuito(float distancia, String nomeCircuito, ArrayList<SegmentoDePista> segmentos) {
        this.distancia = distancia;
        this.nomeCircuito = nomeCircuito;
        this.segmentosdepista = segmentos.stream().map((s)->s.clone()).collect(Collectors.toList());
    }

    public float getDistancia() {
        return distancia;
    }

    public void setDistancia(float distancia) {
        this.distancia = distancia;
    }

    public String getNomeCircuito() {
        return nomeCircuito;
    }

    public void setNomeCircuito(String nomeCircuito) {
        this.nomeCircuito = nomeCircuito;
    }
    public void addSegmentoDePista(SegmentoDePista s){
        segmentosdepista.add(s.clone());
    }
    public void calculaSegmentos(){
        int reta = 0, curva = 0, chicane = 0;
        float distTotal = getDistancia();
        for(SegmentoDePista s: segmentosdepista){
            if (s.getNome() == TipoSegmento.RETA) reta++;
            else if (s.getNome() == TipoSegmento.CURVA) curva++;
            else chicane++;
        }
        for(SegmentoDePista s: segmentosdepista){
            if (s.getNome() == TipoSegmento.RETA) s.setDistancia((float) (distTotal * 0.6 / reta));
            else if (s.getNome() == TipoSegmento.CURVA) s.setDistancia((float) (distTotal * 0.3 /curva));
            else s.setDistancia((float) (distTotal * 0.6 / chicane));
        }
    }
    
    public List<Float> constroiCircuito(){
        calculaSegmentos();
        return segmentosdepista.stream().map((s)->s.getDistancia()).collect(Collectors.toList());
    }
}
