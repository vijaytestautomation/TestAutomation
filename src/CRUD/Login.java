package CRUD;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class Login {

    private String Username;
    private String Password;

    public Login(String Username, String Password) {
        this.Username = Username;
        this.Password = Password;
    }

}
