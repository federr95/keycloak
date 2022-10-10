package com.example.web_app_test.config;

//import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
//import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
//import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
//import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Import;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
//import org.springframework.security.core.session.SessionRegistry;
//import org.springframework.security.core.session.SessionRegistryImpl;
//import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
//import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

//@KeycloakConfiguration
//@EnableGlobalMethodSecurity(jsr250Enabled = true)
//@Import(KeycloakSpringBootConfigResolver.class)
//public class SecurityConfig extends KeycloakWebSecurityConfigurerAdapter {
//    /**
//     * Registers the KeycloakAuthenticationProvider with the authentication manager.
//     */
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        KeycloakAuthenticationProvider authenticationProvider = new KeycloakAuthenticationProvider();
//        authenticationProvider.setGrantedAuthoritiesMapper(new SimpleAuthorityMapper());
//        auth.authenticationProvider(authenticationProvider);
//    }
//
//    /**
//     * Defines the session authentication strategy.
//     */
//    @Bean
//    @Override
//    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
//        return new RegisterSessionAuthenticationStrategy(buildSessionRegistry());
//    }
//
//    @Bean
//    protected SessionRegistry buildSessionRegistry() {
//        return new SessionRegistryImpl();
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception
//    {
//        super.configure(http);
//        http
//                .csrf().disable()
//                .authorizeRequests()
////                .antMatchers("/API/employee/allEmployee").hasAnyRole("user", "admin")
////                .antMatchers("/API/employee/addEmployee").hasRole("admin")
////                .antMatchers("/API/companies/allCompanies").permitAll()
////                .antMatchers("/API/companies/addCompany").hasRole("admin")
////                .antMatchers("/API/companies/**/enrollSupplier").permitAll()
////                .antMatchers("/API/employee/**/addRole").permitAll()
//                .anyRequest().permitAll();
//    }
//}