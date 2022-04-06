package eu.ensup.shopcrm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

    @Autowired
    UnauthorizedHandler unauthorizedHandler;

    @Autowired
    DeniedAccessHandler accessDeniedHandler;

    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/login","/home").permitAll()
                .antMatchers("/user/**").hasAuthority("ROLE_USER")
                .antMatchers("/register").hasAuthority("ROLE_ADMIN")
                .and()
                .formLogin()
                    .loginPage("/login").defaultSuccessUrl("/user/work").failureUrl("/error")
                    .and()
                    .logout()
                .and()
                .exceptionHandling()
                    .accessDeniedHandler(this.accessDeniedHandler)
                    .authenticationEntryPoint(this.unauthorizedHandler);
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .passwordEncoder(passwordEncoder())
//                .withUser("user").password(passwordEncoder().encode("password")).roles("USER")
//                .and()
//                .withUser("admin").password(passwordEncoder().encode("admin")).roles("ADMIN", "USER");
//    }

    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.jdbcAuthentication()
                .dataSource(dataSource);
//                .withUser(
//                        User.withUsername("user")
//                        .password(passwordEncoder().encode("pass"))
//                        .authorities("ROLE_USER"))
//                .withUser(
//                        User.withUsername("admin")
//                        .password(passwordEncoder().encode("admin"))
//                        .authorities("ROLE_USER","ROLE_ADMIN"));
    }
}