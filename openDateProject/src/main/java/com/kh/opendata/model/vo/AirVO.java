package com.kh.opendata.model.vo;

public class AirVO {
	
	private String stationName; // 측정소명
	private String dataTime; // 측정일시
	
	private String so2Value; // 아황산가스 농도
	private String pm10Value; // 미세먼지 농도
	private String o3Value; // 오존농도
	private String khaiValue; // 통합 대기환경 수치
	
	public AirVO() {
		super();
	}
	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	public String getDataTime() {
		return dataTime;
	}
	public void setDataTime(String dataTime) {
		this.dataTime = dataTime;
	}
	public String getSo2Value() {
		return so2Value;
	}
	public void setSo2Value(String so2Value) {
		this.so2Value = so2Value;
	}
	public String getPm10Value() {
		return pm10Value;
	}
	public void setPm10Value(String pm10Value) {
		this.pm10Value = pm10Value;
	}
	public String getO3Value() {
		return o3Value;
	}
	public void setO3Value(String o3Value) {
		this.o3Value = o3Value;
	}
	public String getKhaiValue() {
		return khaiValue;
	}
	public void setKhaiValue(String khaiValue) {
		this.khaiValue = khaiValue;
	}
	@Override
	public String toString() {
		return "AirVO [stationName=" + stationName + ", dataTime=" + dataTime + ", so2Value=" + so2Value
				+ ", pm10Value=" + pm10Value + ", o3Value=" + o3Value + ", khaiValue=" + khaiValue + "]";
	}
	
}	
