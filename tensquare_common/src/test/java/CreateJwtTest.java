import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * @description:
 * @projectName:tensquare_parent
 * @see:PACKAGE_NAME
 * @author:MartinKing
 * @createTime:2021/3/16 14:19
 * @version:1.0
 */
public class CreateJwtTest {
    public static void main(String[] args) {
        JwtBuilder jwtBuilder = Jwts.builder()
                .setId("8848")
                .setSubject("太君手机")
                .setIssuedAt(new Date())
                .claim("role","超级管理员")
                .setExpiration(new Date(new Date().getTime()+60000))
                .signWith(SignatureAlgorithm.HS256, "zhangsan");
        System.out.println(jwtBuilder.compact());
    }
}
