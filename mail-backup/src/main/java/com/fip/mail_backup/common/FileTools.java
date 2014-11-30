package com.fip.mail_backup.common;

import java.nio.file.*;
import java.io.File;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Description  The FileTools class is a collection of file manipulation utilities
 *
 * @author Fabien Ipseiz
 */
public class FileTools {

    /** SLF4J bound to logback-classic. */
    private static final Logger logger = LoggerFactory.getLogger(FileTools.class);

    /**
     * FileTools Constructor (default).
     */
    public FileTools() {
    }

    /**
     * Check if a specified file path is a folder and create a folder if it does not exist.
     *
     * @param folderPath A folder path.
     * @throws java.io.IOException
     */
    public static final void createFolder(String folderPath) throws IOException {
        final Path folder = Paths.get(folderPath);
        if (Files.notExists(folder)) {
            Files.createDirectory(folder);
            logger.info("create directory:\n {}", folder);
        }
    }

    /**
     * Check if a specified file path is a folder and create a folder if it does not exist.
     *
     * @param folderPath A folder path.
     *
     * @deprecated THIS METHODE IS DEPRECATED! => it used the old java.io.file library
     */
    @Deprecated
    public static void checkFolder(String folderPath) {
        File file = new File(folderPath);
        if (!(file.isDirectory())) {
            file.mkdir();
            logger.info("create directory {}", file.getPath());
        }
    }

    /**
     * Delete a folder and all its contents (files and folders).
     *
     * @param folderPath A folder path.
     * @throws java.io.IOException If deleting files fails
     */
    public static final void deleteRecursive(Path folderPath) throws IOException {
        if (!Files.isDirectory(folderPath)) {
            return;
        }
	//DirectoryStream allows to browse all the items in a folder 
        //by performing an iteration on the elements it contains
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(folderPath)) {
            for (Path file : stream) {
                if (Files.isDirectory(file)) {
                    deleteRecursive(file); // folder is deleted only if it is empty
                } else {
                    Files.delete(file);
                }
            }
        //} catch (IOException | DirectoryIteratorException e) {
        //    e.printStackTrace();
        }

        Files.delete(folderPath);
        logger.info("folder {} and all it contents are removed", folderPath);
    }

    /**
     * Delete a directory.
     *
     * @param folderPath A folder path.
     *
     * @deprecated THIS METHODE IS DEPRECATED! => it used the old java.io.file library
     */
    @Deprecated
    public static final void deleteDirectory(File folderPath) {
        if (folderPath.exists()) {
            File[] files = folderPath.listFiles();
            for (File file : files) {
                if (file.isDirectory()) {
                    deleteDirectory(file);
                } else {
                    file.delete();
                    logger.info("file remove {}", file.getAbsolutePath());
                }
            }
        }
        folderPath.delete();
        logger.info("directory removed {}", folderPath.getAbsolutePath());
    }

    /**
     * Move a file from a source to a destination.
     *
     * @param src Source file path.
     * @param dst Destination file path.
     */
    public static final void moveFile(String src, String dst) {
        logger.debug("move process started");
        final Path srcPath = Paths.get(src);
        final Path destPath = Paths.get(dst);
        try {
            Files.move(srcPath, destPath);
        } catch (IOException e) {
            logger.error("move error {}", e);
        }
        logger.debug("move process done");
    }

    /**
     * Copy a file from a source to a destination.
     *
     * @param src Source file path.
     * @param dst Destination file path.
     */
    public static final void copy(String src, String dst) {
        logger.debug("copy process started");
        final Path srcPath = Paths.get(src);
        final Path destPath = Paths.get(dst);
        try {
            Files.copy(srcPath, destPath);
        } catch (IOException e) {
            logger.error("copy error {}", e);
        }
    }

    /**
     * Copy all files and directories from a source Folder to a destination Folder.
     *
     * @param sourceFolder Source folder path.
     * @param destinationFolder Destination folder path.
     * @throws java.io.IOException
     */
    public static final void copyFolderToFolder(Path sourceFolder, Path destinationFolder) throws IOException {
        // source folder is a folder (directory)
        logger.info(": \n" + "sourceFolder: " + sourceFolder + "\n" + "destinationFolder: " + destinationFolder);
        if (!Files.isDirectory(sourceFolder)) {
            logger.info(": \n" + "sourceFolder: " + sourceFolder + " is not a directory \n" + "copyFolderToFolder is cancelled ! ");
            return;
        }

        if (Files.notExists(destinationFolder)) {
            logger.info(destinationFolder + " does not exist. Creating...");
            Files.createDirectory(destinationFolder);
        }

        // Read the files list.
        DirectoryStream<Path> stream = Files.newDirectoryStream(sourceFolder);
        for (Path file : stream) {
            Path newfile = Paths.get(destinationFolder.toString(), file
                    .getFileName().toString());
            if (Files.isDirectory(file)) {
                copyFolderToFolder(file, newfile);
            } else {
                Files.copy(file, newfile, StandardCopyOption.REPLACE_EXISTING);
            }
        }
    }

    /**
     * Remove a file in a specified root directory.
     *
     * @param file A file path.
     * @param rootDirectory A root directory.
     */
    public static final void removeFile(String file, String rootDirectory) {
        // Remove a file on the local machine
        if (file.equalsIgnoreCase("")) {
            logger.info("no file to remove");
        }
        File dir = new File(rootDirectory);
        if (!dir.isDirectory()) {
            logger.error("not a folder message {}", rootDirectory);
        } else {
            String filename;
            if (rootDirectory.charAt(rootDirectory.length() - 1) == '/') {
                filename = rootDirectory + file;
            } else {
                filename = rootDirectory + "/" + file;
            }
            File f = new File(filename);
            if (f.exists()) {
                //TODO manage a return value in order, for example, to display a message frame when a problem occurs 
                f.delete();
                logger.info("file remove {}", filename);
            } else {
                logger.info("file already remove {}", f.getAbsolutePath());
            }
        }
    }
}
