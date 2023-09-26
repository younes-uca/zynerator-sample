package ma.zs.generator.engine.service.util;

import java.io.*;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class ZipUtil {

    public static void zipIt(File fileToZip, String path) {

        try {
            FileOutputStream fos = new FileOutputStream(path + "\\" + fileToZip.getName() + ".zip");
            ZipOutputStream zipOut = new ZipOutputStream(fos);

            zipFile(fileToZip, fileToZip.getName(), zipOut);

            zipOut.close();
            fos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static void zipFile(File fileToZip, String fileName, ZipOutputStream zipOut) throws IOException {
        if (fileToZip.isHidden()) {
            return;
        }
        if (fileToZip.isDirectory()) {
            if (fileName.endsWith("/")) {
                zipOut.putNextEntry(new ZipEntry(fileName));
                zipOut.closeEntry();
            } else {
                zipOut.putNextEntry(new ZipEntry(fileName + "/"));
                zipOut.closeEntry();
            }
            File[] children = fileToZip.listFiles();
            for (File childFile : children) {
                zipFile(childFile, fileName + "/" + childFile.getName(), zipOut);
            }
            return;
        }
        FileInputStream fis = new FileInputStream(fileToZip);
        ZipEntry zipEntry = new ZipEntry(fileName);
        zipOut.putNextEntry(zipEntry);
        byte[] bytes = new byte[1024];
        int length;
        while ((length = fis.read(bytes)) >= 0) {
            zipOut.write(bytes, 0, length);
        }
        fis.close();
    }

    public static void unZipArchive(String zip, String uncompressedDirectory) throws IOException {
        //Open the file
        try (ZipFile file = new ZipFile(zip)) {
            FileSystem fileSystem = FileSystems.getDefault();
            //Get file entries
            Enumeration<? extends ZipEntry> entries = file.entries();


            //Iterate over entries
            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                //If directory then create a new directory in uncompressed folder
                if (entry.isDirectory()) {
                    Files.createDirectories(fileSystem.getPath(uncompressedDirectory + entry.getName()));
                }
                //Else create the file
                else {
                    InputStream is = file.getInputStream(entry);
                    BufferedInputStream bis = new BufferedInputStream(is);
                    String uncompressedFileName = uncompressedDirectory + entry.getName();
                    Path uncompressedFilePath = fileSystem.getPath(uncompressedFileName);
                    Files.createFile(uncompressedFilePath);
                    FileOutputStream fileOutput = new FileOutputStream(uncompressedFileName);
                    while (bis.available() > 0) {
                        fileOutput.write(bis.read());
                    }
                    fileOutput.close();
                }
            }
        } catch (IOException e) {
            throw e;
        }

    }


    public static String convertByteArrayToZipFile(byte[] array, String name) {
        File file = new File(name + ".zip");

        try {

            OutputStream os = new FileOutputStream(file);
            os.write(array);
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file.getName();
    }

    public static byte[] convertZipToByteArray(File f) {
        if (f != null) {
            try {

                byte[] bytesArray = new byte[(int) f.length()];

                FileInputStream fis = new FileInputStream(f);
                fis.read(bytesArray); // read file into bytes[]
                fis.close();
                return bytesArray;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        } else
            return null;
    }

    public static byte[] convertZipToByteArray2(File f) {
        if (f != null) {
            try {
                return java.nio.file.Files.readAllBytes(f.toPath());
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        } else
            return null;
    }

    public static String zipBytes(String filename, byte[] input) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ZipOutputStream zos = new ZipOutputStream(baos);
        ZipEntry entry = new ZipEntry(filename);
        entry.setSize(input.length);
        zos.putNextEntry(entry);
        zos.write(input);
        zos.closeEntry();
        zos.close();
        baos.close();
        return filename;
    }
}
