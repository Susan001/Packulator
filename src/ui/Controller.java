package ui;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class Controller {
    @FXML
    public Button closeButton;
    @FXML
    public void handleCloseButtonAction(ActionEvent e){
        Platform.exit();
    }
}
