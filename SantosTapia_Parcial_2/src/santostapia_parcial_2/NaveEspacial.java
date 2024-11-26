
package santostapia_parcial_2;

import java.io.Serializable;

public class NaveEspacial implements Comparable<NaveEspacial>, CSVSerializable, Serializable{
    
    private String nombre;
    private Categoria categoria;
    private int capacidadTripulacion;
    private int id;

    public NaveEspacial(String nombre, Categoria categoria, int capacidadTripulacion, int id) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.capacidadTripulacion = capacidadTripulacion;
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public int getCapacidadTripulacion() {
        return capacidadTripulacion;
    }

    public int getId() {
        return id;
    }
    

    @Override
    public String toString() {
        return "NaveEspacial{" + "nombre=" + nombre + ", categoria=" + categoria + ", capacidadTripulacion=" + capacidadTripulacion + ", id=" + id + '}';
    }

    @Override
    public int compareTo(NaveEspacial nave) {
        return nombre.compareTo(nave.nombre);
    }
    
    public String toCSV(){
        return nombre + "," + categoria.toString() + "," + capacidadTripulacion + "," + id;
    }
    
    /*public static NaveEspacial fromCSV(String naveCSV){
        String[] values = naveCSV.split(",");
        NaveEspacial toReturn = null;
        if(values.length == 4){

            String nombre = values[0];
            Categoria categoria = Categoria.valueOf(values[1]);
            int capacidadTripulacion = Integer.parseInt(values[2]);
            int dni = Integer.parseInt(values[3]);

            toReturn = new NaveEspacial(nombre, categoria, capacidadTripulacion, dni);
        }
        return toReturn;
    }*/
}
