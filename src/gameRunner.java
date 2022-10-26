import java.io.FileNotFoundException;
import java.util.*;
import java.io.File;

public class gameRunner {

    public static void runGame() throws FileNotFoundException {
        System.out.println("""
                
                Running game, please ensure you have the danish keyboard on by pressing alt+shift:
                                
                """);
        dataReader myDataReader = new dataReader();
        HashMap<String, String> myDataMap = myDataReader.readDataFile(new File("resources/words/failedDanskWords.txt"));
        //TODO change to have an input folder rather than an input file
        Stack<String> myStack = new Stack<>();
        for (String key : myDataMap.keySet()) {
            myStack.push(key);
        }
        Collections.shuffle(myStack);
        String currentWord;
        String currentAnswer;
        String userInputAnswer;
        Scanner myScanner = new Scanner(System.in);
        List<String> incorrectAnswers = new ArrayList<>();
        int numberComplete = 0;
        int totalWords = myStack.size();
        int numberCorrect = 0;
        while (!myStack.isEmpty()) {
            currentWord = myStack.pop();
            currentAnswer = myDataMap.get(currentWord);
            System.out.printf("""
                    Words complete: %d/%d
                    Correct: %d/%d
                                        
                    Enter English for:
                    ------------------
                    %s
                    ------------------

                    """, numberComplete, totalWords, numberCorrect, numberComplete, currentWord);
            userInputAnswer = myScanner.nextLine().replaceAll(" ", "").toLowerCase();
            if (userInputAnswer.equals(currentAnswer)) {
                System.out.println("""
                                                
                        Correct
                                                
                        """);
                numberCorrect++;
            } else {
                System.out.printf("""
                                                
                        Incorrect
                                                
                        User Input: %s
                                                
                        Actual Answer: %s
                                                
                        """, userInputAnswer, currentAnswer);
                incorrectAnswers.add(currentWord);
            }
            numberComplete++;
        }
        System.out.printf("""
                Game Over
                                
                Correct: %d/%d
                                
                Words to practise:
                                
                """, numberCorrect, numberComplete);
        for (String answer : incorrectAnswers) {
            System.out.printf("""
                    %s = %s
                    """, answer, myDataMap.get(answer));
        }
    }
}

