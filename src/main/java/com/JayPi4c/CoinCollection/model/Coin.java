package com.JayPi4c.CoinCollection.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "COINS")
@NoArgsConstructor
public class Coin implements Serializable {

	private static final long serialVersionUID = 1L;

	private Boolean possession = false;

	@Id
	@Column(unique = true, nullable = false)
	private String id;

	private String country;

	private Double value;

	private Integer manufacturingYear;

	public Coin(Integer manufacturingYear, Double value, String country) {
		id = createID(manufacturingYear, value, country);
		this.value = value;
		this.manufacturingYear = manufacturingYear;
		this.country = country;
	}

	public Coin(Integer manufacturingYear, Double value, String country, Boolean possession) {
		this(manufacturingYear, value, country);
		this.possession = possession;
	}

	public static String createID(int year, double value, String country) {
		return Integer.toString(year) + Double.toString(value) + country;
	}
}
