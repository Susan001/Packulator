package ui;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Controller {
    @FXML
    public TextField inputHeight;
    public TextField inputDepth;
    public TextField inputLength;
    public TextField inputWeight;
    public TextField outputPrice;
    public Button closeButton;
    public Button calculateButton;
    @FXML
    public void handleCloseButtonAction(ActionEvent e){
        Platform.exit();
    }

    public void handleCalculateButtonAction(ActionEvent e){
        int packageLength = Integer.parseInt(inputLength.getText());
        int packageHeight = Integer.parseInt(inputHeight.getText());
        int packageDepth = Integer.parseInt(inputDepth.getText());
        int packageWeight = Integer.parseInt(inputWeight.getText());
        outputPrice.setText(packageLength+" "+packageHeight+" "+packageDepth+" "+ packageWeight);

    }
}
