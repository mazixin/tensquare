import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.text.SimpleDateFormat;

/**
 * @description:
 * @projectName:tensquare_parent
 * @see:PACKAGE_NAME
 * @author:MartinKing
 * @createTime:2021/3/16 14:23
 * @version:1.0
 */
public class DecodeJwt {
    public static void main(String[] args) {
        String token =
                "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4ODQ4Iiwic3ViIjoi5aSq5ZCb5omL5py6IiwiaWF0IjoxNjE1ODc2NTUwLCJyb2xlIjoi6LaF57qn566h55CG5ZGYIiwiZXhwIjoxNjE1ODc2NjEwfQ.xQ-QfzFdvE3apahJcHH4xdG3Kx90aTcXTZrhKJzrSc0";
        Claims claims = Jwts.parser().setSigningKey("zhangsan")
                .parseClaimsJws(token).getBody();
        System.out.println("id:"+claims.getId());
        System.out.println("subject:"+claims.getSubject());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = sdf.format(claims.getIssuedAt());
        System.out.println("IssuedAt"+ time);
        System.out.println("role"+claims.get("role"));
        System.out.println("过期时间:"+sdf.format(claims.getExpiration()));
    }
}
