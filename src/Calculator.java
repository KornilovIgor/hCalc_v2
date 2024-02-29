public class Calculator {

    private final String[] _operators = {"+", "-", "/", "*"};
    private final int[] _operandRange = {1, 10};
    private final int[] _operandCountRange = {2, 3};

    public Calculator(){}

    public int calculate (String expr) throws ExpressionParseException {
        String _delimiter = " ";
        var pars = new ExpressionParser (_operators, _operandRange, _operandCountRange, _delimiter);
        String[] _tokens = (pars.parseExpression(expr));
        return Calculate.calculate(_tokens);
    }
}
