public class ExpressionParser {
    private final String[] _operators;
    private final int[] _operandRange;
    private final int[] _operandCountRange;
    private final String _delimiter;

    public ExpressionParser(String[] operators, int[] operandRange, int[] operandCountRange, String delimiter) {
        _operators = operators;
        _operandRange = operandRange;
        _operandCountRange = operandCountRange;
        _delimiter = delimiter;
    }

    public String[] parseExpression(String expression) throws ExpressionParseException {
        String[] tokens = expression.split(_delimiter);
        validateExpression(tokens);
        return tokens;
    }

    private void validateExpression(String[] tokens) throws ExpressionParseException {
        if (!isValidOperandCount(tokens)) {
            throw new ExpressionParseException("Invalid operands count");
        }

        if (!isValidOperators(tokens)) {
            throw new ExpressionParseException("Invalid operator");
        }

        if (!isValidOperands(tokens)) {
            throw new ExpressionParseException("Invalid token");
        }
    }

    private boolean isValidOperandCount(String[] tokens) {
        int operandCount = tokens.length / 2 + 1;
        return operandCount >= _operandCountRange[0] && operandCount <= _operandCountRange[1];
    }

    private boolean isValidOperators(String[] tokens) {
        boolean valid = false;
        for (int i = 1; i < tokens.length; i += 2) {
            String operator = tokens[i];
            for (String validOperator : _operators) {
                if (validOperator.equals(operator)) {
                    valid = true;
                    break;
                }
            }
            if (!valid){
                return false;
            }
        }
        return true;
    }

    private boolean isValidOperands(String[] tokens) throws ExpressionParseException {
        for (int i = 0; i < tokens.length; i += 2) {
            int operand = parseOperand(tokens[i]);
            if (Math.abs(operand) < _operandRange[0] || Math.abs(operand) > _operandRange[1] || isNumeric(tokens[i])){
                return false;
            }
        }
        return true;
    }

    private static boolean isNumeric(String token) {
        return token.matches("-?\\\\b(?:[0-9]|10)\\\\b");
    }

    private int parseOperand(String operand) throws ExpressionParseException {
        try {
            return Integer.parseInt(operand);
        } catch (NumberFormatException e) {
            throw new ExpressionParseException("Invalid operand: " + operand);
        }
    }
}