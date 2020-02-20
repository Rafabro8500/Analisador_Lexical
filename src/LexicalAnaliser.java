import java.util.ArrayList;

public class LexicalAnaliser {

    private ArrayList<String> input;

    public LexicalAnaliser(ArrayList<String> input) {
        this.input = input;
    }

    public void printTokens(){
        for(String word : input){
            int i = 0;
            Character c;
            while (i<word.length()){
                c = word.charAt(i++);
                if(!Character.isDigit(c) && !Character.isLetter(c))
                    System.out.println("<" + getToken(c.toString()) + ", " + c + ">");
            }
            System.out.println("<" + getToken(word) + ", " + word +">");
        }
    }

    public String getToken(String word) {
        int i = 0;
        char c = word.charAt(i);
        if (word.equals("read") || word.equals("write")) return "keyword";
        else if (c == '(') return "lparen";
        else if (c == ')') return "rparen";
        else if (c == '+') return "plus";
        else if (c == '-') return "minus";
        else if (c == '*') return "times";
        else if (c == '/') return "div";
        else if (c == ':') {
            c = word.charAt(++i);
            if (c == '=') return "assign";
            else System.exit(1);
        }
        else if (c == '.') {
            c = word.charAt(++i);
            if (Character.isDigit(c)) {
                while (i<word.length() && Character.isDigit(c))
                    c = word.charAt(i++);
                return "number";
            } else System.exit(1);
        }
        else if (Character.isDigit(c)) {
            while (i<word.length() && Character.isDigit(c))
                c = word.charAt(i++);
            if (c == '.') {
                c = word.charAt(i++);
                while (i<word.length() && Character.isDigit(c)) {
                    c = word.charAt(i++);
                    if(!Character.isDigit(c)) System.exit(1);
                }
                return "number";
            } else{
                if(!Character.isDigit(c)) System.exit(1);
                return "number";
            }
        }
        else if (Character.isLetter(c)) {
            while (i<word.length()  && (Character.isDigit(c) || Character.isLetter(c)))
                c = word.charAt(i++);
            return "id";
        }

        return "Erro";
    }
}



