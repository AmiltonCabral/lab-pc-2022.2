package main.lab1.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FileIOUtil {
  public static ArrayList<String> readFile(String filePath, int numberOfActors){
    String line = "";
    ArrayList<String> fileItems = new ArrayList<String>();
  
    try {
      BufferedReader br = new BufferedReader(new FileReader(filePath));
      while ((line = br.readLine()) != null && numberOfActors > 0) {
        fileItems.add(line.substring(1, line.length() -1));
        numberOfActors--;
      }
      
      br.close();
    } catch(IOException e) {
      e.printStackTrace();
    }

    return fileItems;
  }

  public static void csvWriter(HashMap<String, Float> actorsRatingMap, String filePath) throws IOException {
    String eol = System.getProperty("line.separator");
  
    try (Writer writer = new FileWriter(filePath)) {
      for (Map.Entry<String, Float> entry : actorsRatingMap.entrySet()) {
        writer.append(entry.getKey())
          .append(',')
          .append(entry.getValue().toString())
          .append(eol);
      }
    } catch (IOException ex) {
      ex.printStackTrace(System.err);
    }
  }
}
