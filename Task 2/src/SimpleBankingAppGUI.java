import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class SimpleBankingAppGUI extends Application {
    private double balance;
    private Label resultLabel;
    private Label lblDisplayBalance;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {
        balance = 0.0;

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20));
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(30);
        gridPane.setHgap(20);

        Label lblBalance = new Label("Balance: ");
        lblBalance.setStyle("-fx-text-fill: #000000; -fx-font-weight: bold;");
        gridPane.add(lblBalance, 0, 0);

        lblDisplayBalance = new Label();
        lblDisplayBalance.setStyle("-fx-font-size: 20;");
        gridPane.add(lblDisplayBalance, 1, 0);

        Label lblAmount = new Label("Amount: ");
        lblAmount.setStyle("-fx-text-fill: #000000; -fx-font-weight: bold;");
        gridPane.add(lblAmount, 0, 1);

        resultLabel = new Label();
        resultLabel.setStyle("-fx-font-size: 18; -fx-text-fill: red;");
        gridPane.add(resultLabel, 0, 4, 2, 1);

        TextField txtAmount = new TextField();
        gridPane.add(txtAmount, 1, 1);

        Button btnDeposit = new Button("Deposit");
        btnDeposit.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 20;");
        addShadowOnHover(btnDeposit);
        gridPane.add(btnDeposit, 0, 2);
        btnDeposit.setOnAction(event -> {
            handleDeposit(txtAmount.getText());
            txtAmount.clear();
        });

        Button btnWithdraw = new Button("Withdraw");
        btnWithdraw.setStyle("-fx-background-color: #f44336; -fx-text-fill: white; -fx-font-size: 20;");
        addShadowOnHover(btnWithdraw);
        gridPane.add(btnWithdraw, 1, 2);
        btnWithdraw.setOnAction(event -> {
            handleWithdraw(txtAmount.getText());
            txtAmount.clear();
        });

        Button btnCheckBalance = new Button("Check Balance");
        btnCheckBalance.setStyle("-fx-background-color: #008CBA; -fx-text-fill: white; -fx-font-size: 20;");
        addShadowOnHover(btnCheckBalance);
        gridPane.add(btnCheckBalance, 1, 3);
        btnCheckBalance.setOnAction(event -> {
            updateBalanceLabel();
        });

        Scene scene = new Scene(gridPane, 800, 600);
        primaryStage.setTitle("Simple Banking App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void handleDeposit(String amount) {
        try {
            double depositAmount = Double.parseDouble(amount);
            deposit(depositAmount);
            updateBalanceLabel();
        } catch (NumberFormatException e) {
            showErrorMessage("Invalid input. Please enter a valid number.");
        }
    }

    private void handleWithdraw(String amount) {
        try {
            double withdrawAmount = Double.parseDouble(amount);
            withdraw(withdrawAmount);
            updateBalanceLabel();
        } catch (NumberFormatException e) {
            showErrorMessage("Invalid input. Please enter a valid number.");
        }
    }

    private void updateBalanceLabel() {
        lblDisplayBalance.setText(Double.toString(balance));
    }

    private void showErrorMessage(String message) {
        resultLabel.setText(message);
        resultLabel.setStyle("-fx-text-fill: #d9534f; -fx-font-weight: bold;");
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            resultLabel.setText("");
        } else {
            showErrorMessage("Invalid input. Please enter a valid number.");
        }
    }

    public void withdraw(double amount) {
        if (amount > balance) {
            showErrorMessage("Insufficient funds. Please enter a valid amount.");
        } else if (amount > 0 && amount <= balance) {
            balance -= amount;
            resultLabel.setText("");
        } else {
            showErrorMessage("Invalid input. Please enter a valid number.");
        }
    }

    private void addShadowOnHover(Button button) {
        String defaultStyle = button.getStyle(); 

        button.setOnMouseEntered(event -> {
            button.setStyle("-fx-background-color: #CCCCCC; -fx-text-fill: white; -fx-font-size: 20; -fx-effect: dropshadow( gaussian , rgba(0,0,0,0.7) , 10,0,0,1 );");
        });

        button.setOnMouseExited(event -> {
            button.setStyle(defaultStyle); 
        });
    }

}
