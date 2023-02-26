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
            new ComercioEntity(""),
            new ComercioEntity("carrefour"),
            new ComercioEntity("gadis"),
            new ComercioEntity("lupa"),
            new ComercioEntity("mercadona"),
            new ComercioEntity("dia"),
            new ComercioEntity("el corte inglés"),
            new ComercioEntity("primaprix"),
            new ComercioEntity("carrefour exp."),
    };

    public static List<ComercioEntity> getData(){
        return Arrays.asList( COMERCIO_DATA );
    }
}
