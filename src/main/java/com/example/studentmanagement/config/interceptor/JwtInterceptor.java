package com.example.studentmanagement.config.interceptor;

import com.example.studentmanagement.common.JwtUtil;
import com.example.studentmanagement.common.Result;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * JWT认证拦截器：校验接口请求的token合法性
 */
public class JwtInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 放行OPTIONS预检请求（解决跨域问题）
        if ("OPTIONS".equals(request.getMethod())) {
            return true;
        }

        // 从请求头获取token
        String token = request.getHeader("Authorization");

        // 无token直接拦截
        if (token == null || token.isEmpty()) {
            returnErrorResponse(response, Result.error(401, "未登录，请先登录"));
            return false;
        }

        // 处理Bearer前缀（前端通用格式：Bearer token值）
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        // 校验token合法性
        if (!JwtUtil.verifyToken(token)) {
            returnErrorResponse(response, Result.error(401, "token无效或已过期，请重新登录"));
            return false;
        }

        // 校验通过，放行请求
        return true;
    }

    /**
     * 封装错误响应
     */
    private void returnErrorResponse(HttpServletResponse response, Result<?> result) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        writer.write(new ObjectMapper().writeValueAsString(result));
        writer.flush();
        writer.close();
    }
}