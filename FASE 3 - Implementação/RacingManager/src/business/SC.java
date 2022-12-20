package business;

public class SC extends Carro{

    public SC(String marca, String modelo, int celindrada, int potencia, float fiabilidade, int pac, String id) {
        super(marca, modelo, celindrada, potencia, fiabilidade, pac, id);
    }

    @Override
    public void calculaFiabilidade() {
        setFiabilidade((float) (75+0.006*getCelindrada()));

    }

    @Override
    public void calculaPotencia() {
        //não faz nada, que merda e esta
    }
}
