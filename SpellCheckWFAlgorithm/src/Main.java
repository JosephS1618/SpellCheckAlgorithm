//Compares the edit distance from the first word to the second word using the Wagner Fischer edit distance algorithm
public class Main {
    public static void main(String[] args) {
        try {
            SpellCheck sc = new SpellCheck("boat", 1);
            sc.getEditWords();
            //sc.manualCheck("boat", "boost"); //TODO: repeating 'o' messes with the algorithm
            sc.manualCheck("boat", "bat");
            sc.manualCheck("boat", "bolt");
        } catch (Exception FileNotFoundException) {
            System.err.print("File not found");
        }
    }

}