package com.sedai.sops.secure.commons.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;



public class HostList{
    @JsonProperty("ID") 
    public String getID() { 
		 return this.iD; 
	} 
    public void setID(String iD) { 
		 this.iD = iD; 
	} 
    String iD;
    @JsonProperty("IP") 
    public String getIP() { 
		 return this.iP; 
	} 
    public void setIP(String iP) { 
		 this.iP = iP; 
	} 
    String iP;
    @JsonProperty("TRACKING_METHOD") 
    public String getTRACKING_METHOD() { 
		 return this.tRACKING_METHOD; 
	} 
    public void setTRACKING_METHOD(String tRACKING_METHOD) { 
		 this.tRACKING_METHOD = tRACKING_METHOD; 
	} 
    String tRACKING_METHOD;
    @JsonProperty("OS") 
    public String getOS() { 
		 return this.oS; 
	} 
    public void setOS(String oS) { 
		 this.oS = oS; 
	} 
    String oS;
    @JsonProperty("DNS") 
    public String getDNS() { 
		 return this.dNS; 
	} 
    public void setDNS(String dNS) { 
		 this.dNS = dNS; 
	} 
    String dNS;
    @JsonProperty("QG_HOSTID") 
    public String getQG_HOSTID() { 
		 return this.qG_HOSTID; 
	} 
    public void setQG_HOSTID(String qG_HOSTID) { 
		 this.qG_HOSTID = qG_HOSTID; 
	} 
    String qG_HOSTID;
    @JsonProperty("LAST_SCAN_DATETIME") 
    public Date getLAST_SCAN_DATETIME() { 
		 return this.lAST_SCAN_DATETIME; 
	} 
    public void setLAST_SCAN_DATETIME(Date lAST_SCAN_DATETIME) { 
		 this.lAST_SCAN_DATETIME = lAST_SCAN_DATETIME; 
	} 
    Date lAST_SCAN_DATETIME;
    @JsonProperty("LAST_VM_SCANNED_DATE") 
    public Date getLAST_VM_SCANNED_DATE() { 
		 return this.lAST_VM_SCANNED_DATE; 
	} 
    public void setLAST_VM_SCANNED_DATE(Date lAST_VM_SCANNED_DATE) { 
		 this.lAST_VM_SCANNED_DATE = lAST_VM_SCANNED_DATE; 
	} 
    Date lAST_VM_SCANNED_DATE;
    @JsonProperty("LAST_VM_AUTH_SCANNED_DATE") 
    public Date getLAST_VM_AUTH_SCANNED_DATE() { 
		 return this.lAST_VM_AUTH_SCANNED_DATE; 
	} 
    public void setLAST_VM_AUTH_SCANNED_DATE(Date lAST_VM_AUTH_SCANNED_DATE) { 
		 this.lAST_VM_AUTH_SCANNED_DATE = lAST_VM_AUTH_SCANNED_DATE; 
	} 
    Date lAST_VM_AUTH_SCANNED_DATE;
    @JsonProperty("LAST_PC_SCANNED_DATE") 
    public Date getLAST_PC_SCANNED_DATE() { 
		 return this.lAST_PC_SCANNED_DATE; 
	} 
    public void setLAST_PC_SCANNED_DATE(Date lAST_PC_SCANNED_DATE) { 
		 this.lAST_PC_SCANNED_DATE = lAST_PC_SCANNED_DATE; 
	} 
    Date lAST_PC_SCANNED_DATE;
    @JsonProperty("DETECTION_LIST") 
    public List<DetectionList> getDETECTION_LIST() { 
		 return this.DetectionList; 
	} 
    public void setDETECTION_LIST(List<DetectionList> DetectionList) { 
		 this.DetectionList = DetectionList; 
	} 
    List<DetectionList> DetectionList;
}




