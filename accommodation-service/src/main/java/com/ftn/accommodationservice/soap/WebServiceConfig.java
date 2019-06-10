package com.ftn.accommodationservice.soap;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.soap.security.wss4j2.Wss4jSecurityInterceptor;
import org.springframework.ws.soap.security.wss4j2.callback.KeyStoreCallbackHandler;
import org.springframework.ws.soap.security.wss4j2.support.CryptoFactoryBean;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {
	
	@Value("${web.service.username}")
    String userName;

    @Value("${web.service.password}")
    String password;
	
	@Bean
	public ServletRegistrationBean messageDispatcherServlet(ApplicationContext context) {
		MessageDispatcherServlet messageDispatcherServlet = new MessageDispatcherServlet();
		messageDispatcherServlet.setApplicationContext(context);
		messageDispatcherServlet.setTransformWsdlLocations(true);
		return new ServletRegistrationBean(messageDispatcherServlet, "/ws/*");
	}

	@Bean(name = "accommodations")
	public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema coursesSchema) {
		DefaultWsdl11Definition definition = new DefaultWsdl11Definition();
		definition.setPortTypeName("AccommodationPort");
		definition.setTargetNamespace("http://ftn.com/accommodationservice/xsd");
		definition.setLocationUri("/ws");
		definition.setSchema(coursesSchema);
		return definition;
	}

	@Bean
	public XsdSchema coursesSchema() {
		return new SimpleXsdSchema(new ClassPathResource("soap.xsd"));
	}
	

	/*@Bean
    public SimplePasswordValidationCallbackHandler securityCallbackHandler() {
        SimplePasswordValidationCallbackHandler callbackHandler = new SimplePasswordValidationCallbackHandler();
        Properties users =  new  Properties();
        users.setProperty("admin" , "secret");
        callbackHandler.setUsers(users);
        return callbackHandler;
    }
*/

	
	@Bean
    public KeyStoreCallbackHandler securityCallbackHandler(){
        KeyStoreCallbackHandler callbackHandler = new KeyStoreCallbackHandler();
        callbackHandler.setPrivateKeyPassword("password");
        return callbackHandler;
    }

    @Bean
    public Wss4jSecurityInterceptor securityInterceptor() throws Exception {
        Wss4jSecurityInterceptor securityInterceptor = new Wss4jSecurityInterceptor();

        // validate incoming request
        securityInterceptor.setValidationActions("Timestamp Signature Encrypt");
        securityInterceptor.setSecurementActor("accommodation");
        
        securityInterceptor.setValidationSignatureCrypto(getCryptoFactoryBean().getObject());
        securityInterceptor.setValidationDecryptionCrypto(getCryptoFactoryBean().getObject());
        securityInterceptor.setValidationCallbackHandler(securityCallbackHandler());

        // encrypt the response
        securityInterceptor.setSecurementEncryptionUser("accommodation");
        securityInterceptor.setValidationActor("accommodation");
        securityInterceptor.setSecurementEncryptionParts("{Content}{http://ftn.com/accommodationservice/xsd}GetAllAdditionalServiceResponse");
        securityInterceptor.setSecurementEncryptionCrypto(getCryptoFactoryBean().getObject());

        // sign the response
        securityInterceptor.setSecurementActions("Signature Encrypt");
        securityInterceptor.setSecurementUsername("accommodation");
        securityInterceptor.setSecurementPassword("password");
        securityInterceptor.setSecurementSignatureCrypto(getCryptoFactoryBean().getObject());

        return securityInterceptor;
    }

    @Bean
    public CryptoFactoryBean getCryptoFactoryBean() throws IOException {
        CryptoFactoryBean cryptoFactoryBean = new CryptoFactoryBean();
        cryptoFactoryBean.setKeyStorePassword("password");
        cryptoFactoryBean.setKeyStoreLocation(new ClassPathResource("accommodation.jks"));
        return cryptoFactoryBean;
    }

    @Override
    public void addInterceptors(List<EndpointInterceptor> interceptors) {
        try {
            interceptors.add(securityInterceptor());
        } catch (Exception e) {
            throw new RuntimeException("could not initialize security interceptor");
        }
    }


}
