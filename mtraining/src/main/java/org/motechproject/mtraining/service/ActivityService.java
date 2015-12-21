package org.motechproject.mtraining.service;

import org.motechproject.mtraining.domain.ActivityRecord;

import java.util.List;

/**
 * Service interface for managing activity records. These are used for managing activities for single user/patient
 * and serve as enrollment records and quiz score records. These can be used to report activity for a user or course
 * as well as track progress in the course curriculum.
 */
public interface ActivityService {

    /**
     * Adds activity for a user (identified by externalId field in the record).
     *
     * @param activityRecord activity to record
     * @return activity record from the operation
     */
    ActivityRecord createActivity(ActivityRecord activityRecord);

    /**
     * Updates the activity for a user (set completion time, etc).
     *
     * @param activityRecord activity record to update
     * @return updated activity record
     */
    ActivityRecord updateActivity(ActivityRecord activityRecord);

    /**
     * Gets an activity by the Id.
     *
     * @param activityId Id of the user
     * @return activity record with id
     */
    ActivityRecord getActivityById(long activityId);

    /**
     * Gets a list of all activity for a user with the given externalId.
     *
     * @param externalId Id of the user
     * @return list of activity records
     */
    List<ActivityRecord> getAllActivityForUser(String externalId);

    /**
     * Gets all completed activity for user identified by the external id (decided by
     * whether completion time has been set on the record).
     *
     * @param externalId external id of the user
     * @return list of activity records
     */
    List<ActivityRecord> getCompletedActivityForUser(String externalId);

    /**
     * Deletes the activity by the record id.
     *
     * @param activityRecordId activity record id to delete
     */
    void deleteActivity(long activityRecordId);

    /**
     * Deletes all activity for a user with the given externalId (intended for data cleanups).
     *
     * @param externalId Id of the user
     */
    void deleteAllActivityForUser(String externalId);
}
