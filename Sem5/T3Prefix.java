//2024/02/06 mip24
package Sem5;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class T3Prefix {
    public static void main(String[] args) {
        List<File> files = new ArrayList<>();
        String prefix = "prefix-";

        files.add(new File("path/file1.txt"));
        files.add(new File("path/file2.txt"));
        files.add(new File("path/file3.txt"));
        files.add(new File("path/file4.txt"));                
        files.add(new File("path/file5.txt"));        

        for(File file: files){
            System.out.println("Был файл: ");
            System.out.println(file.getName() + ", полный путь: "+ file.getAbsolutePath());
            File newFile = new File(file.getParent(), prefix+file.getName());
            file = newFile;
            System.out.println("Стал файл: ");
            System.out.println(file.getName() + ", полный путь: "+ file.getAbsolutePath());
            System.out.println("---");
        }
    }
}
