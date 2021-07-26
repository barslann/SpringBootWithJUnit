package com.coderman.springbootwithjunit.springtestingbasics;

import org.assertj.core.util.Lists;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaxService {
    public String getBracket(int income){
        if(income < 1000) return "LOW";
        else if(income  < 5000) return "MEDIUM";
        else return "HIGH";
    }

    public List<String> allBrackets(){
        return Lists.newArrayList("LOW","MEDIUM","HIGH");
    }
}
