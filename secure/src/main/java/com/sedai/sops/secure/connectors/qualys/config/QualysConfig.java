package com.sedai.sops.secure.connectors.qualys.config;

import java.io.IOException;
import java.time.Duration;
import java.util.Collections;
import java.util.Date;
import java.util.List;

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
public class QualysConfig {
	Logger LOGGER = LoggerFactory.getLogger(QualysConfig.class);

	// [TODO] fetch cred from DB
	@Value("${connection.qualys.username}")
	private String uName;
	@Value("${connection.qualys.password}")
	private String pPass;

	private HttpHeaders header() {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		httpHeaders.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
		httpHeaders.setBasicAuth(uName, pPass);
		System.out.println("----------httpHeaders.toString----------"+httpHeaders.toString());
		System.out.println("----------httpHeaders.keySet().size----------"+httpHeaders.keySet().size());
		System.out.println("----------httpHeaders.keySet()----------"+httpHeaders.keySet());

		LOGGER.info("[POINT]Create header :"+httpHeaders.toString());
		return httpHeaders;
	}

	@Bean(value = "qualysRestTemplate")
	public RestTemplate qualysRestTemplate(@Autowired RestTemplateBuilder builder) {

		/*
		 * RestTemplate qualysRestTemplate = new RestTemplate();
		 * qualysRestTemplate.setInterceptors(List.of(new
		 * qualysRestTemplateInterceptor()));
		 * LOGGER.info("[POINT]Created RestTemplate Bean for Qualys :"); return
		 * qualysRestTemplate;
		 */

		LOGGER.info("[POINT]Created RestTemplate Bean for Qualys :");
		return builder
				.setConnectTimeout(Duration.ofSeconds(7))
				.setReadTimeout(Duration.ofSeconds(7))
				.interceptors(List.of(new qualysRestTemplateInterceptor()))
				.build();

	}


	// take interceptor out
	private class qualysRestTemplateInterceptor implements ClientHttpRequestInterceptor {

		public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
				throws IOException {

			request.getHeaders().addAll(header());
			LOGGER.info("Attempt Rest Call :"+request.toString());
			ClientHttpResponse response = null;
			try {
				long time_1 = System.currentTimeMillis();
				response = execution.execute(request, body);
				long time_2 = System.currentTimeMillis();
				System.out.println("after request fire 2  time: " + (time_2 - time_1));
			} catch (IOException ie) {
				LOGGER.error("IO Error While Connecting to Qualys Client");
				throw ie;
			} catch (Exception e) {
				LOGGER.error("Error While Connecting to Qualys Client");
			}
			/*
			 * Throws java.io.IOException: stream is closed finally { if(response != null)
			 * response.close(); }
			 */
			return response;
		}
	}
}
