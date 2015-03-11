/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fip.mail_backup;

import com.fip.mail_backup.common.ProfileConfig;
import com.fip.mail_backup.controller.ProfileConfigController;
import com.fip.mail_backup.model.ListProfileConfigModel;
import com.fip.mail_backup.view.MailBackupGUI;
import java.awt.EventQueue;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Description Java application that performs a backup of Thunderbird data
 *
 * @author Fabien Ipseiz
 * @version 1.0
 *
 */
public class MailBackupMain {

    public static final String CONFIG_PATH = "C:\\Users\\ipseiz\\Documents\\NetBeansProjects\\mail-backup\\mail-backup\\src\\main\\resources\\config.properties.xml";
    
    /**
     * SLF4J bound to logback-classic.
     */
    private static final Logger logger = LoggerFactory.getLogger(ProfileConfigController.class);

    /**
     * Launches Mail Backup application.
     *
     * @param args
     */
    public static void main(String[] args) {

        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MailBackupGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MailBackupGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MailBackupGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MailBackupGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        Properties properties = new Properties();
        // load xml config file in the properties table
        try (FileInputStream in = new FileInputStream(CONFIG_PATH)) {
            properties.loadFromXML(in);
        } catch (IOException e) {
            logger.error("Unable to load config file");
        }
        /* Create and initialize the list of profiles (model) */
        ListProfileConfigModel model = initModel(properties);
        
        /* Create the form */
        final MailBackupGUI view = new MailBackupGUI(model);

        /* Display the form */
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                view.setVisible(true);
            }
        });
    }

    /**
     * Create the list of profile from data of the properties table.
     *
     * @param properties
     */
    private static ListProfileConfigModel initModel(Properties properties) {
        
        ProfileConfig config_0 = new ProfileConfig(properties.getProperty("profile_0", "Fabien"),
                properties.getProperty("source_0", "C:\\Users\\ipseiz\\Documents\\Temp"),
                properties.getProperty("target_0", "C:\\Users\\ipseiz\\Documents\\Temp2"));
        ProfileConfig config_1 = new ProfileConfig(properties.getProperty("profile_1", "Isabelle"),
                properties.getProperty("source_1", ""),properties.getProperty("target_1", ""));
        ProfileConfig config_2 = new ProfileConfig(properties.getProperty("profile_2", "Nicolas"),
                properties.getProperty("source_2", ""),properties.getProperty("target_2", ""));
        
        ArrayList<ProfileConfig> initialListProfileConfig = new ArrayList<>();
        initialListProfileConfig.add(config_0);
        initialListProfileConfig.add(config_1);
        initialListProfileConfig.add(config_2);
        final ListProfileConfigModel model = new ListProfileConfigModel(initialListProfileConfig);
        return model;
    }
}
