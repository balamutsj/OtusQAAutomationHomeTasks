public enum BrowserType {
    chrome("chrome"),
    firefox("firefox");

    private String browser;

    BrowserType(String browser) {
        this.browser = browser;
    }

    public String getBrowser() {
        return browser;
    }
}
