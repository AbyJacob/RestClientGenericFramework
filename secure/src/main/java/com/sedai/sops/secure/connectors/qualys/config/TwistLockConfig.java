/*
 * package com.sedai.sops.secure.connectors.qualys.config;
 * 
 * import java.io.IOException; import java.util.Collections; import
 * java.util.List;
 * 
 * import org.slf4j.Logger; import org.slf4j.LoggerFactory; import
 * org.springframework.beans.factory.annotation.Value; import
 * org.springframework.context.annotation.Bean; import
 * org.springframework.context.annotation.Configuration; import
 * org.springframework.http.HttpHeaders; import
 * org.springframework.http.HttpRequest; import
 * org.springframework.http.MediaType; import
 * org.springframework.http.client.ClientHttpRequestExecution; import
 * org.springframework.http.client.ClientHttpRequestInterceptor; import
 * org.springframework.http.client.ClientHttpResponse; import
 * org.springframework.web.client.RestTemplate;
 * 
 * //[TODO] //may need to change approach to accomodate multiple resttemplate
 * versions for each interface
 * //https://stackoverflow.com/questions/59144415/multiple-rest-templates-in-
 * spring-boot //user @Qualifier to call out the specific injected bean
 * //https://stackoverflow.com/questions/38516644/how-to-create-or-configure-
 * rest-template-using-bean-in-spring-boot
 * 
 * @Configuration public class TwistLockConfig { Logger LOGGER =
 * LoggerFactory.getLogger(TwistLockConfig.class);
 * 
 * //[TODO] fetch cred from DB
 * 
 * @Value("${connection.qualys.username}") private String uName;
 * 
 * @Value("${connection.qualys.password}") private String pPass;
 * 
 * private HttpHeaders header() { HttpHeaders httpHeaders = new HttpHeaders();
 * httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
 * httpHeaders.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
 * httpHeaders.setBasicAuth(uName, pPass);
 * LOGGER.info("Create header :",httpHeaders.toString()); return httpHeaders; }
 * 
 * @Bean (name="twistRestTemplate") public RestTemplate twistRestTemplate() {
 * 
 * Chuck idea to timeout restTemplate due to following
 * https://stackoverflow.com/questions/47467663/java-9-httpclient-java-lang-
 * noclassdeffounderror-jdk-incubator-http-httpclient
 * 
 * HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory
 * = new HttpComponentsClientHttpRequestFactory();
 * httpComponentsClientHttpRequestFactory.setConnectTimeout(10000); //set
 * connection timeout 10s RestTemplate restTemplate = new
 * RestTemplate(httpComponentsClientHttpRequestFactory);
 * 
 * 
 * RestTemplate qualysRestTemplate = new RestTemplate();
 * qualysRestTemplate.setInterceptors(List.of(new
 * qualysRestTemplateInterceptor()));
 * LOGGER.info("Created RestTemplate Bean for Twist :"); return
 * qualysRestTemplate; }
 * 
 * 
 * //take interceptor out private class qualysRestTemplateInterceptor implements
 * ClientHttpRequestInterceptor{
 * 
 * public ClientHttpResponse intercept(HttpRequest request, byte[] body,
 * ClientHttpRequestExecution execution) throws IOException{
 * 
 * 
 * request.getHeaders().addAll(header());
 * LOGGER.info("Attempt Rest Call Twist:",request.toString());
 * ClientHttpResponse response = null; try { System.out.println("here 1");
 * response = execution.execute(request, body); System.out.println("here 2");
 * }catch(IOException ie) { System.out.println("here 3");
 * LOGGER.error("IO Error While Connecting to Twist Client"); throw ie;
 * }catch(Exception e){ System.out.println("here 4");
 * LOGGER.error("Error While Connecting to Twist Client"); } Throws
 * java.io.IOException: stream is closed finally { if(response != null)
 * response.close(); }
 * 
 * return response; } } }
 */