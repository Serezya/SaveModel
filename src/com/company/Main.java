package com.company;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {

    public static void main(String[] args) {
        String pathObjects = "D:\\Games\\savegames\\save.dat";
        String pathZip = "D:\\Games\\savegames\\test.zip";

        saveGame(pathObjects);
        zipFiles(pathObjects, pathZip);
        File deleteFile = new File(pathObjects);
        if (deleteFile.delete()) {
            System.out.println("save.dat удален");
        } else System.out.println("Файла save.dat не обнаружено");
    }


    private static void saveGame(String path) {
        GameProgress gameProgressOne = new GameProgress(50, 5, 10, 15.0);
        GameProgress gameProgressSecond = new GameProgress(89, 2, 12, 4.0);
        GameProgress gameProgressThird = new GameProgress(100, 9, 25, 55.0);

        try (FileOutputStream fos = new FileOutputStream(path)) {
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(gameProgressOne);
            oos.writeObject(gameProgressSecond);
            oos.writeObject(gameProgressThird);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void zipFiles(String pathObjects, String pathZip) {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(pathZip));
             FileInputStream fis = new FileInputStream(pathObjects);) {
            ZipEntry entry1 = new ZipEntry("save.dat");
            zout.putNextEntry(entry1);
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            zout.write(buffer);
            zout.closeEntry();
        } catch (Exception ex) {

            System.out.println(ex.getMessage());
        }
    }
}
