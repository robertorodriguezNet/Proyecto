package dam.proyecto.database.repositories;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import dam.proyecto.database.Repositorio;
import dam.proyecto.database.dao.MarcaDao;
import dam.proyecto.database.entity.CompraEntity;
import dam.proyecto.database.entity.MarcaEntity;

/**
 * Repositorio para las marcas
 * @author Roberto Rodríguez
 * @since 06/02/2023
 * @version 2023.02.21
 */
public class MarcaRepository extends Repositorio{
    public MarcaRepository(Context context) {
        super(context);
    }

    /**
     * Borra los datos de la tabla
     */
    public void clear(){
        db.marcaDao().clear();
    }

    /**
     * Inserta una colección de objetos
     */
    public void insertAll( List<MarcaEntity> data ){
        db.marcaDao().insertAll( data );
    }

    /**
     * Devuelve la colección entera de objetos guardados.
     * @return los datos.
     */
    public ArrayList<MarcaEntity> getAll(){
        return (ArrayList<MarcaEntity>) getDb().marcaDao().getAll();
    }

    /**
     * Devuelve un listado con tan sólo los nombres
     * @return
     */
    public ArrayList<String> getNombres(){
        ArrayList<MarcaEntity> objetos = getAll();
        ArrayList<String> nombres = new ArrayList<>();

        for ( MarcaEntity objeto: objetos ) {
            nombres.add( objeto.getName() );
        }

        return nombres;
    }

    /**
     * Devuelve el nombre de la marca correspondiente al id recibido
     * @param  id del cual hay que devolver el nombre
     * @return
     */
    public String getNameById( int id ){

        MarcaEntity marca = db.marcaDao().findById( id );
        return marca.getName();

    }

    /**
     * Devuelve el id de una marca si existe, si no existe, la guarda.
     * @param marcaStr la marca buscada
     * @param control No tengo claro para qué lo puse, pero debe ser false
     * @return
     */
    public int getIdByName( String marcaStr, boolean control ){

        MarcaDao dao = db.marcaDao();
        String marca = marcaStr.trim();

        // Obtenemos el objeto MarcaEntity
        MarcaEntity marcaEntity = null;
        marcaEntity = dao.findByName( marca );

        // Si marcaEntity es nulo, es que el objeto no existe
        if( marcaEntity == null ){
            // Guardamos la marca
            dao.insert( new MarcaEntity( marca ));
            marcaEntity = dao.findByName( marca );
        }

        return ( marcaEntity == null ) ? -1 : (int) marcaEntity.getId();
    }

    public int getIdByName( String marcaStr ){

        MarcaDao dao = db.marcaDao();
        String marca = marcaStr.trim();

        // Obtenemos el objeto MarcaEntity
        MarcaEntity marcaEntity = null;
        marcaEntity = dao.findByName( marca );

        // Si marcaEntity es nulo, es que el objeto no existe
        if( marcaEntity == null ){
            // Guardamos la marca
            dao.insert( new MarcaEntity( marca ));
            marcaEntity = dao.findByName( marca );
        }

        return ( marcaEntity == null ) ? -1 : (int) marcaEntity.getId();
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
