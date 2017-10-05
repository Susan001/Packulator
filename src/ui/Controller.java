package ui;
import javafx.application.Platform;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import data.Package;

import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public Package priceDhl[];
    public int packageValues[];
    public Package myPackage;
    public int casesDhl;
    @FXML
    public TextField inputHeight;
    public TextField inputDepth;
    public TextField inputLength;
    public TextField inputWeight;
    public TextField outputPrice;
    public TextField outputLength;
    public TextField outputDepth;
    public TextField outputHeight;
    public Button closeButton;
    public Button calculateButton;
    @FXML
    public void handleResetButtonAction(ActionEvent e){
        inputLength.setText("");
        inputHeight.setText("");
        inputDepth.setText("");
        inputWeight.setText("");
        outputPrice.setText("");
        outputDepth.setText("");
        outputHeight.setText("");
        outputLength.setText("");
        resetPackage();
        //Platform.exit();
    }
    public void createPackage(){
        myPackage=new Package(0,0,0,0);
    }
    public void resetPackage(){
        myPackage.setLength(0);
        myPackage.setHeight(0);
        myPackage.setDepth(0);
        myPackage.setWeight(0);
    }
    @FXML
    public void handlePlusButtonAction(ActionEvent e){

        loadDhlPrices();
        packageValues = new int[4];
        try{
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
            myPackage.setLength(myPackage.getLength()+packageValues[3]);
            myPackage.setDepth(myPackage.getDepth()+packageValues[2]);
            myPackage.setHeight(myPackage.getHeight()+packageValues[1]);
            myPackage.setWeight(myPackage.getWeight()+packageValues[0]);
            outputDepth.setText(""+myPackage.getDepth());
            outputHeight.setText(""+myPackage.getHeight());
            outputLength.setText(""+myPackage.getLength());

        }
        catch(Exception ex){
            inputLength.setText("");
            inputHeight.setText("");
            inputDepth.setText("");
            inputWeight.setText("");
            outputPrice.setText("No valid data");

        }


    }
    public void handleCalculateButtonAction(ActionEvent e){
        loadDhlPrices();
        packageValues = new int[4];
        try {
            if(myPackage.getLength()==0) {
                int packageLength = Integer.parseUnsignedInt(inputLength.getText());
                int packageHeight = Integer.parseUnsignedInt(inputHeight.getText());
                int packageDepth = Integer.parseUnsignedInt(inputDepth.getText());
                int packageWeight = Integer.parseUnsignedInt(inputWeight.getText());

                packageValues[0] = packageLength;
                packageValues[1] = packageDepth;
                packageValues[2] = packageHeight;
                packageValues[3] = 0;
                Arrays.sort(packageValues); // after sorting the biggest values is stored in [3] lenght, [2] depth [1] height,[0} weight
                packageValues[0] = packageWeight;

                myPackage.setLength(myPackage.getLength() + packageValues[3]);
                myPackage.setDepth(myPackage.getDepth() + packageValues[2]);
                myPackage.setHeight(myPackage.getHeight() + packageValues[1]);
                myPackage.setWeight(myPackage.getWeight() + packageValues[0]);
            }

                for (int i = 0; i < casesDhl; i++) {
                    if ((myPackage.getLength() <= priceDhl[i].getLength()) && (myPackage.getDepth() <= priceDhl[i].getDepth()) && (myPackage.getHeight() <= priceDhl[i].getHeight()) && (myPackage.getWeight() <= priceDhl[i].getWeight())) {
                        myPackage.setPrice(priceDhl[i].getPrice());
                        break;
                    }
                }
                if (!(myPackage.getPrice() == 0)) {
                    outputPrice.setText(" " + myPackage.getPrice());
                } else {
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


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        createPackage();
    }
}
