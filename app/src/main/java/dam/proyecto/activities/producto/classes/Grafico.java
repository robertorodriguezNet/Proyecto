package dam.proyecto.activities.producto.classes;

import android.annotation.SuppressLint;
import android.content.Context;

import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collections;

import dam.proyecto.utilities.Fecha;

/**
 * @author Roberto Rodríguez
 * @version 1.2023.02.18
 * @since 2023/02/17
 */
public class Grafico extends View {

    private ArrayList<GraficoData> datos;

    // Coordenadas y espacios
    private int marginLeft,
            marginRight,
            marginTop,
            marginBottom;
    private float origenX,
            origenY;

    // Guardan los valores del punto anterior para poder dibujar las
    // líneas del gráfico
    private float anteriorX,
            anteriorY;

    public Grafico(Context context) {
        super(context);
        init(null);
    }

    public Grafico(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public Grafico(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public Grafico(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    /**
     * Iniciamos los valores
     *
     * @param set atributos del componente
     */
    private void init(@Nullable AttributeSet set) {

        marginLeft = 100;
        marginRight = 50;
        marginTop = 150;
        marginBottom = 350;

    }

    /**
     * Dibuja la base del gráfico
     *
     * @param canvas el lienzo
     */
    private void dibujarBase(Canvas canvas) {

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(8);

        // Ejes ------------------------------------------------------------------------------------
        // Horizontal
        canvas.drawLine(origenX, origenY, getMeasuredWidth() - marginRight, origenY, paint);
        // Vertical
        canvas.drawLine(marginLeft, marginTop, origenX, origenY, paint);

        dibujarIntervalosX(canvas);
        dibujarPie(canvas);
        dibujarLeyendaX(canvas);
        dibujarLeyendaY(canvas);
    }



    /**
     * Dibuja los puntos de la gráfica
     * @param canvas el lienzo
     */
    @SuppressLint("DefaultLocale")
    private void dibujarHorizontales(Canvas canvas ) {

        // Inicializamos los precios
        float precioAlto = 0.0f;
        float precioBajo = precioAlto;

        // Buscar el precio más alto y el más bajo
        for (GraficoData d : datos) {

            float precio = Float.parseFloat( d.getDataY() );

            if( precio >= precioAlto ){
                precioAlto = precio;
            }

            if( (precio <= precioBajo) || (precioBajo == 0) ){
                precioBajo = precio;
            }

        }

        // Los precios tope se obtienen sumando o restando al alto
        // y bajo la diferencia entre ellos
        float topeAlto = precioAlto;
        float topeBajo = precioBajo;

        dibujarPuntos( canvas, topeAlto, topeBajo );

    }


    /**
     * Dibuja la línea de intervalos del ejeX
     * @param canvas lienzo
     */
    private void dibujarIntervalosX( Canvas canvas ){

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(30);

        // Dividir el ancho entre los registros
        float ancho = getMeasuredWidth() - marginRight - marginLeft;
        float intervalo = (int) Math.floor(ancho / (datos.size() - 1));

        // Al intervalo hay que restarle los 5 de ancho de la marca
        float[] espacio = {5, (intervalo -5)};


        DashPathEffect estilo = new DashPathEffect( espacio, 1 );
        paint.setPathEffect( estilo );

        canvas.drawLine(origenX, origenY, getMeasuredWidth() - marginRight, origenY, paint);

    }


    /**
     * Dibuja la leyenda del eje X
     * @param canvas el lienzo
     */
    private void dibujarLeyendaX( Canvas canvas ){
        // Leyenda x
        Paint leyenda = new Paint();
        leyenda.setTextSize(48);
        leyenda.setTextAlign(Paint.Align.CENTER);
        leyenda.setAntiAlias( true );
        canvas.drawText( "FECHA", (float) getMeasuredWidth() / 2, getMeasuredHeight() -50, leyenda);
    }

    /**
     * Dibuja la leyenda del eje Y
     * @param canvas el lienzo
     */
    private void dibujarLeyendaY( Canvas canvas ){
        // Leyenda x
        Paint leyenda = new Paint();
        leyenda.setTextSize(48);
        leyenda.setTextAlign(Paint.Align.LEFT);
        leyenda.setAntiAlias( true );
        canvas.drawText( "PRECIO (€)", 0, 70, leyenda);
    }



    /**
     * Método que dibuja el pie del gráfico
     *
     * @param canvas el lienzo
     */
    private void dibujarPie(Canvas canvas) {

        Paint paint = new Paint();
        paint.setTextSize(36);
        paint.setAntiAlias( true );
        paint.setTextAlign(Paint.Align.RIGHT);

        // Dividir el ancho entre los registros
        float ancho = getMeasuredWidth() - marginRight - marginLeft;
        float intervalo = (int) Math.floor(ancho / (datos.size() - 1));
//        float pos = intervalo;
        float pos = origenX;
        for( GraficoData d : datos ){

            String fecha = Fecha.getFechaFormateada( d.getDataX() );
            canvas.save();
            canvas.rotate(-90, pos+18, origenY+50 );
            canvas.drawText( fecha, pos+18, origenY+50, paint);
            canvas.restore();

            pos += intervalo;
        }

    }

    /**
     * Dibuja los puntos de la gráfica
     * @param canvas el lienzo
     */
    private void dibujarPuntos( Canvas canvas, float topeAlto, float topeBajo ) {

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);

        // Dividir el ancho entre los registros
        float ancho = getMeasuredWidth() - marginRight - marginLeft;
        float intervalo = (int) Math.floor(ancho / (datos.size() -1));
        float x = origenX;

        for (GraficoData d : datos) {
            float y = getPosY( Float.parseFloat( d.getDataY() ), topeAlto, topeBajo );

            canvas.drawCircle( x, y, 10, paint); // Dibujamos el punto

            // Dibujar el precio
            canvas.drawText( d.getDataY(), 0, y, getPaintPrecio());

            // Dibujamos la línea horizontal
            canvas.drawLine( origenX,
                    y,
                    getMeasuredWidth() - marginRight,
                    y,
                    getPaintPunteado() );

            if( datos.indexOf(d) > 0 ){
                canvas.drawLine(anteriorX,anteriorY,x,y,paint);
            }

            // Guardar los nuevo valores
            anteriorX = x;
            anteriorY = y;

            x += intervalo;
        }

    }


    /**
     * Dibuja una línea vertica para cada una de las fechas (registros).
     *
     * @param canvas el lienzo
     */
    private void dibujarVerticales(Canvas canvas) {

        // Dividir el ancho entre los registros
        float ancho = getMeasuredWidth() - marginRight - marginLeft;
        float intervalo = (int) Math.floor(ancho / (datos.size() - 1));
        float pos = origenX;

        for (GraficoData d : datos) {
            canvas.drawLine(pos, origenY, pos, marginTop, getPaintPunteado());
            pos += intervalo;
        }

    }

    /**
     *
     */
    private Paint getPaintPrecio(){

        Paint paint = new Paint();
        paint.setTextSize(40);
        paint.setAntiAlias( true );

        return paint;

    }

    /**
     * Devuelve el paint para dibujar las líneas de puntos
     * @return el paint creado
     */
    private Paint getPaintPunteado(){

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(1);

        float[] espacio = {5, 20};
        DashPathEffect estilo = new DashPathEffect(espacio, 1);
        paint.setPathEffect(estilo);

        return paint;

    }


    /**
     * Devuelve la posición y de cada punto
     * @param precio del proucto
     * @param topeAlto el precio más alto calculado
     * @param topeBajo el precio más bajo calculado
     * @return la posición y
     */
    private float getPosY( float precio, float topeAlto, float topeBajo ){

        // topeAlto se dibuja en margonTop + 20
        // topeBajo se dibuja en origenY

        float rango = origenY - (marginTop -20);            // Rango de valores válidos para dibujar
        float centimos = (topeAlto - topeBajo)*100;      // Céntimos de diferencia entre el máx y y el min
        float paso = rango / centimos;                             // Valor para cada céntimo

        // Calcular Y
        // La diferencia entre el mínimo y el precio
        float dif = precio - topeBajo;
        return origenY - ( (dif*100) * paso );
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        origenX = marginLeft;
        origenY = getMeasuredHeight() - marginBottom;

//        canvas.drawColor( Color.GRAY);

        dibujarBase(canvas);
        dibujarVerticales(canvas);
        dibujarHorizontales(canvas);

    }

    /**
     * Método público para recibir los datos
     *
     * @param datos los datos
     */
    public void setDatos(ArrayList<GraficoData> datos) {

        // Los datos vienen en orden descendente, es decir,
        // las compras más nuevas, las primeras, pero necesitamos
        // leerlas al revés.
        this.datos = datos;
        Collections.reverse( this.datos);
    }

}
