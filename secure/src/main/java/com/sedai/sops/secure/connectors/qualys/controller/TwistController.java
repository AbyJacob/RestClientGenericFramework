/*[TODO] Check TwistGetAllContainersService for comments
 * package com.sedai.sops.secure.connectors.qualys.controller;
 * 
 * import org.slf4j.Logger; import org.slf4j.LoggerFactory; import
 * org.springframework.http.HttpStatus; import
 * org.springframework.http.ResponseEntity; import
 * org.springframework.web.bind.annotation.GetMapping; import
 * org.springframework.web.bind.annotation.RestController;
 * 
 * import
 * com.sedai.sops.secure.connectors.qualys.service.TwistGetAllContainersService;
 * 
 * import lombok.RequiredArgsConstructor;
 * 
 * @RestController
 * 
 * @RequiredArgsConstructor public class TwistController {
 * 
 * Logger LOGGER = LoggerFactory.getLogger(TwistController.class); //convert to
 * autowiring private final TwistGetAllContainersService
 * qualysGetAllContainersService;
 * 
 * 
 * 
 * @GetMapping(path = "/allcontainers" , produces = "application/json") public
 * ResponseEntity<String> getAllContainers() {
 * LOGGER.info("Invoked getAllContainers"); String containerProp =
 * qualysGetAllContainersService.getAllContainers();
 * 
 * if(containerProp != null) return new
 * ResponseEntity<>(containerProp,HttpStatus.OK); else return new
 * ResponseEntity<>("Container Doesnt exist",HttpStatus.INTERNAL_SERVER_ERROR);
 * 
 * }
 * 
 * }
 */