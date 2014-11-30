/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fip.mail_backup.model;

import com.fip.mail_backup.common.ProfileConfig;
import java.util.ArrayList;
import javax.swing.AbstractListModel;

/**
 * Model This model is completely independent of the user interface. 
 * This class contains a list of ProfileConfig objects
 *
 * @author Fabien Ipseiz
 */
public class ListProfileConfigModel extends AbstractListModel<Object> {

    private ArrayList<ProfileConfig> listProfileConfig = new ArrayList<>();
   
    /**
     * Creates a ProfilConfig and add it to the list of ProfileConfigModel
     * 
     * @param profileName   profile name of a Thunderbird e-mail account
     * @param profileSource path of the Thunderbird archive
     * @param profileTarget path of the Thunderbird backup
     */
    public ListProfileConfigModel(String profileName,String profileSource,String profileTarget){
        listProfileConfig.add(new ProfileConfig(profileName,profileSource,profileTarget));
    }
    
    /**
     * Create and initialize the model 
     * 
     * @param listProfileConfig
     */
    public ListProfileConfigModel(ArrayList listProfileConfig){
        this.listProfileConfig = listProfileConfig;
    }
    
    /**
     * Add a ProfilConfig to the list of ProfileConfigModel
     * 
     * @param profile 
     */
    public void addProfile(ProfileConfig profile) {
        int index = listProfileConfig.size();
        listProfileConfig.add(profile);
        fireIntervalAdded(this, index, index);
    }

    /**
     * Remove a ProfilConfig of the list of ProfileConfigModel
     * 
     * @param profile 
     */
    public void removeProfile(ProfileConfig profile) {
        int index = listProfileConfig.lastIndexOf(profile);
        if (index >= 0) {
            listProfileConfig.remove(profile);
            fireIntervalRemoved(this, index, index);
        }
    }
    
    /**
     * Change a selected ProfilConfig of the list of ProfileConfigModel
     * 
     * @param index
     * @param profile 
     */
    public void changeProfile(int index, ProfileConfig profile) {
        listProfileConfig.set(index, profile);
        fireIntervalRemoved(this, index, index);
    }
    
    public ProfileConfig getElementWithName(String name) {
        for (ProfileConfig listProfileConfig1 : listProfileConfig) {
            if (listProfileConfig1.getProfileName().equals(name)) {
                return listProfileConfig1;
            }
        }
        return null;   // Should never happen
    }
    
    public Integer getIndexForName(String name) {
        for (int i=0; i < listProfileConfig.size(); i++){
            if (listProfileConfig.get(i).getProfileName().equals(name)) {
                return i; 
            }
        }
        return null;    // Should never happen
    }
    
     public String getNameAt(int index) {
        return listProfileConfig.get(index).getProfileName();
    }
    
    @Override
    public int getSize() {
        return listProfileConfig.size();
    }

    @Override
    public ProfileConfig getElementAt(int index) {
        return listProfileConfig.get(index);
    }
    
}
