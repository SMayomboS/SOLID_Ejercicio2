package pizzashop;

class Authenticator {
    private final DataBaseManager dbManager;

    public Authenticator(DataBaseManager dbManager) {
        this.dbManager = dbManager;
    }

    public boolean authenticate(String username, String password) {
        return password.equals(dbManager.getPassword(username));
    }

    public boolean register(String username, String password) {
        if (dbManager.userExists(username)) {
            return false;
        } else {
            dbManager.saveUser(username, password);
            return true;
        }
    }
}
