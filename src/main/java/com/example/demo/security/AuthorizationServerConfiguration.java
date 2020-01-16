package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Value("${jwt.signing-key:oui214hmui23o4hm1pui3o2hp4m1o3h2m1o43}")
    private String jwtSigningKey;
    
    @Value("${jwt_pwd.clientId:live-test1}")
    private String clientId_pwd;

    @Value("${jwt_pwd.client-secret:bGl2ZS10ZXN0}")
    private String clientSecret_pwd;

    @Value("${jwt_pwd.accessTokenValidititySeconds:43200}") // 12 hours
    private int accessTokenValiditySeconds_pwd;

    @Value("${jwt_pwd.authorizedGrantTypes:password,authorization_code,refresh_token}")
    private String[] authorizedGrantTypes_pwd;

    @Value("${jwt_refresh.clientId:um1}")
    private String clientId_refresh;

    @Value("${jwt_refresh.client-secret:VXB0YWtlLUlyb24h}")
    private String clientSecret_refresh;

    @Value("${jwt_refresh.accessTokenValidititySeconds:43200}") // 12 hours
    private int accessTokenValiditySeconds_refresh;

    @Value("${jwt_refresh.authorizedGrantTypes:password,authorization_code,refresh_token}")
    private String[] authorizedGrantTypes_refresh;

    @Value("${jwt_refresh.refreshTokenValiditySeconds:2592000}") // 30 days
    private int refreshTokenValiditySeconds_refresh;
    
    
    
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthorizationServerConfiguration() {
        super();
    }
  
    
    
    // beans

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        final JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setSigningKey(jwtSigningKey);
        return jwtAccessTokenConverter;
    }

    @Bean
    @Primary
    public DefaultTokenServices tokenServices() {
        final DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setTokenStore(tokenStore());
        return tokenServices;
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    // config

    @Override
    public void configure(final ClientDetailsServiceConfigurer clients) throws Exception {
        // @formatter:off
        clients.inMemory()
        .withClient(clientId_pwd)
        .secret(passwordEncoder.encode(clientSecret_pwd))
        .authorizedGrantTypes(authorizedGrantTypes_pwd)
        .scopes("webapp", "read", "write", "trust")
        .autoApprove("webapp")
        .accessTokenValiditySeconds(accessTokenValiditySeconds_pwd)
        .and()
        //
        .withClient(clientId_refresh)
        .secret(passwordEncoder.encode(clientSecret_refresh))
        .authorizedGrantTypes(authorizedGrantTypes_refresh)
        .scopes("webapp", "read", "write", "trust")
        .refreshTokenValiditySeconds(refreshTokenValiditySeconds_refresh)
        .autoApprove("webapp")
        .accessTokenValiditySeconds(accessTokenValiditySeconds_refresh)
        ;
        // @formatter:on
    }

    @Override
    public void configure(final AuthorizationServerEndpointsConfigurer endpoints) {// @formatter:off
        endpoints.
        tokenStore(tokenStore()).
        authenticationManager(authenticationManager).
        userDetailsService(userDetailsService).
        allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST).
        accessTokenConverter(accessTokenConverter());
    }// @formatter:on

    @Override
    public void configure(final AuthorizationServerSecurityConfigurer security) throws Exception {
        security.checkTokenAccess("permitAll()");
        super.configure(security);
    }

}
