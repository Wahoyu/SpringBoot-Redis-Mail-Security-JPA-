package com.example.controller;

import com.example.entity.Account;
import com.example.entity.resp.RestBean;
import com.example.repo.AccountRepository;
import io.swagger.annotations.Api;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(tags = "用户操作接口", description = "包括用户登录、注册、验证码请求等操作。")
@RestController
@RequestMapping("/api/user")
public class AccountController {

    @Resource
    AccountRepository repository;

    //获取用户信息
    @GetMapping("/info")
    public RestBean<Account> info(){
        SecurityContext context = SecurityContextHolder.getContext();
        Account account = repository.findAccountByUsername(context.getAuthentication().getName());
        account.setPassword("");
        return new RestBean<>(200,"请求成功",account);
    }

}