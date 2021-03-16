package com.tensquare.spit.interceptor;

import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @description:
 * @projectName:tensquare_parent
 * @see:com.tensquare.user.interceptor
 * @author:MartinKing
 * @createTime:2021/3/16 16:34
 * @version:1.0
 */
@Component
public class JwtInterceptor implements HandlerInterceptor {
    @Autowired
    private JwtUtil jwtUtil;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String header = request.getHeader("Authorization");
        //验证获取到了header 并且 里面不能为空格字符串
        if (header != null && StringUtils.isNotBlank(header)) {
            //验证符合格式的header
            if (header.startsWith("Bearer ")){
                String token = header.substring(7);
                try {
                    Claims claims = jwtUtil.parseJWT(token);
                    if (claims != null) {
                        if ("admin".equals(claims.get("roles"))){ //如果是管理员，则给request域中添加属性admin_claims
                            request.setAttribute("admin_claims",token);
                        }
                        if ("user".equals(claims.get("roles"))) {
                            request.setAttribute("user_claims", token);
                        }
                    }
                } catch (Exception e) {
                    throw new RuntimeException("令牌不正确");
                }

            }
        }
        System.out.println("拦截器执行了");
        return true; //true表示放行，false表示不放行
    }
}
