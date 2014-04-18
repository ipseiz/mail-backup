package com.fip.mail_backup.common;
// DirectoryPair.java

/**
 * Represents source directory of a Thunderbird profile and target directory
 * where this profile is backuped
 *
 * @author Fabien Ipseiz
 */
public class ProfileConfig {

     /**
     * The Thunderbird profile name.
     */
    private String profileName;

    /**
     * Returns the name of the profile.
     *
     * @return profile name.
     */
    public String getProfileName() {
        return profileName;
    }
    /**
     * Sets the name of the profile.
     *
     * @param name : name of the profile.
     */
    public void setProfileName(String name) {
        profileName = name;
    }
    
    /**
     * The source directory.
     */
    private String srcDir;

    /**
     * The target directory.
     */
    private String tgtDir;

    /**
     * Creates objects out of corresponding strings.
     *
     * @param name   String of the Thunderbird profile name
     * @param srcDir String of the source directory.
     * @param tgtDir String of the target directory.
     */
    public ProfileConfig(String name, String srcDir, String tgtDir) {
        profileName = name;
        this.srcDir = srcDir;
        this.tgtDir = tgtDir;
    }

    /**
     * Returns the source directory.
     *
     * @return Source Directory.
     */
    public final String getSrc() {
        return srcDir;
    }

    /**
     * Sets the source directory.
     *
     * @param srcDir Source Directory.
     */
    public final void setSrc(String srcDir) {
        this.srcDir = srcDir;
    }

    /**
     * Returns the target directory.
     *
     * @return Target Directory.
     */
    public final String getTgt() {
        return tgtDir;
    }

    /**
     * Sets the target directory.
     *
     * @param tgtDir Target Directory.
     */
    public final void setTgt(String tgtDir) {
        this.tgtDir = tgtDir;
    }
}
