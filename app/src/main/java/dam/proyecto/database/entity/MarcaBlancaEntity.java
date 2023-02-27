package dam.proyecto.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * @author Roberto Rodríguez
 * @since 27/02/2023
 * @version 2023.02.27
 */
@Entity( tableName = "MarcaBlanca")

public class MarcaBlancaEntity {


    @PrimaryKey( autoGenerate = true ) private long id;
    @ColumnInfo( name = "marca" ) private int marca;
    @ColumnInfo( name = "comercio" ) private int comercio;

    public MarcaBlancaEntity( int marca, int comercio) {

        this.marca = marca;
        this.comercio = comercio;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getName() {
        return this.marca;
    }

    public void setName(int marca) {
        this.marca = marca;
    }

    public int getMarca() {
        return marca;
    }

    public void setMarca(int marca) {
        this.marca = marca;
    }

    public int getComercio() {
        return comercio;
    }

    public void setComercio(int comercio) {
        this.comercio = comercio;
    }
}
