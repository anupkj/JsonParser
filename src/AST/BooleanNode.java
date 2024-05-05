package AST;

import java.util.Map;

public class BooleanNode implements ASTNode{
    public boolean value;

    public BooleanNode(boolean value) {
        this.value = value;
    }
}
