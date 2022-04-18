package br.com.boaentrega.bewarehousemanager.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "tb_wareshouse")
public class Warehouse {
    @Id
    private Long id;
    private String latitude;
    private String longitude;
    private String postcode;
    private Boolean active;
    private String type;
}
