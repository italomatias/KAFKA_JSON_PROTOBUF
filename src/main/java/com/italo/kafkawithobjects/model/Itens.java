package com.italo.kafkawithobjects.model;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
public class Itens {

    private String parcela;
    private BigDecimal valor;
    private Date vencimento;

}
