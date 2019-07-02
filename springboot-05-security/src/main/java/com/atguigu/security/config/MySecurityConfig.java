package com.atguigu.security.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity//开启web安全模式
public class MySecurityConfig extends WebSecurityConfigurerAdapter {//继承 WebSecurityConfigurerAdapter

    //定义授权规则
    @Override
    protected void configure(HttpSecurity http) throws Exception {//重写configure(HttpSecurity http)方法
        //super.configure(http);
        //定制请求的授权规则
        //authorizeRequests-认证请求,antMatchers-路径匹配,permitAll-允许所有人访问，hasRole-有指定角色才可访问
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/level1/**").hasRole("VIP1")
                .antMatchers("/level2/**").hasRole("VIP2")
                .antMatchers("/level3/**").hasRole("VIP3");

        //开启自动配置的登陆功能。效果：如果没有登陆、没有权限就会自动跳转到登陆页面。
        http.formLogin().usernameParameter("user").passwordParameter("pwd")
                .loginPage("/userlogin");
        //formLogin()自动登录配置：（下面这些是由SpringSecurity完成）
        //1、默认是（get请求）/login来到登陆页
        //2、重定向到/login?error表示登陆失败
        //3、更多详细规定
        //4、默认post形式的 /login 代表处理登陆（只要请求发送/login，SpringSecurity就会帮我们去处理，参数默认是username、password，可以在上面修改参数名）
        //5、loginPage()是定制登录页面，告诉SpringSecurity跳转定制登录页的请求。不设置的话，请求为/login使用的就是默认登录页（SpringSecurity给我们提供）。
        //一旦定制loginPage请求，那么 loginPage的post请求就是处理登陆逻辑。比如这里定制/userlogin，那么get方式的是跳转到指定登录页，post请求方式的就是处理登录。
        //可以使用loginProcessingUrl指定处理登录逻辑的请求，不设置的话就跟loginPage一样的请求路径，方式为post。


        //开启自动配置的注销功能。
        http.logout().logoutSuccessUrl("/");//注销成功以后来到首页
        //1、访问 /logout（默认post请求） 表示用户注销，清空session。
        //2、注销成功默认会返回 /login?logout 登录页面；你可以使用logoutSuccessUrl配置注销成功默认返回的页面地址

        //开启记住我功能
        http.rememberMe().rememberMeParameter("remeber");
        //设置http.rememberMe()后，在默认登录页会出现一行记住我选项。
        //登陆成功以后，将cookie（名为remember-me）发给浏览器保存，以后访问页面带上这个cookie，只要通过浏览器检查就可以免登录
        //点击注销会删除cookie
        //rememberMeParameter设置rememberMe的参数名字（这个和定制登录页面记住我标签的名字保持一致），默认就是rememberMe

    }

    //定义认证规则
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //super.configure(auth);
        //inMemoryAuthentication表示从内存中查询用户，如果使用JDBC也可以使用jdbcAuthentication
        auth.inMemoryAuthentication()
                .withUser("zhangsan").password("123456").roles("VIP1","VIP2")
                .and()
                .withUser("lisi").password("123456").roles("VIP2","VIP3")
                .and()
                .withUser("wangwu").password("123456").roles("VIP1","VIP3");

    }
}
