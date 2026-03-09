public class WordsCounter {

    public static void main(String[] args) {

        String str = "This is a test string ";

        String[] words = str.split(" ");

        int wordCount = words.length;

        System.out.println("Number of words in the string: " + wordCount);
    }
}
