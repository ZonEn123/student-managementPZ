package com.example.studentmanagement.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.example.studentmanagement.common.JwtUtil;
import com.example.studentmanagement.common.Result;
import com.example.studentmanagement.dto.LoginRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "登录认证", description = "用户登录、获取JWT token接口")
public class LoginController {

    // 演示用固定账号，实际项目请从数据库查询
    private static final String VALID_USERNAME = "admin";
    private static final String VALID_PASSWORD = "123456";

    @PostMapping("/login")
    @Operation(summary = "用户登录", description = "登录成功返回JWT token，后续请求需在header中携带")
    public Result<String> login(@RequestBody LoginRequest loginRequest) {
        // 校验账号密码
        if (VALID_USERNAME.equals(loginRequest.getUsername())
                && VALID_PASSWORD.equals(loginRequest.getPassword())) {
            // 生成token
            String token = JwtUtil.generateToken(1L, loginRequest.getUsername());
            return Result.success(token);
        }
        return Result.error("用户名或密码错误");
    }
}