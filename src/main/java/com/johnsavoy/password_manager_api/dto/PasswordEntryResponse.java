package com.johnsavoy.password_manager_api.dto;

public class PasswordEntryResponse {
    private Long id;
    private String siteName;
    private String siteUrl;
    private String siteUsername;
    private String password;

    public PasswordEntryResponse(Long id, String siteName,
                                 String siteUrl, String siteUsername,
                                 String password) {
        this.id = id;
        this.siteName = siteName;
        this.siteUrl = siteUrl;
        this.siteUsername = siteUsername;
        this.password = password;
    }

    // Getter & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getSiteName() { return siteName; }
    public void setSiteName(String siteName) { this.siteName = siteName; }

    public String getSiteUrl() { return siteUrl; }
    public void setSiteUrl(String siteUrl) { this.siteUrl = siteUrl; }

    public String getSiteUsername() { return siteUsername; }
    public void setSiteUsername(String siteUsername) { this.siteUsername = siteUsername; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

}
