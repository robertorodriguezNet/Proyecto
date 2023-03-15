package dam.proyecto.utilities;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Preferencias {

    // Lista que se está editando en ListaActicvity
    private static final String LISTA_ABIERTA_ID = "listadelacompraabierta_id";

    // Guarda si ya hay datos de ejemplo guardados
    private static final String DATOS_CARGADOS = "datos_cargados";     // 0: false; 1: true

    private static SharedPreferences sp( Activity activity ){
        return activity.getPreferences( Context.MODE_PRIVATE );
    }

    // -- GETTERS ----------------------------------------------------------------------------------
    public static String getListaAbiertaId(Context context ){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences( context );
        return  preferences.getString( LISTA_ABIERTA_ID, null );
    }

    public static boolean isDatosCargados( Activity activity ){
        return sp(activity).getInt(DATOS_CARGADOS, 0) != 0;
    }

    // -- SETTERS ----------------------------------------------------------------------------------
    public static void setListaAbiertaId( String id, Context context ){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences( context );
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString( LISTA_ABIERTA_ID, id);
        editor.apply();
    }

    public static void setDatosCargados( boolean cargados, Activity activity ){
        SharedPreferences.Editor editor = sp( activity ).edit();
        editor.putInt(DATOS_CARGADOS, (cargados) ? 1 : 0 ).apply();
    }

    // -- REMOVE -----------------------------------------------------------------------------------
    // -- Borrar LISTA_ABIERTA_ID
    public static void removeListaAbiertaId( Context context ){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences( context );
        preferences.edit().remove( LISTA_ABIERTA_ID).apply();
    }

    // -- Borrar las prepreferencias
    public static void clear( Activity activity ){
        SharedPreferences.Editor editor = sp( activity ).edit();
        editor.clear().apply();

        // Indicamos que tenemos los datos cargados
        setDatosCargados( true, activity );
    }





}
