package dam.proyecto.activities.lista.clases;

import java.util.ArrayList;

import dam.proyecto.database.relaciones.VistaCompra;

public class ComercioDiferente {

    private String comercio;
    private float total;
    private int articulos;
    private String desde;
    private String hasta;

    private ArrayList<VistaCompra> listaDeProductos;

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

    public ArrayList<VistaCompra> getListaDeProductos() {
        return listaDeProductos;
    }

    public void setListaDeProductos(ArrayList<VistaCompra> listaDeProductos) {
        this.listaDeProductos = listaDeProductos;
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
