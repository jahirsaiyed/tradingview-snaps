package com.analysis.data.support.aop;

import com.analysis.data.support.annotation.TakeScreenshot;
import com.analysis.data.support.service.ScreenshotService;
import java.io.IOException;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ScreenshotAspect {

  @Autowired private ScreenshotService screenshotService;

  @After("@annotation(takeScreenshot)")
  public void after(TakeScreenshot takeScreenshot) throws IOException {
    this.screenshotService.takeScreenshot();
  }
}
