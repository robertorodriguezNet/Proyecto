package dam.proyecto.activities.producto.classes;

import android.content.Context;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Dimension;
import androidx.annotation.Nullable;


import java.util.ArrayList;
import java.util.Collection;
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
     * @param set
     */
    private void init(@Nullable AttributeSet set) {

        marginLeft = 100;
        marginRight = 50;
        marginTop = 150;
        marginBottom = 350;

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
        canvas.drawLine(origenX, origenY, getMeasuredWidth() - marginRight, origenY, paint);
        canvas.drawLine(marginLeft, marginTop, origenX, origenY, paint);

        dibujarIntervalosX(canvas);
        dibujarPie(canvas);
        dibujarLeyendaX(canvas);
        dibujarLeyendaY(canvas);
    }

    /**
     * Dibuja la línea de intervalos del ejeX
     * @param canvas
     */
    private void dibujarIntervalosX( Canvas canvas ){

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(30);

        // Dividir el ancho entre los registros
        float ancho = getMeasuredWidth() - marginRight - marginLeft;
        float intervalo = (int) Math.floor(ancho / (datos.size() + 1));

        // Al intervalo hay que restarle los 5 de ancho de la marca
        float[] espacio = {5, (intervalo -5)};


        DashPathEffect estilo = new DashPathEffect( espacio, 1 );
        paint.setPathEffect( estilo );

        canvas.drawLine(origenX, origenY, getMeasuredWidth() - marginRight, origenY, paint);

    }

    /**
     * Dibuja una línea vertica para cada una de las fechas (registros).
     *
     * @param canvas el lienzo
     */
    private void dibujarVerticales(Canvas canvas) {

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(1);

        // Dividir el ancho entre los registros
        float ancho = getMeasuredWidth() - marginRight - marginLeft;
        float intervalo = (int) Math.floor(ancho / (datos.size() + 1));
        float pos = intervalo + marginLeft;

        float[] espacio = {5, 20};
        DashPathEffect estilo = new DashPathEffect(espacio, 1);
        paint.setPathEffect(estilo);

        for (GraficoData d : datos) {
            canvas.drawLine(pos, origenY, pos, marginTop, paint);
            pos += intervalo;
        }

    }


    /**
     * Método que dibuja el pie del gráfico
     *
     * @param canvas el lienzo
     */
    private void dibujarPie(Canvas canvas) {

        Paint paint = new Paint();
        paint.setTextSize(40);
        paint.setAntiAlias( true );
        paint.setTextAlign(Paint.Align.RIGHT);

        // Dividir el ancho entre los registros
        float ancho = getMeasuredWidth() - marginRight - marginLeft;
        float intervalo = (int) Math.floor(ancho / (datos.size() + 1));
        float pos = intervalo;
        for( GraficoData d : datos ){

            String fecha = Fecha.getFechaFormateada( d.getDataX() );
            canvas.save();
            canvas.rotate(-60, pos+100, origenY+50 );
            canvas.drawText( fecha, pos+100, origenY+50, paint);
            canvas.restore();

            pos += intervalo;
        }

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
        canvas.drawText( "FECHA", getMeasuredWidth() / 2, getMeasuredHeight() -50, leyenda);
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
     * Dibuja los puntos de la gráfica
     * @param canvas el lienzo
     */
    private void dibujarHorizontales( Canvas canvas ) {

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
        float topeAlto = precioAlto + ( precioAlto - precioBajo );
        float topeBajo = precioBajo - ( precioAlto - precioBajo );

        Paint paint = new Paint();
        paint.setTextSize(40);
        paint.setTextAlign( Paint.Align.LEFT);

        canvas.drawText( String.format("%.02f", topeAlto), 5, (marginTop + 20),  paint);
        canvas.drawText( String.format("%.02f", topeBajo), 5, origenY,  paint);

        dibujarPuntos( canvas, topeAlto, topeBajo );

    }

    /**
     * Dibuja los puntos de la gráfica
     * @param canvas el lienzo
     * @param topealto el precio más alto calculado
     * @param topeBajo el precio más bajo calculado
     */
    private void dibujarPuntos( Canvas canvas, float topealto, float topeBajo ) {

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);

        // Dividir el ancho entre los registros
        float ancho = getMeasuredWidth() - marginRight - marginLeft;
        float intervalo = (int) Math.floor(ancho / (datos.size() + 1));
        float pos = intervalo + marginLeft;

        float precio;
        for (GraficoData d : datos) {
            canvas.drawCircle( pos, 1000, 10, paint);
            pos += intervalo;
        }
    }

}
