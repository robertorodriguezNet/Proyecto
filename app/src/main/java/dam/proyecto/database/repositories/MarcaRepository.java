package dam.proyecto.database.repositories;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import dam.proyecto.database.Repositorio;
import dam.proyecto.database.dao.MarcaDao;
import dam.proyecto.database.dao.MarcaDao_Impl;
import dam.proyecto.database.entity.ComercioEntity;
import dam.proyecto.database.entity.MarcaEntity;

/**
 * Repositorio para las marcas
 * @author Roberto Rodríguez
 * @since 06/02/2023
 * @version 2023.03.06
 */
public class MarcaRepository extends Repositorio{

    private Context context;
    private MarcaDao dao;

    public MarcaRepository(Context context) {

        super(context);
        this.context = context;
        dao = getDb().marcaDao();
    }

    /**
     * Borra los datos de la tabla
     */
    public void clear(){
        dao.clear();
    }

    /**
     * Inserta una colección de objetos
     */
    public void insertAll( List<MarcaEntity> data ){
        dao.insertAll( data );
    }

    /**
     * Devuelve la colección entera de objetos guardados.
     * @return los datos.
     */
    public ArrayList<MarcaEntity> getAll(){
        return (ArrayList<MarcaEntity>) dao.getAll();
    }

    /**
     * Devuelve el objeto marca a partir de su id
     * @param id id de la marca
     * @return MarcaEntity relacionado con el id
     */
    public MarcaEntity findById( int id ){
        return  dao.findById( id );
    }

    /**
     * Devuelve el objeto marca a partir de su nombre
     * @param name nombre de la marca
     * @return marcaEntity relacionado con el nombre
     */
    public MarcaEntity findByName(String name ){
        return  dao.findByName( name );
    }

    /**
     * Devuelve el id de una marca si existe, si no existe, la guarda.
     * @param marcaStr la marca buscada
     * @param control No tengo claro para qué lo puse, pero debe ser false
     * @return
     */
    public int getIdByName( String marcaStr, boolean control ){

        String marca = marcaStr.trim();

        // Obtenemos el objeto MarcaEntity
        MarcaEntity marcaEntity = null;
        marcaEntity = dao.findByName( marca );

        // Si marcaEntity es nulo, es que el objeto no existe
        if( marcaEntity == null ){
            // Guardamos la marca
            dao.insert( new MarcaEntity( getMaxId(), marca ));
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
            dao.insert( new MarcaEntity( getMaxId(), marca ));
            marcaEntity = dao.findByName( marca );
        }

        return ( marcaEntity == null ) ? -1 : (int) marcaEntity.getId();
    }

    /**
     * Devuelve el nombre de la marca correspondiente al id recibido
     * @param  id del cual hay que devolver el nombre
     * @return
     */
    public String getNameById( int id ){
        MarcaEntity marca = dao.findById( id );
        return marca.getName();
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
     * Develve el valor máximo del id
     * @return
     */
    public int getMaxId(){
        return dao.getMaxId();
    }

    /**
     * Inserta un comercio en la base de datos
     * @param objeto
     */
    public void insert( MarcaEntity objeto){
        dao.insert( objeto );
    }


    @Override
    public String toString() {
        return super.toString();
    }

}
