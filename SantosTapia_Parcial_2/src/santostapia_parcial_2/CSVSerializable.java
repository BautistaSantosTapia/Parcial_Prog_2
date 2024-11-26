
package santostapia_parcial_2;

public interface CSVSerializable {
    public static NaveEspacial fromCSV(String naveCSV){
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
    }
}
