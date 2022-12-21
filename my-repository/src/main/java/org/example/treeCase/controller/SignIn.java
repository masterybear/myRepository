package org.example.treeCase.controller;

import org.example.treeCase.javaConfig.JavaConfig;
import org.example.treeCase.model.Result;
import org.example.treeCase.model.User;
import org.example.treeCase.service.UserService;
import org.example.treeCase.utils.JwtUtils;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class SignIn {
    AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(JavaConfig.class);
    public UserService service = (UserService) ctx.getBean("userService");
    public Result result = (Result) ctx.getBean("result");

    @PostMapping("/signIn")
    public Result signIn(String token, String email, String password) {
        //查询邮箱是否注册
        User flag = service.queryEmail(email);
        if (flag != null) {
            //查询密码是否正确
            User user = service.signIn(email, password);
            if (user != null) {
                //密码正确
                //查验token
                if (token == null || token.isEmpty() || JwtUtils.verifyToken(token)) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("email", email);
                    String invalidToken = JwtUtils.createToken(map);
                    result.setSignInfo("invalidToken");
                    result.setToken(invalidToken);
                } else {
                    result.setSignInfo("validToken");
                }
            } else {
                // 密码错误
                result.setSignInfo("pwdErr");
            }
        } else {
            // 邮箱错误
            result.setSignInfo("fail");
        }
        return result;
    }

    @PostMapping("/signUp")
    public String signUp(User user) {
        User flag = service.queryName(user.getUsername());
        if (flag == null) {
            //用户名未被使用
            User email = service.queryEmail(user.getEmail());
            if (email == null) {
                //邮箱未被注册
                service.signUp(user);
                return "success";
            } else {
                // 邮箱已被注册
                return "emailErr";
            }
        }else {
            //用户名已被使用
            return "Unavailable";
        }
    }
}
