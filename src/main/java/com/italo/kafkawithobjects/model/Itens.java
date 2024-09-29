package com.italo.kafkawithobjects.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@RequiredArgsConstructor
public class Itens {

    private String parcela;
    private BigDecimal valorparcela;
    private Date vencimento;

}
