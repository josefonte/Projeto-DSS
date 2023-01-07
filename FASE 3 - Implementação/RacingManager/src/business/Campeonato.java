package business;

import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

public class Campeonato {
    private String nomeCampeonato;
    private int corridaAtual;
    private List<Corrida> corridas;
    private Map<String,Participante> campParticipantes;

    public Campeonato(String nomeCampeonato, int corridaAtual, List<Corrida> corridas, Map<String, Participante> campParticipantes) {
        this.nomeCampeonato=nomeCampeonato;
        this.corridaAtual=corridaAtual;
        this.corridas=corridas;
        this.campParticipantes=campParticipantes;
    }
    
    public Campeonato(Campeonato c){
        this.setNomeCampeonato(c.getNomeCampeonato());
        this.setCorridaAtual(c.getCorridaAtual());
        this.setCorridas(c.getCorridas());
        this.setCampParticipantes(c.getCampParticipantes());
    }

    public String getNomeCampeonato() {
        return nomeCampeonato;
    }

    public void setNomeCampeonato(String nomeCampeonato) {
        this.nomeCampeonato = nomeCampeonato;
    }

    public int getCorridaAtual() {
        return corridaAtual;
    }

    public void setCorridaAtual(int corridaAtual) {
        this.corridaAtual = corridaAtual;
    }

    public List<Corrida> getCorridas() {
        return new ArrayList<Corrida>(corridas);
    }

    public void setCorridas(List<Corrida> corridas) {
        this.corridas = new ArrayList<Corrida>();
        for (Corrida c : corridas){
            this.corridas.add(c);
        }
    }

    public void addCorrida(Corrida corrida){
        ArrayList<Corrida> res = this.getCorridas();
        res.add(corrida);
        this.setCorridas(res);
    }
    public Map<String, Participante> getCampParticipantes() {
        Map<String, Participante> res = new HashMap<String,Participante>();
        for (Map.Entry<String,Participante> e : this.campParticipantes.entrySet()){
            res.put(e.getKey(),e.getValue().clone());
        }
        return res;
    }

    public void setCampParticipantes(Map<String, Participante> campParticipantes) {
        this.campParticipantes = new HashMap<String,Participante>();
        Iterator<Map.Entry<String,Participante>> it = campParticipantes.entrySet().iterator();
        while (it.hasNext()){
            Map.Entry<String,Participante> aux = it.next();
            this.campParticipantes.put(aux.getKey(),aux.getValue());
        }
    }

    public void addParticipante(Participante participante){
        Map<String, Participante> res = this.getCampParticipantes();
        res.put(participante.getUtilizador().getNomeUtilizador(),participante.clone());
        this.setCampParticipantes(res);
    }

    private void atualizarParticipantes(List<Participante> update){

    }

    public List<Participante> classificacaoFinal(){

    }

    private int calculaAfinacoes(){
        int s = this.getCorridas().size();
        int max = Math.round(s * (2/3));
        Map<String, Participante> old = this.getCampParticipantes();
        Map<String, Participante> res = new HashMap<String,Participante>();
        Iterator<Map.Entry<String,Participante>> it = old.entrySet().iterator();
        while (it.hasNext()){
            Map.Entry<String,Participante> aux = it.next();
            aux.getValue().setAfinacoesRestantes(max);
            res.put(aux.getKey(),aux.getValue());
        }
        this.setCampParticipantes(res);
        return max;
    }


    private void simularCampeonato(){

    }
}
