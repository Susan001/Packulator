package ui;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import data.Package;

import java.util.Arrays;
import java.util.Collections;

public class Controller {
    public Package priceDhl[];
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
    public void handleResetButtonAction(ActionEvent e){
        inputLength.setText("");
        inputHeight.setText("");
        inputDepth.setText("");
        inputWeight.setText("");
        outputPrice.setText("");
        //Platform.exit();
    }

    public void handleCalculateButtonAction(ActionEvent e){
        loadDhlPrices();
        packageValues = new int[4];
        try {
            int packageLength = Integer.parseUnsignedInt(inputLength.getText());
            int packageHeight = Integer.parseUnsignedInt(inputHeight.getText());
            int packageDepth = Integer.parseUnsignedInt(inputDepth.getText());
            int packageWeight = Integer.parseUnsignedInt(inputWeight.getText());

            packageValues[0]=packageLength;
            packageValues[1]=packageDepth;
            packageValues[2]=packageHeight;
            packageValues[3]=0;
            Arrays.sort(packageValues); // after sorting the biggest values is stored in [3] lenght, [2] depth [1] height,[0} weight
            packageValues[0]=packageWeight;
            Package p0 = new Package(packageValues[3], packageValues[2], packageValues[1], packageValues[0]);

            for(int i=0; i<casesDhl; i++){
                if((p0.getLength()<=priceDhl[i].getLength()) && (p0.getDepth()<=priceDhl[i].getDepth()) && (p0.getHeight()<=priceDhl[i].getHeight()) && (p0.getWeight()<=priceDhl[i].getWeight())){
                    p0.setPrice(priceDhl[i].getPrice());
                    break;
                }
            }
            if(!(p0.getPrice()==0)) {
                outputPrice.setText(" " + p0.getPrice());
            }
            else{
                outputPrice.setText("Your Package is to large/heavy!");
            }

        }catch(Exception ex){
            inputLength.setText("");
            inputHeight.setText("");
            inputDepth.setText("");
            inputWeight.setText("");
            outputPrice.setText("No valid data");

        }
    }

    public void loadDhlPrices(){
        casesDhl=5;
        priceDhl = new Package[casesDhl];
        //the current package types are stored with the border values
        Package p1 = new Package(300,300,150,1000);
        p1.setPrice(389);
        Package p2 = new Package(600,300,150,2000);
        p2.setPrice(439);
        Package p3 = new Package(1200,600,600,5000);
        p3.setPrice(599);
        Package p4 = new Package(1200,600,600,10000);
        p4.setPrice(849);
        Package p5 = new Package(1200,600,600,31500);
        p5.setPrice(1649);

        priceDhl[0]=p1;
        priceDhl[1]=p2;
        priceDhl[2]=p3;
        priceDhl[3]=p4;
        priceDhl[4]=p5;


    }


}
