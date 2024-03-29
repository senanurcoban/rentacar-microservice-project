package com.kodlamaio.InventoryService.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Table(name="brands")
public class Brand {

	@Id
	@Column(name = "id")
	private String id;

	@Column(name = "name")
	private String name;
}
