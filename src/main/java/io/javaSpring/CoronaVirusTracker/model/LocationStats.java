package io.javaSpring.CoronaVirusTracker.model;

public class LocationStats {
	
	private String state;
	
	private String Country;
	
	private long lastestTotalCases;
	
	private long diffOfCasesFromPrevDay;
	

	public long getDiffOfCasesFromPrevDay() {
		return diffOfCasesFromPrevDay;
	}

	public void setDiffOfCasesFromPrevDay(long diffOfCasesFromPrevDay) {
		this.diffOfCasesFromPrevDay = diffOfCasesFromPrevDay;
	}

	public String getState() {
		return this.state;
	}
	
	public String getCountry() {
		return this.Country;
	}
	
	public long getLatestTotalCases() {
		return this.lastestTotalCases;
	}
	
	public void setState(String state) {
		this.state= state;
	}
	
	public void setCountry(String country) {
		this.Country= country;
	}
	
	public void setLatestTotalCases(long latestTotalCases) {
		this.lastestTotalCases = latestTotalCases;
	}

	@Override
	public String toString() {
		return "LocationStats [state=" + state + ", Country=" + Country + ", lastestTotalCases=" + lastestTotalCases
				+ ", diffOfCasesFromPrevDay=" + diffOfCasesFromPrevDay + "]";
	}


	
	
	
}
