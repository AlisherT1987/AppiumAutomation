package com.cydeo.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

public class Driver {
    private static AppiumDriver<MobileElement> driver;
    private static URL url;

    private Driver() {
    }

    public static AppiumDriver<MobileElement> getDriver() {
        String platform = ConfigurationReader.getProperty("platform");
        if (Objects.isNull(driver)) {
            switch (platform) {
                case "android":
                    DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
                    desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
                    desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.ANDROID);
                    desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "10.0");
                    desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel 3");
                    desiredCapabilities.setCapability(MobileCapabilityType.APP, "https://cybertek-appium.s3.amazonaws.com/calculator.apk");
                    try {
                        url = new URL("http://localhost:4723/wd/hub");
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    driver = new AndroidDriver<>(url, desiredCapabilities);
                    break;
                case "android-remote":
                    DesiredCapabilities caps = new DesiredCapabilities();

                    // Set your access credentials
                    caps.setCapability("browserstack.user", "testuser_1PhU8f");
                    caps.setCapability("browserstack.key", "qxU7LUK78o8BK1ki799f");

                    // Set URL of the application under test
                    caps.setCapability("app", "bs://e0ce6dfd61f8f7d9fd9c4fb11c746b65fd1d79f1");

                    // Specify device and os_version for testing
                    caps.setCapability("device", "OnePlus 8");
                    caps.setCapability("os_version", "10.0");
                    caps.setCapability("realMobile", "true");

                    // Set other BrowserStack capabilities
                    caps.setCapability("project", "My test appium automation");
                    caps.setCapability("build", "Java Android");
                    caps.setCapability("name", "Regression");

                    // Initialise the remote Webdriver using BrowserStack remote URL
                    // and desired capabilities defined above
                    try {
                        driver = new AndroidDriver<>(new URL("http://hub.browserstack.com/wd/hub"), caps);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    break;
                case "android-sauceLabApp":
                    DesiredCapabilities desiredCapabilities2 = new DesiredCapabilities();
                    desiredCapabilities2.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
                    desiredCapabilities2.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.ANDROID);
                    desiredCapabilities2.setCapability(MobileCapabilityType.PLATFORM_VERSION, "10.0");
                    desiredCapabilities2.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel 3");
                    desiredCapabilities2.setCapability(MobileCapabilityType.APP, "/Users/alishertussupbayev/IdeaProjects/Rest_Assured/AppiumAutomation/Android.SauceLabs.Mobile.Sample.app.2.7.1.apk");
                    desiredCapabilities2.setCapability("appPackage","com.swaglabsmobileapp");
                    desiredCapabilities2.setCapability("appActivity","com.swaglabsmobileapp.SplashActivity");
                    try {
                        url = new URL("http://localhost:4723/wd/hub");
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    driver = new AndroidDriver<>(url, desiredCapabilities2);
                    break;
                case "remote-android-swaglab":
                    // we get following capabilities setup from saucelab configurator, we change some lines according to our test need, and we need to add app location
                    String personalHubInfo = "oauth-XXXXXX-2ca62:4XXXXXXXc247364-XXXXXXXa2ea";
                    MutableCapabilities capsAndroid = new MutableCapabilities();
                    capsAndroid.setCapability("platformName", "Android");
                    capsAndroid.setCapability("appium:deviceName", "Samsung.*");
                    capsAndroid.setCapability("appium:deviceOrientation", "portrait");
                    capsAndroid.setCapability("appium:automationName", "UiAutomator2");
                    capsAndroid.setCapability(MobileCapabilityType.APP,"https://github.com/saucelabs/sample-app-mobile/releases/download/2.7.1/Android.SauceLabs.Mobile.Sample.app.2.7.1.apk");
                    capsAndroid.setCapability("appPackage","com.swaglabsmobileapp");
                    capsAndroid.setCapability("appActivity","com.swaglabsmobileapp.SplashActivity");
                    MutableCapabilities sauceOptions = new MutableCapabilities();
                    sauceOptions.setCapability("name", "swaglab test");
                    capsAndroid.setCapability("sauce:options", sauceOptions);

                    try {
                        url = new URL("https://oauth-alisher.tussupbayev.qa-64cca:f49d582e-15f4-489e-b718-a10610a275ae@ondemand.us-west-1.saucelabs.com:443/wd/hub");
                    } catch (MalformedURLException e) {
                        throw new RuntimeException(e);
                    }
                    driver = new AndroidDriver(url, capsAndroid); // polymorphism
                    break;
                case "remote-iphone-swaglab":
                    String personalHubInfoIOS = "oauth-XXXXXXXX-2ca62:4c247364-XXXX-4b73-b8df-2a2d945XXXXXba2ea";
                    MutableCapabilities capsIphone = new MutableCapabilities();
                    capsIphone.setCapability("platformName", "iOS");
                    capsIphone.setCapability("browserName", "Safari");
                    capsIphone.setCapability("appium:deviceName", "iPhone.*");
                    capsIphone.setCapability("appium:deviceOrientation", "portrait");
                    capsIphone.setCapability("appium:automationName", "XCUITest");
                    capsIphone.setCapability(MobileCapabilityType.APP,"https://github.com/saucelabs/sample-app-mobile/releases/download/2.7.1/iOS.RealDevice.SauceLabs.Mobile.Sample.app.2.7.1.ipa");
                    MutableCapabilities sauceOptionsIOS = new MutableCapabilities();
                    sauceOptionsIOS.setCapability("name", "swaglab test iphone");
                    capsIphone.setCapability("sauce:options", sauceOptionsIOS);
                   /* MutableCapabilities caps = new MutableCapabilities();
                    caps.setCapability("platformName", "iOS");
                    caps.setCapability("browserName", "Safari");
                    caps.setCapability("appium:deviceName", "iPhone.*");
                    caps.setCapability("appium:deviceOrientation", "portrait");
                    caps.setCapability("appium:automationName", "XCUITest");
                    MutableCapabilities sauceOptions = new MutableCapabilities();
                    sauceOptions.setCapability("username", "oauth-alisher.tussupbayev.qa-64cca");
                    sauceOptions.setCapability("accessKey", "f49d582e-15f4-489e-b718-a10610a275ae");
                    sauceOptions.setCapability("build", "<your build id>");
                    sauceOptions.setCapability("name", "<your test name>");
                    caps.setCapability("sauce:options", sauceOptions);



                    URL url = new URL("https://ondemand.us-west-1.saucelabs.com:443/wd/hub");
                    IOSDriver driver = new IOSDriver(url, caps);

                    */
                    try {
                        url = new URL("https://oauth-alisher.tussupbayev.qa-64cca:f49d582e-15f4-489e-b718-a10610a275ae@ondemand.us-west-1.saucelabs.com:443/wd/hub");
                    } catch (MalformedURLException e) {
                        throw new RuntimeException(e);
                    }
                    driver = new IOSDriver(url, capsIphone);
                    break;
                case "remote-android-swaglab-systemProp":
                    MutableCapabilities capsAndroidSYS = new MutableCapabilities();
                    capsAndroidSYS.setCapability("platformName", "Android");
                    capsAndroidSYS.setCapability("appium:deviceName", "Samsung.*");
                    capsAndroidSYS.setCapability("appium:deviceOrientation", "portrait");
                    capsAndroidSYS.setCapability("appium:automationName", "UiAutomator2");
                    capsAndroidSYS.setCapability(MobileCapabilityType.APP,"https://github.com/saucelabs/sample-app-mobile/releases/download/2.7.1/Android.SauceLabs.Mobile.Sample.app.2.7.1.apk");
                    capsAndroidSYS.setCapability("appPackage","com.swaglabsmobileapp");
                    capsAndroidSYS.setCapability("appActivity","com.swaglabsmobileapp.SplashActivity");
                    MutableCapabilities sauceOptionsSYS = new MutableCapabilities();
                    sauceOptionsSYS.setCapability("name", "swaglab test");
                    sauceOptionsSYS.setCapability("username",System.getenv("SAUCE_USERNAME"));
                    sauceOptionsSYS.setCapability("accessKey",System.getenv("SAUCE_ACCESS_KEY"));
                    capsAndroidSYS.setCapability("sauce:options", sauceOptionsSYS);

                    try {
                        url = new URL("https://ondemand.eu-central-1.saucelabs.com/wd/hub");   // either US or EU central hub
                        /*
                    private String SAUCE_EU_URL = "https://ondemand.eu-central-1.saucelabs.com/wd/hub";
                    private String SAUCE_US_URL = "https://ondemand.us-west-1.saucelabs.com/wd/hub";
                         */
                    } catch (MalformedURLException e) {
                        throw new RuntimeException(e);
                    }
                    driver = new AndroidDriver(url, capsAndroidSYS);
                    break;
            }
        }
        return driver;
    }

    public static void closeDriver() {
        if (Objects.nonNull(driver)) {
            driver.closeApp();
            driver = null;
        }
    }
}