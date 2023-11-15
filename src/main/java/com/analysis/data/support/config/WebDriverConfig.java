package com.analysis.data.support.config;

import com.analysis.data.support.annotation.ThreadScopeBean;
import com.analysis.data.support.annotation.LazyConfiguration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.*;

@LazyConfiguration
@Profile("!remote")
public class WebDriverConfig {

  @Primary
  @ThreadScopeBean
  @ConditionalOnProperty(name = "browser", havingValue = "firefox")
  public WebDriver firefoxDriver() {
    System.setProperty(
        "webdriver.gecko.driver", "");
    return new FirefoxDriver();
  }

  @ThreadScopeBean
  // @Scope("prototype")
  @ConditionalOnMissingBean
  public WebDriver chromeDriver() {
    System.setProperty(
        "webdriver.chrome.driver", "/opt/homebrew/bin/chromedriver");
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--remote-allow-origins=*");
    return new ChromeDriver(options);
  }
}
