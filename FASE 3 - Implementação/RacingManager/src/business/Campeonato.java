package business;

import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

public class Campeonato {
    public String nomeCampeonato;
    public int corridaAtual;
    public List<Corrida> corridas;
    public Map<String,Participante> campParticipantes;

    public Compeonato(String nome,int corAtual,List<Corrida> cor, Map<String, Participante> participantes) {
        
    }
    
    public String getNomeCampeonato() {
        return nomeCampeonato;
    }Campeonato

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
        List<Corrida> r = new ArrayList<>();

        for (Corrida c : this.corridas){
            r.add(c.clone());
        }
        return r;
    }

    public void setCorridas(List<Corrida> corridas) {
        this.corridas = new ArrayList<>();
        for (Corrida c : corridas){
            this.corridas.add(c.clone());
        }
    }

    public void addCorrida(Corrida corrida){
        this.corridas.add(corrida.clone());
    }
    public Map<String, Participante> getCampParticipantes() {
        Map<String, Participante> res = new HashMap<String,Participante>();
        for(Participante p : this.campParticipantes.values()){
            res.put(p.getNome(),p.clone());
        }
        return res;
    }

    public void setCampParticipantes(Map<String, Participante> campParticipantes) {
        this.campParticipantes = new HashMap<String,Participante>();
        for(Participante p : campParticipantes.values()){
            this.campParticipantes.put(p.getNome(),p.clone());
        }
    }

    public void addParticipante(Participante participante){
        this.campParticipantes.put(participante.getNome(),participante.clone());
    }

    private void atualizarParticipantes(Corrida corrida){

    }

    private void simularCorrida(){
        Corrida c = this.corridas.get(corridaAtual);
        c.simularCorrida();
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
