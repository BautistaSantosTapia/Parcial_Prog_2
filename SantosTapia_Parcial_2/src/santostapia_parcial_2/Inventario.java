
package santostapia_parcial_2;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Inventario<T> implements Serializable{
    
    private List<T> naves = new ArrayList<>();

    public void agregar(T nave){
        if (nave == null){
            throw new IllegalArgumentException("No podes agregar nulos");
        }
        naves.add(nave);
    }
    
    public void eliminar(int indice) {
        validarIndice(indice);
        naves.remove(indice);
    }
    
    private void validarIndice(int indice){
        if(indice < 0 || indice >= naves.size()){
            throw new IndexOutOfBoundsException("Indice invalido");
        }
    }    
    
    public void listarContenido(){
        System.out.println("Contenido: ");
        listarContenido((Comparator<? super T>)Comparator.naturalOrder());
    }
    
    public void listarContenido(Comparator<? super T> comparator){
        System.out.println("Contenido: ");
        Iterator<T> it = iterator(comparator);
        while(it.hasNext()){
            System.out.println(it.next());
        }
    }

    public void ordenar() {
        if (!naves.isEmpty() && naves.get(0) instanceof Comparable) {
            naves.sort((Comparator<? super T>) Comparator.naturalOrder());
        }
    }

    public void ordenar(Comparator<? super T> comparator) {
        naves.sort(comparator);
    }
    
    public Iterator<T> iterator() {
        if(!naves.isEmpty() && naves.get(0) instanceof Comparable){
            return iterator((Comparator<? super T>) Comparator.naturalOrder());
        }
        return (new ArrayList<>(naves)).iterator();
    }
    
    public Iterator<T> iterator(Comparator<? super T> comparator) {
        List<T> aux = new ArrayList<>(naves);
        aux.sort(comparator);
        return aux.iterator();
    }
    
    public List<T> filtrar(Predicate<? super T> criterio) {
        List<T> toReturn = new ArrayList<>();
        for(T nave : naves){
            if(criterio.test(nave)){
                toReturn.add(nave);
            }
        }
        return toReturn;
    }
    
    public List<T> transformar(Function<? super T, ? extends T> transformacion) {
        List<T> toReturn = new ArrayList<>();
        for(T nave : naves){
            toReturn.add(transformacion.apply(nave));
        }
        return toReturn;
    }
    
    public void guardar(T nave){

        try(FileOutputStream archivo = new FileOutputStream("src/archivos/nave.csv");
                ObjectOutputStream salida = new ObjectOutputStream(archivo))
        {
            salida.writeObject(nave);

        } catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    public void cargar(){
        NaveEspacial naveRecuperada = null;

        try(ObjectInputStream entrada = 
                new ObjectInputStream(new FileInputStream("src/archivos/nave.csv"))){

            naveRecuperada = (NaveEspacial) entrada.readObject();

        } catch(IOException | ClassNotFoundException ex){
            System.out.println(ex.getMessage());
        }

        System.out.println(naveRecuperada);
    }

    
    public void paraCadaElemento(Consumer<? super T> accion) {
        for(T nave : naves){
            accion.accept(nave);
        }
    }
    
}
