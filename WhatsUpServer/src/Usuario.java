import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

public class Usuario {
    private String m_name;
    private String m_password;
    public Boolean isConnected;
    public Queue<Message> m_pendingMessages;
    public String GetName(){return m_name;}
    public String GetPassword(){return m_password;}

    public ClientListener thread;
    public Usuario(String name, String password)
    {
        m_name      = name;
        m_password  = password;
        isConnected = false;
        m_pendingMessages = new LinkedBlockingQueue<>();
    }
}
