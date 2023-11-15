package com.analysis.data.page.tradingview;

import com.analysis.data.page.Base;
import com.analysis.data.support.annotation.PageFragment;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@PageFragment
public class SearchScrip extends Base {

  @FindBy(xpath = "//*[@id=\"header-toolbar-symbol-search\"]/div")
  private WebElement searchBox;


  @FindBy(xpath = "//*[@id=\"overlap-manager-root\"]/div/div/div[2]/div/div[2]/div[1]/input")
  private WebElement searchInput;

  @FindBy(xpath = "//*[@id=\"overlap-manager-root\"]/div/div/div[2]/div/div[5]/div/div/div[1]/div[1]")
  private List<WebElement> searchBtns;

  public void search(final String keyword) throws InterruptedException {
    this.searchBox.click();
    this.searchInput.sendKeys(keyword);
    this.searchInput.sendKeys(Keys.TAB);
    Thread.sleep(1000l);
    this.searchBtns.stream().findFirst().ifPresent(WebElement::click);
  }

  @Override
  public boolean isAt() {
    return isAt(searchBox);
  }

  private boolean isAt(WebElement webElement) {
    return this.webDriverWait.until(d -> webElement.isDisplayed());
  }
}
