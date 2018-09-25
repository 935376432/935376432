package com.demo.security;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.savedrequest.NullRequestCache;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

/**
 * �����¼��֤����spring security
 *
 */

@EnableWebSecurity
@EnableGlobalMethodSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${api.csrftoken}")
    private String csrfTokenApi;

    @Value("${api.login}")
    private String loginApi;

    @Value("${api.logout}")
    private String logoutApi;

    @Bean
    public GenericAuthenticationSuccessHandler genericAuthenticationSuccessHandler() {
        return new GenericAuthenticationSuccessHandler();
    }

    @Bean
    public MixedAuthenticationProvider mixedAuthenticationProvider() {
        MixedAuthenticationProvider provider = new MixedAuthenticationProvider();
        provider.setPreAuthenticationChecks(new AccountStatusUserDetailsChecker());
        return provider;
    }


    @Bean
    public RequestMatcher requireCsrfProtectionMatcher() {
        return new RequestMatcher() {

            @Override
            public boolean matches(HttpServletRequest request) {
                String servletPath = request.getServletPath();
                if (servletPath != null && (servletPath.contains("/api"))) {
                    return false;
                }
                return !Pattern.compile("^(GET|HEAD|TRACE|OPTIONS)$").matcher(request.getMethod()).matches();
            }
        };
    }

    /**
     * ��֤
     * @param auth
     * @throws Exception
     */
    /*@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        System.out.println(auth);

        auth.inMemoryAuthentication().withUser("admin").password("admin").roles("USER");
    }*/

    /**
     * ������֤�����ʺ�����
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //����ڴ��е����룬
        auth.eraseCredentials(true);
        auth.authenticationProvider(mixedAuthenticationProvider());

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //ȷ������Ӧ���е�����������Ҫ�û�����֤
        //�����û����л��ڱ�����֤
        //�����û�ʹ��HTTP������֤������֤
        http.authorizeRequests().anyRequest().authenticated()
        .and().formLogin().and().httpBasic();
        //ָ����¼ҳ��·��
        //���Ǳ������������û��������ǵĵ�¼ҳ������Ϊ��֤���û��������formLogin().permitAll()����������ڱ���¼�����е�URL�������û��ķ��ʡ�
        //http.formLogin().loginPage("/login").failureUrl("/login?error").successHandler(genericAuthenticationSuccessHandler()).permitAll();
        //����ǳ�
        http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/login?logout")).permitAll();

        http
        .authorizeRequests()
            .antMatchers("/api/**", "/web-demo", "/about","/login*").permitAll();

        http.csrf().disable();
        //http.csrf().requireCsrfProtectionMatcher(requireCsrfProtectionMatcher());


        /*http
        .authorizeRequests()
            .antMatchers("/api/**", "/signup", "/about","/login*").permitAll()
            .antMatchers("/admin/**").hasRole("ADMIN")
            .antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')")
            .anyRequest().authenticated()
            .and().formLogin().loginPage("/login").failureUrl("/login?error").successHandler(genericAuthenticationSuccessHandler()).permitAll();*/

        /*http.authorizeRequests().antMatchers(csrfTokenApi).permitAll()
        .and().formLogin().loginProcessingUrl("/login")
        .successHandler(genericAuthenticationSuccessHandler());*/

        /*http
        .authorizeRequests()
            .antMatchers("/", "/home").permitAll()
            .anyRequest().authenticated()
            .and()
        .formLogin()
            .loginPage("/login")
            .successHandler(genericAuthenticationSuccessHandler())
            .permitAll()
            .and()
        .logout()
            .permitAll();*/
        http
        .authorizeRequests()
          .antMatchers("/api/**", "/web-demo", "/about","/login*").permitAll()
          .anyRequest().authenticated()
        .and()
          .formLogin()
          .loginPage("/login")
          .permitAll()
        .and()
          .logout()
          .permitAll()
        .and()
          .requestCache().requestCache(new NullRequestCache());


    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("user").password("password").roles("USER");
    }

}
