package br.com.boaentrega.bewarehousemanager.repository;

import br.com.boaentrega.bewarehousemanager.model.WarehouseSchedules;
import org.springframework.data.repository.Repository;

import java.util.List;

@org.springframework.stereotype.Repository
public interface WarehouseSchedulesRepository extends Repository<WarehouseSchedules, Long> {

    List<WarehouseSchedules> findByWarehouseIdIn(List<Long> warehouseIds);
}