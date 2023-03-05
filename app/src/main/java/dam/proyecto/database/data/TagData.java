package dam.proyecto.database.data;

import dam.proyecto.controllers.TagController;
import dam.proyecto.database.entity.TagEntity;

import java.util.Arrays;
import java.util.List;

/**
 * @since 2023/01/23
 * @author Roberto Rodríguez
 * @version 2023.02.15
 */
public class TagData {

    private static final TagEntity[] TAG_DATA = {
            new TagEntity(1,"agua"),
            new TagEntity(2,"ahumado"),
            new TagEntity(3,"alcohol"),
            new TagEntity(4,"alibia"),
            new TagEntity(5,"atun"),
            new TagEntity(6,"balsámico"),
            new TagEntity(7,"bifidus"),
            new TagEntity(8,"bollo"),
            new TagEntity(9,"café"),
            new TagEntity(10,"centeno"),
            new TagEntity(11,"cerveza"),
            new TagEntity(12,"conserva"),
            new TagEntity(13,"crema"),
            new TagEntity(14,"desnatado-a"),
            new TagEntity(15,"ensalada"),
            new TagEntity(16,"fruta"),
            new TagEntity(17,"garbanzo"),
            new TagEntity(18,"gaseosa"),
            new TagEntity(19,"huevos"),
            new TagEntity(20,"leche"),
            new TagEntity(21,"lentejas"),
            new TagEntity(22,"limpieza"),
            new TagEntity(23,"lonchas"),
            new TagEntity(24,"melocotón"),
            new TagEntity(25,"microondas"),
            new TagEntity(26,"paleta"),
            new TagEntity(27,"pan"),
            new TagEntity(28,"panecillo"),
            new TagEntity(29,"pavo"),
            new TagEntity(30,"picatostes"),
            new TagEntity(31,"platano"),
            new TagEntity(32,"precocinados"),
            new TagEntity(33,"queso"),
            new TagEntity(34,"salmon"),
            new TagEntity(35,"tomate"),
            new TagEntity(36,"tortilla"),
            new TagEntity(37,"verduras"),
            new TagEntity(38,"vinagre"),
            new TagEntity(39,"yogurt"),
            new TagEntity(40,"zumo"),
            new TagEntity(41,"natural"),
            new TagEntity(42,"mayonesa"),
            new TagEntity(43,"0,0"),
            new TagEntity(44,"pimiento"),
            new TagEntity(45,"rojo-a"),
            new TagEntity(46,"hortalizas"),
            new TagEntity(47,"pera"),
            new TagEntity(48,"verde"),
            new TagEntity(49,"patata"),
            new TagEntity(50,"suelo"),
            new TagEntity(51,"aceite"),
            new TagEntity(52,"oliva"),
            new TagEntity(53,"virgen"),
            new TagEntity(54,"extra"),
            new TagEntity(55,"champú"),
            new TagEntity(56,"colutorio"),
            new TagEntity(57,"jabón"),

    };

    public static List<TagEntity> getData(){
        return Arrays.asList( TAG_DATA );
    }


}
