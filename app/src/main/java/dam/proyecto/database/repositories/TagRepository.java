package dam.proyecto.database.repositories;

import android.content.Context;
import android.nfc.Tag;
import android.util.Log;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import dam.proyecto.database.Repositorio;
import dam.proyecto.database.dao.TagDao;
import dam.proyecto.database.entity.MarcaEntity;
import dam.proyecto.database.entity.TagEntity;

/**
 * @author Roberto Rodríguez
 * @since 2023/01/23
 * @version 2023.03.01
 */
public class TagRepository extends Repositorio {

    private TagDao dao;

    public TagRepository(Context context) {
        super(context);
        this.dao = db.tagDao();
    }

    /**
     * Borra los datos de la tabla
     */
    public void clear(){
        dao.clear();
    }

    public ArrayList<TagEntity> getAll(){
        return (ArrayList<TagEntity>) db.tagDao().getAll();
    }

    /**
     * Devuelve una colección de tags contienen el texto
     * @param texto
     * @return
     */
    public ArrayList<TagEntity> getAll( String texto ){
        return (ArrayList<TagEntity>) dao.getAll( texto );
    }
    /**
     * Devuelve una colección de id de las etiquetas que contienen el texto
     * @param texto
     * @return
     */
    public ArrayList<Integer> getAllId( String texto ){
        return (ArrayList<Integer>) dao.getAllId( texto );
    }

    /**
     * Devuelve el id de una etiqueta por su nombre
     * @param name el tag buscado
     * @return
     */
    public int getIdByName( String name ){

        int idName = dao.getIdByName( name );
//        Log.d("LDLC", "TagRepository.getIdByName() \n" +
//            "Pedido el id de " + name + ": " + idName );
        return idName;
    }

    /**
     * Devuelve el valor máximo del id
     * @return
     */
    public int getMaxId(){
        return dao.getMaxId();
    }

    /**
     * Devuelve una colección con los id de los productos que contienen el texto
     * en la etiqueta
     * @param texto
     * @return
     */
    public ArrayList<String> getProductosByTag( String texto ){
        ArrayList<String> list = (ArrayList<String>) dao.getProductosByTag( texto );
        ArrayList<String> idList = new ArrayList<>();

        Iterator it = list.iterator();
        while ( it.hasNext() ){
            String producto = (String) it.next();
            idList.add( producto );
        }

        return idList;
    }


    /**
     * Devuelve un listado con tan sólo los nombres
     * @return
     */
    public ArrayList<String> getNombres(){
        ArrayList<TagEntity> objetos = getAll();
        ArrayList<String> nombres = new ArrayList<>();

        for ( TagEntity objeto: objetos ) {
            nombres.add( objeto.getName() );
        }

        return nombres;
    }

    /**
     * Devuelve un listado con las etiquetas pedidas
     * @param ids listado de etiquetas pedidas
     * @return
     */
    public ArrayList<String> getNombres( ArrayList<Integer> ids ){

        ArrayList<String> etiquetas = new ArrayList<>();

        for(Integer id : ids) {
            etiquetas.add( dao.getNameById( id ));
        }

        return etiquetas;
    }

    /**
     * Devuelve true si el tag existe
     * @param tag
     * @return
     */
    public boolean exists( String tag ){
        TagEntity tagEntity = null;
        tagEntity = dao.findByName( tag );
        return tagEntity != null;
    }
    /**
     * Devuelve el objeto si el tag existe
     * @param tag
     * @return
     */

    /**
     * Inserta una colección de objetos
     */
    public void insertAll( List<TagEntity> data ){
        db.tagDao().insertAll( data );
    }


    /**
     * Inserta un nuevo tag y devuelve el id
     * @param tag
     * @return
     */
    public int insert( String tag ){

//        String log = "TagRepository.insert() Estamos insertando un nuevo tag" +
//                "\ntag: " + tag;

        // Crear un objeto tag a partir de la etiqueta recibida
        int newId = dao.getMaxId();
        newId++;
        TagEntity newTag = new TagEntity( newId, tag );

        // Insertamos el tagEntity si no existe
        if( !exists( tag ) ){
//            log += "\nEl tag no existe y se ha insertado en la BD";
            dao.insert( newTag );  // Insertamos el tag
        }

        // Recuperar el tag recién guardado
        if( exists( tag ) ){
//            log += "\nHemos recuperado el tag de la BD";
            newTag = dao.findByName( tag );
        }

//        log += "\nEl id de " + tag + " es " + newTag.getId();
//        Log.d("LDLC", log);

        // Si la etiqueta no existiera, el id = 0
        return newTag.getId();
    }

    /**
     * Insrta un tag a partir de un objeto
     * @param tag
     */
    public void insert( TagEntity tag ){
        dao.insert( tag );
    }



    @Override
    public String toString() {
        return super.toString();
    }
}
