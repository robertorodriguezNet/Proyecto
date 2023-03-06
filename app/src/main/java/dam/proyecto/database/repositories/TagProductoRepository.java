package dam.proyecto.database.repositories;

import android.content.Context;
import android.util.Log;

import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

import dam.proyecto.database.Repositorio;
import dam.proyecto.database.dao.TagsProductoDao;
import dam.proyecto.database.entity.TagsProductoEntity;

/**
 * @author Roberto Rodríguez
 * @since 23/01/2023
 * @version 2023.03.06
 */
public class TagProductoRepository extends Repositorio {

    private Context context;
    private TagsProductoDao dao;

    public TagProductoRepository(Context context) {
        super(context);
        this.context = context;
        dao = getDb().tagsProductoDao();
    }


    /**
     * Borra los datos de la tabla
     */
    public void clear(){
        dao.clear();
    }

    /**
     * Devuelve true si ya hay un producto asociado a la etiqueta
     * @param producto
     * @param tag
     * @return
     */
    public boolean existsAsociacion( String producto, int tag){
        TagsProductoEntity asociacion = null;
        asociacion = dao.getAsociacion( producto, tag );
        return ( asociacion != null );
    }

    /**
     * Obtiene una colección de productos
     * @return
     */
    public ArrayList<TagsProductoEntity> getAll(){
        return (ArrayList<TagsProductoEntity>) dao.getAll();
    }

    /**
     * Devuelve el último id insertado en la tabla
     * @return el id más alto registrado
     */
    public int getMaxId(){
        return  dao.getMaxId();
    }

    /**
     * A partir de un producto devuelve las etiquetas
     * @param idProducto el producto buscado
     * @return
     */
    public ArrayList<String> getNombres( String idProducto ){

        try {
            // Repositorio de tags
            TagRepository tagRepository = new TagRepository(context);

            // Obtener los id de los tags que corresponden al producto
            ArrayList<Integer> idTags = (ArrayList<Integer>) dao
                    .getTagByProducto(idProducto);

            // Pedimos los nombres y los devolvemos
            return tagRepository.getNombres(idTags);

        }catch ( Exception e){
            Log.e("LDLC", "TagProductoRepository.getNombres() id: " + idProducto
            + "\n" + e.getMessage());
            return new ArrayList<>();
        }

    }

    /**
     * Inserta un objeto TagsProductoEntity
     * @param objeto que se inserta
     */
    public void insert( TagsProductoEntity objeto){
        dao.insert( objeto );
    }

    /**
     * Inserta una colección de objetos
     */
    public void insertAll(List<TagsProductoEntity> data ){
        dao.insertAll( data );
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
