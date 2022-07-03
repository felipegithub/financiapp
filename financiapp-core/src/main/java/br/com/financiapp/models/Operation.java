package br.com.financiapp.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Operation {

    private UUID id;
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private BigDecimal value;
    private CurrencyEnum currency;
    private OperationType type;
    private boolean active;

}
