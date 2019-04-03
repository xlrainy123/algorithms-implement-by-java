package annotation;

public class PasswordUtils {

    @UserCase(id=1, description="password must contains one number!")
    public boolean validate(String password){
        return password.matches("\\w*\\D\\w*");
    }

    @UserCase(id=2)
    public String encryptPwd(String password){
        return new StringBuilder(password).reverse().toString();
    }
}
