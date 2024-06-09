package backend.actors;

public class Headmaster extends Manager {
    int schoolID;
    int districtID;

    public int getSchoolID() {
        return this.schoolID;
    }

    public void setSchoolID(int schoolID) {
        this.schoolID = schoolID;
    }

    public int getDistrictID() {
        return this.districtID;
    }

    public void setDistrictID(int districtID) {
        this.districtID = districtID;
    }

}
