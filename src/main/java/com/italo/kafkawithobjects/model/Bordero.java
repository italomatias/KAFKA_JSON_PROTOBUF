package com.italo.kafkawithobjects.model;

import lombok.Getter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
public class Bordero implements Serializable {

    private String bordero;
    private BigDecimal valor;
    private Date dataOperacao;
    private List<Itens> itens;

}
