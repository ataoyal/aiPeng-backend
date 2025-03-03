package com.aiPeng.aiP.auth.controller;


import com.aiPeng.aiP.auth.entity.User;
import com.aiPeng.aiP.auth.reqParam.LoginReq;
import com.aiPeng.aiP.auth.service.UserService;
import com.aiPeng.aiP.common.ResultInfo;
import com.aiPeng.aiP.common.ResultUtil;
import com.aiPeng.aiP.utils.JwtUtils;
import jakarta.annotation.Resource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.PublicKey;
import java.util.Base64;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Resource
    private AuthenticationManager authManager;
    @Resource
    private JwtUtils jwtUtils;
    @Resource
    private UserService userService;
    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private KeyPair keyPair;

    @GetMapping("/publicKey")
    public String getPublicKey() {
        PublicKey publicKey = keyPair.getPublic();
        return Base64.getEncoder().encodeToString(publicKey.getEncoded());
    }

    @PostMapping("/login")
    public ResultInfo login(@RequestBody LoginReq loginReq) {
        // 执行认证
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginReq.getUsername(), decrypt(loginReq.getPassword()))
        );
        // 生成JWT
        User user = userService.loadUserByUsername(loginReq.getUsername());
        String token = jwtUtils.generateToken(user.getUsername());
        return ResultUtil.getSuccessResult(Map.of("token", token));
    }

    @PostMapping("/register")
    public ResultInfo<String> register(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // 加密密码
        userService.register(user);
        return ResultUtil.getSuccessResult("注册成功");
    }

    private String decrypt(String encryptedData) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, keyPair.getPrivate());
            byte[] bytes = Base64.getDecoder().decode(encryptedData);
            return new String(cipher.doFinal(bytes), StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException("解密失败", e);
        }
    }


}
