package com.analysis.data.tradingview;

import com.analysis.data.Data;
import com.analysis.data.SpringBaseTestNGTest;
import com.analysis.data.support.annotation.LazyAutowired;
import com.analysis.data.support.service.ScreenshotService;
import com.analysis.data.page.tradingview.TradingviewPage;
import org.springframework.context.annotation.Lazy;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

@Lazy
public class OpenScripTest extends SpringBaseTestNGTest {
    @LazyAutowired
    private TradingviewPage tradingviewPage;
    @LazyAutowired private ScreenshotService screenshotUtil;

    @Test
    public void captureScrips() throws IOException, InterruptedException, URISyntaxException {

        Object[][] csvData = Data.getCsvData(new File (this.getClass().getResource("/data/nasdaq_screener.csv").toURI()));
        this.tradingviewPage.getLauncher().launch();

        for (Object[] csv : csvData) {
            String scripName = (String) ((Object[])csv[1])[0];
            if (screenshotUtil.skipDirectory(scripName)) continue;
            logger.info(scripName);
            this.tradingviewPage.getSearchComponent().search(scripName);
            this.tradingviewPage.getSearchResult().select1WeekInterval();
            this.screenshotUtil.takeScreenshot(scripName, "-1W");

            this.tradingviewPage.getSearchResult().select1DayInterval();
            this.screenshotUtil.takeScreenshot(scripName, "-1D");

            this.tradingviewPage.getSearchResult().select4hourInterval();
            this.screenshotUtil.takeScreenshot(scripName, "-4H");

            this.tradingviewPage.getSearchResult().select1hourInterval();
            this.screenshotUtil.takeScreenshot(scripName, "-1H");
        }

//        this.tradingviewPage.close();
    }
}
