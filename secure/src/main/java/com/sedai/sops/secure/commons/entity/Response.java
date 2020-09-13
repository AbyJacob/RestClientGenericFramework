package com.sedai.sops.secure.commons.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Response{
    @JsonProperty("DATETIME") 
    public Date getDATETIME() { 
		 return this.dATETIME; 
	} 
    public void setDATETIME(Date dATETIME) { 
		 this.dATETIME = dATETIME; 
	} 
    Date dATETIME;
    @JsonProperty("HOST_LIST") 
    public List<HostList> getHOST_LIST() { 
		 return this.HostList; 
	} 
    public void setHOST_LIST(List<HostList> HostList) { 
		 this.HostList = HostList; 
	} 
    List<HostList> HostList;
}
