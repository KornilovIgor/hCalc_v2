import java.util.Objects;
import java.util.Stack;

public class Calculate {

    public static int calculate(String[] tokens) {
        Stack<Integer> operands = new Stack<>();
        Stack<String> operators = new Stack<>();

        operands.push(Integer.parseInt(tokens[0]));
        for (int i = 1; i < tokens.length - 1; i+=2){
            if (Objects.equals(tokens[i], "+") || Objects.equals(tokens[i], "-")){
                operators.push(tokens[i]);
                operands.push(Integer.parseInt(tokens[i + 1]));
            }
            else
            {
                operands.push(operation(operands.pop(), Integer.parseInt(tokens[i + 1]), tokens[i]));
            }
        }

        int tempSum = 0;
        while (!operators.isEmpty()) {
            tempSum = operation(tempSum, operands.pop(), operators.pop());
        }
        tempSum = operation(tempSum, operands.pop(), "+");

        return tempSum;
    }

    private static int operation(int operand1, int operand2, String operator) {
        if (Objects.equals(operator, "/") && operand2 == 0){
            throw new IllegalArgumentException("Argument 'divisor' is 0");
        }
        return switch (operator) {
            case "+" -> operand1 + operand2;
            case "-" -> operand1 - operand2;
            case "*" -> operand1 * operand2;
            case "/" -> operand1 / operand2;
            default -> throw new IllegalArgumentException("Invalid operator: " + operator);
        };
    }
}