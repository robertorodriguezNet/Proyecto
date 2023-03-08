package dam.proyecto.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 *
 * @author Roberto Rodríguez
 * @since 27/02/2023
 * @version 2023.02.27
 */
@Entity( tableName = "MarcaBlanca")

public class MarcaBlancaEntity {


    @PrimaryKey( autoGenerate = false ) private int id;
    @ColumnInfo( name = "marca" ) private int marca;
    @ColumnInfo( name = "comercio" ) private int comercio;

    public MarcaBlancaEntity( int id, int marca, int comercio) {

        this.id = id;
        this.marca = marca;
        this.comercio = comercio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
