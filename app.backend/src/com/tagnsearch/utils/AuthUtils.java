package com.tagnsearch.utils;

import com.tagnsearch.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.*;

/**
 * Created by JS on 8/21/16.
 */
public final class AuthUtils {

    private static final String ENCRYPTION_TYPE_MD5 = "MD5";
    private static final String SECRETKEY = new BigInteger(130, new SecureRandom()).toString(32);
    private static final Map<String, String> USERTOKENS = new HashMap<>();


    public static String encryptPassword(final String password) {
        try {
            MessageDigest md = MessageDigest.getInstance(ENCRYPTION_TYPE_MD5);
            md.update(password.getBytes());
            final byte[] encryptedPassword = md.digest();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < encryptedPassword.length; i++) {
                sb.append(Integer.toString((encryptedPassword[i] & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new InternalError(e);
        }
    }

    public static void addToken(final User user, final String token) {
        USERTOKENS.put(user.getUsername(), token);
    }

    public static void removeToken(final User user) {
        USERTOKENS.remove(user.getUsername());
    }

    public static boolean isTokenActicve(final String token) {
        return USERTOKENS.containsValue(token);
    }

    public static boolean hasActiveToken(final User user) {
        return USERTOKENS.containsKey(user.getUsername());
    }

    public static String getTokenByUser(final User user) {
        return USERTOKENS.get(user.getUsername());
    }

    public static String generateToken(final User user) {
        final String token = Jwts.builder().setSubject(user.getUsername())
                .claim("roles", user.getRole()).setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, SECRETKEY).compact();
        addToken(user, token);
        return token;
    }

    public static Claims getClaims(final String token) {
        return Jwts.parser()
                .setSigningKey(SECRETKEY)
                .parseClaimsJws(token)
                .getBody();
    }

    private AuthUtils(){
        throw new AssertionError();
    }


}
