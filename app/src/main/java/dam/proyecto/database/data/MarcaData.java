package dam.proyecto.database.data;

import dam.proyecto.database.entity.MarcaEntity;

import java.util.Arrays;
import java.util.List;

/**
 * @since 2023/01/23
 * @author Roberto Rodríguez
 * @version 2023.02.15
 */
public class MarcaData {

    private static final MarcaEntity[] TAG_DATA = {
            new MarcaEntity(""),
            new MarcaEntity("aguadoy"),
            new MarcaEntity("alteza"),
            new MarcaEntity("carrefour"),
            new MarcaEntity("danone"),
            new MarcaEntity("estrella galicia"),
            new MarcaEntity("florete"),
            new MarcaEntity("granel"),
            new MarcaEntity("hacendado"),
            new MarcaEntity("jovi"),
            new MarcaEntity("knorr"),
            new MarcaEntity("la casera"),
            new MarcaEntity("luengo"),
            new MarcaEntity("lugareño"),
            new MarcaEntity("marcilla"),
            new MarcaEntity("marpe"),
            new MarcaEntity("ligeresa"),
            new MarcaEntity("Guillen huevos"),
            new MarcaEntity("Bonka"),
            new MarcaEntity("Carbonell"),
            new MarcaEntity("Tacto"),
            new MarcaEntity("H&S"),
            new MarcaEntity("Listerine"),
            new MarcaEntity("Xoia"),

    };

    public static List<MarcaEntity> getData(){
        return Arrays.asList( TAG_DATA );
    }
}
