package ui;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.util.Arrays;
import java.util.Collections;

public class Controller {
    public int priceDhl[][];
    public int packageValues[];
    public int casesDhl;
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
        loadDhlPrices();
        int price=0;
        packageValues=new int[4];
        int packageLength = Integer.parseInt(inputLength.getText());
        int packageHeight = Integer.parseInt(inputHeight.getText());
        int packageDepth = Integer.parseInt(inputDepth.getText());
        int packageWeight = Integer.parseInt(inputWeight.getText());

        packageValues[0]=packageLength;
        packageValues[1]=packageDepth;
        packageValues[2]=packageHeight;
        packageValues[3]=0;
        Arrays.sort(packageValues); // after sorting the biggest values is stored in [3] lenght, [2] depth [1] height,[0} weight
        packageValues[0]=packageWeight;

        for(int i=0; i<casesDhl; i++){
            if((packageValues[3]<=priceDhl[i][0]) && (packageValues[2]<=priceDhl[i][1]) && (packageValues[1]<=priceDhl[i][2]) && (packageValues[0]<=priceDhl[i][3])){
                price=(priceDhl[i][4]);
                break;
            }
        }

        //System.out.println(packageValues[0]+ " " + packageValues[1] +" "+ packageValues[2]+ " "+packageValues[3]);
        outputPrice.setText(" "+price);

    }

    public void loadDhlPrices(){
        casesDhl=6;
        priceDhl= new int[casesDhl][5];
        priceDhl[0][0]=300; //leght
        priceDhl[0][1]=300; //depth
        priceDhl[0][2]=150; //height
        priceDhl[0][3]=1000; //weight
        priceDhl[0][4]=389; //price

        priceDhl[1][0]=600;
        priceDhl[1][1]=300;
        priceDhl[1][2]=150;
        priceDhl[1][3]=2000;
        priceDhl[1][4]=439;

        priceDhl[2][0]=1200;
        priceDhl[2][1]=600;
        priceDhl[2][2]=600;
        priceDhl[2][3]=5000;
        priceDhl[2][4]=599;

        priceDhl[3][0]=1200;
        priceDhl[3][1]=600;
        priceDhl[3][2]=600;
        priceDhl[3][3]=10000;
        priceDhl[3][4]=849;

        priceDhl[4][0]=1200;
        priceDhl[4][1]=600;
        priceDhl[4][2]=600;
        priceDhl[4][3]=31500;
        priceDhl[4][4]=1649;

    }
}
