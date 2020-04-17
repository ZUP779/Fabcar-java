package org.example;

import com.owlike.genson.annotation.JsonProperty;

import java.util.Date;
import java.util.Objects;

public final class Certification {
    private String bizId;

    private String userId;

    private String bizName;

    private Date createdTime;

    private Integer tag;

    private String filesHash;

    private String filesId;

    private String description;

    public Certification(@JsonProperty("bizId") String bizId, @JsonProperty("userId") String userId, @JsonProperty("bizName") String bizName,
                         @JsonProperty("createdTime") Date createdTime, @JsonProperty("tag") Integer tag, @JsonProperty("filesHash") String filesHash,
                         @JsonProperty("filesId") String filesId, @JsonProperty("description") String description) {
        this.bizId = bizId;
        this.userId = userId;
        this.bizName = bizName;
        this.createdTime = createdTime;
        this.tag = tag;
        this.filesHash = filesHash;
        this.filesId = filesId;
        this.description = description;
    }

    public String getBizId() {
        return bizId;
    }
    public String getUserId() {
        return userId;
    }

    public String getBizName() {
        return bizName;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public Integer getTag() {
        return tag;
    }

    public String getFilesHash() {
        return filesHash;
    }

    public String getFilesId() {
        return filesId;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Certification)) return false;
        Certification that = (Certification) o;
        return Objects.equals(bizId, that.bizId) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(bizName, that.bizName) &&
                Objects.equals(createdTime, that.createdTime) &&
                Objects.equals(tag, that.tag) &&
                Objects.equals(filesHash, that.filesHash) &&
                Objects.equals(filesId, that.filesId) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bizId, userId, bizName, createdTime, tag, filesHash, filesId, description);
    }

    @Override
    public String toString() {
        return "Certification{" +
                "bizId='" + bizId + '\'' +
                ", userId='" + userId + '\'' +
                ", bizName='" + bizName + '\'' +
                ", createdTime=" + createdTime +
                ", tag=" + tag +
                ", filesHash='" + filesHash + '\'' +
                ", filesId='" + filesId + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
