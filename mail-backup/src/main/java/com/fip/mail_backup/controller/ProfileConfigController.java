/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.fip.mail_backup.controller;

import com.fip.mail_backup.common.FileTools;
import static com.fip.mail_backup.common.FileTools.createFolder;
import com.fip.mail_backup.common.ProfileConfig;
import com.fip.mail_backup.model.ListProfileConfigModel;
import com.fip.mail_backup.view.ProfileConfigView;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Handles user interaction with listeners.
 * Calls View and Model as needed.
 * 
 * @author Fabien Ipseiz
 */
public class ProfileConfigController {

    private final ProfileConfigView profileConfigView;
    private final ListProfileConfigModel profileConfigModel;
    
    /** SLF4J bound to logback-classic. */
    private static final Logger logger = LoggerFactory.getLogger(ProfileConfigController.class);
    
    //----------------------------------------------------------------------------
    // Le constructeur reçoit en paramètre les références du modèle et de la vue
    //----------------------------------------------------------------------------
    /**
     * Creates Controller (of the MVC pattern)
     * 
     * @param model the model object reference
     * @param view  the View object reference
     */
    public ProfileConfigController(ListProfileConfigModel model, ProfileConfigView view) {
        profileConfigModel = model;
        profileConfigView = view;
    }
    
    /**
     * Updating of the selected Thunderbird profile config
     * 
     * @param e action on OK button
     */
    public void okPerformed(ActionEvent e) {
        ProfileConfig profileConfig = new ProfileConfig(profileConfigView.getNameText(), 
                profileConfigView.getSrcText(), profileConfigView.getTgtText());
        
        // check if a specified file path is a folder and create a folder if it does not exist
        try {
            FileTools.createFolder(profileConfigView.getSrcText());
            createFolder(profileConfigView.getTgtText());
        } catch (IOException ioe) {
            logger.error("An error occurs during Directory creation process");
        }
        // update selected profile config in the list of profiles  
        int index = profileConfigModel.getIndexForName(profileConfigView.getNameText());
        logger.info("Index: " + index + " - name: " + profileConfigView.getNameText());
        profileConfigModel.changeProfile(index,profileConfig);
        
        // close the Profile Configuration Frame
        profileConfigView.setVisible(false);
        profileConfigView.dispose();
    }

    /**
     * Quit the Profile Configuration Frame without any change
     * 
     * @param e action on Cancel button
     */
    public void cancelPerformed(ActionEvent e) {
        profileConfigView.setVisible(false);
        profileConfigView.dispose();
    }
    
    //----------------------------------------------------------------------------
    // Méthode appelée lorsque l'utilisateur clique sur le bouton "changeSrc"
    //----------------------------------------------------------------------------
    /**
     * Select the path corresponding on the Thunderbird profile to backup
     * 
     * @param e action on Change button
     */
    public void changeSrcPerformed(ActionEvent e) {
        // Create JFileChooser dialog panel:
        JFileChooser chooser = new JFileChooser();
        chooser.setApproveButtonText("Select");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setCurrentDirectory(new File(profileConfigView.getSrcText()));
        chooser.setDialogTitle("Select a Source Directory");
        int returnVal = chooser.showOpenDialog(null);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            // a file has been selected (button Select)
            File file = chooser.getSelectedFile();
            // name of the selected file
            logger.info("Directory selected: " + file.getPath() + ".\n");
            // view is updated with new file path
            profileConfigView.setSrcText(file.getPath());
        } else {
            logger.info("Open command cancelled by user.\n");
        }
    }
    
    //----------------------------------------------------------------------------
    // Méthode appelée lorsque l'utilisateur clique sur le bouton "changeTgt"
    //----------------------------------------------------------------------------
    /**
     * Select the path where the Thunderbird profile will be saved
     * 
     * @param e action on Change button
     */
    public void changeTgtPerformed(ActionEvent e) {
        // Create JFileChooser dialog panel:
        JFileChooser chooser = new JFileChooser();
        chooser.setApproveButtonText("Select");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setCurrentDirectory(new File(profileConfigView.getTgtText()));
        chooser.setDialogTitle("Select a Target Directory");
        int returnVal = chooser.showOpenDialog(null);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            // a file has been selected (button Select)
            File file = chooser.getSelectedFile();
            // name of the selected file
            logger.debug("Directory selected: " + file.getPath() + ".\n");
            // view is updated with new file path
            profileConfigView.setTgtText(file.getPath());
        } else {
            logger.debug("Open command cancelled by user.\n");
        }
    }
    
}
