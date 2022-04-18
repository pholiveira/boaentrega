package br.com.boaentrega.bewarehousemanager.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "tb_locality_postcode")
public class LocalityPostcode {
    @Id
    private Long id;
    private String state;
    private String locality;
    private Long postcodeBegin;
    private Long postcodeEnd;
    @ManyToOne
    @JoinColumn(name = "local_warehouse")
    private Warehouse localWarehouse;
    @ManyToOne
    @JoinColumn(name = "state_warehouse")
    private Warehouse stateWarehouse;
    @ManyToOne
    @JoinColumn(name = "regional_warehouse")
    private Warehouse regionalWarehouse;
}
