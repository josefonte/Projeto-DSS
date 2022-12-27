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
    public Utilizador utilizador;

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

    public void addTempo(LocalTime tempo){
        List<LocalTime> res = this.getTempos();
        res.add(tempo);
        this.setTempos(res);
    }

    public void addPontuacao(int pont){
        int total = this.getPontuacao() + pont;
        this.setPontuacao(total);
    }

    public String getNome(){
        return this.getUtilizador().getNomeUtilizador();
    }
}
