package br.com.financiapp.models;

import java.time.LocalDateTime;
import java.util.UUID;

public class OperationType {

    private UUID id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String name;
    private UUID userId;
    private boolean active;

}
