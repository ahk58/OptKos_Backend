package data_models;

import java.util.UUID;

public class Colour {

	private String brightness;
	private UUID colourId;
	private String hue;
	private String manufacturer;

	public Colour(){
	this.colourId = UUID.randomUUID();
	}

	public Colour(UUID colourId, String brightness, String hue, String manufacturer) {
		this.colourId = colourId;
		this.brightness = brightness;
		this.hue = hue;
		this.manufacturer = manufacturer;
	}

	public String getBrightness() {
		return brightness;
	}

	public void setBrightness(String brightness) {
		this.brightness = brightness;
	}

	public UUID getColourId() {
		return colourId;
	}

	public void setColourId(UUID colourId) {
		this.colourId = colourId;
	}

	public String getHue() {
		return hue;
	}

	public void setHue(String hue) {
		this.hue = hue;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
}