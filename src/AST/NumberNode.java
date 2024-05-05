package AST;

import java.math.BigDecimal;
import java.util.Map;

public class NumberNode implements ASTNode{
    public BigDecimal value;

    public NumberNode(BigDecimal value) {
        this.value = value;
    }
}
