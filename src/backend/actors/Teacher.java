package backend.actors;

public class Teacher extends User {
    protected int areaCode;
    protected String schoolName;
    protected String schoolID;
    protected String teacherID;

    /**
     * @return int return the areaCode
     */
    public int getAreaCode() {
        return areaCode;
    }

    /**
     * @param areaCode the areaCode to set
     */
    public void setAreaCode(int areaCode) {
        this.areaCode = areaCode;
    }

    /**
     * @return String return the schoolName
     */
    public String getSchoolName() {
        return schoolName;
    }

    /**
     * @param schoolName the schoolName to set
     */
    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    /**
     * @return int return the schoolID
     */
    public String getSchoolID() {
        return schoolID;
    }

    /**
     * @param schoolID the schoolID to set
     */
    public void setSchoolID(String schoolID) {
        this.schoolID = schoolID;
    }

    /**
     * @return int return the teacherID
     */
    public String getTeacherID() {
        return teacherID;
    }

    /**
     * @param teacherID the teacherID to set
     */
    public void setTeacherID(String teacherID) {
        this.teacherID = teacherID;
    }

}
