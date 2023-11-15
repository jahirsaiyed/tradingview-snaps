package com.analysis.data.page.tradingview;

import com.analysis.data.page.Base;
import com.analysis.data.support.annotation.LazyAutowired;
import com.analysis.data.support.annotation.PageFragment;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@PageFragment
public class SearchScripLauncherPage extends Base {

  @FindBy(xpath = "//*[@id=\"tv-content\"]/div[1]/div[1]/div/div[1]/button")
  private WebElement searchLauncher;

  @FindBy(name = "query")
  private WebElement searchBox;

  @FindBy(xpath = "//em[1]")
  private List<WebElement> searchBtns;


  @LazyAutowired
  private SearchScrip searchScrip;

  @LazyAutowired
  private ScripSearchResult searchResult;

  public void launch() throws InterruptedException {
    this.searchLauncher.click();
    search("AAPL");
    searchResult.isAt();
    searchResult.select();
    searchResult.hideWatchList();
    Actions action = new Actions(driver);
    action.keyDown(Keys.COMMAND).sendKeys(Keys.DOWN).perform();
    action.keyDown(Keys.COMMAND).sendKeys(Keys.DOWN).perform();
    action.keyDown(Keys.COMMAND).sendKeys(Keys.DOWN).perform();
    action.keyDown(Keys.COMMAND).sendKeys(Keys.DOWN).perform();
    action.keyDown(Keys.COMMAND).sendKeys(Keys.DOWN).perform();
  }

  private void search(final String keyword) throws InterruptedException {
    isAt(searchBox);
    this.searchBox.sendKeys(keyword);
    this.searchBox.sendKeys(Keys.TAB);
  }

  @Override
  public boolean isAt() {
    return isAt(this.searchLauncher);
  }

  private boolean isAt(WebElement webElement) {
    return this.webDriverWait.until(d -> webElement.isDisplayed());
  }
}
