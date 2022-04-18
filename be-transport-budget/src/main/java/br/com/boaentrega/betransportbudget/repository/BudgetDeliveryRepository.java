package br.com.boaentrega.betransportbudget.repository;

import br.com.boaentrega.betransportbudget.model.BudgetDelivery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BudgetDeliveryRepository extends MongoRepository<BudgetDelivery, String> {
}
