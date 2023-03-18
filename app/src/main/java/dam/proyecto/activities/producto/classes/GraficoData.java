package dam.proyecto.activities.producto.classes;

/**
 * Representa los datos que se muestran en el gráfico
 * @author Roberto Rodríguez
 * @version 1.2023.02.18
 * @since 2023/02/17
 */
public class GraficoData {

    private String dataX;                                                // Datos del eje horizontal
    private String dataY;                                                  // Datos del eje vertical

    public GraficoData(String dataX, String dataY) {
        this.dataX = dataX;
        this.dataY = dataY;
    }

    public GraficoData(int dataX, int dataY ){
        this.dataX = String.valueOf( dataX );
        this.dataY = String.valueOf( dataY );
    }

    public GraficoData(String dataX, int dataY ){
        this.dataX = String.valueOf( dataX );
        this.dataY = String.valueOf( dataY );
    }

    public GraficoData(String dataX, float dataY ){
        this.dataX = dataX;
        this.dataY = String.valueOf( dataY );
    }

    public String getDataX() {
        return dataX;
    }

    public void setDataX(String dataX) {
        this.dataX = dataX;
    }

    public String getDataY() {
        return dataY;
    }

    public void setDataY(String dataY) {
        this.dataY = dataY;
    }
}
