package com.netwrix.pages;


import com.netwrix.utilities.ConfigurationReader;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import static com.netwrix.utilities.Driver.getDriver;

public class Base {


    public Base(){
        PageFactory.initElements(getDriver(), this);
    }


    @FindBy(xpath = "//div[contains(@id,'Authenticate_login')]")
    public WebElement postLogin;

    @FindBy(xpath = "//button[@class='btn try-out__btn']")
    public WebElement tryItOut;

    @FindBy(xpath = "//textarea")
    public WebElement bodyParam;

    @FindBy(xpath = "//button[.='Execute']")
    public WebElement execute;

    @FindBy(xpath = "(//div/pre/code/span[5])[2]")
    public WebElement tokenText;


    @FindBy(xpath = "(//div[contains(@id,'Authenticate_register')])[1]")
    public WebElement postRegister;

    @FindBy(xpath = "//div/pre/code/span[10]")
    public WebElement registerSuccessMsg;

    @FindBy(xpath = "//div[contains(@id,'Baseball_batter_list')]")
    public WebElement baseballList;

    @FindBy(xpath = "//button[@class='btn authorize unlocked']")
    public WebElement authorizeBtnInitial;

    @FindBy(xpath = "//input[@type='text']")
    public WebElement tokenInputBox;

    @FindBy(xpath = "(//button[.='Authorize'])[2]")
    public WebElement authorizeBtnFinal;

    @FindBy(xpath = "//button[.='Close']")
    public WebElement closeBtn;

    @FindBy(xpath = "//tr[@class='response']//td[@class='response-col_status']")
    public WebElement responseStatusCode;



    public String getToken(String body){

        postLogin.click();

        tryItOut.click();

        bodyParam.clear();

        bodyParam.sendKeys(body);

        execute.click();

        return "Bearer " + tokenText.getText().substring(1, tokenText.getText().length() - 1);
    }


    public void registerUser(String body){
        postRegister.click();

        tryItOut.click();

        bodyParam.clear();

        bodyParam.sendKeys(body);

        execute.click();
    }

    public void authorize(String token){

        authorizeBtnInitial.click();

        tokenInputBox.sendKeys(token);

        authorizeBtnFinal.click();

        closeBtn.click();
    }



}
