package kit.security.util;

import kit.security.transfer.JwtUserDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * convenience class to generate a token for testing your requests.
 * Make sure the used secret here matches the on in your application.yml
 *
 */
public class JwtTokenGenerator {

    /**
     * Generates a JWT token containing username as subject, and userId and role as additional claims. These properties are taken from the specified
     * User object. Tokens validity is infinite.
     *
     * @param u the user for which the token will be generated
     * @return the JWT token
     */
    public static String generateToken(JwtUserDto u, String secret) {
        Claims claims = Jwts.claims().setSubject(u.getUsername());
        claims.put("userId", u.getId() + "");
        claims.put("role", u.getRole());
        claims.put("surname",u.getSurname());
        claims.put("age",u.getAge());

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        JwtUserDto user = new JwtUserDto();
        user.setId(123L);
        user.setUsername("Musa");
        user.setSurname("Aliyev");
        user.setAge("27");
        user.setRole("admin");

        System.out.println("**************************************\n\n" + generateToken(user, "my-very-secret-key-by-Musa-Aliyev") + "\n\n**************************************");
    }
}