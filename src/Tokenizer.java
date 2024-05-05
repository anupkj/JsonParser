import java.util.ArrayList;
import java.util.List;

public class Tokenizer {
    static List<Token> Tokenize(String token){
       List<Token> tokenList = new ArrayList<>();
       int charPointer = 0;
       while(charPointer < token.length()){
           char currChar = token.charAt(charPointer++);
           if(currChar == ' ' || currChar == '\n') continue;
           if(currChar == '{'){
               tokenList.add(new Token(TokenType.OpeningBrace,String.valueOf(currChar)));
           }
           else if(currChar == '}'){
               tokenList.add(new Token(TokenType.ClosingBrace,String.valueOf(currChar)));
           }
           else if(currChar == '['){
               tokenList.add(new Token(TokenType.BracketOpen,String.valueOf(currChar)));
           }
           else if(currChar == ']'){
               tokenList.add(new Token(TokenType.BracketClose,String.valueOf(currChar)));
           }
           else if(currChar == ','){
               tokenList.add(new Token(TokenType.Comma,String.valueOf(currChar)));
           }
           else if(currChar == ':'){
               tokenList.add(new Token(TokenType.Colon,String.valueOf(currChar)));
           }
           else if(currChar == '"'){
               int endingPointer = charPointer;
               StringBuilder sb = new StringBuilder();
               while(endingPointer < token.length() && token.charAt(endingPointer) != '"') {
                   sb.append(token.charAt(endingPointer++));
               }
               tokenList.add(new Token(TokenType.String,sb.toString()));
               endingPointer++;
               charPointer = endingPointer;
           }
           else{
                StringBuilder sb = new StringBuilder();
                while (isNumberOrValidCharacter(currChar)){
                    sb.append(currChar);
                    currChar = token.charAt(charPointer++);
                }
                charPointer--;
               tokenList.add(new Token(getTokenType(sb.toString()),sb.toString()));
           }
       }
       return tokenList;
    }

    static TokenType getTokenType(String tokenString){
        if(tokenString.equals("True") || tokenString.equals("true")) return TokenType.True;
        else if(tokenString.equals("False") || tokenString.equals("false")) return TokenType.False;
        else if(tokenString.equals("Null") || tokenString.equals("null")) return TokenType.Null;
        else if(isValidNumber(tokenString)) return TokenType.Number;
        else throw new RuntimeException("Invalid Token String :" + tokenString);

    }

    static Boolean isValidNumber(String token){
        for(int i = 0; i < token.length(); i++){
            if(token.charAt(i) < '0' || token.charAt(i) > '9') return  false;
        }
        return true;
    }
    static Boolean isNumberOrValidCharacter(char currentChar){
        Boolean result = (currentChar >= '0' && currentChar < '9') ||
                (currentChar >= 'a' && currentChar < 'z') ||
                (currentChar >= 'A' && currentChar < 'Z');
        return result;
    }
}
