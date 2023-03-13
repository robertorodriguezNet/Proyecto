package dam.proyecto.utilities;

import java.text.Normalizer;
import java.util.Locale;

/**
 * Clase que proporciona métodos para trabajar con palabras
 * @author Roberto Rodríguez Jiménez
 * @since 13/03/2023
 * @version 2023.03.13
 */
public class Words {

    /**
     * Devuelve una palabra en minúscula y sin tildes
     * @param palabra que hay que normalizar
     * @return la palabra normalizada
     */
    public static String normalizar( String palabra ){
        return destildar( palabra.toLowerCase().trim() );
    }

    /**
     * Devuelve una palabra sin tildes
     * @param palabra que hay que destildar
     * @return la palabra sin la tilde
     */
    public static String destildar( String palabra ){
        String normal =null;
        if (palabra !=null) {
            String valor = palabra;
            valor = valor.toUpperCase();
            // Normalizar texto para eliminar acentos, dieresis, cedillas y tildes
            normal = Normalizer.normalize(valor, Normalizer.Form.NFD);
            // Quitar caracteres no ASCII excepto la enie, interrogacion que abre, exclamacion que abre, grados, U con dieresis.
            normal = normal.replaceAll("[^\\p{ASCII}(N\u0303)(n\u0303)(\u00A1)(\u00BF)(\u00B0)(U\u0308)(u\u0308)]", "");
            // Regresar a la forma compuesta, para poder comparar la enie con la tabla de valores
            normal = Normalizer.normalize(normal, Normalizer.Form.NFC);
        }
        return normal;
    }

}
