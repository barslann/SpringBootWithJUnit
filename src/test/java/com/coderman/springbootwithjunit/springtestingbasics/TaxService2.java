package com.coderman.springbootwithjunit.springtestingbasics;

import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaxService2 {

    @Autowired  // -> Dependency
    private  TaxBracketService taxBracketService;

    public String getBracket(int income){
        if(income < 1000) return "LOW";
        else if(income  < 5000) return "MEDIUM";
        else return "HIGH";
    }

    public List<String> allBrackets(){
        return taxBracketService.all();
    }

    public void setTaxBracketService(TaxBracketService taxBracketService) {
        this.taxBracketService = taxBracketService;
    }
}
