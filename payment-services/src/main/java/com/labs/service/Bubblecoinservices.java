package com.labs.service;

import com.labs.dto.SoldesBCoinDto;
import com.labs.entities.SoldesBCoin;
import com.labs.repository.SoldesBCoinRepository;
import lombok.extern.slf4j.Slf4j;
import javax.inject.Inject;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.math.BigDecimal;


@Slf4j
@ApplicationScoped
@Transactional
public class Bubblecoinservices {

    @Inject
    SoldesBCoinRepository soldesBCoinRepository;

    public SoldesBCoin create(String user_id) {
        if(!existsoldebubblecoin(user_id)){
            var solde = new SoldesBCoin(BigDecimal.ZERO,user_id);
            return this.soldesBCoinRepository.save(solde);
        }  else {
            throw new IllegalStateException("There is already a balance for this user");
        }

    }
    public SoldesBCoinDto createDto(String user_id) {
        return mapToDto(this.create(user_id));
    }

    public SoldesBCoinDto findById(Long id) {
        log.debug("Request to get OrderItem : {}", id);
        return this.soldesBCoinRepository.findById(id)
                .map(Bubblecoinservices::mapToDto).orElse(null);
    }
    public SoldesBCoinDto getsolde(String id){
        var solde= this.soldesBCoinRepository.getByUser_id(id);
        if (solde != null) {
            return mapToDto(solde);
        } else{
                throw new IllegalStateException("No new cart yet !!!");
        }
    }

    public SoldesBCoinDto loadaccount(Long id , BigDecimal balance){
        var soldesbc=this.soldesBCoinRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalStateException("The soldebc does not exist!"));
        soldesbc.setBalance(soldesbc.getBalance().add(balance));
        this.soldesBCoinRepository.save(soldesbc);
        soldesbc.getBalance().multiply(balance);
        this.soldesBCoinRepository.save(soldesbc);
        return mapToDto(soldesbc);
    }
    public SoldesBCoinDto unloadaccount(Long id , BigDecimal balance){
        var soldesbc=this.soldesBCoinRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalStateException("The soldebc does not exist!"));
        if(soldesbc.getBalance().compareTo(balance)<=0){
           soldesbc.setBalance(BigDecimal.ZERO);
        }else
        soldesbc.setBalance(soldesbc.getBalance().subtract(balance));
        this.soldesBCoinRepository.save(soldesbc);
        return mapToDto(soldesbc);
    }

    public boolean existsoldebubblecoin(String user_id){
        return this.soldesBCoinRepository.existsByUser_id(user_id);
    }

    public static SoldesBCoinDto mapToDto(SoldesBCoin soldesBCoin) {
        return new SoldesBCoinDto(
                soldesBCoin.getId(),
                soldesBCoin.getUser_id(),
                soldesBCoin.getBalance()
        );
    }
}