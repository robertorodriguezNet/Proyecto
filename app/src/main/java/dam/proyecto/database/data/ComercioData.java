package dam.proyecto.database.data;

import dam.proyecto.database.entity.ComercioEntity;

import java.util.Arrays;
import java.util.List;


/**
 * @since 2023/01/23
 * @author Roberto Rodríguez
 * @version 2023.02.15
 */
public class ComercioData {

    private static final ComercioEntity[] COMERCIO_DATA = {
            new ComercioEntity(1,""),
            new ComercioEntity(2,"carrefour"),
            new ComercioEntity(3,"gadis"),
            new ComercioEntity(4,"lupa"),
            new ComercioEntity(5,"mercadona"),
            new ComercioEntity(6,"dia"),
            new ComercioEntity(7,"el corte inglés"),
            new ComercioEntity(8,"primaprix"),
            new ComercioEntity(9,"carrefour exp."),
    };

    public static List<ComercioEntity> getData(){
        return Arrays.asList( COMERCIO_DATA );
    }
}
