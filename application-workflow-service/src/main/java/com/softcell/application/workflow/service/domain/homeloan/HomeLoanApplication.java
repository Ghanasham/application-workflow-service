package com.softcell.application.workflow.service.domain.homeloan;

import javax.persistence.Entity;

import com.softcell.application.workflow.service.domain.Application;

@Entity
public class HomeLoanApplication extends Application{

	private int loanAmount;
	
	private byte tenure;
	
	public HomeLoanApplication(){
		applicationType = ApplicationType.HOME_LOAN;  
	}
	
	public int getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(int loanAmount) {
		this.loanAmount = loanAmount;
	}

	public byte getTenure() {
		return tenure;
	}

	public void setTenure(byte tenure) {
		this.tenure = tenure;
	}
}
