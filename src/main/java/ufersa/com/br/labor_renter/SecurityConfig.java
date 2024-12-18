package ufersa.com.br.labor_renter;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ufersa.com.br.labor_renter.domain.services.ContractorDetailsServiceImpl;
import ufersa.com.br.labor_renter.filters.AuthorizationFilter;
import ufersa.com.br.labor_renter.filters.LoginFilter;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig  {
    private final ContractorDetailsServiceImpl userDetailsService;
    public SecurityConfig(ContractorDetailsServiceImpl userDetailsService){
        this.userDetailsService = userDetailsService;
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception{
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        AuthenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());

        http.cors(cors -> cors.configurationSource(corsConfigurationSource())).csrf(csrf -> csrf.disable())
                . authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers(HttpMethod.POST,"/api/v1/login","/api/v1/contractor/").permitAll()
                        .requestMatchers(HttpMethod.GET,"/labor_renter/**").permitAll()
                        .requestMatchers(HttpMethod.POST,"/labor_renter?**").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(new LoginFilter("api/v1/login",authenticationManager), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new AuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
                .authenticationManager(authenticationManager)
                .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return null;
    }
}
