package com.sedai.sops.secure.commons.entity;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty; 


public class DetectionList{
    @JsonProperty("QID")  
    public String getQID() { 
		 return this.qID; 
	} 
    public void setQID(String qID) { 
		 this.qID = qID; 
	} 
    String qID;
    @JsonProperty("TYPE") 
    public String getTYPE() { 
		 return this.tYPE; 
	} 
    public void setTYPE(String tYPE) { 
		 this.tYPE = tYPE; 
	} 
    String tYPE;
    @JsonProperty("SEVERITY") 
    public String getSEVERITY() { 
		 return this.sEVERITY; 
	} 
    public void setSEVERITY(String sEVERITY) { 
		 this.sEVERITY = sEVERITY; 
	} 
    String sEVERITY;
    @JsonProperty("SSL") 
    public String getSSL() { 
		 return this.sSL; 
	} 
    public void setSSL(String sSL) { 
		 this.sSL = sSL; 
	} 
    String sSL;
    @JsonProperty("RESULTS") 
    public String getRESULTS() { 
		 return this.rESULTS; 
	} 
    public void setRESULTS(String rESULTS) { 
		 this.rESULTS = rESULTS; 
	} 
    String rESULTS;
    @JsonProperty("STATUS") 
    public String getSTATUS() { 
		 return this.sTATUS; 
	} 
    public void setSTATUS(String sTATUS) { 
		 this.sTATUS = sTATUS; 
	} 
    String sTATUS;
    @JsonProperty("FIRST_FOUND_DATETIME") 
    public Date getFIRST_FOUND_DATETIME() { 
		 return this.fIRST_FOUND_DATETIME; 
	} 
    public void setFIRST_FOUND_DATETIME(Date fIRST_FOUND_DATETIME) { 
		 this.fIRST_FOUND_DATETIME = fIRST_FOUND_DATETIME; 
	} 
    Date fIRST_FOUND_DATETIME;
    @JsonProperty("LAST_FOUND_DATETIME") 
    public Date getLAST_FOUND_DATETIME() { 
		 return this.lAST_FOUND_DATETIME; 
	} 
    public void setLAST_FOUND_DATETIME(Date lAST_FOUND_DATETIME) { 
		 this.lAST_FOUND_DATETIME = lAST_FOUND_DATETIME; 
	} 
    Date lAST_FOUND_DATETIME;
    @JsonProperty("TIMES_FOUND") 
    public String getTIMES_FOUND() { 
		 return this.tIMES_FOUND; 
	} 
    public void setTIMES_FOUND(String tIMES_FOUND) { 
		 this.tIMES_FOUND = tIMES_FOUND; 
	} 
    String tIMES_FOUND;
    @JsonProperty("LAST_TEST_DATETIME") 
    public Date getLAST_TEST_DATETIME() { 
		 return this.lAST_TEST_DATETIME; 
	} 
    public void setLAST_TEST_DATETIME(Date lAST_TEST_DATETIME) { 
		 this.lAST_TEST_DATETIME = lAST_TEST_DATETIME; 
	} 
    Date lAST_TEST_DATETIME;
    @JsonProperty("LAST_UPDATE_DATETIME") 
    public Date getLAST_UPDATE_DATETIME() { 
		 return this.lAST_UPDATE_DATETIME; 
	} 
    public void setLAST_UPDATE_DATETIME(Date lAST_UPDATE_DATETIME) { 
		 this.lAST_UPDATE_DATETIME = lAST_UPDATE_DATETIME; 
	} 
    Date lAST_UPDATE_DATETIME;
    @JsonProperty("IS_IGNORED") 
    public String getIS_IGNORED() { 
		 return this.iS_IGNORED; 
	} 
    public void setIS_IGNORED(String iS_IGNORED) { 
		 this.iS_IGNORED = iS_IGNORED; 
	} 
    String iS_IGNORED;
    @JsonProperty("IS_DISABLED") 
    public String getIS_DISABLED() { 
		 return this.iS_DISABLED; 
	} 
    public void setIS_DISABLED(String iS_DISABLED) { 
		 this.iS_DISABLED = iS_DISABLED; 
	} 
    String iS_DISABLED;
    @JsonProperty("LAST_PROCESSED_DATETIME") 
    public Date getLAST_PROCESSED_DATETIME() { 
		 return this.lAST_PROCESSED_DATETIME; 
	} 
    public void setLAST_PROCESSED_DATETIME(Date lAST_PROCESSED_DATETIME) { 
		 this.lAST_PROCESSED_DATETIME = lAST_PROCESSED_DATETIME; 
	} 
    Date lAST_PROCESSED_DATETIME;
}
