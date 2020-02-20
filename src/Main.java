import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> input = new ArrayList<>();
        while (sc.hasNext()) {
            input.add(sc.next());
        }
        LexicalAnaliser please = new LexicalAnaliser(input);
        please.printTokens();
    }
}
