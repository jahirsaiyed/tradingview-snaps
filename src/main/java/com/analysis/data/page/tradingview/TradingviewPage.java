package com.analysis.data.page.tradingview;

import com.analysis.data.support.annotation.Page;
import com.analysis.data.page.Base;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

@Page
public class TradingviewPage extends Base {

  @Autowired private SearchScrip searchComponent;
  @Autowired private ScripSearchResult searchResult;
  @Autowired private SearchScripLauncherPage launcher;

  public SearchScripLauncherPage getLauncher() {
    return launcher;
  }

  public TradingviewPage setLauncher(SearchScripLauncherPage launcher) {
    this.launcher = launcher;
    return this;
  }

  @Value("${tradingview.url}")
  private String url;

  public void goTo() {
    this.driver.get(this.url);
  }

  public SearchScrip getSearchComponent() {
    return searchComponent;
  }

  public ScripSearchResult getSearchResult() {
    return searchResult;
  }

  @Override
  public boolean isAt() {
    return this.launcher.isAt();
  }

  public void maximize() {
    this.driver.manage().window().maximize();
  }

  public void close() {
    this.driver.quit();
  }
}
