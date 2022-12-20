package business;
public abstract class Carro {
    private String marca;
    private String modelo;
    private int celindrada;
    private int potencia;
    private float fiabilidade;
    private int pac;
    private String id;
    private TipoPneus pneus;
    private ModoMotor modoMotor;
    
    public Carro(String marca, String modelo, int celindrada, int potencia, float fiabilidade, int pac, String id) {
        this.marca = marca;
        this.modelo = modelo;
        this.celindrada = celindrada;
        this.potencia = potencia;
        this.fiabilidade = fiabilidade;
        this.pac = pac;
        this.id = id;
    }


    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getCelindrada() {
        return celindrada;
    }

    public void setCelindrada(int celindrada) {
        this.celindrada = celindrada;
    }

    public int getPotencia() {
        return potencia;
    }

    public void setPotencia(int potencia) {
        this.potencia = potencia;
    }

    public float getFiabilidade() {
        return fiabilidade;
    }

    public void setFiabilidade(float fiabilidade) {
        this.fiabilidade = fiabilidade;
    }

    public int getPac() {
        return pac;
    }

    public void setPac(int pac) {
        this.pac = pac;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPneus(TipoPneus pneus){
        this.pneus = pneus;
    }
    public TipoPneus getPneus(){
        return pneus;
    }

    public ModoMotor getMotor(){
        return this.modoMotor;
    }
    public void setMotor(ModoMotor modo){
        this.modoMotor = modo;
    }
    public abstract void calculaFiabilidade();
    public abstract void calculaPotencia();
    public void alteraafinacao(int pac, ModoMotor motor){
        setPac(pac);
        setMotor(motor);
    };

    @Override
    public String toString() {
        return "Carro{" +
                "marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", celindrada=" + celindrada +
                ", potencia=" + potencia +
                ", fiabilidade=" + fiabilidade +
                ", pac=" + pac +
                ", id='" + id + '\'' +
                '}';
    }
}