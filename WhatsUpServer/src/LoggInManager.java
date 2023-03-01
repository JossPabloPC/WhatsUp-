public class LoggInManager {
    public static String CheckUserAuth(String name, String password){
        String res = "false";

        for (int i = 0; i < UserManager.userDatabase.size(); i++){
            if(UserManager.userDatabase.containsKey(name) && password.equals(((Usuario)(UserManager.userDatabase.get(name))).GetPassword())){
                res =  "true";
                break;
            }
        }
        return res;
    }
}
