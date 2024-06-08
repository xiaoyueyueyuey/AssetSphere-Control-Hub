package com.ach.domain.system.config;


import com.ach.domain.DomainEvent;
import lombok.Data;

@Data
public class ConfigUpdateEvent implements DomainEvent {
    private Integer configId;
    private String configValue;

}
