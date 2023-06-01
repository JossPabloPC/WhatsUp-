import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

public class Usuario {
    private String m_id;
    public String m_password;
    public Boolean isConnected;
    public Queue<Message> m_pendingMessages;
    public String GetID(){return m_id;}
    public String GetPassword(){return m_password;}

    public ClientListener thread;
    public Usuario(String name, String password)
    {
        m_id = name;
        m_password  = password;
        isConnected = false;
        m_pendingMessages = new LinkedBlockingQueue<>();
    }
}
