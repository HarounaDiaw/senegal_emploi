package sat.gdil.emploi.security;

/**
 * Constants for Spring Security authorities.
 */
public final class AuthoritiesConstants {

    public static final String ADMIN = "ROLE_ADMIN";

    public static final String USER = "ROLE_USER";

    public static final String ANONYMOUS = "ROLE_ANONYMOUS";
    public static final String CANDIDAT = "ROLE_CANDIDAT";
    public static final String RECRUTEUR = "ROLE_RECRUTEUR";

    private AuthoritiesConstants() {}
}
