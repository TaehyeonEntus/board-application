package entus.resourceServer.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.Cookie;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.web.servlet.MockMvc;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SecurityTest {
    @Value("${JWT_SECRET}")
    private String secret;
    private static SecretKey secretKey;

    @PostConstruct
    public void init() {
        secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("public")
    public void publicTest() throws Exception {
        //given

        //when

        //then
        mockMvc.perform(get("/public"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/user"))
                .andExpect(status().isUnauthorized());

        mockMvc.perform(get("/admin"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("user")
    public void userTest() throws Exception {
        //given
        String accessToken = createAccessToken("USER");
        String refreshToken = createRefreshToken("USER");

        //when
        Cookie accessTokenCookie = new Cookie("access_token", accessToken);
        Cookie refreshTokenCookie = new Cookie("refresh_token", refreshToken);

        //then
        mockMvc.perform(get("/public")
                        .cookie(accessTokenCookie, refreshTokenCookie))
                .andExpect(status().isOk());

        mockMvc.perform(get("/user")
                        .cookie(accessTokenCookie, refreshTokenCookie))
                .andExpect(status().isOk());

        mockMvc.perform(get("/admin")
                        .cookie(accessTokenCookie, refreshTokenCookie))
                .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("admin")
    public void adminTest() throws Exception {
        //given
        String accessToken = createAccessToken("ADMIN");
        String refreshToken = createRefreshToken("ADMIN");

        //when
        Cookie accessTokenCookie = new Cookie("access_token", accessToken);
        Cookie refreshTokenCookie = new Cookie("refresh_token", refreshToken);

        //then
        mockMvc.perform(get("/public")
                        .cookie(accessTokenCookie, refreshTokenCookie))
                .andExpect(status().isOk());

        mockMvc.perform(get("/user")
                        .cookie(accessTokenCookie, refreshTokenCookie))
                .andExpect(status().isOk());

        mockMvc.perform(get("/admin")
                        .cookie(accessTokenCookie, refreshTokenCookie))
                .andExpect(status().isOk());
    }


    public String createAccessToken(String role) {
        return Jwts.builder()
                .claim("sub", "1")
                .claim("name", "name")
                .claim("role", role)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + Duration.ofMinutes(10).toMillis()))//10분
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public String createRefreshToken(String role) {
        return Jwts.builder()
                .claim("sub", "1")
                .claim("name", "name")
                .claim("role", role)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + Duration.ofDays(7).toMillis()))//7일
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }
}
