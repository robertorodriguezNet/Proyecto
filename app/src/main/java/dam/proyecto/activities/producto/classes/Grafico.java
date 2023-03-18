package dam.proyecto.activities.producto.classes;

import android.content.Context;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;


import java.util.ArrayList;

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
            marginBottom,
            ancho,
            alto;

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

    private void init(@Nullable AttributeSet set) {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.GRAY);

        dibujarBase(canvas);

    }

    /**
     * Método público para recibir los datos
     *
     * @param datos los datos
     */
    public void setDatos(ArrayList<GraficoData> datos) {
        this.datos = datos;
    }

    /**
     * Dibuja la base del gráfico
     */
    private void dibujarBase(Canvas canvas) {

        marginLeft = 70;
        marginRight = 50;
        marginTop = 50;
        marginBottom = 200;
        ancho = getMeasuredWidth() - marginLeft - marginRight;
        alto = getMeasuredHeight() - marginTop - marginBottom;

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(8);

        // Ejes ------------------------------------------------------------------------------------
        Path ejeX = new Path();
        ejeX.moveTo(0, 0);
        ejeX.lineTo(ancho, 1);
        ejeX.offset(marginLeft, alto);
        canvas.drawPath(ejeX, paint);

        Path ejeY = new Path();
        ejeY.moveTo(0, 0);
        ejeY.lineTo(1,
                alto - marginTop);
        ejeY.offset(marginLeft, marginTop);
        canvas.drawPath(ejeY, paint);

        Path divisorX = new Path();
        divisorX.moveTo(0, 0);
        divisorX.lineTo(ancho, 1);
        float[] espcioX = intervalosX(ancho);
        DashPathEffect estiloX = new DashPathEffect(espcioX, 1);
        paint.setPathEffect(estiloX);
        paint.setStrokeWidth(32);
        divisorX.offset(marginLeft, alto);
        canvas.drawPath(divisorX, paint);

        dibujarPie(canvas);


    }

    /**
     * Dibuja el eje X con las divisiones
     *
     * @param ancho ancho del eje
     * @return array con los intervalos
     */
    private float[] intervalosX(int ancho) {


        // Dividir el ancho entre los registros
        int intervalos = (int) Math.floor(ancho / (datos.size()+1));

        // {intervalo de marca, intervalo blanco}
        return new float[]{5, intervalos};
    }

    /**
     * Método que dibuja el pie del gráfico
     *
     * @param canvas el lienzo
     */
    private void dibujarPie(Canvas canvas) {

        Paint paint = new Paint();
        paint.setTextSize(40);

//        canvas.save();
//        canvas.rotate(90, 100, 100  );
//        canvas.drawText("texto vertical", 100,100 , paint);
//        canvas.restore();
        // Dividir el ancho entre los registros
        int intervalo = (int) Math.floor(ancho / (datos.size()+1) );
        int pos = intervalo;
        for( GraficoData d : datos ){

            canvas.save();
            canvas.rotate(90, pos+40, alto+10 );
            canvas.drawText(d.getDataX(), pos+40, alto+10, paint);
            canvas.restore();

            pos += intervalo;
        }

    }

}
