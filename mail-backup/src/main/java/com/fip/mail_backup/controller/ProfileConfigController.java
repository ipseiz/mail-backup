/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.fip.mail_backup.controller;

import com.fip.mail_backup.common.FileTools;
import com.fip.mail_backup.model.ListProfileConfigModel;
import com.fip.mail_backup.view.ProfileConfigView;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.JFileChooser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Handles user interaction with listeners.
 * Calls View and Model as needed.
 * 
 * @author Fabien Ipseiz
 */
public class ProfileConfigController implements ButtonsListener {

    private final ProfileConfigView profileConfigView;
    private final ListProfileConfigModel profileConfigModel;
    
    /** SLF4J bound to logback-classic. */
    private static final Logger logger = LoggerFactory.getLogger(FileTools.class);
    
    //----------------------------------------------------------------------------
    // Le constructeur reçoit en paramètre les références du modèle et de la vue
    //----------------------------------------------------------------------------
    public ProfileConfigController(ListProfileConfigModel model, ProfileConfigView view) {
        profileConfigModel = model;
        profileConfigView = view;
    }
    
    @Override
    public void okPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void cancelPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * 
     * 
     */
    @Override
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
            profileConfigView.setSrcText(file.getPath());
        } else {
            logger.info("Open command cancelled by user.\n");
        }
    }

    @Override
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
            logger.info("Directory selected: " + file.getPath() + ".\n");
            profileConfigView.setTgtText(file.getPath());
        } else {
            logger.info("Open command cancelled by user.\n");
        }
    }
    
}
