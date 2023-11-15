package com.analysis.data.page.tradingview;

import com.analysis.data.support.annotation.PageFragment;
import com.analysis.data.page.Base;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@PageFragment
public class ScripSearchResult extends Base {

  @FindBy(xpath = "//em[1]")
  private List<WebElement> results;

  @FindBy(xpath = "/html/body/div[2]/div[6]/div/div[1]/div/div/div/div/button[1]")
  private List<WebElement> watchListButton;


  @FindBy(xpath = "//*[@id=\"header-toolbar-intervals\"]/button/div/div")
  private List<WebElement> intervalsBtn;

  @FindBy(xpath = "//span[text()='1 week']")
  private List<WebElement> weekInterval;

  @FindBy(xpath = "//span[text()='1 day']")
  private List<WebElement> dayInterval;

  @FindBy(xpath = "//span[text()='4 hours']")
  private List<WebElement> hour4Interval;

  @FindBy(xpath = "//span[text()='1 hour']")
  private List<WebElement> hour1Interval;

  @FindBy(xpath = "/html/body/div[2]/div[5]/div[2]/div[1]/div/div[2]/div[1]/div[2]/div/div[2]/div[1]/div[1]/div[2]/div/div[2]/div[2]")
  private List<WebElement> canvasLoaded;

  public int getCount() {
    return this.results.size();
  }

  @Override
  public boolean isAt() {
    return this.webDriverWait.until(d -> !this.results.isEmpty());
  }



  public void clickIntervalsBtn() {
    this.webDriverWait.until(d -> !this.intervalsBtn.isEmpty());
    this.intervalsBtn.stream().findFirst().ifPresent(WebElement::click);
  }

  public void select1WeekInterval() {
    clickIntervalsBtn();
    this.webDriverWait.until(d -> !this.weekInterval.isEmpty());
    this.weekInterval.stream().findFirst().ifPresent(WebElement::click);
    waitForChartLoad();
  }

  private void waitForChartLoad() {
    try {
      Thread.sleep(2000l);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public void select1DayInterval() {
    clickIntervalsBtn();
    this.webDriverWait.until(d -> !this.dayInterval.isEmpty());
    this.dayInterval.stream().findFirst().ifPresent(WebElement::click);
    waitForChartLoad();
  }

  public void select4hourInterval() {
    clickIntervalsBtn();
    this.webDriverWait.until(d -> !this.hour4Interval.isEmpty());
    this.hour4Interval.stream().findFirst().ifPresent(WebElement::click);
    waitForChartLoad();
  }

  public void select1hourInterval() {
    clickIntervalsBtn();
    this.webDriverWait.until(d -> !this.hour1Interval.isEmpty());
    this.hour1Interval.stream().findFirst().ifPresent(WebElement::click);
    waitForChartLoad();
  }

  public void select() {
    results.stream().findFirst().ifPresent(WebElement::click);
  }

  public boolean watchListVisible() {
    return this.webDriverWait.until(d -> !this.watchListButton.isEmpty());
  }
  public void hideWatchList() {
    watchListVisible();
    watchListButton.stream().findFirst().ifPresent(WebElement::click);
  }
}
