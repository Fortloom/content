package com.fortlom.content.interfaces.mapping.configuration;

import com.fortlom.content.interfaces.mapping.entity.EventMapper;
import com.fortlom.content.interfaces.mapping.entity.PublicationMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("enhancedModelMapperConfiguration")
public class MappingConfiguration {
    @Bean
    public EnhancedModelMapper modelMapper() {
        return new EnhancedModelMapper();
    }
    @Bean
    public EventMapper EventMapper() {
        return new EventMapper();
    }
    @Bean
    public PublicationMapper PublicationMapper() {
        return new PublicationMapper();
    }
}
