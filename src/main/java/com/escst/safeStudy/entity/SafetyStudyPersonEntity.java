package com.escst.safeStudy.entity;

import java.io.Serializable;

/**
 * @author caozx
 * @desc
 * @date 2017/3/7 15:46
 */
public class SafetyStudyPersonEntity implements Serializable {
    /**
	 * @Fields serialVersionUID 
	 */
	private static final long serialVersionUID = -3004534488045181495L;
	private String id;
    /**安全学习id**/
    private String safetyStudyId;
    /**人员id**/
    private String  personId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSafetyStudyId() {
        return safetyStudyId;
    }

    public void setSafetyStudyId(String safetyStudyId) {
        this.safetyStudyId = safetyStudyId;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }
}
