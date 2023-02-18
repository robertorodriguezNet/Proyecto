package dam.proyecto.database.data;

import android.content.Context;
import android.util.Log;

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

/**
 * Clase creada para cargar datos de ejemplo.
 */
public class Ejemplos {

    private static final String TAG = "BD";
    private static Context context;

    /**
     * Método que es llamado para insertar los datos de ejemplo.
     * Necesitamos saber si ya hay datos guardadados, si los hay,
     * no se guardan nuevamente
     */
    public static void cargarDatos(Context c) {

        Log.d(TAG, "Ejemplos.class: llamada a cargarDatos");
        context = c;

        cargarProductos();
        cargarNombreCompra();
        cargarTag();
        cargarComercio();
        cargarMedidas();
        cargarCompraEntitys();
        cargarMarcas();
        cargarTagsProductoEntity();
        cargarOfertaEntity();

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

}
