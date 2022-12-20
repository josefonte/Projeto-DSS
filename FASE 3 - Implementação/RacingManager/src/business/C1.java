package business;

public class C1 extends Carro{
    private int potenciaHibrida;

    public C1(String marca, String modelo, int celindrada, int potencia, float fiabilidade, int pac, String id, int potenciaHibrida) {
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
        setFiabilidade(95);

    }
    @Override
    public void calculaPotencia() {
        setPotencia(getPotenciaHibrida()+getPotencia());
    }

}
