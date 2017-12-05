package com.huqi;

import org.apache.commons.httpclient.Header;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.annotation.Nullable;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.*;

/**
 * @author huqi
 * @create 2017-11-30 18:02
 **/
public class FirstTest {
    private static String username;
    private static String password;
    private static String config = "config.properties";
    private static String classpath = Class.class.getClass().getResource("/").getPath();

    static {
        System.setProperty("webdriver.chrome.driver", classpath + "chromedriver.exe");
        init();
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(gethrefInfo("诺梵松露黑巧克力礼盒装送女友400g年货团购散装批发（代可可脂）"));
    }

    public static List<Header> getHeaders() {
        List<Header> list = new ArrayList<Header>();
        list.add(new Header("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8"));
        list.add(new Header("accept-encoding", "gzip, deflate, br"));
        list.add(new Header("accept-language", "zh-CN,zh;q=0.8,en;q=0.6"));
        list.add(new Header("cache - control", "max-age=0"));
        list.add(new Header("upgrade-insecure-requests", "1"));
        list.add(new Header("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.78 Safari/537.36"));
        return list;
    }

    public static Map<String, String> gethrefInfo(String goods_name) {

        String loginUrl = "https://login.taobao.com/member/login.jhtml?style=mini&newMini2=true&css_style=alimama&from=alimama&redirectURL=http%3A%2F%2Fwww.alimama.com&full_redirect=true&disableQuickLogin=true";

//        String loginUrl = "https://login.taobao.com/member/login.jhtml?style=mini&newMini2=true&css_style=alimama&redirectURL=http%3A%2F%2Fpub.alimama.com&full_redirect=true&disableQuickLogin=true";

        WebDriver diver = new ChromeDriver();

        diver.manage().window().maximize();

        diver.get(loginUrl);

        WebDriverWait wait = new WebDriverWait(diver, 40);
        wait.until(new ExpectedCondition<WebElement>() {
            public WebElement apply(WebDriver d) {
                return d.findElement(By.id("J_Quick2Static"));
            }

        }).click();


        wait.until(new ExpectedCondition<WebElement>() {
            public WebElement apply(WebDriver d) {

                return d.findElement(By.id("TPL_username_1"));
            }
        }).sendKeys(username);


        wait.until(new ExpectedCondition<WebElement>() {
            public WebElement apply(WebDriver d) {

                return d.findElement(By.id("TPL_password_1"));
            }
        }).sendKeys(password);


        wait.until(new ExpectedCondition<WebElement>() {
            public WebElement apply(WebDriver d) {

                return d.findElement(By.id("J_SubmitStatic"));
            }
        }).click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        diver.get("http://pub.alimama.com/");


        wait.until(new ExpectedCondition<WebElement>() {
            public WebElement apply(WebDriver d) {

                return d.findElement(By.cssSelector(".search-inp.input"));
            }
        }).sendKeys(goods_name);


        wait.until(new ExpectedCondition<WebElement>() {
            public WebElement apply(WebDriver d) {

                return d.findElement(By.cssSelector(".btn.btn-brand.search-btn"));
            }
        }).click();

//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        wait.until(new ExpectedCondition<WebElement>() {
            public WebElement apply(WebDriver d) {
                return d.findElement(By.cssSelector(".box-btn-left"));
            }
        }).click();


        if (diver.findElement(By.cssSelector(".login-panel")).isDisplayed()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            wait.until(new ExpectedCondition<WebElement>() {
                public WebElement apply(WebDriver d) {

                    return d.findElement(By.id("TPL_password_1"));
                }
            }).sendKeys("noral137863mo");

            wait.until(new ExpectedCondition<WebElement>() {
                public WebElement apply(WebDriver d) {

                    return d.findElement(By.id("J_SubmitStatic"));
                }
            }).click();
        }

        wait.until(new ExpectedCondition<WebElement>() {
            public WebElement apply(WebDriver d) {
                return d.findElement(By.cssSelector(".btn.btn-brand.w100.mr10"));
            }
        }).click();

        String goods_href = wait.until(new ExpectedCondition<WebElement>() {
            public WebElement apply(WebDriver webDriver) {
                return webDriver.findElement(By.id("clipboard-target-1"));
            }
        }).getAttribute("value");


        String gift_href = wait.until(new ExpectedCondition<WebElement>() {
            public WebElement apply(WebDriver webDriver) {
                return webDriver.findElement(By.id("clipboard-target-2"));
            }
        }).getAttribute("value");

        Map<String, String> map = new HashMap<String, String>();
        map.put("gift_href", gift_href);
        map.put("goods_href", goods_href);

        return map;
    }


   /* public void back() throws InterruptedException {
        String loginUrl = "https://login.taobao.com/member/login.jhtml?style=mini&newMini2=true&css_style=alimama&from=alimama&redirectURL=http%3A%2F%2Fwww.alimama.com&full_redirect=true&disableQuickLogin=true";
//        System.setProperty("webdriver.gecko.driver", "E:/software/geckodriver.exe");
        WebDriver diver = new ChromeDriver();

        diver.manage().window().maximize();

        diver.get(loginUrl);

        WebDriverWait wait = new WebDriverWait(diver, 40);
        wait.until(new ExpectedCondition<WebElement>() {
            public WebElement apply(WebDriver d) {
                return d.findElement(By.id("J_Quick2Static"));
            }

        }).click();


        wait.until(new ExpectedCondition<WebElement>() {
            public WebElement apply(WebDriver d) {

                return d.findElement(By.id("TPL_username_1"));
            }
        }).sendKeys("changshazhuyijia");


        wait.until(new ExpectedCondition<WebElement>() {
            public WebElement apply(WebDriver d) {

                return d.findElement(By.id("TPL_password_1"));
            }
        }).sendKeys("noral137863mo");


        wait.until(new ExpectedCondition<WebElement>() {
            public WebElement apply(WebDriver d) {

                return d.findElement(By.id("J_SubmitStatic"));
            }
        }).click();

        Thread.sleep(2000);

//        diver.findElement(By.id("J_Quick2Static")).click();
//        diver.findElement(By.id("TPL_username_1")).sendKeys("");
//        diver.findElement(By.id("TPL_password_1")).sendKeys("");
//        diver.findElement(By.id("J_SubmitStatic")).click();


//            Thread.sleep(5000);

//        diver.get("http://pub.alimama.com/myunion.htm");


        diver.get("http://pub.alimama.com/");

//        WebDriverWait wait = new WebDriverWait(diver, 10);
//        Set<Cookie> cookies = diver.manage().getCookies();

//            Thread.sleep(1000);


        wait.until(new ExpectedCondition<WebElement>() {
            public WebElement apply(WebDriver d) {

                return d.findElement(By.cssSelector(".search-inp.input"));
            }
        }).sendKeys("诺梵松露黑巧克力礼盒装送女友400g年货团购散装批发（代可可脂）");

//        diver.findElement(By.cssSelector(".search-inp.input")).sendKeys("诺梵松露黑巧克力礼盒装送女友400g年货团购散装批发（代可可脂）");


        wait.until(new ExpectedCondition<WebElement>() {
            public WebElement apply(WebDriver d) {

                return d.findElement(By.cssSelector(".btn.btn-brand.search-btn"));
            }
        }).click();
//        diver.findElement(By.cssSelector(".btn.btn-brand.search-btn")).click();

//            org.apache.commons.httpclient.Cookie[] cookies1 = cookies.toArray(new org.apache.commons.httpclient.Cookie[cookies.size()]);


//            Thread.sleep(9000);


//        wait.until(new ExpectedCondition<WebElement>() {
//            public WebElement apply(WebDriver d) {
//                return d.findElement(By.cssSelector(".box-btn-left"));
//            }
//        }).click();


//        Thread.sleep(8000);

//        System.out.println("-----------------------" + diver.findElement(By.cssSelector(".box-btn-left")).getAttribute("data-spm-click"));
        wait.until(new ExpectedCondition<WebElement>() {
            public WebElement apply(WebDriver d) {
                return d.findElement(By.cssSelector(".box-btn-left"));
            }
        }).click();

//        diver.findElement(By.cssSelector("a.box-btn-left")).click();


        if (diver.findElement(By.cssSelector(".login-panel")).isDisplayed()) {
            Thread.sleep(1000);
            wait.until(new ExpectedCondition<WebElement>() {
                public WebElement apply(WebDriver d) {

                    return d.findElement(By.id("TPL_password_1"));
                }
            }).sendKeys("noral137863mo");

            wait.until(new ExpectedCondition<WebElement>() {
                public WebElement apply(WebDriver d) {

                    return d.findElement(By.id("J_SubmitStatic"));
                }
            }).click();
        }

        wait.until(new ExpectedCondition<WebElement>() {
            public WebElement apply(WebDriver d) {

                return d.findElement(By.cssSelector(".btn.btn-brand.w100.mr10"));
            }
        }).click();

        String goods_href = wait.until(new ExpectedCondition<WebElement>() {
            public WebElement apply(WebDriver webDriver) {
                return webDriver.findElement(By.id("clipboard-target-1"));
            }
        }).getAttribute("value");


        String gift_href = wait.until(new ExpectedCondition<WebElement>() {
            public WebElement apply(WebDriver webDriver) {
                return webDriver.findElement(By.id("clipboard-target-2"));
            }
        }).getAttribute("value");

//        wait.until(new ExpectedCondition<WebElement>() {  clipboard-target-2
//            public WebElement apply(WebDriver d) {
//                return d.findElement(By.cssSelector(".btn.btn-brand.w100.mr10"));
//            }
//        }).click();
//        diver.findElement(By.cssSelector(".btn.btn-brand.w100.mr10")).click();

//            diver.close();

    }*/

    public static void init() {
        InputStream is = null;
        try {
            File f = new File(classpath
                    + config);
            if (f.exists()) {
                is = new FileInputStream(f);
            }
            ResourceBundle prop = new PropertyResourceBundle(is);
            username = prop.getString("username");
            password = prop.getString("password");

        } catch (Exception e) {
        }
    }
}
