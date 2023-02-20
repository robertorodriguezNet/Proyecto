package dam.proyecto.utilities;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class Preferencias {

    // Guarda si ya hay datos de ejemplo guardados
    private static final String DATOS_CARGADOS = "datos_cargados";     // 0: false; 1: true

    private static SharedPreferences sp( Activity activity ){
        return activity.getPreferences( Context.MODE_PRIVATE );
    }

    // -- GETTERS ----------------------------------------------------------------------------------

    public static boolean isDatosCargados( Activity activity ){
        return  (sp( activity ).getInt( DATOS_CARGADOS, 0 ) == 0 ) ? false : true;
    }

    // -- SETTERS ----------------------------------------------------------------------------------

    public static void setDatosCargados( boolean cargados, Activity activity ){
        SharedPreferences.Editor editor = sp( activity ).edit();
        editor.putInt(DATOS_CARGADOS, (cargados) ? 1 : 0 ).commit();
    }

}
