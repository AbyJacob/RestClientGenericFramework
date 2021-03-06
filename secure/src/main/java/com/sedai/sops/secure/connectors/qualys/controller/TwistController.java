//[TODO] Check TwistGetAllContainersService for comments
  package com.sedai.sops.secure.connectors.qualys.controller;
  
  import org.json.XML;
import org.slf4j.Logger; import org.slf4j.LoggerFactory; import
  org.springframework.http.HttpStatus; import
  org.springframework.http.ResponseEntity; import
  org.springframework.web.bind.annotation.GetMapping; import
  org.springframework.web.bind.annotation.RestController;
  
  import  com.sedai.sops.secure.connectors.qualys.service.TwistGetAllContainersService;
  
  import lombok.RequiredArgsConstructor;
  
  @RestController
  @RequiredArgsConstructor 
  public class TwistController 
  {
  
  Logger LOGGER = LoggerFactory.getLogger(TwistController.class); 
  
  //convert to autowiring 
  private final TwistGetAllContainersService twistGetAllContainersService;
  
	@GetMapping(path = "/fetchAllVulnerabilities", produces = "application/xml")
	public ResponseEntity<String> getAllTwistContainers() {
		LOGGER.info("Invoked getAllVulnerability");
		String containerProp = twistGetAllContainersService.getAllContainers();
		
		
		

		if (containerProp != null)
			return new ResponseEntity<>((XML.toJSONObject(containerProp)).toString(), HttpStatus.OK);
		else
			return new ResponseEntity<>("Container Doesnt exist", HttpStatus.INTERNAL_SERVER_ERROR);

	}

}
 