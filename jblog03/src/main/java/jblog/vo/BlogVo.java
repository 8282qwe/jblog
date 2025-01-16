package jblog.vo;

public class BlogVo {

    private String userId;

    private String title;

    private String profile;

    public BlogVo() {
    }

    public BlogVo(String userId, String title) {
        this.userId = userId;
        this.title = title;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }
}
