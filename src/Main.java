import AST.ASTNode;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        String jsonString = "{" +
                "\"id\":\"647ceaf3657eade56f8224eb\"," +
                "\"index\":0," +
                "\"anArray\":[\"5\"]," +
                "\"boolean\":true," +
                "\"nullValue\":null}";
        List<Token> tokenList = Tokenizer.Tokenize(jsonString);
        ASTNode parsedAST = Parser.parse(tokenList);
        System.out.println("Parser  Process Completed");


    }
}