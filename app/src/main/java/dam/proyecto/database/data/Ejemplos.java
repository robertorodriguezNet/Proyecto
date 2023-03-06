package dam.proyecto.database.data;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import dam.proyecto.activities.MainActivity;
import dam.proyecto.controllers.MarcaBlancaController;
import dam.proyecto.database.AppDatabase;
import dam.proyecto.database.entity.CompraEntity;
import dam.proyecto.database.repositories.ComercioRespository;
import dam.proyecto.database.repositories.CompraRepository;
import dam.proyecto.database.repositories.MarcaRepository;
import dam.proyecto.database.repositories.MedidaRepository;
import dam.proyecto.database.repositories.NombreCompraRepository;
import dam.proyecto.database.repositories.OfertaRespository;
import dam.proyecto.database.repositories.ProductoRepository;
import dam.proyecto.database.repositories.TagProductoRepository;
import dam.proyecto.database.repositories.TagRepository;
import dam.proyecto.utilities.Preferencias;

/**
 * Clase creada para cargar datos de ejemplo.
 */
public class Ejemplos {

    private static final String TAG = "BD";
    private static Context context;

    /**
     * Método que es llamado para insertar los datos de ejemplo.
     * Necesitamos saber si ya hay datos guardadados, si los hay,
     * no se guardan nuevamente.
     * @param c es el contexto, necesario para la conexión a la BD
     * @param activity es necesario para obtener las SharedPreferences de la actividad
     */
    public static void cargarDatos(Context c, Activity activity) {

        context = c;

        if (!Preferencias.isDatosCargados(  activity )) {
            Log.d(TAG, "Ejemplos.class: llamada a cargarDatos");

            cargarProductos();
            cargarNombreCompra();
            cargarTag();
            cargarComercio();
            cargarMedidas();
            cargarCompraEntitys();
            cargarMarcas();
            cargarMarcaBlanca();
            cargarTagsProductoEntity();
            cargarOfertaEntity();

            Preferencias.setDatosCargados(true, activity );
        }
    }

    private static void cargarProductos() {
        ProductoRepository repository = new ProductoRepository(context);
        repository.clear();
        repository.insertAll(ProductoData.getData());
    }

    private static void cargarNombreCompra() {
        NombreCompraRepository repository = new NombreCompraRepository(context);
        repository.clear();
        repository.insertAll(NombreCompraData.getData());
    }

    private static void cargarTag() {
        TagRepository repository = new TagRepository(context);
        repository.clear();
        repository.insertAll(TagData.getData());
    }

    private static void cargarComercio() {
        ComercioRespository repository = new ComercioRespository(context);
        repository.clear();
        repository.insertAll(ComercioData.getData());
    }

    private static void cargarMedidas() {
        MedidaRepository repository = new MedidaRepository(context);
        repository.clear();
        repository.insertAll(MedidaData.getData());
    }

    private static void cargarCompraEntitys() {
        CompraRepository repository = new CompraRepository(context);
        repository.clear();
        repository.insertAll(CompraEntityData.getData());
    }

    private static void cargarMarcas() {
        MarcaRepository repository = new MarcaRepository(context);
        repository.clear();
        repository.insertAll(MarcaData.getData());
    }


    private static void cargarTagsProductoEntity() {
        TagProductoRepository repository = new TagProductoRepository(context);
        repository.clear();
        repository.insertAll(TagProductoData.getData());
    }

    private static void cargarOfertaEntity() {
        OfertaRespository repository = new OfertaRespository(context);
        repository.clear();
        repository.insertAll(OfertaData.getData());
    }

    private static void cargarMarcaBlanca() {
        MarcaBlancaController controller = new MarcaBlancaController(context);
        controller.clear();
        controller.insertAll(MarcaBlancaData.getData());
    }
}
