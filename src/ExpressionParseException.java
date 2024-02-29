public class ExpressionParseException extends Exception {
    public ExpressionParseException(String message) {
        super("Expression error: " + message);
    }
}