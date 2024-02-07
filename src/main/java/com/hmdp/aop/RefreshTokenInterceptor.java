package com.hmdp.aop;

import cn.hutool.core.bean.BeanUtil;
import com.hmdp.dto.UserDTO;
import com.hmdp.utils.RedisService;
import com.hmdp.utils.UserHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

import static com.hmdp.utils.RedisConstants.LOGIN_USER_KEY;

public class RefreshTokenInterceptor implements HandlerInterceptor {
    private final RedisService redisSerivce;

    public RefreshTokenInterceptor(RedisService redisService) {
        this.redisSerivce = redisService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //1. 获取请求头中的Token
        String token = request.getHeader("authorization");
        if (StringUtils.isEmpty(token)) {
            response.setStatus(401);
            return false;
        }
        //2.基于Token获取redis中的用户
        Map<Object, Object> user = redisSerivce.entries(LOGIN_USER_KEY + token);
        //3. 判断用户是否存在
        if (user.isEmpty()){
            //4. 不存在，拦截
            response.setStatus(401);
            return false;
        }
        UserDTO userDTO = BeanUtil.fillBeanWithMap(user, new UserDTO(), false);
        //5. 存在 保存用户信息到ThreadLocal
        UserHolder.saveUser(userDTO);
        //6. 放行
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //移除用户
        UserHolder.removeUser();
    }
}
