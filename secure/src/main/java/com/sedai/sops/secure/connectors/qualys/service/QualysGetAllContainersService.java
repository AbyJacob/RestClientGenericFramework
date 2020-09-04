package com.sedai.sops.secure.connectors.qualys.service;

import java.net.URI;
import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;


import lombok.RequiredArgsConstructor;
import lombok.NonNull;

//move package to Qualys 
/*
 *Service for Qualys Connector 
 *@author  : Aby.Jacob
 *@version : 1.0
 *@since   : 
 *@remarks :  [Watch] Multiple recovery methods in same class results in ArrayIndexOutOfBoundsException #145
 *			https://github.com/spring-projects/spring-retry/issues/145 [Closed]
 *			 [Watch]
 */
@Service
@RequiredArgsConstructor
public class QualysGetAllContainersService {

	@Qualifier("qualysRestTemplate")
	@NonNull private final RestTemplate qualysRestTemplate;
	
	@Qualifier("twistRestTemplate")
	@NonNull private final RestTemplate twistRestTemplate;
	
	Logger LOGGER = LoggerFactory.getLogger(QualysGetAllContainersService.class);

	String response;
	// int COUNTER = 1;

	@Retryable(value = { RestClientException.class,	RuntimeException.class }, maxAttempts = 4, backoff = @Backoff(multiplier = 3))
	public String getAllContainers() {
		response = "No Container";
		LOGGER.info("Invoked QualysGetAllContainersService");

		// [TODO] implement fetch with pagination filter
		// 'https://<QualysURL>/csapi/v1.1/containers?pageNo=1&pageSize=50&sort=created%3Adesc'
		URI url = URI.create("https://qualysapi.qg1.apps.qualys.in/csapi/v1.1/containers");
		try {
			response = qualysRestTemplate.getForObject(url, String.class);
		} catch (RestClientException re) {
			LOGGER.error("RestClientException Error While Connecting to Qualys Client" + re.getMessage());
		} catch (NullPointerException ne) {
			LOGGER.error("Catching Null Exception While Connecting to Qualys Client /n"
					+ "Malformed URI or URI not available", ne);
		} catch (RuntimeException rte) {
			LOGGER.error("RestClientException Error While Connecting to Qualys Client");
		}

		/*
		 * if(COUNTER == 1) throw new RuntimeException(); else if(COUNTER == 2) throw
		 * new RuntimeException(); else
		 */

		return (response);

	}
	
	@Retryable(value = { RestClientException.class,	RuntimeException.class }, maxAttempts = 4, backoff = @Backoff(multiplier = 3))
	public String getAllTwistContainers() {
		response = "No Container";
		LOGGER.info("Invoked TwistsGetAllContainersService");

		// [TODO] implement fetch with pagination filter
		// 'https://<QualysURL>/csapi/v1.1/containers?pageNo=1&pageSize=50&sort=created%3Adesc'
		URI url = URI.create("https://qualysapi.qg1.apps.qualys.in/csapi/v1.1/containers");
		try {
			response = twistRestTemplate.getForObject(url, String.class);
		} catch (RestClientException re) {
			LOGGER.error("RestClientException Error While Connecting to Qualys Client" + re.getMessage());
		} catch (NullPointerException ne) {
			LOGGER.error("Catching Null Exception While Connecting to Qualys Client /n"
					+ "Malformed URI or URI not available", ne);
		} catch (RuntimeException rte) {
			LOGGER.error("RestClientException Error While Connecting to Qualys Client");
		}

		/*
		 * if(COUNTER == 1) throw new RuntimeException(); else if(COUNTER == 2) throw
		 * new RuntimeException(); else
		 */

		return (response);

	}

	@Recover
	public String recover() {
		LOGGER.info("[QualysGetAllContainersService] Qualys Connecton failed: Audit to be implemented" + response);
		return response;
	}

	public String checkQualysCred(String uName, String pwd) {
		String response = "Please provide valid Qualys credentials";
		LOGGER.info("Invoked QualysGetAllContainersService.checkQualysCred");

		// url formed to fetch single container to ensure proper auth
		URI url = URI.create("https://qualysapi.qg1.apps.qualys.in/csapi/v1.1/containers?pageNo=1&pageSize=1");

		try {
			RestTemplate credTemplate = new RestTemplate();
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
			httpHeaders.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
			httpHeaders.setBasicAuth(uName, pwd);
			RequestEntity<String> eRequest = new RequestEntity<>(httpHeaders, HttpMethod.GET, url);
			ResponseEntity<String> eResponse = credTemplate.exchange(eRequest, String.class);
			LOGGER.info("cred check response " + eResponse.getBody());

			if (eResponse.getStatusCode() == HttpStatus.OK)
				response = "Authentication Successful";

		} catch (RestClientException re) {
			LOGGER.error("[checkQualysCred] RestClientException Error While Connecting to Qualys Client" + re.getMessage());
		} catch (NullPointerException ne) {
			LOGGER.error("[checkQualysCred] Catching Null Exception While Connecting to Qualys Client /n"
					+ "Malformed URI or URI not available", ne);
		} catch (RuntimeException rte) {
			LOGGER.error("[checkQualysCred] RestClientException Error While Connecting to Qualys Client");
		}

		/*
		 * if(COUNTER == 1) throw new RuntimeException(); else if(COUNTER == 2) throw
		 * new RuntimeException(); else
		 */

		return (response);

	}

	/*
	 * @SuppressWarnings({ "unchecked", "rawtypes" }) public HashMap getTest() {
	 * //response = "No Container"; //[highly unlikely] reset response to prevent
	 * assigned value of previous retry HashMap<String, String> response = new
	 * HashMap<String, String>(); LOGGER.info("Invoked getContainerById");
	 * 
	 * URI url =
	 * URI.create("https://qualysapi.qg1.apps.qualys.in/csapi/v1.1/containers"); try
	 * { response = restTemplate.getForObject(url, HashMap.class); } catch
	 * (RestClientException re) {
	 * LOGGER.error("RestClientException Error While Connecting to Qualys Client" +
	 * re.getMessage()); } catch (NullPointerException ne) {
	 * LOGGER.error("Catching Null Exception While Connecting to Qualys Client /n" +
	 * "Malformed URI or URI not available", ne); } catch (RuntimeException rte) {
	 * LOGGER.error("RestClientException Error While Connecting to Qualys Client");
	 * }
	 * 
	 * 
	 * if(COUNTER == 1) throw new RuntimeException(); else if(COUNTER == 2) throw
	 * new RuntimeException(); else
	 * 
	 * return (response);
	 * 
	 * }
	 */
}
