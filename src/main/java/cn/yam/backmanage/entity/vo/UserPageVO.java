package cn.yam.backmanage.entity.vo;

/**
 * 功能：
 * 日期：2024/7/14 下午11:00
 */
public class UserPageVO {
    private Long id;
    private String username;
    private String roleName;
    private String lastLoginTime;
    private Boolean status;
    private String createTime;
    private String updateTime;

    public UserPageVO(Long id, String username, String roleName, String lastLoginTime, Boolean status, String createTime, String updateTime) {
        this.id = id;
        this.username = username;
        this.roleName = roleName;
        this.lastLoginTime = lastLoginTime;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public UserPageVO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}