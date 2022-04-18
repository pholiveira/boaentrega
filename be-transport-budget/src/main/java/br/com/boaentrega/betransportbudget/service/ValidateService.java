package br.com.boaentrega.betransportbudget.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ValidateService {

    public Boolean isValidClient(Integer clientId) {
        return Boolean.TRUE;
    }
}
