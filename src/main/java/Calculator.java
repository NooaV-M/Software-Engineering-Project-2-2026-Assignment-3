import java.util.Scanner;

public class Calculator {
    private double total = 0;
    private double currentValue = -1;
    private Scanner scanner;

    public Calculator() {
        this.scanner = new Scanner(System.in);
    }

    public record PriceResult(boolean isValid, boolean isZero) {}

    public record QuantityResult(boolean isValid, boolean isZero) {}

    public PriceResult getCurrentItemPrice() {
        String input = scanner.nextLine();
        try {
            double enteredValue = Double.parseDouble(input);
            if (enteredValue > 0) {
                currentValue = enteredValue;
                return new PriceResult(true, false);
            } else if (enteredValue == 0) {
                return new PriceResult(true, true);
            } else {
                return new PriceResult(false, false);
            }
        } catch (NumberFormatException e) {
            return new PriceResult(false, false);
        }
    }

    public QuantityResult getCurrentItemQuantity() {
        String input = scanner.nextLine();
        try {
            int enteredValue = Integer.parseInt(input);
            if (enteredValue > 0) {
                total += currentValue * enteredValue;
                currentValue = 0;
                return new QuantityResult(true, false);
            } else if (enteredValue == 0) {
                return new QuantityResult(true, true);
            } else {
                return new QuantityResult(false, false);
            }
        } catch (NumberFormatException e) {
            return new QuantityResult(false, false);
        }
    }

    public double getTotal() {
        return total;
    }
}
