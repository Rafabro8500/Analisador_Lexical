import java.util.ArrayList;
import java.util.Hashtable;

public class LexicalAnaliser {

    private ArrayList<String> input;
    private Hashtable<String, String> tokensTable;
    private ArrayList<String> tokens;
    private ArrayList<String> tokenTypes;

    public LexicalAnaliser(ArrayList<String> input) {
        this.input = input;
        tokensTable = new Hashtable<>();
        tokens = new ArrayList<>();
        tokenTypes = new ArrayList<>();
        tableInit();
        tokenize();
    }

    private void tableInit() {
        tokensTable.put(":=", "assign");
        tokensTable.put("+", "plus");
        tokensTable.put("-", "minus");
        tokensTable.put("*", "times");
        tokensTable.put("/", "div");
        tokensTable.put("(", "lparen");
        tokensTable.put(")", "rparen");
        tokensTable.put("read", "keyword");
        tokensTable.put("write", "keyword");
    }


    public void printFormatTokens(){
        for (int i = 0; i<tokens.size(); i++) {
            System.out.println("<"+tokenTypes.get(i)+", "+tokens.get(i)+">");
        }
    }

    private void tokenize() {
        for (String word : input) {
            StringBuilder token = new StringBuilder();
            for (int i = 0; i < word.length(); i++) {
                Character c = word.charAt(i);
                if (tokensTable.containsKey(c.toString())) {
                    tokens.add(Character.toString(c));
                    tokenTypes.add(tokensTable.get(c.toString()));
                    token = new StringBuilder();
                } else if (c == ':') {
                    if (i + 1 <= word.length()) {
                        if (word.charAt(i + 1) == '=') {
                            tokens.add(":=");
                            tokenTypes.add(tokensTable.get(":="));
                            i++;
                            token = new StringBuilder();
                        }
                    }
                } else if (c == '.') {
                    c=word.charAt(i);
                    token.append(c);
                    while (i + 1 < word.length() && Character.isDigit(word.charAt(i+1))) {
                        c = word.charAt(++i);
                        token.append(c);
                    }
                    tokens.add(token.toString());
                    tokenTypes.add("number");
                    token = new StringBuilder();
                } else if (Character.isDigit(c)) {
                    while (i<word.length() && Character.isDigit(c)){
                        c = word.charAt(i++);
                        token.append(c);
                    }
                    if (c == '.') {
                        c = word.charAt(i);
                        while (i<word.length() && Character.isDigit(c)) {
                            c = word.charAt(i++);
                            token.append(c);
                        }
                        tokens.add(token.toString());
                        tokenTypes.add("number");
                        token = new StringBuilder();
                    } else {
                        tokens.add(token.toString());
                        tokenTypes.add("number");
                        token = new StringBuilder();
                    }
                } else if (Character.isLetter(c)) {
                    token.append(c);
                    while (i + 1 < word.length() && (Character.isLetter(c) || Character.isDigit(c))) {
                        c = word.charAt(++i);
                        token.append(c);
                    }
                    tokens.add(token.toString());
                    tokenTypes.add("id");
                    token = new StringBuilder();
                }
            }
        }
    }
}



