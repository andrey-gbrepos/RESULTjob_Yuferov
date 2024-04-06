   
import java.io.BufferedReader;
import java.io.File;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileStream {
    
    /**
     * Записывает список строк в файл
     * @param str
     * @param fileName
     * @return
     */
    public Boolean writeListTofile(List <String> str, String fileName){
        Boolean bool = false;
      File nameFile = new File(fileName);
       try {
        if (nameFile.createNewFile() ) {
           FileWriter writer = new FileWriter(nameFile, false);
            for (String item : str) {
                writer.append(item+"\n");
            }
            bool = true;
            //writer.flush();
            writer.close();
        }
        else {
            FileWriter writer = new FileWriter(nameFile, false);
           for (String item : str) {
                writer.append(item+"\n");
            }
            bool = true;

            //writer.flush();
            writer.close();
        }
    } catch (Exception e) {
     //e.printStackTrace();
     return bool;
    }
    return bool;
    }


    /**
     * Считывает файл в список строк
     * @param fileName
     * @return
     */
    public List <String> readfile(String fileName){

        List <String> items = new ArrayList<>();
    try (
        BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
        while (reader.ready()) { 
        items.add(reader.readLine()); 
        }
        reader.close();
    } 
    catch (IOException e) { 
        System.out.println("Проблема связи с файлом");
        e.printStackTrace();
    } 
    return items;
    }
    
}
