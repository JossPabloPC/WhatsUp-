

public class Main {
    public static void main(String[] args) {
        //Creación de usuarios
        UserManager.LoadUsers();

        //Iniciar Server
        Server server = new Server();
        server.StartServer(50000);
    }
}