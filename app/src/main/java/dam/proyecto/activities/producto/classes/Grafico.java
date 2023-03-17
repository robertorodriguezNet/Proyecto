package dam.proyecto.activities.producto.classes;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

import androidx.core.content.ContextCompat;

import java.util.ArrayList;

import dam.proyecto.R;
import dam.proyecto.database.entity.CompraEntity;

/**
 * Clase para dibujar los gráficos.
 *
 * @author Roberto Rodríguez Jiménez
 * @since 17/03/2023
 * @version 2023.03.17
 */
@SuppressLint("ViewConstructor")
public class Grafico extends View {

    private final ArrayList<CompraEntity> DATOS;

    public Grafico(ArrayList<CompraEntity> datos, Context context) {
        super(context);

        this.DATOS = datos;

    }

    /**
     * Método necesario para crear la vista propia.
     * Se encarga de pintar en el canvas.
     *
     * @param canvas objeto que sirve como lienzo.
     */
    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);

        dibujarTitulo( canvas );

        dibujarEjeX( canvas );

        dibujarTrazo( canvas );


    }

    /**
     * Dibuja el ejeX
     * @param canvas
     */
    private void dibujarEjeX(Canvas canvas) {

        Paint paint = new Paint();
        paint.setColor(ContextCompat.getColor( getContext(), R.color.Gris ));
        paint.setStrokeWidth(10);
        paint.setStyle( Paint.Style.STROKE);        // Estilo: fill:relleno, stroke:contorno

        canvas.drawLine( 50, 800, 800, 800, paint);

    }

    /**
     * Dibuja el trazo según los datos
     * @param canvas el lienzo en el que se dibuja
     */
    private void dibujarTrazo(Canvas canvas) {

        Paint paint = new Paint();
        paint.setColor(ContextCompat.getColor( getContext(), R.color.black ));
        paint.setStrokeWidth(10);
        paint.setStyle( Paint.Style.STROKE);        // Estilo: fill:relleno, stroke:contorno

//        canvas.drawLine( 200,200,250,150,paint);
//        canvas.drawLine( 250,150,300,175,paint);

    }

    /**
     * Dibuja el título
     * @param canvas lienzo en el que se dibuja
     */
    private void dibujarTitulo( Canvas canvas ){

        Paint paint = new Paint();
        paint.setColor(ContextCompat.getColor( getContext(), R.color.black ));
        paint.setTextSize(48);
        paint.setStyle( Paint.Style.FILL);        // Estilo: fill:relleno, stroke:contorno

        canvas.drawText("Número de registros: " + DATOS.size(), 100, 200, paint);
    }
}
