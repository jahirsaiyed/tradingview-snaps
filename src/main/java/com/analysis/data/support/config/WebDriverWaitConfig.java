package com.analysis.data.support.config;

import com.analysis.data.support.annotation.LazyConfiguration;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

@LazyConfiguration
public class WebDriverWaitConfig {

  @Value("${default.timeout:30}")
  private int timeout;

  @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
  @Bean
  public WebDriverWait webDriverWait(WebDriver driver) {
    return new WebDriverWait(driver, Duration.ofSeconds(this.timeout));
  }
}
