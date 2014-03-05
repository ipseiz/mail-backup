package com.fip.mail_backup;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Description  Java application that performs a backup of Thunderbird data
 *
 * @author Fabien Ipseiz
 * @version 1.0
 *
 */
public class MailBackupGui extends JFrame {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPane;
    private boolean profileFabienSelected = true;
    private boolean profileIsabelleSelected;
    private boolean profileNicolasSelected;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MailBackupGui frame = new MailBackupGui();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public MailBackupGui() {
        setTitle("Thunderbird mail backup");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 330, 270);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu mnFile = new JMenu("File");
        menuBar.add(mnFile);

        JMenuItem mntmExit = new JMenuItem("Exit");
        mntmExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        mnFile.add(mntmExit);

        JMenu mnAbout = new JMenu("About");
        menuBar.add(mnAbout);

        JMenuItem mntmAboutThis = new JMenuItem("Version 1.0");
        mnAbout.add(mntmAboutThis);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        final JButton btnNewButton_1 = new JButton(" Save Profile");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (!profileFabienSelected && !profileIsabelleSelected && !profileNicolasSelected) {
                        JOptionPane.showMessageDialog(btnNewButton_1, "Sélectionner un profile à sauvegarder");
                    }
                    if (profileFabienSelected) {
                        Path srcPath = Paths.get("C:/Users/Fabien/AppData/Roaming/Thunderbird");
                        Path destPath = Paths.get("F:/Utilisateurs/Fabien/Documents/Thunderbird_Backup");
                        FileTools.copyFolderToFolder(srcPath, destPath);
                    }
                    if (profileIsabelleSelected) {
                        Path srcPath = Paths.get("C:/Users/Isabelle/AppData/Roaming/Thunderbird");
                        Path destPath = Paths.get("F:/Utilisateurs/Isabelle/Documents/Thunderbird_Backup");
                        FileTools.copyFolderToFolder(srcPath, destPath);
                    }
                    if (profileNicolasSelected) {
                        Path srcPath = Paths.get("C:/Users/Nicolas/AppData/Roaming/Thunderbird");
                        Path destPath = Paths.get("F:/Utilisateurs/Nicolas/Documents/Thunderbird_Backup");
                        FileTools.copyFolderToFolder(srcPath, destPath);
                    }
                } catch (IOException exception) {
                    exception.printStackTrace();
                    JOptionPane.showMessageDialog(btnNewButton_1, exception.toString()
                            + " Fermer Thunderbird et essayez à nouveau",
                            "Erreur lors de la sauvegarde", 0);
                }
            }
        });

        final JButton btnNewButton_2 = new JButton("Delete backup");
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (!profileFabienSelected && !profileIsabelleSelected && !profileNicolasSelected) {
                        JOptionPane.showMessageDialog(btnNewButton_2, "Sélectionner un profile à effacer");
                    }
                    if (profileFabienSelected) {
                        Path path = Paths.get("F:/Utilisateurs/Fabien/Documents/Thunderbird_Backup");
                        FileTools.deleteRecursive(path);
                    }
                    if (profileIsabelleSelected) {
                        Path path = Paths.get("F:/Utilisateurs/Isabelle/Documents/Thunderbird_Backup");
                        FileTools.deleteRecursive(path);
                    }
                    if (profileNicolasSelected) {
                        Path path = Paths.get("F:/Utilisateurs/Nicolas/Documents/Thunderbird_Backup");
                        FileTools.deleteRecursive(path);
                    }
                } catch (IOException exception) {
                    exception.printStackTrace();
                    JOptionPane.showMessageDialog(btnNewButton_2, exception.toString(),
                            "Erreur lors de l'effacement du répertoire Thunderbird_Backup", 0);
                }
            }
        });

        final JCheckBox chckbxNewCheckBox = new JCheckBox("Profile Fabien", true);

        chckbxNewCheckBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                profileFabienSelected = chckbxNewCheckBox.isSelected();
            }
        });

        final JCheckBox chckbxNewCheckBox_1 = new JCheckBox("Profile Isabelle");
        chckbxNewCheckBox_1.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                profileIsabelleSelected = chckbxNewCheckBox_1.isSelected();
            }
        });

        final JCheckBox chckbxNewCheckBox_2 = new JCheckBox("Profile Nicolas");
        chckbxNewCheckBox_2.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                profileNicolasSelected = chckbxNewCheckBox_2.isSelected();
            }
        });

        JLabel lblSelectTheProfiles = new JLabel("Select the profiles to manage");
        lblSelectTheProfiles.setFont(new Font("Tahoma", Font.PLAIN, 12));
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPane.createSequentialGroup()
                        .addGap(23)
                        .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addComponent(lblSelectTheProfiles)
                                                .addContainerGap(123, Short.MAX_VALUE))
                                        .addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
                                                .addComponent(btnNewButton_1)
                                                .addPreferredGap(ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                                                .addComponent(btnNewButton_2)
                                                .addGap(33)))
                                .addGroup(gl_contentPane.createSequentialGroup()
                                        .addGap(10)
                                        .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                                .addComponent(chckbxNewCheckBox_1)
                                                .addComponent(chckbxNewCheckBox)
                                                .addComponent(chckbxNewCheckBox_2))
                                        .addGap(180))))
        );
        gl_contentPane.setVerticalGroup(
                gl_contentPane.createParallelGroup(Alignment.TRAILING)
                .addGroup(gl_contentPane.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblSelectTheProfiles)
                        .addGap(14)
                        .addComponent(chckbxNewCheckBox)
                        .addPreferredGap(ComponentPlacement.UNRELATED)
                        .addComponent(chckbxNewCheckBox_1)
                        .addPreferredGap(ComponentPlacement.UNRELATED)
                        .addComponent(chckbxNewCheckBox_2)
                        .addGap(32)
                        .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
                                .addComponent(btnNewButton_2)
                                .addComponent(btnNewButton_1))
                        .addContainerGap())
        );
        contentPane.setLayout(gl_contentPane);
    }
}
