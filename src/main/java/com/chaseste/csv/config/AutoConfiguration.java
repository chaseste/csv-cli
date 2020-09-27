package com.chaseste.csv.config;

import com.chaseste.csv.aop.CliAspect;
import com.chaseste.csv.core.io.writer.OutputWriters;
import com.chaseste.csv.core.mapper.Mappers;
import com.chaseste.csv.core.transform.Transformers;
import com.chaseste.csv.core.validate.Validators;
import com.chaseste.csv.core.ops.Ops;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableConfigurationProperties({ApplicationProperties.class})
public class AutoConfiguration {

    @Bean
    public OutputWriters outputWriters(ApplicationProperties properties) {
        return new OutputWriters(properties);
    }

    @Bean
    public Mappers mappers() {
        return new Mappers();
    }

    @Bean
    public Transformers transformers(OutputWriters writers, Mappers mappers) {
        return new Transformers(writers, mappers);
    }

    @Bean
    public Validators validators(OutputWriters writers, Mappers mappers) {
        return new Validators(writers, mappers);
    }

    @Bean
    public Ops ops(ApplicationProperties properties, Transformers transformers, Validators validators, 
        OutputWriters writers) {
        return new Ops(properties, transformers, validators, writers);
    }

    @Configuration
    @EnableAspectJAutoProxy
    @ConditionalOnProperty(
        value="cli.verbose", 
        havingValue = "true")
    public class AopAutoConfiguration {

        @Bean
        public CliAspect cliAspect() {
            return new CliAspect();
        }
    }
}