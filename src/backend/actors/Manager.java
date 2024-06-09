package backend.actors;

public abstract class Manager extends User {
    protected int employeeID;
    protected int regionID;
    protected int hierarchy;
    protected int[] region;

    /**
     * @return int return the employeeID
     */
    public int getEmployeeID() {
        return employeeID;
    }

    /**
     * @param employeeID the employeeID to set
     */
    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    /**
     * @return int return the regionID
     */
    public int getRegionID() {
        return regionID;
    }

    /**
     * @param regionID the regionID to set
     */
    public void setRegionID(int regionID) {
        this.regionID = regionID;
    }

    /**
     * @return int return the hierarchy
     */
    public int getHierarchy() {
        return hierarchy;
    }

    /**
     * @param hierarchy the hierarchy to set
     */
    public void setHierarchy(int hierarchy) {
        this.hierarchy = hierarchy;
    }

    /**
     * @return int[] return the region
     */
    public int[] getRegion() {
        return region;
    }

    /**
     * @param region the region to set
     */
    public void setRegion(int[] region) {
        this.region = region;
    }
}
