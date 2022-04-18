package br.com.boaentrega.bewarehousemanager.repository;

import br.com.boaentrega.bewarehousemanager.model.LocalityPostcode;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

@org.springframework.stereotype.Repository
public interface LocalityPostcodeRepository extends Repository<LocalityPostcode, Long> {

    @Query(" FROM LocalityPostcode lp WHERE lp.postcodeBegin < :postcode AND postcodeEnd > :postcode ")
    LocalityPostcode findByRangePostcode(Long postcode);
}