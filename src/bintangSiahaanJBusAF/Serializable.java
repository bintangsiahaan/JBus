package bintangSiahaanJBusAF;
import java.util.HashMap;

/**
 * Write a description of class Serializable here.
 *
 * @author Bintang Siahaan
 * @21/9/2023
 */
public class Serializable
{
    public final int id;
    private static HashMap<Class<?>,Integer> mapCounter = new HashMap<>();
    protected Serializable(){
        Integer count = mapCounter.get(getClass());
        if(count == null){
            count = 0;
        }else{
            count += 1;
        }
        mapCounter.put(getClass(), count);
        this.id = count;
    }

    public static <T extends Serializable> Integer setLastAssignedId(Class<T> Class, int id){
        return mapCounter.put(Class, id);
    }
    public static <T extends Serializable> Integer getLastAssignedId(Class<T> Class){
        return mapCounter.get(Class);
    }
    public Serializable(int id) {
        this.id = id;
    }
        public int compareTo(Serializable cmp){
            return Integer.compare(this.id, cmp.id);
        }

        public boolean equals(Object object){
            return object instanceof Serializable && ((Serializable)object).id == this.id;
        }

        public boolean equals(Serializable cmp){
            return cmp.id == id;
        }

}
