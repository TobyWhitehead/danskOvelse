import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class dataReader {

    public HashMap<String, String> readDataFile(File dataFile) throws FileNotFoundException {
        Scanner myScanner = new Scanner(dataFile);
        HashMap<String, String> outputMap = new HashMap<>();
        String currentLine;
        String[] splitLine;
        int lineCounter = 0;
        while (myScanner.hasNextLine()) {
            lineCounter++;
            currentLine = myScanner.nextLine();
            splitLine = currentLine.replaceAll(" ", "").toLowerCase().split("=");
            try {
                outputMap.put(splitLine[0], splitLine[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.printf("index out of bounds on line %d", lineCounter);
            }
        }
        return outputMap;
    }
}
