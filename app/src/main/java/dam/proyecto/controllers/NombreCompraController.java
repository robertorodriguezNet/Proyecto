package dam.proyecto.controllers;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.regex.Pattern;

import dam.proyecto.database.entity.NombreCompraEntity;
import dam.proyecto.database.repositories.NombreCompraRepository;
import dam.proyecto.utilities.Fecha;

/**
 * Clase que realiza operaciones con los comercios
 *
 * @author Roberto Rodríguez Jiménez
 * @version 2023.03.08
 * @since 04/03/2023
 */

public class NombreCompraController {

    private final NombreCompraRepository REPOSITORY;
    private final Context CONTEXT;

    public NombreCompraController(Context context) {
        this.REPOSITORY = new NombreCompraRepository(context);
        this.CONTEXT = context;
    }

    /**
     * Borra los datos de la tabla
     */
    public void clear() {
        REPOSITORY.clear();
    }


    /**
     * Elimina la compra de la base de datos
     *
     * @param compra que se debe eliminar
     */
    public void delete(NombreCompraEntity compra) {
        REPOSITORY.delete(compra);
    }

    /**
     * Comprueba si un id existe
     *
     * @param id id que se comprueba
     * @return true si existe
     */
    public boolean existsId(String id) {
        NombreCompraEntity objeto = REPOSITORY.findById(id);
        return objeto != null;
    }


    /**
     * Comprueba si existe un nombre de la compra en la base de datos.
     * El nombre de la compra puede estar repetido, pero no el id
     *
     * @param id el id buscado
     * @return true | false si existe o no el nombre de la compra
     */
    public boolean existsIdDeLaCompra(String id) {
        NombreCompraEntity objeto = REPOSITORY
                .findById(id);
        return objeto != null;
    }

    public boolean existsNombreDeLaCompra(String id) {
        NombreCompraEntity objeto = REPOSITORY
                .findById(id);
        return objeto != null;
    }

    /**
     * Devuelve un listado completo de los registros.
     *
     * @return listado de objetos
     */
    public ArrayList<NombreCompraEntity> getAll() {
        return (ArrayList<NombreCompraEntity>) REPOSITORY.getAll();
    }

    /**
     * Obtener un nombre de la compra a partir del id
     *
     * @param id el id de la compra, con formato aammddhhmm
     * @return nombre de la compra
     */
    public NombreCompraEntity getById(String id) {
        return REPOSITORY.getById(id);
    }

    /**
     * Inserta un nuevo registro
     *
     * @param id       de la compra
     * @param nombre   que se le da a la compra
     * @param comercio en el que se compra (int id)
     */
    public void insert(String id, String nombre, int comercio) {
        REPOSITORY.insert(new NombreCompraEntity(id, nombre, comercio));
    }

    /**
     * Inserta un objeto en la base de datos
     *
     * @param objeto que se debe insertar
     */
    public void insert(NombreCompraEntity objeto) {
        REPOSITORY.insert(objeto);
    }


    /**
     * Comprueba si el id recibido está libre.
     * Para ello debe ser válido
     *
     * @param id que se quiere comprobar
     * @return true si está libre
     */
    public boolean isIdLibre(String id) {
        return isValido(id) && !existsId(id);
    }

    /**
     * Comprueba si el id recibido es válido.
     * Los requisitos para que sea válido son que tenga una longitud de 10 caracteres
     * y que sea numérico.
     *
     * @param id que hay que validar
     * @return true si es válido
     */
    public boolean isValido(String id) {

        boolean cadenaCorrecta,
                horaCorrecta;


        // Nos aseguramos de que tenga la longitud adecuada
        Pattern pattern = Pattern.compile("[0-9]+");
        cadenaCorrecta = (id.length() == 10) && (pattern.matcher(id).matches());

        // Solo si la longitud es correcta
        if (cadenaCorrecta) {

            // Comprobar el formato
            String yy = "20" + id.substring(0, 2);
            String MM = id.substring(2, 4);
            String dd = id.substring(4, 6);
            int hh = Integer.parseInt(id.substring(6, 8));
            int mm = Integer.parseInt(id.substring(8, 10));

            horaCorrecta = (hh >= 0) && (hh < 24) && (mm >= 0) && (mm < 60);

            return cadenaCorrecta && horaCorrecta && Fecha.isValidaFecha(dd, MM, yy);

        } else {
            return false;
        }
    }

    public void update(NombreCompraEntity nombreCompra) {
        REPOSITORY.update(nombreCompra);
    }

    /**
     * Actualiza el id de un NombreCompra.
     * Se exige que el id esté validado.
     *
     * @param idAnterior el id que se quiere actualizar
     * @param idNuevo    el id nuevo.
     */
    public void updateId(String idAnterior, String idNuevo) {
        Toast.makeText(CONTEXT, "Valido " + isIdLibre(idNuevo), Toast.LENGTH_SHORT).show();
        REPOSITORY.updateId(idAnterior, idNuevo, isIdLibre(idNuevo));
    }


}
