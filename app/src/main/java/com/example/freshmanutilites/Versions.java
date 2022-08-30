package com.example.freshmanutilites;

public class Versions {

    private String codeName , version , apiLevel , description;

    // for expand the list ;)

    private boolean expandable;

    //

    public boolean isExpandable() {
        return expandable;
    }

    public void setExpandable(boolean expandable) {
        this.expandable = expandable;
    }

    public Versions(String codeName, String version, String apiLevel, String description) {
        this.codeName = codeName;
        this.version = version;
        this.apiLevel = apiLevel;
        this.description = description;
        this.expandable = false;        // create greater and setter // note : you create it after completing the recycler view so, if you
                                                                    //  need to understand and head or tail then create it at last :o..!!

    }

    public String getCodeName() {
        return codeName;
    }

    public String getVersion() {
        return version;
    }

    public String getApiLevel() {
        return apiLevel;
    }

    public String getDescription() {
        return description;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setApiLevel(String apiLevel) {
        this.apiLevel = apiLevel;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Versions{" +
                "codeName='" + codeName + '\'' +
                ", version='" + version + '\'' +
                ", apiLevel='" + apiLevel + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    // adapter class
}
