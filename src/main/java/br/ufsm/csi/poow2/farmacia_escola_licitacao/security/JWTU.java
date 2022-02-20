package br.ufsm.csi.poow2.farmacia_escola_licitacao.security;

import br.ufsm.csi.poow2.farmacia_escola_licitacao.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTU {

    public static final long DURATION = Duration.ofMinutes(30).toMillis();

    public String generateToken(Usuario usuario) {
        final Map<String, Object> claims = new HashMap<>();
        claims.put("sub", usuario.getNome());
        claims.put("permissao", usuario.getPermissao());

        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + this.DURATION))
                .signWith(SignatureAlgorithm.HS256, "Date_of_creation:_2022/01/31")
                .compact();
    }

    public String getUsernameToken(String token) {
        if(token != null) {
            return this.parseToken(token).getSubject();
        } else {
            return null;
        }
    }

    public boolean isTokenExpired(String token) {
        if (token != null) {
            return this.parseToken(token).getExpiration().before(new Date());
        } else {
            return false;
        }
    }

    private Claims parseToken(String token) {
        return Jwts
                .parser()
                .setSigningKey("Date_of_creation:_2022/01/31")
                .parseClaimsJws(token.replace("Bearer", ""))
                .getBody();
    }

}
