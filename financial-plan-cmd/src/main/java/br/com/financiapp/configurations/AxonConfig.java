package br.com.financiapp.configurations;

import br.com.financiapp.aggregates.FinancialProjectAggregate;
import io.micronaut.context.annotation.Factory;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.config.Configurer;
import org.axonframework.config.DefaultConfigurer;

@Factory
@Slf4j
public class AxonConfig {

    @Singleton
    public Configurer axonConfigurer(){
        log.info("Produzindo o configurador do servico de command");
        return DefaultConfigurer.defaultConfiguration()
                .configureAggregate(FinancialProjectAggregate.class);
    }

}
