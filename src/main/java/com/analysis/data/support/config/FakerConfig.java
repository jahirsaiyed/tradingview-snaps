package com.analysis.data.support.config;

import com.github.javafaker.Faker;
import com.analysis.data.support.annotation.LazyConfiguration;
import java.util.List;
import org.springframework.context.annotation.Bean;

@LazyConfiguration
public class FakerConfig {

  @Bean
  public Faker getFaker() {
    return new Faker();
  }

  @Bean
  public List<String> list() {
    return List.of("a", "b", "c");
  }
}
