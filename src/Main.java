import java.io.PrintStream;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class Main {

    public static void main(String[] args) {

        new Main().start(args);
    }

    private void start(String[] args){

        Pattern specialPattern = Pattern.compile("[*?^+\\[\\]{}-]");
        Pattern[] patterns = new Pattern[args.length];
        for (int i = 0; i < args.length; i++){
            try{
                patterns[i] = Pattern.compile(args[i]);
            }catch (PatternSyntaxException e){
                StringBuilder stringBuilder = new StringBuilder();
                for (int j = 0; j < args[i].length(); j++){
                    if (specialPattern.matcher(String.valueOf(args[i]
                                                     .charAt(j)))
                                                     .matches()){
                        stringBuilder.append('\\');
                    }
                    stringBuilder.append(args[i].charAt(j));
                }
                patterns[i] = Pattern.compile(stringBuilder.toString());
            }
        }

        Scanner in = new Scanner(System.in);
        PrintStream out = new PrintStream(System.out);

        String currentString;
        boolean end;
        while (true) {
            if (in.hasNextLine()) {
                currentString = in.nextLine();
                if (currentString.equals("")) {
                    in.close();
                    out.close();
                    break;
                }
                end = false;
                String[] parsingString = currentString.split("[ ;,\\s]");
                for (Pattern pattern : patterns) {
                    for (String source : parsingString) {
                        if (pattern.matcher(source).matches()) {
                            out.println(currentString);
                            end = true;
                            break;
                        }
                    }
                    if (end){
                        break;
                    }
                }
            }
        }
        in.close();
        out.close();
    }
}


