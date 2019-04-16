package com.ftn.authservice.security;


import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11NioProtocol;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
@EnableConfigurationProperties
public class ConnectorConfig {

	@Value("${server.ssl.key-store}")
	private String keystorePath;
	
	@Bean
	public ServletWebServerFactory servletContainer() {
	    TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
	        @Override
	        protected void postProcessContext(Context context) {
	            SecurityConstraint securityConstraint = new SecurityConstraint();
	            securityConstraint.setUserConstraint("CONFIDENTIAL");
	            SecurityCollection collection = new SecurityCollection();
	            collection.addPattern("/*");
	            securityConstraint.addCollection(collection);
	            context.addConstraint(securityConstraint);
	        }
	    };
	    tomcat.addAdditionalTomcatConnectors(redirectConnector());
	    return tomcat;
	}

	private Connector redirectConnector() {
	    Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
	    Http11NioProtocol protocol = (Http11NioProtocol) connector.getProtocolHandler();
	    /*File keyStore = null;
	    try {
            keyStore = getKeyStoreFile();
        } catch (IOException e) {
            throw new IllegalStateException("Cannot access keyStore: [" + keyStore + "]", e);
        }
	    */
	    protocol.setKeystoreFile("C:\\Users\\nemanja\\Desktop\\XML-BSEP\\auth-service\\src\\main\\resources\\MyServer.jks");
	    protocol.setKeystorePass("password");
	    protocol.setTruststoreFile("C:\\Users\\nemanja\\Desktop\\XML-BSEP\\auth-service\\src\\main\\resources\\MyServer.jks");
	    protocol.setTruststorePass("password");
	    protocol.setSSLEnabled(true);
	    protocol.setSslProtocol("TLSv1.2");
	    connector.setScheme("https");
	    connector.setPort(8085);
	    connector.setSecure(true);
	    connector.setRedirectPort(8443);
	    return connector;
	}
	
	
   /* private File getKeyStoreFile() throws IOException {
        ClassPathResource resource = new ClassPathResource(keystorePath);
        
        File jks = File.createTempFile("server_keystore", ".jks");
        InputStream inputStream = resource.getInputStream();
        try {
            FileUtils.copyInputStreamToFile(inputStream, jks);
        } finally {
            IOUtils.closeQuietly(inputStream);
        }
        return jks;
    }*/


}