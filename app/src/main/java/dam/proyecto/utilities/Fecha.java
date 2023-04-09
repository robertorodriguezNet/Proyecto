package dam.proyecto.utilities;

import android.content.Context;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Fecha {

    /**
     * Devuelve la fecha normalizada
     * @param f está en formato yyMMddhhmm
     * @return dd-mm-aaaaaa hh:mm
     */
    public static String getFecha( String f ) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat( "dd-MM-yyyy" );

        String yy = "20" + f.substring(0,2);
        String MM = f.substring(2,4);
        String dd = f.substring(4,6);
        String hh = f.substring(6,8);
        String mm = f.substring(8,10);

        String strDate = dd + "-" + MM + "-" + yy;
        Date date = new Date( sdf.parse(strDate).getTime() );

        String strDia = (String) android.text.format.DateFormat.format( "EEEE", date);
        String strFecha = DateFormat.getDateInstance().format( date );

        return strDia + "," + strFecha + " " + hh + ":" + mm;
    }


    /**
     * Obtener el formato correctode la fecha.
     * @param f la fecha con formato aammddhhmm
     * @return dd - mes(3 carac.) - aaaa
     */
    public static String getFechaFormateada( String f ){

        try{
            String fecha = Fecha.getFecha( f );

            // Eliminamos el día
            String[] data = fecha.split(",");
            fecha = data[1];

            // Eliminamos la hora
            data = fecha.split(" ");
            fecha = data[0] + " " + data[1] + " " + data[2];

            return fecha;
        }catch ( Exception e){
            return f;
        }
    }

    /**
     * Esta función sobrecarga getFechaFormateada para permitir devolver
     * el formato del año en dos dígitos
     * @param f la fecha en formato aammddhhmm
     * @param corta true si queremos el año en dos dígitos
     * @return la fehca con formato dd - mes(3 carac.) - [aaaa | aa]
     */
    public static String getFechaFormateada( String f, boolean corta ){
        String fecha = getFechaFormateada(f);
        if(corta){
            String nuevaFecha = fecha.substring(0, fecha.length() -4);
            String anho = fecha.substring(fecha.length()-2);
            fecha = nuevaFecha + anho;
        }
        return fecha;
    }

    /**
     * valida una fecha con formato dd/mm/aaaa
     * @param dd día
     * @param mm mes
     * @param yyyy ano con 4 dígitos
     * @return true si es válida la fecha
     */
    public static boolean isValidaFecha( String dd, String mm, String yyyy ){

        try{

            // Formato de fecha (dia/mes/año)
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            sdf.setLenient(false);

            sdf.parse( dd + "/" + mm + "/" + yyyy);

            // Si la comprobación no lanza error, se duvuelve true
            return true;

        }catch (Exception e){
            return false;
        }

    }

    /**
     * Devuelve una cadena de texto con el nombre que se le asignará por
     * defecto a una nueva compra.
     * @return una cadena de texto con formato yyMMddhhmm
     */
    public static String getNuevaFecha(){

        String formato = "yyMMddHHmm";
        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat( formato );

        return sdf.format( date );

    }

    /**
     * Devuelve una cadena de texto con el nombre que se le asignará por
     * defecto a una nueva compra.
     * Este método incrementa en 1 el valor de la fecha.
     * @id es la fehca que hay que incrementar
     * @return una cadena de texto con formato yyMMddhhmm
     */
    public static String getNuevaFecha( String id ){

        int m = Integer.parseInt( id.substring(8,10));
        m++;

        return id.substring(0,8) + String.format("%02d", Integer.parseInt(String.valueOf( m )));

    }
}
