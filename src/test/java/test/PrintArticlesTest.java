package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;

public class PrintArticlesTest extends BaseTest{
    HomePage homePage;

    @Test(priority = 0)
    public void verifySpanishWebsite(){
        homePage = new HomePage(this.driver);
        Assert.assertTrue(homePage.validatePageInSpanish());
    }

    @Test(priority = 1)
    public void printArticles() {
        homePage = new HomePage(this.driver);
        homePage.printArticlesinOpinionSection();
        homePage.translateHeadersToEnglish();
    }

//    @Test(priority = 2)
//    public void verifyImageDownload() throws Exception {
//        homePage = new HomePage(this.driver);
//        homePage.saveImage();
//    }
}
