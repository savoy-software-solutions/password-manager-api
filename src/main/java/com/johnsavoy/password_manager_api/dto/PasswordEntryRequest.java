package com.johnsavoy.password_manager_api.dto;

public class PasswordEntryRequest {
    private String siteName;
    private String siteUrl;
    private String siteUsername;
    private String password;


    //Getter & Setters
    public String getSiteName() {
        return siteName;
    }
    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getSiteUrl() {
        return siteUrl;
    }
    public void setSiteUrl(String siteUrl) {
        this.siteUrl = siteUrl;
    }

    public String getSiteUsername() {
        return siteUsername;
    }
    public void setSiteUsername(String siteUsername) {
        this.siteUsername = siteUsername;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
