package com.JayPi4c.CoinCollection.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "COINS")
public class Coin implements Serializable {

	private static final long serialVersionUID = 1L;

	private Boolean possession;

	@Id
	@Column(unique = true, nullable = false)
	private String id;

	private String country;

	private Double value;

	private Integer manufacturingYear;

}
