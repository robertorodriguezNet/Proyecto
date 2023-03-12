package dam.proyecto;


/**
 * Clase para definir algunos datos globales de la aplicación.
 *
 * @author Roberto Rodríguez
 * @version 1.2023.02.06
 * @since 2023/02/06
 */
public class Config {

    // Longitud mínima de la denominación del producto
    public static final int PRODUCTO_DENOMINACION_MIN_LENGTH = 3;

    // Longitud del código de barras
    public static final int[] CODIGO_DE_BARRAS_LENGTH = {13,8};

    // Errores al crear un producto
    public static final String[] ERROR_VALIDAR_PRODUCTO = {
            "El Código de barras ya existe",
            "La longitud del código de barras debe ser de " +
                    CODIGO_DE_BARRAS_LENGTH +
                    " carcteres",
            "El código de barras no puede comenzar con 0",
            "La descripción no puede estar en blanco",
            "La longitud de la descripción debe contener, al menos, "
                    + PRODUCTO_DENOMINACION_MIN_LENGTH
                    + " caracteres",
    };


    // Errores al crear un comercio
    public static final String[] ERROR_CREAR_COMERCIO = {
            "La longitud del nombre no es correcta",
            "El nombre del comercio ya existe"
    };

    // Botonera de Editar producto (0 a 1)
    public static final float BOTON_DESACTIVADO_ALPHA = 0.3f;

    // Buscar productos
    // Caracteres mínimos para iniciar una búsqueda, a parte de 0
    public final static int CARACTERES_MINIMOS_PARA_BUSCAR = 3;

    // Ruta hacia las miniaturas de los productos
    public final static String PATH_PRODUCTS_THUMB = "https://robertorodriguez.webcindario.com/ldlc/thumbs/";
}
