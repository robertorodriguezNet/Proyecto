package dam.proyecto.database.repositories;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import dam.proyecto.database.Repositorio;
import dam.proyecto.database.entity.TagsProductoEntity;

/**
 * @author Roberto Rodríguez
 * @since 23/01/2023
 * @version 2023.02.28
 */
public class TagProductoRepository extends Repositorio {

    private Context context;

    public TagProductoRepository(Context context) {
        super(context);
        this.context = context;
    }

    public ArrayList<TagsProductoEntity> getAll(){
        return (ArrayList<TagsProductoEntity>) db.tagsProductoDao().getAll();
    }

    /**
     * Devuelve un listado de los tags de un producto con tan sólo los nombres
     * @param idProducto el producto buscado
     * @return
     */
    public ArrayList<String> getNombres( String idProducto ){

        try {
            // Repositorio de tags
            TagRepository tagRepository = new TagRepository(context);

            // Obtener los id de los tags que corresponden al producto
            ArrayList<Integer> idTags = (ArrayList<Integer>) db
                    .tagsProductoDao()
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
     * Borra los datos de la tabla
     */
    public void clear(){
        db.tagsProductoDao().clear();
    }

    /**
     * Inserta una colección de objetos
     */
    public void insertAll(List<TagsProductoEntity> data ){
        db.tagsProductoDao().insertAll( data );
    }

    @Override
    public String toString() {
        return super.toString();
    }

    /**
     * Devuelve true si ya hay un producto asociado a la etiqueta
     * @param producto
     * @param tag
     * @return
     */
    public boolean existsAsociacion( String producto, int tag){
        TagsProductoEntity asociacion = null;
        asociacion = db.tagsProductoDao().getAsociacion( producto, tag );
        return ( asociacion != null );
    }

    /**
     * Asocia un producto con una etiqueta
     * @param producto
     * @param tag
     */
    public void insert( String producto, int tag){

        if( !existsAsociacion( producto, tag ) ) {

            Log.d("LDLC", "TagProductoRepository.insert() \n" +
                    "No existe la asocición " + producto + " - " + tag);
            db.tagsProductoDao().insert(new TagsProductoEntity(producto, tag));

        } else {
            Log.d("LDLC", "TagProductoRepository.insert() \n" +
                    "Sí existe la asocición " + producto + " - " + tag);
        }
    }

}
