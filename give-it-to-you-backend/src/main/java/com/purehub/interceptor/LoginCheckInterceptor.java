package com.purehub.interceptor;



import com.purehub.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


        String jwt = request.getHeader("Authorization");
        System.out.println(jwt);
        if(!StringUtils.hasLength(jwt))
        {
            response.getWriter().write("NOT_LOGIN");
            return false;
        }
        try{
            JwtUtils.parseJWT(jwt);
        }catch (Exception e){
            e.printStackTrace();
            response.getWriter().write("NOT_LOGIN");
            return false;
        }
        return true;
    }
}
