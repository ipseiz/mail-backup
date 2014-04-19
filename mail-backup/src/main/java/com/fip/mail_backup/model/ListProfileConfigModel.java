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
 * Model This model is completely independent of the user interface. It could as
 * easily be used by a command line or web interface.
 *
 * @author Fabien Ipseiz
 */
public class ListProfileConfigModel extends AbstractListModel<Object> {

    private final ArrayList<ProfileConfig> listProfileConfig = new ArrayList<>();
   
//    public void initProfile(ProfileConfig profile) {
//        listProfileConfig.add(new ProfileConfig("Fabien", "", ""));
//    }
    
    public void addProfile(ProfileConfig profile) {
        int index = listProfileConfig.size();
        listProfileConfig.add(profile);
        fireIntervalAdded(this, index, index);
    }

    public void removeProfile(ProfileConfig profile) {
        int index = listProfileConfig.lastIndexOf(profile);
        if (index >= 0) {
            listProfileConfig.remove(profile);
            fireIntervalRemoved(this, index, index);
        }
    }
    
    public void changeProfile(int index, ProfileConfig profile) {
        listProfileConfig.set(index, profile);
        fireIntervalRemoved(this, index, index);
    }
    
    public ProfileConfig getElementWithName(String name) {
        for (int i=0; i < listProfileConfig.size(); i++){
            if (listProfileConfig.get(i).getProfileName().equals(name)) {
                return listProfileConfig.get(i);
            }
        }
        return null;   
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
