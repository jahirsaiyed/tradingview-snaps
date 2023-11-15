package com.analysis.data.tradingview;

import com.analysis.data.SpringBaseTestNGTest;
import com.analysis.data.support.annotation.LazyAutowired;
import com.analysis.data.support.service.ScreenshotService;
import com.analysis.data.page.tradingview.TradingviewPage;
import org.springframework.context.annotation.Lazy;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

@Lazy
public class TradingviewTest extends SpringBaseTestNGTest {

  @LazyAutowired
  private OpenTradingviewTest openTradingviewTest;
  @LazyAutowired private TradingviewPage tradingviewPage;
  @LazyAutowired private ScreenshotService screenshotUtil;

  @Test
  public void googleTest() throws IOException, InterruptedException {
    this.openTradingviewTest.open();

    // this.googlePage.getSearchComponent().search("spring boot");
    this.tradingviewPage.getLauncher().launch();
    Assert.assertTrue(this.tradingviewPage.getSearchComponent().isAt());
    this.tradingviewPage.getSearchComponent().search("csx");
    Assert.assertTrue(this.tradingviewPage.getSearchResult().isAt());
    Thread.sleep(3000);
    Assert.assertTrue(this.tradingviewPage.getSearchResult().getCount() > 0);
    this.tradingviewPage.getSearchResult().select();
    Thread.sleep(10000);
    this.screenshotUtil.takeScreenshot();
    this.tradingviewPage.close();
  }
}
