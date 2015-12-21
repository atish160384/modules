package org.motechproject.mtraining.domain;

import org.motechproject.mds.annotations.Access;
import org.motechproject.mds.annotations.Entity;
import org.motechproject.mds.annotations.Field;
import org.motechproject.mds.domain.MdsEntity;
import org.motechproject.mds.util.SecurityMode;
import org.motechproject.mtraining.util.Constants;

import java.util.Map;
import java.util.Objects;

/**
 * Bookmark object to store the progress for the user. This stores the identifier for the individual course units (like
 * chapters, lessons)
 */
@Entity(nonEditable = true)
@Access(value = SecurityMode.PERMISSIONS, members = {Constants.VIEW_MTRAINING_LOGS})
public class Bookmark extends MdsEntity {

    /**
     * external id used by implementation to track user
     */
    @Field
    private String externalId;

    /**
     * course identifier
     */
    @Field
    private String courseIdentifier;

    /**
     * chapter identifier
     */
    @Field
    private String chapterIdentifier;

    /**
     * lesson identifier
     */
    @Field
    private String lessonIdentifier;

    /**
     * Open, extensible map object field to let implementation store more details relevant
     * to bookmark
     */
    @Field
    private Map<String, Object> progress;

    public Bookmark() {
    }

    public Bookmark(String externalId, String courseIdentifier, String chapterIdentifier, String lessonIdentifier, Map<String, Object> progress) {
        this.externalId = externalId;
        this.courseIdentifier = courseIdentifier;
        this.chapterIdentifier = chapterIdentifier;
        this.lessonIdentifier = lessonIdentifier;
        this.progress = progress;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getCourseIdentifier() {
        return courseIdentifier;
    }

    public void setCourseIdentifier(String courseIdentifier) {
        this.courseIdentifier = courseIdentifier;
    }

    public String getChapterIdentifier() {
        return chapterIdentifier;
    }

    public void setChapterIdentifier(String chapterIdentifier) {
        this.chapterIdentifier = chapterIdentifier;
    }

    public String getLessonIdentifier() {
        return lessonIdentifier;
    }

    public void setLessonIdentifier(String lessonIdentifier) {
        this.lessonIdentifier = lessonIdentifier;
    }

    public Map<String, Object> getProgress() {
        return progress;
    }

    public void setProgress(Map<String, Object> progress) {
        this.progress = progress;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        final Bookmark other = (Bookmark) obj;

        return Objects.equals(this.getExternalId(), other.getExternalId())
                && Objects.equals(this.getChapterIdentifier(), other.getChapterIdentifier())
                && Objects.equals(this.getCourseIdentifier(), other.getCourseIdentifier())
                && Objects.equals(this.getLessonIdentifier(), other.getLessonIdentifier())
                && Objects.equals(this.getProgress(), other.getProgress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getExternalId(), getChapterIdentifier(), getCourseIdentifier(), getLessonIdentifier(), getProgress());
    }
}
