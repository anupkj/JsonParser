import AST.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Parser {
    static int tokenPointer = 0;
    static ASTNode parse(List<Token> tokenList){
        if(tokenList.isEmpty()){
            throw new RuntimeException("Nothing to Parse!. TokenList is empty.");
        }
        Token currentToken = tokenList.get(tokenPointer);
         if(currentToken.type == TokenType.OpeningBrace){
            return parseObject(tokenList);
        }
        else if(currentToken.type == TokenType.BracketOpen){
            return parseArray(tokenList);
        }
        else if(currentToken.type == TokenType.String){
            return new StringNode(currentToken.value);
        }
        else if(currentToken.type == TokenType.Number){
            return new NumberNode(BigDecimal.valueOf(Long.parseLong(currentToken.value)));
        }
        else if(currentToken.type == TokenType.False ||
                currentToken.type == TokenType.True){
            return new BooleanNode(currentToken.type == TokenType.True );
        }
        else if(currentToken.type == TokenType.Null){
            return new NullNode();
        }
        else {
            throw new RuntimeException("Token Type Unexpected : " + currentToken.type);
        }
    }

    static ASTNode parseObject(List<Token> tokenList){

        Map<String,ASTNode>  keyValueMap = new HashMap<>();
        Token currentToken = advance(tokenList);
        while (currentToken.type != TokenType.ClosingBrace){
            String key = currentToken.value;
            if(currentToken.type == TokenType.String){
                currentToken = advance(tokenList);
                if(currentToken.type != TokenType.Colon){
                    throw new RuntimeException("Expected Token type : Colon, Found : " + currentToken.type);
                }
                advance(tokenList);
                ASTNode value = parse(tokenList);
                keyValueMap.put(key,value);
            }
            else{
                throw new RuntimeException("Expected Token type to be string, but found :" + currentToken.type);
            }
            currentToken = advance(tokenList);
            if(currentToken.type == TokenType.Comma) {
                currentToken = advance(tokenList);
            }
        }
        return new ObjectNode(keyValueMap);
    }

    static ASTNode parseArray(List<Token> tokenList){
        Token currentToken = advance(tokenList);
        List<ASTNode> astNodeList = new ArrayList<>();
        while(currentToken.type != TokenType.BracketClose){
            ASTNode astNode = parse(tokenList);
            astNodeList.add(astNode);

            currentToken = advance(tokenList);
            if(currentToken.type == TokenType.Comma) {
                currentToken = advance(tokenList);
            }
        }
        return new ArrayNode(astNodeList);
    }


    static Token advance(List<Token> tokenList){
        return tokenList.get(++tokenPointer);
    }
}
