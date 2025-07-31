import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
public class forth {
    public static void main(String args[]) {
        Scanner n = new Scanner(System.in);
        File f = new File(n.nextLine());
        String content = "";
        try (Scanner s = new Scanner(f)) {
            while (s.hasNextLine()) {
                content += s.nextLine();
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error! File not found, exiting...");
        }
        ArrayList<String> tokens = lex(content);
        System.out.println(tokens);
        n.close();
    }
    public static ArrayList<String> lex(String content) {
        ArrayList<String> t = new ArrayList<>();
        char[] chars = content.toCharArray();
        boolean inStr = false;
        String token = "";
        for (char i : chars) {
            if (inStr) {
                if (i == '"') {
                    t.add(token);
                    t.add("\"");
                    token = "";
                    inStr = false;
                } else {
                    token += i;
                }
            } else {
                if (i == '"') {
                    if (!token.equals("")) {
                        t.add(token);
                        token = "";
                    }
                    t.add("\"");
                    inStr = true;
                } else if (i == ' ') {
                    if (!token.equals("")) {
                        t.add(token);
                        token = "";
                    }
                } else if (":;*+-/.".indexOf(i) >= 0) {
                    if (!token.equals("")) {
                        t.add(token);
                        token = "";
                    }
                    t.add("" + i);
                } else {
                    token += i;
                }
            }
        }
        if (!token.equals("")) {
            t.add(token);
        }
        return t;
    }
}