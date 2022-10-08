import java.io.*;
import java.util.*;

public class AddQuality {
    public static void main(String[] args) {

        String[] facilityType = {"busexchange", "healthcare", "mrt", "prisch", "secsch"};
        Random random = new Random();
        try{
            for (int i=0;i<facilityType.length;i++){
                File file = new File("src/resources/"+facilityType[i]+"_locations.csv");
                Scanner scanner = new Scanner(file);

                File output = new File("src/resources/updated/"+facilityType[i]+"_locations.csv");
                FileWriter fileWriter = new FileWriter(output);

                while(scanner.hasNextLine()){
                    fileWriter.append(scanner.nextLine()+","+(random.nextInt(100)+1)+"\n");
                }
                fileWriter.close();
            }
        }
        catch (java.io.FileNotFoundException exception){
            exception.printStackTrace();
        }
        catch (java.io.IOException exception){
            exception.printStackTrace();
        }

    }


}
