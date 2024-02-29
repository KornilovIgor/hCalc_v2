import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ExpressionParseException {

        Calculator calc = new Calculator();

        while (true){
            Scanner in = new Scanner(System.in);
            System.out.print("Input: ");
            String expr = in.nextLine();
            System.out.println ("Output: " + calc.calculate(expr));
            System.out.println("Input 1 - repeat, 2 - exit");
            int ch = Integer.parseInt(in.nextLine());
            switch (ch){
                case 1:
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    break;
                case 2:
                    return;
                default:
                    throw new IllegalStateException("Invalid input menu");
            }
        }
    }
}