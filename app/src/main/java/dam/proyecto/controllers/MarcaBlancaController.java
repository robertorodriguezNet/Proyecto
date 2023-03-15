package dam.proyecto.controllers;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

import dam.proyecto.database.entity.ComercioEntity;
import dam.proyecto.database.entity.MarcaBlancaEntity;
import dam.proyecto.database.entity.ProductoEntity;
import dam.proyecto.database.repositories.MarcaBlancaRepository;

/**
 * Clase que realiza operaciones con los comercios
 *
 * @author Roberto Rodríguez Jiménez
 * @since 04/03/2023
 * @version 2023.03.07
 */

public class MarcaBlancaController {

    private final MarcaBlancaRepository REPOSITORY;

    public MarcaBlancaController(Context context ){
        this.REPOSITORY = new MarcaBlancaRepository( context );
    }

    /**
     * Borra los datos de la tabla
     */
    public void clear(){
        REPOSITORY.clear();
    }

    /**
     * Comprueba la asociación de una marca con un comercio
     * @param comercio buscado
     * @param marca buscada
     * @return true si la asociación existe
     */
    public boolean exists( int comercio, int marca ){
        return REPOSITORY.getByArgs( comercio, marca ) != null;
    }

    /**
     * Elimna de la colección de productos aquellos que son marca blanca de otros comercios.
     * ProductoEntity tiene una propiedad int marca.
     * @param productos listado de productos que se evalúan
     * @param idComercio comercio buscado
     * @return colección limpia
     */
    public ArrayList<ProductoEntity> filtrarMarcaBlanca(ArrayList<ProductoEntity>productos,
                                                        int idComercio ){
        Log.d("LDLC", "Filtrado de marca blanca. " +
                "\nComercio: " + idComercio );

        // Lista que se va a devolver
        ArrayList<ProductoEntity> listaLimpia = new ArrayList<>();

        // Se recorren los productos
        for (ProductoEntity producto : productos ){

            // Preguntamos si el producto pertenece a otro comercio
            boolean esAjena = isMarcaAjena( idComercio, producto.getMarca() );

            if(!esAjena){
                listaLimpia.add( producto );
            }
        }

        return listaLimpia;
    }

    /**
     * Devuelve un listado completo de los registros.
     * @return la colección de objetos completa
     */
    public ArrayList<MarcaBlancaEntity> getAll(){
        return REPOSITORY.getAll();
    }

    /**
     * Devuelve los comercios (id) de uan marca dada
     * @param marca buscada
     * @return listado de comercios
     */
    public ArrayList<Integer> getComerciosByMarca( int marca ){
        return REPOSITORY.getComerciosByMarca( marca );
    }

    /**
     * Devuelve las marcas (id) de un comercio dado
     * @param comercio buscado
     * @return listado de marcas
     */
    public ArrayList<Integer> getMarcasByComercio( int comercio ){
        return REPOSITORY.getMarcasByComercio( comercio );
    }

    /**
     * Devuleve el primer id válido para insertar un objeto
     * @return id válido
     */
    public int getNewId(){
        int id = REPOSITORY.getMaxId();
        id++;
        return id;
    }

    /**
     * Inserta un objeto a partir de la marca y el comercio
     * @param marca nueva
     * @param comercio al que pertenece la marca
     */
    public void insert( int marca, int comercio ){
        REPOSITORY.insert( new MarcaBlancaEntity( getNewId(), marca, comercio ));
    }

    /**
     * Inserta un objeto a partir de todos sus datos
     * @param id identificador
     * @param marca nueva
     * @param comercio al que pertenece la marca
     */
    public void insert( int id, int marca, int comercio ){
        REPOSITORY.insert( new MarcaBlancaEntity( id, marca, comercio ));
    }

    /**
     * Comprueba si una marca es marca blanca de otro comercio
     * @param marca buscado
     * @param comercio buscado
     * @return true si la marca buscada pertenece al comercio
     */
    public boolean isMarcaAjena( int comercio, int marca ){

        // Obtener la relación si la hay
        ArrayList<Integer> relacion = getComerciosByMarca( marca );

        return ( ( relacion.size() > 0 ) && ( !relacion.contains( comercio ) ) );
    }

    /**
     * Comprueba si una marca es marca blanca de un comercio
     * @param marca buscado
     * @param comercio buscado
     * @return true si la marca buscada pertenece al comercio
     */
    public boolean isMarcaBlanca( int comercio, int marca ){
        MarcaBlancaEntity marcaBlanca = REPOSITORY.getByArgs( comercio, marca );
        return marcaBlanca != null;
    }

}
