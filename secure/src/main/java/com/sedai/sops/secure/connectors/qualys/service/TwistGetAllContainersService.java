/*  ####################TODO############################
 * Failed to do multiple restTemplate bean injection. inspite of using @Qualifier
 * Change pattern of initialisation for each interface
 * 
 * Description:
 * 
 * Parameter 0 of constructor in
 * com.sedai.sops.secure.connectors.qualys.service.QualysGetContainerService
 * required a single bean, but 2 were found: - qualysRestTemplate: defined by
 * method 'qualysRestTemplate' in class path resource
 * [com/sedai/sops/secure/connectors/qualys/config/QualysConfig.class] -
 * twistRestTemplate: defined by method 'twistRestTemplate' in class path
 * resource
 * [com/sedai/sops/secure/connectors/qualys/config/TwistLockConfig.class]
 * 
 * 
 * Action:
 * 
 * Consider marking one of the beans as @Primary, updating the consumer to
 * accept multiple beans, or using @Qualifier to identify the bean that should
 * be consumed
 * 
 */
/*
 * package com.sedai.sops.secure.connectors.qualys.service;
 * 
 * import java.net.URI; import java.util.Collections;
 * 
 * import org.slf4j.Logger; import org.slf4j.LoggerFactory; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.beans.factory.annotation.Qualifier; import
 * org.springframework.http.HttpHeaders; import
 * org.springframework.http.HttpMethod; import
 * org.springframework.http.HttpStatus; import
 * org.springframework.http.MediaType; import
 * org.springframework.http.RequestEntity; import
 * org.springframework.http.ResponseEntity; import
 * org.springframework.retry.annotation.Backoff; import
 * org.springframework.retry.annotation.Recover; import
 * org.springframework.retry.annotation.Retryable; import
 * org.springframework.stereotype.Service; import
 * org.springframework.web.client.RestClientException; import
 * org.springframework.web.client.RestTemplate;
 * 
 * import lombok.RequiredArgsConstructor;
 * 
 * //move package to Twist
 * 
 * Service for Twist Connector
 * 
 * @author : Aby.Jacob
 * 
 * @version : 1.0
 * 
 * @since :
 * 
 * @remarks : [Watch] Multiple recovery methods in same class results in
 * ArrayIndexOutOfBoundsException #145
 * https://github.com/spring-projects/spring-retry/issues/145 [Closed] [Watch]
 * 
 *
 * 
 * @Service public class TwistGetAllContainersService {
 * 
 * 
 * private RestTemplate restTemplate;
 * 
 * @Autowired private
 * TwistGetAllContainersService(@Qualifier("twistRestTemplate") RestTemplate
 * restTemplate) { this.restTemplate=restTemplate; };
 * 
 * 
 * Logger LOGGER = LoggerFactory.getLogger(TwistGetAllContainersService.class);
 * 
 * String response; // int COUNTER = 1;
 * 
 * @Retryable(value = { RestClientException.class, RuntimeException.class },
 * maxAttempts = 4, backoff = @Backoff(multiplier = 3)) public String
 * getAllContainers() { response = "No Container";
 * LOGGER.info("Invoked QualysGetAllContainersService");
 * 
 * // [TODO] implement fetch with pagination filter //
 * 'https://<QualysURL>/csapi/v1.1/containers?pageNo=1&pageSize=50&sort=created%
 * 3Adesc' URI url =
 * URI.create("https://qualysapi.qg1.apps.qualys.in/csapi/v1.1/containers"); try
 * { response = restTemplate.getForObject(url, String.class); } catch
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
 * 
 * return (response);
 * 
 * }
 * 
 * @Recover public String recover() { LOGGER.
 * info("[QualysGetAllContainersService] Qualys Connecton failed: Audit to be implemented"
 * + response); return response; } }
 */