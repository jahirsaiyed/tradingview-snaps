package com.analysis.data.tradingview;

import com.google.common.util.concurrent.Uninterruptibles;
import com.analysis.data.SpringBaseTestNGTest;
import com.analysis.data.support.annotation.LazyAutowired;
import com.analysis.data.page.tradingview.TradingviewPage;
import org.springframework.context.annotation.Lazy;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Lazy
public class OpenTradingviewTest extends SpringBaseTestNGTest{
    @LazyAutowired
    private TradingviewPage tradingviewPage;

    @Test
    public void open() throws IOException, InterruptedException {
        this.tradingviewPage.goTo();
        this.tradingviewPage.maximize();
        Assert.assertTrue(this.tradingviewPage.isAt());

        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
    }
}
