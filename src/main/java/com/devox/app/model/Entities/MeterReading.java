package com.devox.app.model.Entities;

import com.devox.app.model.security.User;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the meter_reading database table.
 * 
 */
@Entity
@Table(name="meter_reading")
public class MeterReading implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	@GeneratedValue(strategy = GenerationType.AUTO)

	private int id;

	@Column(length=255)
	private String allmoneyReading;

	@Column(length=255)
	private String buildingimage;

	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	private Date date;

	@Column(name="`meter reading`", length=255)
	private String meter_reading;

	@Column(length=255)
	private String meterimage;

	@Column(length=255)
	private String moneyreading;

	@Column(length=255)
	private String readingimage;

	@Column(nullable=false)
	private byte revised;

	@Column(length=255)
	private String typereading;

	//bi-directional many-to-one association to WaterMeter
	@ManyToOne
	@JoinColumn(name="water_meter_id", nullable=false)
	private WaterMeter waterMeter;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="userid", nullable=false)
	private User user;

	public MeterReading() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAllmoneyReading() {
		return this.allmoneyReading;
	}

	public void setAllmoneyReading(String allmoneyReading) {
		this.allmoneyReading = allmoneyReading;
	}

	public String getBuildingimage() {
		return this.buildingimage;
	}

	public void setBuildingimage(String buildingimage) {
		this.buildingimage = buildingimage;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getMeter_reading() {
		return this.meter_reading;
	}

	public void setMeter_reading(String meter_reading) {
		this.meter_reading = meter_reading;
	}

	public String getMeterimage() {
		return this.meterimage;
	}

	public void setMeterimage(String meterimage) {
		this.meterimage = meterimage;
	}

	public String getMoneyreading() {
		return this.moneyreading;
	}

	public void setMoneyreading(String moneyreading) {
		this.moneyreading = moneyreading;
	}

	public String getReadingimage() {
		return this.readingimage;
	}

	public void setReadingimage(String readingimage) {
		this.readingimage = readingimage;
	}

	public byte getRevised() {
		return this.revised;
	}

	public void setRevised(byte revised) {
		this.revised = revised;
	}

	public String getTypereading() {
		return this.typereading;
	}

	public void setTypereading(String typereading) {
		this.typereading = typereading;
	}

	public WaterMeter getWaterMeter() {
		return this.waterMeter;
	}

	public void setWaterMeter(WaterMeter waterMeter) {
		this.waterMeter = waterMeter;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}