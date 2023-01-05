package business;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Participante {
    public int pontuacao;
    public List<LocalTime> tempos;
    public int afinacoesRestantes;
    public int voltasTotais;
    public int localizacaoPista;

    public Carro carro;
    public Utilizador utilizador;

    public Participante(Participante u){
        this.setPontuacao(u.getPontuacao());
        this.setTempos(u.getTempos());
        this.setAfinacoesRestantes(u.getAfinacoesRestantes());
        this.setVoltasTotais(u.getVoltasTotais());
        this.setLocalizacaoPista(u.getLocalizacaoPista());
        this.setCarro(u.getCarro());
        this.setUtilizador(u.getUtilizador());
    }

    public int getPontuacao() {
        return this.pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    public List<LocalTime> getTempos() {
        return new ArrayList<LocalTime>(tempos);
    }

    public void setTempos(List<LocalTime> tempos) {
        this.tempos = new ArrayList<LocalTime>();
        for (LocalTime t : tempos){
            this.tempos.add(t);
        }
    }

    public int getAfinacoesRestantes() {
        return afinacoesRestantes;
    }

    public void setAfinacoesRestantes(int afinacoesRestantes) {
        this.afinacoesRestantes = afinacoesRestantes;
    }

    public int getVoltasTotais() {
        return voltasTotais;
    }

    public Carro getCarro() {
        return this.carro.clone();
    }

    public void setCarro(Carro carro) {
        this.carro = carro.clone();
    }

    public void setVoltasTotais(int voltasTotais) {
        this.voltasTotais = voltasTotais;
    }

    public int getLocalizacaoPista() {
        return localizacaoPista;
    }

    public void setLocalizacaoPista(int localizacaoPista) {
        this.localizacaoPista = localizacaoPista;
    }

    public Utilizador getUtilizador(){
        return this.utilizador.clone();
    }

    public void setUtilizador(Utilizador u){
        this.utilizador = u.clone();
    }

    public void addTempo(LocalTime tempo){
        List<LocalTime> res = this.getTempos();
        res.add(tempo);
        this.setTempos(res);
    }

    public void addPontuacao(int pont){
        int total = this.getPontuacao() + pont;
        this.setPontuacao(total);
    }
    
    public LocalTime tempoTotal(){
        LocalTime r = LocalTime.of(0,0,0);
        List<LocalTime> list = this.getTempos();
        for(LocalTime lt: list){
            r.plusHours(lt.getHour()).plusMinutes(lt.getMinute()).plusSeconds(lt.getSecond());
        }
        return r;
    }

    public String getNome(){
        return this.getUtilizador().getNomeUtilizador();
    }

    public Participante clone(){
        return new Participante(this);
    }
}
