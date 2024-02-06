package Sem5.T1Backup;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Files;
import static java.nio.file.StandardCopyOption.*;
import java.io.File;
import java.io.IOException;

public class backup {
//    Path path = new Paths.get(".");
    public static void main(String[] args) {

        File backUpFolder = new File("./Sem5/T1Backup/backup");
        File folder = new File("./Sem5/T1Backup/filesToBackup");
        File[] listOfFilesAndFolders = folder.listFiles();
        List<File> listOfFiles = new ArrayList<>();
        
        System.out.println("");
        System.out.println("Имя папки источник: " + folder.getName());
        System.out.println("Полный путь таков : " + folder.getAbsolutePath());
        System.out.println("----- Список элементов папки -----");
        for(File fileOrFolder: listOfFilesAndFolders){
            System.out.print(fileOrFolder.getName());
            if(fileOrFolder.isFile()){
                System.out.print(" - это файл");
                listOfFiles.add(fileOrFolder);
            } else {
                System.out.print(" - это папка");
            }
            System.out.println("");
        }
        
        if(!backUpFolder.exists()){
            backUpFolder.mkdir();
        } else if(!backUpFolder.isDirectory()) {
            String errorMsg = "Имя папки для бэкапа занято файлом";
            System.out.println(errorMsg);
            throw new RuntimeException(errorMsg);
        }

        for(File fileToCopy: listOfFiles){
            try{
                File newFileName = new File(backUpFolder, fileToCopy.getName());
                System.out.print("Копируем файл: " + fileToCopy.getName());
                System.out.print(", в файл: " + newFileName.getAbsolutePath());

                Path oldPath = fileToCopy.toPath();
                Path newPath = newFileName.toPath();
                Path copied = Files.copy(oldPath, newPath, REPLACE_EXISTING);
                System.out.println(" - скопированно байт: " + Files.size(copied));
            } catch (IOException e) {
                System.out.println(""); 
                System.out.println("IO Error. Exeption: " + e.getClass());
                System.out.println("Message: " + e.getLocalizedMessage());
            }
        }
        //Files.
            
    }
}
