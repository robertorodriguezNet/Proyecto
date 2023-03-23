package dam.proyecto.activities.lista.clases;

public class ComercioDiferente {

    private String comercio;
    private float total;
    private int articulos;
    private String desde;
    private String hasta;

    public ComercioDiferente(String comercio, float total, int articulos, String desde, String hasta) {
        this.comercio = comercio;
        this.total = total;
        this.articulos = articulos;
        this.desde = desde;
        this.hasta = hasta;
    }

    public ComercioDiferente() {
    }

    public String getComercio() {
        return comercio;
    }

    public void setComercio(String comercio) {
        this.comercio = comercio;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public int getArticulos() {
        return articulos;
    }

    public void setArticulos(int articulos) {
        this.articulos = articulos;
    }

    public String getDesde() {
        return desde;
    }

    public void setDesde(String desde) {
        this.desde = desde;
    }

    public String getHasta() {
        return hasta;
    }

    public void setHasta(String hasta) {
        this.hasta = hasta;
    }

    @Override
    public String toString() {
        return "ComercioDiferente{" +
                "comercio='" + comercio + '\'' +
                ", total=" + total +
                ", articulos=" + articulos +
                ", desde='" + desde + '\'' +
                ", hasta='" + hasta + '\'' +
                '}';
    }
}
