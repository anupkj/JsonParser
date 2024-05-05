package AST;

import java.util.Map;

public class StringNode implements ASTNode{
    public String value;

    public StringNode(String value) {
        this.value = value;
    }
}
