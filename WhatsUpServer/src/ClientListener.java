import java.net.*;
import java.io.*;
public class ClientListener extends Thread{
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    public ClientListener(Socket socket) {
        this.clientSocket = socket;
    }

    public void run() {
        System.out.println("New client running in thread: " + currentThread());
        try {
            //Streams de entrada y de salida
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));


            String      mssg;
            String      method;
            String[]    clientMsgDecoded;
            while ((mssg = in.readLine()) != null) {
                clientMsgDecoded    = DecodeMessage(mssg);
                method              = clientMsgDecoded[0];

                switch (method){
                    case "LoggMeIn":
                        String res = LoggInManager.CheckUserAuth(clientMsgDecoded[1], clientMsgDecoded[2]);
                        if(res.equals("true")){
                            out.println(res);
                            Usuario loggedUser = (Usuario)UserManager.userDatabase.get(clientMsgDecoded[1]);
                            loggedUser.thread = this;
                            loggedUser.isConnected = true;
                            if(loggedUser.m_pendingMessages.size() > 0){
                                out.println("Has pending messages");
                            }
                        }else{
                            out.println("Invalid user or password");
                        }
                        break;

                    case "SendMessage":
                        Usuario receiver = (Usuario)UserManager.userDatabase.get(clientMsgDecoded[1]);
                        if(receiver != null){

                            if(receiver.isConnected){
                                receiver.thread.SendMessage(clientMsgDecoded[2], clientMsgDecoded[3]);
                            }
                            else{
                                receiver.m_pendingMessages.add(new Message(clientMsgDecoded[2], clientMsgDecoded[3]));
                            }
                        }
                        else {
                            out.println("Non existant user");

                        }
                        break;
                }

                if ("Exit".equals(mssg)) {
                    out.println("bye");
                    break;
                }
                System.out.println(mssg);

            }

            in.close();
            out.close();
            clientSocket.close();
        } catch (IOException e) {
            System.out.println("Cant receive message");

            throw new RuntimeException(e);
        }
    }

    private String[] DecodeMessage(String message){
        String [] clientMsgDecoded = message.split(",");
        return  clientMsgDecoded;
    }

    private void SendMessage(String message, String sender){
        out.println("ReceiveMessage," + message + "," + sender);
    }
}
