package business;

public class SegmentoDePista {
    private int gdu;
    private float distancia;
    private TipoSegmento nome;


    public SegmentoDePista(int gdu, int distancia, TipoSegmento nome) {
        this.distancia = distancia;
        this.gdu = gdu;
        this.nome = nome;
    }

    public SegmentoDePista(SegmentoDePista s) {
        this.distancia = s.getDistancia();
        this.gdu = s.getGdu();
        this.nome = s.getNome();
    }

    public int getGdu() {
        return gdu;
    }

    public void setGdu(int gdu) {
        this.gdu = gdu;
    }

    public float getDistancia() {
        return distancia;
    }

    public void setDistancia(float distancia) {
        this.distancia = distancia;
    }

    public TipoSegmento getNome() {
        return nome;
    }

    public void setNome(TipoSegmento nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "SegmentoDePista{" +
                "gdu=" + gdu +
                ", distancia=" + distancia +
                ", nome=" + nome +
                '}';
    }
    public SegmentoDePista clone(){
        return new SegmentoDePista(this);
    }
}
