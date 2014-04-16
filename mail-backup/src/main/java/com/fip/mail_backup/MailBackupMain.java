/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fip.mail_backup;

import java.awt.EventQueue;
import javax.swing.UIManager;

/**
 * Description  Java application that performs a backup of Thunderbird data
 *
 * @author Fabien Ipseiz
 * @version 1.0
 *
 */
public class MailBackupMain {

    /**
     * Launches MailBackup application.
     *
     * @param args
     */
    public static void main(String[] args) {
//        EventQueue.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    MailBackupGui frame = new MailBackupGui();
//                    frame.setVisible(true);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });

        // Set the GUI look and feel:
        try {
            // Set System L&F
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        MailBackupGui view = new MailBackupGui();

        view.pack();
        view.setVisible(true);

    }
}
