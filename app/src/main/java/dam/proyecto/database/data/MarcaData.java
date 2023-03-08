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
            new MarcaEntity(1,""),
            new MarcaEntity(2,"aguadoy"),
            new MarcaEntity(3,"alteza"),
            new MarcaEntity(4,"carrefour"),
            new MarcaEntity(5,"danone"),
            new MarcaEntity(6,"estrella galicia"),
            new MarcaEntity(7,"florete"),
            new MarcaEntity(8,"granel"),
            new MarcaEntity(9,"hacendado"),
            new MarcaEntity(10,"jovi"),
            new MarcaEntity(11,"knorr"),
            new MarcaEntity(12,"la casera"),
            new MarcaEntity(13,"luengo"),
            new MarcaEntity(14,"lugareño"),
            new MarcaEntity(15,"marcilla"),
            new MarcaEntity(16,"marpe"),
            new MarcaEntity(17,"ligeresa"),
            new MarcaEntity(18,"Guillen huevos"),
            new MarcaEntity(19,"Bonka"),
            new MarcaEntity(20,"Carbonell"),
            new MarcaEntity(21,"Tacto"),
            new MarcaEntity(22,"H&S"),
            new MarcaEntity(23,"Listerine"),
            new MarcaEntity(24,"Xoia"),
            new MarcaEntity(25,"Selex"),
            new MarcaEntity(26,"Rio d'oro"),
            new MarcaEntity(27,"El mercado"),

    };

    public static List<MarcaEntity> getData(){
        return Arrays.asList( TAG_DATA );
    }
}
