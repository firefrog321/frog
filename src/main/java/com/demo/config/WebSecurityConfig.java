/*
package com.demo.config;

import com.demo.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

*/
/**
 * 通过 @EnableWebSecurity注解开启Spring Security的功能
 * EnableGlobalMethodSecurity(prePostEnabled = true)这个注解，可以开启security的注解，
 * 我们可以在需要控制权限的方法上面使用@PreAuthorize，@PreFilter这些注解。
 *
 * @author Mr.Deng
 * Created on 2018/3/29 22:28
 **//*

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    protected UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }
    */
/**
     * 配置通过重写configure方法添加我们自定义的认证方式
     *
     * @param auth
     * @throws Exception
     *//*

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService());
    }

    */
/**
     * 配置授权和认证规则
     *
     * @param http
     * @throws Exception
     *//*

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(
                        "/css/**",
                        "/images/**",
                        "/js/**",
                        "/editormd/**",
                        "/",
                        "/index",
                        "/article/**"
                ).permitAll()//符合匹配的，允许所有
                //.anyRequest().authenticated()//任何请求，必须认证过才才能操作
                .anyRequest().permitAll()
                .and()
                .formLogin().loginPage("/login")//设置登录页面
                //设置默认登录失败成功跳转页面
                .defaultSuccessUrl("/article/14").failureUrl("/login?error").permitAll()
                */
/*//*
/开启cookie保存用户数据
                .rememberMe()
                //设置cookie有效期
                .tokenValiditySeconds(60 * 60 * 24 * 7)*//*

                .and()
                .logout()//允许登出
                .logoutUrl("/login") //默认注销行为为logout，可以修改
                //设置注销成功后跳转页面，默认是跳转到登录页面
                .logoutSuccessUrl("/article/14").permitAll();

    }


}*/
