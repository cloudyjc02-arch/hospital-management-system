package project;

/**
 * Represents the access role assigned to an authenticated user.
 * Stored in the 'users' table as a VARCHAR column named 'role'.
 *
 * SQL to add the column (run once):
 *   ALTER TABLE users ADD COLUMN role VARCHAR(20) NOT NULL DEFAULT 'VIEWER';
 *
 * Example rows:
 *   INSERT INTO users (username, password_hash, role) VALUES
 *     ('xhesiba_saraci', '<bcrypt_hash>', 'NURSE'),
 *     ('elsiana_shehu',  '<bcrypt_hash>', 'MANAGER'),
 *     ('argjend_haldeda','<bcrypt_hash>', 'VIEWER');
 *
 * Use PasswordUtil.hash("plainPassword") to generate the hash.
 */
public enum UserRole {
    NURSE,    // can view patients
    MANAGER,  // can view employees + stats
    VIEWER    // can view details
}