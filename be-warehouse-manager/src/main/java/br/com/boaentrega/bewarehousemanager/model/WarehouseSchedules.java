package br.com.boaentrega.bewarehousemanager.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "tb_warehouse_schedules")
public class WarehouseSchedules {
    @Id
    private Long id;
    @ManyToOne
    @JoinColumn(name = "warehouse")
    private Warehouse warehouse;
    private String schedules;
    private String type;
}
