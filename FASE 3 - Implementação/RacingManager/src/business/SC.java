package business;

public class SC extends Carro{

    public SC() {
        super("SC","", "", 2500, 0, 0, 0, "0");
    }

    public SC(String marca, String modelo, int celindrada, int potencia, float fiabilidade, int pac, String id) {
        super("SC",marca, modelo, celindrada, potencia, fiabilidade, pac, id);
    }

    public SC(SC c) {
        super("SC",c.getMarca(), c.getModelo(), c.getCelindrada(), c.getPotencia(), c.getFiabilidade(), c.getPac(), c.getId());
    }

    @Override
    public void calculaFiabilidade() {
        setFiabilidade((float) (75+0.006*getCelindrada()));

    }

    @Override
    public void calculaPotencia(){}
    
    public SC clone(){
        return new SC(this);
    }
}
