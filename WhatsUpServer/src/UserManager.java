import java.util.*;
import java.util.Enumeration;

public class UserManager {
    public static Hashtable userDatabase;

    public static void LoadUsers(){
        //LEER DE ARCHIVO MEJOR
        userDatabase = new Hashtable();
        userDatabase.put("Pablo", new Usuario("Pablo", "Panamericana"));
        userDatabase.put("Juan", new Usuario("Juan", "Amigo"));

    }
}
