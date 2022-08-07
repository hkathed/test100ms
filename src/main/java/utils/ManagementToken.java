package utils;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class ManagementToken {

    public String generateManagementToken() {
        Map<String, Object> payload = new HashMap<>();
        payload.put("access_key", "62ed6f04c166400656969ff4");
        payload.put("type", "management");
        payload.put("version", 2);
        return Jwts.builder().setClaims(payload).setId(UUID.randomUUID().toString())
                .setExpiration(new Date(System.currentTimeMillis() + 86400 * 1000))
                .setIssuedAt(Date.from(Instant.ofEpochMilli(System.currentTimeMillis() - 60000)))
                .setNotBefore(new Date(System.currentTimeMillis()))
                .signWith(SignatureAlgorithm.HS256, "rh_o7i73LZdIj5UCe6FwsYGbobQLPJqRtkev0K54LGvdFDtJhbdWe0MmTC_z4xoNsXKItS87EQ7t5NhoR_7-mv6fWzCOjh3o9l9mmWAPs5Jm4xDtWPhjDE1YDpec5kkvld8No8n3tdMfkSpmsu5HG85QNv7qGKdO4mF-Up_Iq14=".getBytes()).compact();
    }
}
