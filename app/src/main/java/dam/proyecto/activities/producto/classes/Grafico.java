package dam.proyecto.activities.producto.classes;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * Esta clase dibuja el gráfico
 *
 * @author Roberto Rodríguez Jiménez
 * @since 17/03/2023
 * @version 2023.03.17
 */
public class Grafico extends View {


    public Grafico(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        @SuppressLint("DrawAllocation") Paint paint = new Paint();
        paint.setStrokeWidth( 10 );
        paint.setARGB(255,255,0,0);

        canvas.drawLine( 100, 100, 600, 800, paint);
    }

}
