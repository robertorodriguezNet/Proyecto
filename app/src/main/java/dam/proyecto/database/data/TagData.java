package dam.proyecto.database.data;

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
            new TagEntity("agua"),
            new TagEntity("ahumado"),
            new TagEntity("alcohol"),
            new TagEntity("alibia"),
            new TagEntity("atun"),
            new TagEntity("balsámico"),
            new TagEntity("bifidus"),
            new TagEntity("bollo"),
            new TagEntity("café"),
            new TagEntity("centeno"),
            new TagEntity("cerveza"),
            new TagEntity("conserva"),
            new TagEntity("crema"),
            new TagEntity("desnatado-a"),
            new TagEntity("ensalada"),
            new TagEntity("fruta"),
            new TagEntity("garbanzo"),
            new TagEntity("gaseosa"),
            new TagEntity("huevos"),
            new TagEntity("leche"),
            new TagEntity("lentejas"),
            new TagEntity("limpieza"),
            new TagEntity("lonchas"),
            new TagEntity("melocotón"),
            new TagEntity("microondas"),
            new TagEntity("paleta"),
            new TagEntity("pan"),
            new TagEntity("panecillo"),
            new TagEntity("pavo"),
            new TagEntity("picatostes"),
            new TagEntity("platano"),
            new TagEntity("precocinados"),
            new TagEntity("queso"),
            new TagEntity("salmon"),
            new TagEntity("tomate"),
            new TagEntity("tortilla"),
            new TagEntity("verduras"),
            new TagEntity("vinagre"),
            new TagEntity("yogurt"),
            new TagEntity("zumo"),
            new TagEntity("natural"),
            new TagEntity("mayonesa"),
            new TagEntity("0,0"),
            new TagEntity("pimiento"),
            new TagEntity("rojo-a"),
            new TagEntity("hortalizas"),
            new TagEntity("pera"),
            new TagEntity("verde"),
            new TagEntity("patata"),
            new TagEntity("suelo"),
            new TagEntity("aceite"),
            new TagEntity("oliva"),
            new TagEntity("virgen"),
            new TagEntity("extra"),
            new TagEntity("champú"),
            new TagEntity("colutorio"),
            new TagEntity("jabón"),

    };

    public static List<TagEntity> getData(){
        return Arrays.asList( TAG_DATA );
    }


}
