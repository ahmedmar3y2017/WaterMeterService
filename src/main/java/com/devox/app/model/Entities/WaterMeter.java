package com.devox.app.model.Entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the water_meter database table.
 * 
 */
@Entity
@Table(name="water_meter")
public class WaterMeter implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	@GeneratedValue(strategy = GenerationType.AUTO)

	private int id;

	@Column(length=255)
	private String anotherWays;

	@Column(nullable=false, length=255)
	private String barcode;

	@Column(length=255)
	private String buildingname;

	@Column(length=255)
	private String buildingtype;

	@Column(nullable=false, length=255)
	private String metertype;

	@Column(length=255)
	private String notes;

	@Column(nullable=false, length=255)
	private String number;

	@Column(length=255)
	private String state;

	@Column(length=255)
	private String x;

	@Column(length=255)
	private String y;

	//bi-directional many-to-one association to MeterReading
	@OneToMany(mappedBy="waterMeter")
	private List<MeterReading> meterReadings;

	public WaterMeter() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAnotherWays() {
		return this.anotherWays;
	}

	public void setAnotherWays(String anotherWays) {
		this.anotherWays = anotherWays;
	}

	public String getBarcode() {
		return this.barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getBuildingname() {
		return this.buildingname;
	}

	public void setBuildingname(String buildingname) {
		this.buildingname = buildingname;
	}

	public String getBuildingtype() {
		return this.buildingtype;
	}

	public void setBuildingtype(String buildingtype) {
		this.buildingtype = buildingtype;
	}

	public String getMetertype() {
		return this.metertype;
	}

	public void setMetertype(String metertype) {
		this.metertype = metertype;
	}

	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getNumber() {
		return this.number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getX() {
		return this.x;
	}

	public void setX(String x) {
		this.x = x;
	}

	public String getY() {
		return this.y;
	}

	public void setY(String y) {
		this.y = y;
	}

	public List<MeterReading> getMeterReadings() {
		return this.meterReadings;
	}

	public void setMeterReadings(List<MeterReading> meterReadings) {
		this.meterReadings = meterReadings;
	}

	public MeterReading addMeterReading(MeterReading meterReading) {
		getMeterReadings().add(meterReading);
		meterReading.setWaterMeter(this);

		return meterReading;
	}

	public MeterReading removeMeterReading(MeterReading meterReading) {
		getMeterReadings().remove(meterReading);
		meterReading.setWaterMeter(null);

		return meterReading;
	}

}