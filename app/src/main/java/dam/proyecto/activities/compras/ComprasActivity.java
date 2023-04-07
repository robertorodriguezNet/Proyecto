package dam.proyecto.activities.compras;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import dam.proyecto.R;
import dam.proyecto.activities.MainActivity;
import dam.proyecto.activities.almacen.AlmacenActivity;
import dam.proyecto.activities.compras.adapters.AdaptadorCompras;
import dam.proyecto.activities.compras.listener.ListenerCompras;
import dam.proyecto.activities.lista.ListaActivity;
import dam.proyecto.controllers.CompraController;
import dam.proyecto.controllers.NombreCompraController;
import dam.proyecto.database.entity.CompraEntity;
import dam.proyecto.database.entity.NombreCompraEntity;
import dam.proyecto.database.repositories.NombreCompraRepository;
import dam.proyecto.databinding.ActivityComprasBinding;
import dam.proyecto.utilities.Fecha;
import dam.proyecto.utilities.Preferencias;

/**
 * @author Roberto Rodríguez Jiménez
 * @version 2023.02.19
 * @since 19/02/2023
 */
public class ComprasActivity extends AppCompatActivity {

    ActivityComprasBinding bindingCompras;

    // Datos de las compras (NombreCompraEntity)
    private ArrayList<NombreCompraEntity> dataNombreCompra;

    AdaptadorCompras adaptadorCompras;                  // Adapatador para el listado de las compras

    /* ***************************************************************************************** */
    /* *** FUNCIONES *************************************************************************** */
    /* ***************************************************************************************** */
    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Obtener la vista mediante ViewBinding
        bindingCompras = ActivityComprasBinding.inflate(getLayoutInflater());
        View view = bindingCompras.getRoot();
        setContentView(view);

        bindingCompras.navegador.setSelectedItemId(R.id.itCompras);


        // == Zona del campo de texto ==============================================================
        // Establecemos el hint para para el input del nuevoNombre        
        bindingCompras.acInpNuevoNombre.setHint(Fecha.getNuevaFecha() + " o escribe un nombre");

        // Oyente para el check
        bindingCompras.acImgCrearCompra.setOnClickListener(view1 -> crearCompra(true));

        // == Zona de la lista =====================================================================
        // Obtener el listado de las compras
        dataNombreCompra = new NombreCompraRepository(this).getAll();

        // Crear el adaptador que vamos a pasar al ListView
        adaptadorCompras = new AdaptadorCompras(
                this,
                R.layout.item_compra,
                dataNombreCompra,
                new ListenerCompras() {
                    @Override
                    public void cli_img_deleteOnClik(NombreCompraEntity compra, int posicion) {
                        borrarCompra(compra, posicion);
                    }

                    @Override
                    public void cli_img_copyOnClik(NombreCompraEntity compra) {
                        duplicarCompra(compra);
                    }

                    @Override
                    public void cli_lly_datosCompraOnClick(NombreCompraEntity compra) {
                        editarCompra(compra);
                    }

                    @Override
                    public void cli_lly_datosCompraOnLongClick(NombreCompraEntity compra) {
                        abrirDialogoCambiarIdCompra(compra);
                    }
                }
        );
        bindingCompras.listaCompras.setAdapter(adaptadorCompras);


        // Oyente para el navegador
        bindingCompras.navegador.setOnItemSelectedListener(item -> {
                    switch (item.getItemId()) {
                        case R.id.itAlamacen:
                            startActivity(new Intent(this, AlmacenActivity.class));
                            overridePendingTransition(0, 0);
                            return true;
                        case R.id.itInicio:
                            startActivity(new Intent(this, MainActivity.class));
                            overridePendingTransition(0, 0);
                            return true;
                        case R.id.itLista:
                            startActivity(new Intent(this, ListaActivity.class));
                            overridePendingTransition(0, 0);
                            return true;
                        case R.id.itCompras:
                            return true;

                    }
                    return false;
                }
        );

    }


    /**
     * Abre el diálogo para modificar el id de una compra (NombreCompra)
     *
     * @param compra que se quiere modificar
     */
    private void abrirDialogoCambiarIdCompra(NombreCompraEntity compra) {

        LayoutInflater inflater = this.getLayoutInflater();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = inflater.inflate(R.layout.dialog_cambiar_id_nombre_compra, null);

        TextView input = view.findViewById(R.id.dc_inp_id_nuevo);

        builder.setTitle("Nuevo id");
        builder.setMessage("El id debe tener 10 caracterer numéricos");
        builder.setView(view);
        builder.setNegativeButton("Cancelar", null);
        builder.setPositiveButton("Aceptar", (dialogInterface, i) ->
                cambiarIdNombreCompra(compra.getId(), input.getText().toString())
        );

        builder.create();
        builder.show();

    }


    /**
     * Borrar una compra de la lista.
     *
     * @param compra la compra que se quiere borrar
     */
    public void borrarCompra(NombreCompraEntity compra, int ignoredPosicion) {

        // 1.- Borrar la compra de dataNombreCompra
        dataNombreCompra.remove(compra);

        // 2.- Hay que asegurarse de que la compra borrada no esté guardada en preferencias
        // Si la compra está siendo editada, establecemos la preferencia como null
        // La preferencia puede ser nula, lo que significa que la compra
        // que se va a borrar no está abierta
        String compraPreferencias = Preferencias.getListaAbiertaId(this);
        if ((compraPreferencias != null) && (compraPreferencias.equals(compra.getId()))) {
            Preferencias.setListaAbiertaId(null, this);
            Toast.makeText(this, "Esta compra se está editando", Toast.LENGTH_SHORT).show();
        }

        // 3.- Borrar la compra de la base de datos
        NombreCompraController controller = new NombreCompraController(this);
        controller.delete(compra);

        // 4.- Informar al adptador del cambio
        adaptadorCompras.notifyDataSetChanged();

        Toast.makeText(this,
                "Compra " + compra.getNombre() + " eliminada",
                Toast.LENGTH_SHORT).show();
    }


    /**
     * Método que cambia el id de un Nombre de la compra
     *
     * @param idAnterior id anterior
     * @param idNuevo    id nuevo
     */
    private void cambiarIdNombreCompra(String idAnterior, String idNuevo) {

        NombreCompraController ncc = new NombreCompraController(this);
        CompraController cc = new CompraController(this);


        // El cambio se lo pedimos al controlador de NombreCompra
        // El controlador se encarga de comprobar que los id's existan
        // cambiarId devuelve true si el cambio se ha llevado a cabo
        if (ncc.cambiarId(idAnterior, idNuevo)) {

            // El cambio se ha realizado correctamente
            // Ahora hay que cambiar la fecha de CompraEntity que tengan idAnterior como fecha.
            cc.updateFecha(idAnterior, idNuevo);

            // Recargar la lista
            adaptadorCompras.notifyDataSetChanged();


            // Si hemos cambiado el id de la compra correctamente, y esta compra
            // estaba siendo editada, el id de la lista abierta aún se mantienen
            // como la lista anterior.
            // Hay que comprobar si el id de la lista abierta es el mismo de la
            // lista anterior y ,si lo es, actualizarlo
            if(Preferencias.getListaAbiertaId( this).equals(idAnterior) ){
                Preferencias.setListaAbiertaId(idNuevo, this);
            }
            // Recargar la actividad
            startActivity(new Intent(this, ComprasActivity.class));

        } else {
            Toast.makeText(this, "El id no es válido", Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * Crear una compra.
     * En la vista recibimos el nombre que se le quiere dar a la nueva compra.
     * Este nombre puede estar en blanco o puede estar repetido.
     * Al crear la compra, se genera un id único con formato: aammddhhmm
     * Si el nombre de la compra se ha dejado en blanco, se le asigna el id.
     *
     * @param abrir true si queremos abrir la nueva lista
     * @return el id de la nueva lista
     */
    @SuppressLint("SetTextI18n")
    private String crearCompra(boolean abrir) {

        // Crear una instancia del repositorio de NombreCompra
        NombreCompraController controller = new NombreCompraController(this);

        // Obtenemos el id para la nueva lista
        String id = Fecha.getNuevaFecha();

        // Nombre de la lista del formualrio
        String nombre = bindingCompras.acInpNuevoNombre.getText().toString();

        // 1.- Comprobamos si el id ya existe
        // Comprobamos si el nombre ya existe
        if (!controller.existsIdDeLaCompra(id)) {

            // El id no existe

            // Si no hay nombre o tiene menos de 3 caracteres, se toma el id
            String nombreDeLaLista = (nombre.length() >= 3) ? nombre : id;

            // Creamos el objeto NombreCompra
            // Por defecto se crea el comercio 1 -> ""
            NombreCompraEntity compra = new NombreCompraEntity(id, nombreDeLaLista, 1);

            // 2.- Lo guardamos en la base de datos en la
            // posición 0 para que se muestre al inicio
            controller.insert(compra);

            // 3.- Abrimos la lista
            if (abrir) {
                editarCompra(compra);
            }

            return id;
        } else {

            // El nombre existe
            int minuto = Integer.parseInt(id.substring(8, 10));
            minuto++;
            String hora = id.substring(6, 8) + ":" + minuto;

            // El nombre ya existe
            LayoutInflater inflater = this.getLayoutInflater();
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            View v = inflater.inflate(R.layout.alert_nueva_compra, null);

            TextView solucion = v.findViewById(R.id.anc_tv_solucion);
            solucion.setText(solucion.getText() + " " + hora);

            builder.setPositiveButton("Aceptar", null);
            builder.setView(v);

            AlertDialog dialog = builder.create();
            dialog.show();

        }

        return null;
    }

    /**
     * Duplica una compra.
     * Duplicamos NombreCompraEntity
     *
     * @param compraOriginal que se quiere duplicar
     */
    private void duplicarCompra(NombreCompraEntity compraOriginal) {

        // Controlador para el nombre de la compra
        NombreCompraController nombreCompraController = new NombreCompraController(this);
        CompraController compraController = new CompraController(this);

        /*
          Se crea un objeto NombreCompraEntity
          .- String id:     se genera automáticamente con formado aammddhhmm
          .- String nombre: el mismo texto que el id
          .- int comercio:  1, corresponde a comercio en blanco
         */
        String idCompraNueva = crearCompra(false);

        // En este punto la compra copia está creada y aparece en el listado

        // La compra está creada y se muestra
        // Obtenemos la compra copia
        NombreCompraEntity compraCopia = nombreCompraController.getById(idCompraNueva);

        // Establecer el comercio de la compra copia
        compraCopia.setComercio(compraOriginal.getComercio());

        // Actualizar la compra copia
        nombreCompraController.update(compraCopia);

        // Duplicar las compra de la original a la copia
        // Obtener todos los productos que pertenecen a la compra original
        // CompraEntity guarda el producto y la fecha de su compra
        // Le pedimos a CompraEntity todas las CompraEntity que tengan como fecha la fecha original
        ArrayList<CompraEntity> productos = compraController.getProductosByFecha(compraOriginal.getId());

        // Recorrer los productos, establecer la nueva fecha e insertalos
        // origen es un objeto CompraEntity
        productos.forEach(origen -> {

            CompraEntity copia = new CompraEntity(
                    origen.getProducto(),
                    idCompraNueva,
                    origen.getCantidad(),
                    origen.getPagado(),
                    origen.getPrecio(),
                    origen.getPrecioMedido(),
                    origen.getOferta()
            );
            copia.setId(origen.getProducto() + idCompraNueva);
            copia.setFecha(idCompraNueva);
            compraController.insert(copia);
        });

        // Abrimos la lista
        editarCompra(compraCopia);
    }

    /**
     * Edita una compra
     *
     * @param compra que se quiere editar
     */
    public void editarCompra(NombreCompraEntity compra) {

        // Abrimos la lista
        Preferencias.setListaAbiertaId(compra.getId(), this);
        startActivity(new Intent(this, ListaActivity.class));
    }


}