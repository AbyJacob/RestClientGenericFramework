package com.sedai.sops.secure.commons.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Root{ 
    @JsonProperty("RESPONSE") 
    public Response getRESPONSE() { 
		 return this.Response; 
	} 
    public void setRESPONSE(Response Response) { 
		 this.Response = Response; 
	} 
    Response Response;
}
