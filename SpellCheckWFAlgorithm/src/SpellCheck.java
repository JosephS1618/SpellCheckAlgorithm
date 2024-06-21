import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// gives all of the suggestions for the misspelled word within the given target distance for the minimum edit distance.
// minimum edit distance is calculated using the Wagner Fischer algorithm.
public class SpellCheck {
    private int[][] distanceArray;
    private String checkWord;
    private int targetDistance;
    private List<String> editWords;

    public SpellCheck(String checkWord,int targetDistance) throws Exception {
        this.checkWord = checkWord;
        this.targetDistance = targetDistance;
        editWords = new ArrayList<>();
        getValidWords();
    }

    private void getValidWords() throws Exception {
        File infile = new File("dictionary.txt");
        Scanner input = new Scanner(infile);
        String compareWord;

        while(input.hasNext()) {
            compareWord = input.next();
            if (editDistanceCalculator(checkWord, compareWord) == targetDistance) {
                editWords.add(compareWord);
            }
        }
        input.close();
    }

    //x traverses the first word. y traverses the second word. each team comparing the letters.
    private int editDistanceCalculator(String wrd1, String wrd2) {
        distanceArray = new int[wrd1.length()+1][wrd2.length()+1];
        fillFirstColumn();

        for (int x = 1; x < distanceArray.length; x++) {
            for (int y = 1; y < distanceArray[x].length; y++) {
                if (compareChar(wrd1.charAt(x-1), wrd2.charAt(y-1)) && !checkRepeat(x, y, wrd1, wrd2)) {
                    distanceArray[x][y] = findMin(x, y);
                } else {
                    distanceArray[x][y] = findMin(x, y) + 1;
                }
            }
        }

        return distanceArray[wrd1.length()][wrd2.length()];
    }

    private boolean compareChar(char c1, char c2) {
        return Character.toLowerCase(c1) == Character.toLowerCase(c2);
    }

    //Covers cases where repeating chars (e.g. "tt", "oo") are not accounted for
    //returns true if the given char is preceded by the same char
    //false otherwise. Uses an XOR operator to compare either for the first word or second word
    private boolean checkRepeat(int x, int y, String wrd1, String wrd2) {
        if (x == 1 || y == 1) {
            return false;
        }
        return compareChar(wrd2.charAt(y-1), wrd2.charAt(y-2)) ^ compareChar(wrd1.charAt(x-1), wrd1.charAt(x-2));
    }

    //finds the lowest distance between the corner, top, or left of the start value.
    private int findMin(int startX, int startY) {
        int corner = distanceArray[startX-1][startY-1];
        int top = distanceArray[startX-1][startY];
        int left = distanceArray[startX][startY-1];
        return Math.min(corner, Math.min(top, left));
    }

    //fills the first column with 0 to the total length of the array
    private void fillFirstColumn() {
        for (int x = 0; x < distanceArray[0].length; x++) {
            distanceArray[0][x] = x;
        }

        for (int y = 1; y < distanceArray.length; y++) {
            distanceArray[y][0] = y;
        }
    }

    public void getEditWords() {
        for (String s : editWords) {
            System.out.println(s);
        }
    }

    // TESTING

    public void manualCheck(String wrd1, String wrd2) {
        System.out.println(wrd1 + " -> " + wrd2 + " : " + editDistanceCalculator(wrd1, wrd2));
        printDistances();
    }

    private void printDistances() {
        for (int a = 0; a < distanceArray.length; a++) {
            for (int i = 0; i < distanceArray[a].length; i++) {
                System.out.print(distanceArray[a][i] + " ");
            }
            System.out.println();
        }
    }
}
