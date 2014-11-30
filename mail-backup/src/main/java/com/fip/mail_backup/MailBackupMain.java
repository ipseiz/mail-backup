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
import java.io.FileOutputStream;
import java.io.IOException;
import static java.lang.System.getProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;
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

        /* Create and initialize the list of profiles (model) */
        final String configPath = "F:\\Utilisateurs\\Fabien\\NetBeansProjects\\mail-backup\\mail-backup\\src\\main\\resources\\config.properties.xml";
        ArrayList<ProfileConfig> initialListProfileConfig = new ArrayList<>();
        
        Properties properties = new Properties();
        // load xml config file in the properties table
        try (FileInputStream in = new FileInputStream(configPath)) {
            properties.loadFromXML(in);
        } catch (IOException e) {
            logger.error("Unable to load config file");
        }
        // create the list of profile from data of the properties table
        ProfileConfig config_1 = new ProfileConfig(properties.getProperty("profile_1", "Profile Template"),
                properties.getProperty("source_1", "F:\\Utilisateurs\\Fabien\\Documents\\Temp"),
                properties.getProperty("target_1", "F:\\Utilisateurs\\Fabien\\Documents\\Temp2"));
        ProfileConfig config_2 = new ProfileConfig(properties.getProperty("profile_2"),
                properties.getProperty("source_2"),properties.getProperty("target_2"));
        ProfileConfig config_3 = new ProfileConfig(properties.getProperty("profile_3"),
                properties.getProperty("source_3"),properties.getProperty("target_3"));
        initialListProfileConfig.add(config_1);
        initialListProfileConfig.add(config_2);
        initialListProfileConfig.add(config_3);
        
        //reading stuff
//        properties.setProperty("profile_2", "un nouveau profile");
//        FileOutputStream out;
//        try {
//            out = new FileOutputStream(configPath);
//            properties.storeToXML(out, "---config---");
//            out.close();
//        } catch (IOException e) {
//            System.err.println("Unable to write config file.");
//        }

        final ListProfileConfigModel model = new ListProfileConfigModel(initialListProfileConfig);
        
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
}
