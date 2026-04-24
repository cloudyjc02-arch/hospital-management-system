package project;

/**
 * Immutable result returned by DatabaseHelper.authenticateUser().
 */
public class AuthResult {

    private final boolean success;
    private final String username;
    private final UserRole role;

    private AuthResult(boolean success, String username, UserRole role) {
        this.success  = success;
        this.username = username;
        this.role     = role;
    }

    /** Factory for a successful login. */
    public static AuthResult ok(String username, UserRole role) {
        return new AuthResult(true, username, role);
    }

    /** Factory for a failed login. */
    public static AuthResult fail() {
        return new AuthResult(false, null, null);
    }

    public boolean isSuccess()  { return success;  }
    public String  getUsername(){ return username; }
    public UserRole getRole()   { return role;     }
}