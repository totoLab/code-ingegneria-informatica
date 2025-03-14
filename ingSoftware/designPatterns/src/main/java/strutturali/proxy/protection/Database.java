package strutturali.proxy.protection;

public class Database {

    private static Database instance;

    private Database() {}

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public boolean checkCredentials(String username, String password) {
        if ("admin".equals(username) && "admin".equals(password)) {
            return true;
        }
        return false;
    }
}
