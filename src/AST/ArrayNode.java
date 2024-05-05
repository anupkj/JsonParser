package AST;

import java.util.List;

public class ArrayNode implements ASTNode{
    public List<ASTNode> value;

    public ArrayNode(List<ASTNode> value) {
        this.value = value;
    }
}
