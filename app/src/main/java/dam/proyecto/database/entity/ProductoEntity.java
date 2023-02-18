package dam.proyecto.database.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity( tableName = "Productos" )
public class ProductoEntity implements Serializable {

    @PrimaryKey( autoGenerate = false )
    @NonNull
    private String id;
    @ColumnInfo( name = "denominacion" ) private String denominacion;
    @ColumnInfo( name = "marca" ) private int marca;
    @ColumnInfo( name = "unidades" ) private int unidades;
    @ColumnInfo( name = "medida" ) private String medida;
    @ColumnInfo( name = "cantidad" ) private float cantidad;

    public ProductoEntity(String id, String denominacion, int marca, int unidades, String medida, float cantidad) {
        this.id = id;
        this.denominacion = denominacion;
        this.marca = marca;
        this.unidades = unidades;
        this.medida = medida;
        this.cantidad = (cantidad * 1000) / 1000;
    }

    @Ignore
    public ProductoEntity( String id, String denominacion ) {
        this.id = id;
        this.denominacion = denominacion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    public int getMarca() {
        return marca;
    }

    public void setMarca(int marca) {
        this.marca = marca;
    }

    public int getUnidades() {
        return unidades;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }

    public float getCantidad() {
        return cantidad;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }

    public String getMedida() {
        return medida;
    }

    public void setMedida(String medida) {
        this.medida = medida;
    }

    @Override
    public String toString() {
        return "ProductoEntity{" +
                "id='" + id + '\'' +
                ", denominacion='" + denominacion + '\'' +
                ", marca=" + marca +
                ", unidades=" + unidades +
                ", medida='" + medida + '\'' +
                ", cantidad=" + cantidad +
                '}';
    }
}