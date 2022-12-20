package business;

public class C2 extends Carro{
    private int potenciaHibrida;
    public C2(String marca, String modelo, int celindrada, int potencia, float fiabilidade, int pac, String id, int potenciaHibrida) {
        super(marca, modelo, celindrada, potencia, fiabilidade, pac, id);
        this.potenciaHibrida = potenciaHibrida;
    }

    public int getPotenciaHibrida() {
        return potenciaHibrida;
    }

    public void setPotenciaHibrida(int potenciaHibrida) {
        this.potenciaHibrida = potenciaHibrida;
    }

    @Override
    public void calculaFiabilidade() {
        setFiabilidade((float) (80+0.001*getCelindrada()));
    }

    @Override
    public void calculaPotencia() {
        setPotencia(getPotenciaHibrida()+getPotencia());
    }
}
