package dam.proyecto.activities.producto.classes;

import android.content.Context;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

import dam.proyecto.R;
import dam.proyecto.database.entity.CompraEntity;

public class Grafico extends View {

    private ArrayList<CompraEntity> datos;

    public Grafico(Context context) {
        super(context);
        init(null);
    }

    public Grafico(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init( attrs );
    }

    public Grafico(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init( attrs );
    }

    public Grafico(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init( attrs );
    }

    private void init(@Nullable AttributeSet set ){

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();
        paint.setColor(ContextCompat.getColor( getContext(), R.color.Gris ));
        paint.setStrokeWidth(10);
        paint.setStyle( Paint.Style.STROKE);        // Estilo: fill:relleno, stroke:contorno

        canvas.drawLine( 50, 800, 800, 800, paint);

        paint.setColor(ContextCompat.getColor( getContext(), R.color.black ));
        paint.setTextSize(48);
        paint.setStyle( Paint.Style.FILL);        // Estilo: fill:relleno, stroke:contorno

        canvas.drawText("Registros: " + datos.size(), 100, 200, paint);

    }

    public void setDatos( ArrayList<CompraEntity> datos ){
        this.datos = datos;
    }

}
