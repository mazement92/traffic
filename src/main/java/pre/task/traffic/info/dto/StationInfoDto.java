package pre.task.traffic.info.dto;

import java.util.Date;

public class StationInfoDto {
	private String stationId;
	private String stationName;
	private Date regDts;
	private int month;
	private int monthlyStationUser;
	private int dailyAvgUser;
	private int monthlyAvgUser;
	
	public String getStationId() {
		return stationId;
	}
	public void setStationId(String stationId) {
		this.stationId = stationId;
	}
	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	public Date getRegDts() {
		return regDts;
	}
	public void setRegDts(Date regDts) {
		this.regDts = regDts;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getMonthlyStationUser() {
		return monthlyStationUser;
	}
	public void setMonthlyStationUser(int monthlyStationUser) {
		this.monthlyStationUser = monthlyStationUser;
	}
	public int getDailyAvgUser() {
		return dailyAvgUser;
	}
	public void setDailyAvgUser(int dailyAvgUser) {
		this.dailyAvgUser = dailyAvgUser;
	}
	public int getMonthlyAvgUser() {
		return monthlyAvgUser;
	}
	public void setMonthlyAvgUser(int monthlyAvgUser) {
		this.monthlyAvgUser = monthlyAvgUser;
	}
}