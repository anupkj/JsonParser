package AST;

import java.util.Map;

public class ObjectNode implements ASTNode{
    public Map<String, ASTNode> value;
    public ObjectNode() {
    }

    public ObjectNode(Map<String, ASTNode> value) {
        this.value = value;
    }
}
