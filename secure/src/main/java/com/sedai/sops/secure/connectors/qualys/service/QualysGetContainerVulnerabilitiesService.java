package com.sedai.sops.secure.connectors.qualys.service;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;

//move package to Qualys 
/*
 *Service for Qualys Connector 
 *@author  : Aby.Jacob
 *@version : 1.0
 *@since   :  
 *@remarks :  [Watch] Multiple recovery methods in same class results in ArrayIndexOutOfBoundsException #145
 *			https://github.com/spring-projects/spring-retry/issues/145 [Closed]
 *			 [Watch]
 *			 [Resolved] Multiple revoery method doesnt allow granular control of events and logs
 *			 [Ref] https://stackoverflow.com/questions/50322126/spring-retry-method-annotated-with-recover-not-being-called
 * 
 */			 	
@Service
@RequiredArgsConstructor
public class QualysGetContainerVulnerabilitiesService {

	private final RestTemplate restTemplate;
	Logger LOGGER = LoggerFactory.getLogger(QualysGetContainerVulnerabilitiesService.class);
	//	int COUNTER = 1;

	String response;
	@Retryable(value = { RestClientException.class,RuntimeException.class }, maxAttempts = 4, backoff = @Backoff(multiplier = 3))
	public String getContainerVulnerabilities(String containerId) {
		response = "No Container"; //[highly unlikely] reset response to prevent assigned value of previous retry
		LOGGER.info("Invoked QualysGetContainerVulnerabilitiesService");

		URI url = URI.create("https://qualysapi.qg1.apps.qualys.in/csapi/v1.1/containers/"+containerId+"/vuln");
		try {
			response = restTemplate.getForObject(url, String.class);
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

		/*
		 * [TODO] check for Errors
		 * 
		 * RestClientException Error While Connecting to Qualys Client
		 * 404 : [{"errorCode":"404",
		 * "message":"Container not found with sha =111111111111111111113ce9346709af",
		 * "timestamp":1599029159222}]
		 * 
		 * check err java.net.UnknownHostException: qualysapi.qg1.apps.qualys.in
		 *
		 * //nullcheck
		 */

		// [TODO] REF
		// https://stackoverflow.com/questions/23674046/get-list-of-json-objects-with-spring-resttemplate
		// https://stackoverflow.com/questions/50322126/spring-retry-method-annotated-with-recover-not-being-called
	}

	@Recover
	public String recover() {
		LOGGER.info(" [QualysGetContainerVulnerabilitiesService] Qualys Connecton failed: Audit to be implemented" + response);
		return response;
		// [TODO] REF
		// https://stackoverflow.com/questions/50322126/spring-retry-method-annotated-with-recover-not-being-called
		// [Resolved] Have diff Service Classes
	}

}
