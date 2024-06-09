package backend.actors;

public class Commissioner extends Manager {
    protected boolean isPublished;

    /**
     * @return boolean return the isPublished
     */
    public boolean isIsPublished() {
        return isPublished;
    }

    /**
     * @param isPublished the isPublished to set
     */
    public void setIsPublished(boolean isPublished) {
        this.isPublished = isPublished;
    }

    public boolean publish(String stats) {
        // Publish stuff
        return true;
    }

}
