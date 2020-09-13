package com.sedai.sops.secure.connectors.qualys.config;

import java.io.IOException;
import java.time.Duration;
import java.util.Collections;
import java.util.List;

import org.json.XML;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RestTemplate;


//[TODO]
//may need to change approach to accomodate multiple resttemplate versions for each interface
//https://stackoverflow.com/questions/59144415/multiple-rest-templates-in-spring-boot
//user @Qualifier to call out the specific injected bean
//https://stackoverflow.com/questions/38516644/how-to-create-or-configure-rest-template-using-bean-in-spring-boot

@Configuration
public class TwistLockConfig {
	Logger LOGGER = LoggerFactory.getLogger(QualysConfig.class);

	//[TODO] fetch cred from DB 
	@Value("${connection.qualys.username}")
	private String uName;
	@Value("${connection.qualys.password}")
	private String pPass;

	public static final String QualysAgentIdentifier = "Cloud Agent";
	public static final String QualysAgentHeader = "X-Requested-With";

	
	private HttpHeaders header() {
		
		HttpHeaders httpHeaders = new HttpHeaders(); 
		httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_XML));
		httpHeaders.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_XML_VALUE);
		httpHeaders.set(QualysAgentHeader,QualysAgentIdentifier);
		//Commented Auth header for testing open API 
		httpHeaders.setBasicAuth(uName, pPass);
		LOGGER.info("[POINT]Create header :"+httpHeaders.toString());
		return httpHeaders;
	}

//move to twist files 
	@Bean (value="twistRestTemplate")
	public RestTemplate twistRestTemplate(@Autowired RestTemplateBuilder builder) { 
		/*
		 * RestTemplate twistRestTemplate = new RestTemplate();
		 * twistRestTemplate.setInterceptors(List.of(new
		 * qualysRestTemplateInterceptor()));
		 * LOGGER.info("[POINT]Created RestTemplate Bean for Twist :"); return
		 * twistRestTemplate;
		 */
		
		LOGGER.info("[POINT]Created RestTemplate Bean for Twist :");
		return builder
				.setConnectTimeout(Duration.ofSeconds(60))
		        .setReadTimeout(Duration.ofSeconds(60))
		        .interceptors(List.of(new twistRestTemplateInterceptor()))
		        .build();
	}


	//take interceptor out 
	private class twistRestTemplateInterceptor implements ClientHttpRequestInterceptor{

		public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
				throws IOException{


			request.getHeaders().addAll(header());
			LOGGER.info("Attempt TWIST Rest Call :"+request.toString());
			ClientHttpResponse response = null;
			try {
				long time_1 = System.currentTimeMillis();
				response = execution.execute(request, body);
				long time_2 = System.currentTimeMillis();
				System.out.println("after request fire 2  time: "+ (time_2 - time_1));
				LOGGER.info("response available from vul :"
				+ response.getStatusCode()
				+"||"
				+ response.getStatusText()
				+"||"
				+response.getBody()
				+"||"
			//	+bodyToString(response.getBody()) 
				);
				
					
			}catch(IOException ie) {
				LOGGER.error("IO Error While Connecting to Qualys Client");
				throw ie;
			}catch(Exception e){
				LOGGER.error("Error While Connecting to Qualys Client");
			}
			/* Throws java.io.IOException: stream is closed
			 * finally { if(response != null) response.close(); }
			 */
			
			return response;
		}
		
		/*
		 * private String bodyToString(InputStream body) throws IOException {
		 * StringBuilder builder = new StringBuilder(); BufferedReader bufferedReader =
		 * new BufferedReader(new InputStreamReader(body, StandardCharsets.UTF_8));
		 * String line = bufferedReader.readLine(); while (line != null) {
		 * builder.append(line).append(System.lineSeparator()); line =
		 * bufferedReader.readLine(); } bufferedReader.close(); return
		 * builder.toString(); }
		 */
    }
	
}
