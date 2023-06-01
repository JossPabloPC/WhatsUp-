public class LoggInManager {
    public static String CheckUserAuth(String id){
        String res = "false";

        for (int i = 0; i < UserManager.userDatabase.size(); i++){
            if(UserManager.userDatabase.containsKey(id)){
                res =  "true";
                break;
            }
        }
        return res;
    }
}
