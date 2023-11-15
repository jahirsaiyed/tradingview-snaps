package com.analysis.data.support.service;

import com.github.javafaker.Faker;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.annotation.PostConstruct;

import org.apache.commons.lang3.BooleanUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

@Lazy
@Service
public class ScreenshotService {

  // @Autowired private TakesScreenshot driver;
  @Autowired private ApplicationContext ctx;

  @Value("${screenshot.path}")
  private Path path;

  @Value("${screenshot.skip.directory}")
  private Boolean skipDirectory;

  @Autowired private Faker faker;

  @PostConstruct
  private void init() {
    for (int i = 0; i < 10; i++) {
      try {
        Thread.sleep(500);
      } catch (Exception exception) {
        exception.printStackTrace();
      }
    }
  }

  public void takeScreenshot() throws IOException {
    takeScreenshot(faker.name().firstName(), faker.name().suffix());
  }

  public void takeScreenshot(String name, String suffix) throws IOException {
    File sourceFile = this.ctx.getBean(TakesScreenshot.class).getScreenshotAs(OutputType.FILE);
    Path directory = this.path.resolve(name);
    if (null != directory && !Files.isDirectory(directory)) {
      directory = Files.createDirectory(this.path.resolve(name));
    }
    FileCopyUtils.copy(sourceFile, directory.resolve( name + suffix + ".png").toFile());
  }

  public boolean skipDirectory(String name) {
    boolean skip = Boolean.FALSE;
    if (BooleanUtils.isFalse(skipDirectory)) {
      skip = Boolean.FALSE;
    }
    Path directory = this.path.resolve(name);
    if (null != directory && Files.isDirectory(directory)) {
      skip = Boolean.TRUE;
    }
    return skip;
  }

  public byte[] getScreenshot() {
    return this.ctx.getBean(TakesScreenshot.class).getScreenshotAs(OutputType.BYTES);
  }
}
