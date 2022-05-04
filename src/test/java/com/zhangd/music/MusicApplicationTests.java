package com.zhangd.music;

import io.jsonwebtoken.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.UUID;

/**
 * ctrl+f9 快速执行热加载
 */
@SpringBootTest
class MusicApplicationTests {

    long time = 1000*60*60*24;
    String signature = "admin";

    @Test
    void contextLoads() {
    }

    @Test
    public void jwt(){
        /**
         * jwt三部分 Header、Payload、Signature
         */
        JwtBuilder jwtBuilder = Jwts.builder();//构建jwt对象
        String jwtToken = jwtBuilder
                //Header（类型、加密算法）
                .setHeaderParam("typ","JWT")
                .setHeaderParam("alg","HS256")
                //Payload（用户、角色、标题、有效期时间、id）
                .claim("username","zhangda")
                .claim("role","admin")
                .setSubject("admin-test")
                .setExpiration(new Date(System.currentTimeMillis()+time))
                .setId(UUID.randomUUID().toString())
                //Signature（算法、角色、将上面两个进行拼接）
                .signWith(SignatureAlgorithm.HS256,signature)
                .compact();
        System.out.println("生成的token:"+jwtToken);
   }

   @Test
   public void checkToken(){
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZSI6InpoYW5nZGEiLCJyb2xlIjoiYWRtaW4iLCJzdWIiOiJhZG1pbi10ZXN0IiwiZXhwIjoxNjUxNzIzMDExLCJqdGkiOiJmNTJiYmM1Yy0yNDJlLTRjNTUtYTZjYy05MmI2NmQxOTQ4NTgifQ.jyLWyR8-SK4ne4-0YQsbjqC3bwneuGcrpmac_16PnaY";
        JwtParser jwtParser = Jwts.parser();
        Jws<Claims> claimsJws = jwtParser.setSigningKey(signature).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
       System.out.println(claims.get("username"));
       System.out.println(claims.get("role"));
       System.out.println(claims.getId());
       System.out.println(claims.getSubject());
       System.out.println(claims.getExpiration());

   }

}
