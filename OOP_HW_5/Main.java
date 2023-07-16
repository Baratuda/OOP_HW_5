import java.util.Scanner;

class CalculatorModel {
    private int result;
    public int calculate(int number1, int number2, String parameter) {
            switch (parameter) {
                case "+":
                    result = number1+number2;
                    break;
                case "-":
                    result = number1-number2;
                    break;
                case "*":
                    result = number1*number2;
                    break;
                case "/":
                    result = number1/number2;
                    break; 
                default:
                  throw new NullPointerException();             
            }

        return result;
    }
}

class CalculatorView {
    public void displayResult(int result) {
    System.out.println("Результат: " + result);
    }

    public String getUserInput(String message) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(message);
        return scanner.nextLine();
    }
}

// Презентер (Presenter)
class CalculatorPresenter {
    private CalculatorModel model;
    private CalculatorView view;
    private int number1;
    private String parameter;
    private int number2;
    public CalculatorPresenter(CalculatorModel model, CalculatorView view) {
        this.model = model;
        this.view = view;
    }

    public void buttonClicked() {
        try{
           this.number1 = Integer.valueOf(view.getUserInput("Plese input first number: "));
           this.parameter = view.getUserInput("Plese input parameter: ");
           this.number2 = Integer.valueOf(view.getUserInput("Plese input second number: "));
           try{
              int result = model.calculate(number1, number2,parameter);
              view.displayResult(result); 
           }catch(NullPointerException ex){
            System.out.println("Please input valid parameter. Parameter must have String data type and eqals one of these symbols [+,-,*,/]");
            return;
           }
        }catch(NumberFormatException ex){
            System.out.println("Please input valid numbers.");
            return;
        }
        
        
    }
}

// Главный класс приложения
public class Main {
    public static void main(String[] args) {
    // Создание экземпляров модели, представления и презентера
    CalculatorModel model = new CalculatorModel();
    CalculatorView view = new CalculatorView();
    CalculatorPresenter presenter = new CalculatorPresenter(model, view);
        presenter.buttonClicked();
    }
}