package org.hochnt.springmvcshop.config;

import org.hochnt.springmvcshop.authentication.MyDBAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
// @EnableWebSecurity = @EnableWebMVCSecurity + Extra features
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	MyDBAuthenticationService myDBAauthenticationService;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

		// Các User trong bộ nhớ (MEMORY).

		// auth.inMemoryAuthentication().withUser("user1").password("12345").roles("USER");
		// auth.inMemoryAuthentication().withUser("admin1").password("12345").roles("USER,
		// ADMIN");

		// Các User trong Database
		auth.userDetailsService(myDBAauthenticationService);

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception { // Cấu hình cho việc phân quyền, role khi di chuyển
																	// đến các trang trong hệ thống với csdl người dùng

		http.csrf().disable();

		// Các yêu cầu phải login với vai trò EMPLOYEE, CUSTOMER đã đăng kí hoặc MANAGER.
		// Nếu chưa login, nó sẽ redirect tới trang /login.
		http.authorizeRequests().antMatchers("/orderList", "/order", "/accountInfo")//
				.access("hasAnyRole('ROLE_EMPLOYEE', 'ROLE_MANAGER', 'ROLE_CUSTOMER')");

		// Trang chỉ dành cho MANAGER
		http.authorizeRequests().antMatchers("/product","/createAccount","/manageAccount","/manageAccountInfo","/deacAccount").access("hasRole('ROLE_MANAGER')");

		// Khi người dùng đã login, với vai trò XX.
		// Nhưng truy cập vào trang yêu cầu vai trò YY,
		// Ngoại lệ AccessDeniedException sẽ ném ra.
		http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");

		// Cấu hình cho Login Form.
		http.authorizeRequests().and().formLogin()//

				// Submit URL của trang login
				.loginProcessingUrl("/j_spring_security_check") // Submit URL
				.loginPage("/login")//
				.defaultSuccessUrl("/accountInfo")//login thanh cong sau khi nhan method post thi chuyen toi account infor
				.failureUrl("/login?error=true")//
				.usernameParameter("userName")//
				.passwordParameter("password")

				// Cấu hình cho Logout Page.
				// (Sau khi logout, chuyển tới trang home)
				.and().logout().logoutUrl("/logout").logoutSuccessUrl("/");

	}

}
