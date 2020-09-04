package com.sedai.sops.secure.connectors.qualys.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sedai.sops.secure.connectors.qualys.service.QualysGetAllContainersService;
import com.sedai.sops.secure.connectors.qualys.service.QualysGetContainerService;
import com.sedai.sops.secure.connectors.qualys.service.QualysGetContainerSoftwareService;
import com.sedai.sops.secure.connectors.qualys.service.QualysGetContainerVulnerabilitiesService;
import com.sedai.sops.secure.connectors.qualys.service.QualysGetContainerVulnerabilityCountService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class QualysController {

	Logger LOGGER = LoggerFactory.getLogger(QualysController.class);

	private final QualysGetAllContainersService qualysGetAllContainersService;
	private final QualysGetContainerService qualysGetContainerService;
	private final QualysGetContainerSoftwareService qualysGetContainerSoftwareService;
    private final QualysGetContainerVulnerabilitiesService qualysGetContainerVulnerabilitiesService; 
    private final QualysGetContainerVulnerabilityCountService qualysGetContainerVulnerabilityCountService;

	// Check App Status
	@GetMapping(path = "/appstatus", produces = "application/json")
	public ResponseEntity<String> appStatus() {
		return new ResponseEntity<>("sOps Running!", HttpStatus.OK);
	}

	// [TODO]Convert to Post
	@GetMapping(path = "/qualiscred/{uname}/{pwd}", produces = "application/json")
	public ResponseEntity<String> checkAndSaveQualysCred(@PathVariable("uname") String uname,
			@PathVariable("pwd") String pwd) {

		LOGGER.info("Invoked checkAndSaveQualysCred");
		String containerProp = qualysGetAllContainersService.checkQualysCred(uname, pwd);

		if (containerProp != null)
			return new ResponseEntity<>(containerProp, HttpStatus.OK);
		else
			return new ResponseEntity<>("Container Doesnt exist", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping(path = "/allcontainers", produces = "application/json")
	public ResponseEntity<String> getAllContainers() {
		LOGGER.info("Invoked getAllContainers");
		String containerProp = qualysGetAllContainersService.getAllContainers();

		if (containerProp != null)
			return new ResponseEntity<>(containerProp, HttpStatus.OK);
		else
			return new ResponseEntity<>("Container Doesnt exist", HttpStatus.INTERNAL_SERVER_ERROR);

	}


	
	@GetMapping(path = "/containers/{containerId}", produces = "application/json")
	public ResponseEntity<String> getContainerById(@PathVariable("containerId") String containerId) {
		LOGGER.info("Invoked geContainerById" + containerId);
		String containerProp = "";

		if (containerId != null)
			 containerProp = qualysGetContainerService.getContainerById(containerId);

			if (containerProp != null)
				return new ResponseEntity<>(containerProp, HttpStatus.OK);
			else
				return new ResponseEntity<>("Container Doesnt Exist", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping(path = "/containers/{containerId}/software", produces = "application/json")
	public ResponseEntity<String> getContainerSoftwareById(@PathVariable("containerId") String containerId) {
		LOGGER.info("Invoked geContainerById" + containerId);
		String containerProp = "";

		if (containerId != null)
			 containerProp = qualysGetContainerSoftwareService.getContainerSoftware(containerId);

			if (containerProp != null)
				return new ResponseEntity<>(containerProp, HttpStatus.OK);
			else
				return new ResponseEntity<>("Container Doesnt Exist", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping(path = "/containers/{containerId}/vulnerabilities", produces = "application/json")
	public ResponseEntity<String> getContainerVulnerabilitiesService(@PathVariable("containerId") String containerId) {
		LOGGER.info("Invoked getContainerVulnerabilitiesService" + containerId);
		String containerProp = "";

		if (containerId != null)
				containerProp = qualysGetContainerVulnerabilitiesService.getContainerVulnerabilities(containerId);

			if (containerProp != null)
				return new ResponseEntity<>(containerProp, HttpStatus.OK);
			else
				return new ResponseEntity<>("Container Doesnt Exist", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping(path = "/containers/{containerId}/vulnerabilitycount", produces = "application/json")
	public ResponseEntity<String> getContainerVulnerabilityCount(@PathVariable("containerId") String containerId) {
		LOGGER.info("Invoked getContainerVulnerabilitiesService" + containerId);
		String containerProp = "";

		if (containerId != null)
			 containerProp = qualysGetContainerVulnerabilityCountService.getContainerVulnerabilityCount(containerId);

			if (containerProp != null)
				return new ResponseEntity<>(containerProp, HttpStatus.OK);
			else
				return new ResponseEntity<>("Container Doesnt Exist", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/*
	 * Below Methods are test methods.
	 * 
	 */

	@GetMapping(path = "/testharcodecontainer", produces = "application/json")
	public ResponseEntity<String> trigger() {
		LOGGER.info("Invoked Trigger");
		String containerProp = "";
		containerProp = qualysGetContainerService.getContainerById("3ce9346709af");

		if (containerProp != null)
			return new ResponseEntity<>(containerProp, HttpStatus.OK);
		else
			return new ResponseEntity<>("Container Doesnt exist", HttpStatus.INTERNAL_SERVER_ERROR);

	}

}
