package business;

public class Piloto {
    private String nome;
    private int sva;
    private int cts;

    public Piloto(String nome, int sva, int cts) {
        this.nome = nome;
        this.sva = sva;
        this.cts = cts;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getSva() {
        return sva;
    }

    public void setSva(int sva) {
        this.sva = sva;
    }

    public int getCts() {
        return cts;
    }

    public void setCts(int cts) {
        this.cts = cts;
    }
}
