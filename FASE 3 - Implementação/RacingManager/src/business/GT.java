package business;

public class GT extends Carro{
    int potenciaHibrida;
    int taxaDeteorizacao;

    public GT() {
        super("GT","", "", 2000, 0, 0, 0, "0");
        this.potenciaHibrida = 0;
        this.taxaDeteorizacao = 0;
    }

    public GT(String marca, String modelo, int celindrada, int potencia, float fiabilidade, int pac, String id, int potenciaHibrida, int taxaDeteorizacao) {
        super("GT",marca, modelo, celindrada, potencia, fiabilidade, pac, id);
        this.potenciaHibrida=potenciaHibrida;
        this.taxaDeteorizacao=taxaDeteorizacao;
    }

    public GT(GT c) {
        super("GT",c.getMarca(), c.getModelo(), c.getCelindrada(), c.getPotencia(), c.getFiabilidade(), c.getPac(), c.getId());
        this.potenciaHibrida = c.getPotenciaHibrida();
        this.taxaDeteorizacao = c.getTaxaDeteorizacao();
    }

    public int getPotenciaHibrida() {
        return potenciaHibrida;
    }

    public void setPotenciaHibrida(int potenciaHibrida) {
        this.potenciaHibrida = potenciaHibrida;
    }

    public int getTaxaDeteorizacao() {
        return taxaDeteorizacao;
    }

    public void setTaxaDeteorizacao(int taxaDeteorizacao) {
        this.taxaDeteorizacao = taxaDeteorizacao;
    }

    @Override
    public void calculaFiabilidade() {
        setFiabilidade((float) (92-0.003*getCelindrada()));

    }

    @Override
    public void calculaPotencia() {
        setPotencia(getPotenciaHibrida()+getPotencia());
    }

    public GT clone(){
        return this.clone();
    }

}