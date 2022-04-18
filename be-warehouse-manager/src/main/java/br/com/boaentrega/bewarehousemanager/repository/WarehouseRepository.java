package br.com.boaentrega.bewarehousemanager.repository;

import br.com.boaentrega.bewarehousemanager.model.Warehouse;
import org.springframework.data.repository.Repository;

@org.springframework.stereotype.Repository
public interface WarehouseRepository extends Repository<Warehouse, Long> {

}