import java.io.Serializable;

class User  implements Serializable {
    private String fullname;
    private String username;
    private String password;
    private int role;

    public String getFullname() {
        return fullname;
    }

    public User(String fullname, String username, String password, int role) {
        this.fullname = fullname;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    @Override
    public String toString() {
        return fullname +'['+
                "username='" + username + "', " +
                "password='" + password + "', " +
                "role=" + (role==1? "'user'": " 'admin'")+ "]";
    }
}
