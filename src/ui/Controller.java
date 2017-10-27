package ui;
import control.ShippingCostCalculatable;
import control.ShippingCostCalculatable;
import javafx.application.Platform;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import data.Packet;

import java.net.URL;
import java.util.*;

/**
 * Main Controller class
 */

public class Controller implements Initializable, ShippingCostCalculatable {
    public Packet priceDhl[];
    public int PacketValues[];
    public Packet myPacket;
    public int casesDhl;
    public List<Packet> packages;
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
        resetPacket();
        //Platform.exit();
    }
    public void createMyPacket(){
        myPacket=new Packet(0,0,0,0);
    }
    public void resetPacket(){
        myPacket.setLength(0);
        myPacket.setHeight(0);
        myPacket.setDepth(0);
        myPacket.setWeight(0);
    }
    @FXML
    public void handlePlusButtonAction(ActionEvent e){

        loadDhlPrices();
        PacketValues = new int[4];
        try{
            Packet helpPacket;
            helpPacket = createPacket();
            packages.add(helpPacket);
            loadMyPackage(packages);
            outputLength.setText(""+myPacket.getLength());
            outputHeight.setText(""+myPacket.getHeight());
            outputDepth.setText(""+myPacket.getDepth());


        }
        catch(Exception ex){
            inputLength.setText("");
            inputHeight.setText("");
            inputDepth.setText("");
            inputWeight.setText("");
            outputPrice.setText("No valid data3");

        }


    }

    public Packet createPacket(){
        try {
            Packet helpPacket = new Packet(0, 0, 0, 0);
            int PacketLength = Integer.parseUnsignedInt(inputLength.getText());
            int PacketHeight = Integer.parseUnsignedInt(inputHeight.getText());
            int PacketDepth = Integer.parseUnsignedInt(inputDepth.getText());
            int PacketWeight = Integer.parseUnsignedInt(inputWeight.getText());

            PacketValues[0] = PacketLength;
            PacketValues[1] = PacketDepth;
            PacketValues[2] = PacketHeight;
            PacketValues[3] = 0;
            Arrays.sort(PacketValues); // after sorting the biggest values is stored in [3] lenght, [2] depth [1] height,[0} weight
            PacketValues[0] = PacketWeight;
            helpPacket.setLength(PacketValues[3]);
            helpPacket.setDepth(PacketValues[2]);
            helpPacket.setHeight(PacketValues[1]);
            helpPacket.setWeight(PacketValues[0]);

            return helpPacket;
        }
        catch(Exception ex){
            inputLength.setText("");
            inputHeight.setText("");
            inputDepth.setText("");
            inputWeight.setText("");
            outputPrice.setText("No valid data1");
            return null;
        }
    }

    public void handleCalculateButtonAction(ActionEvent e){
        loadDhlPrices();
        PacketValues = new int[4];
        try {
            if(packages.size() ==0) {
                Packet helpPacket;
                helpPacket = createPacket();
                if(helpPacket !=null){
                    packages.add(helpPacket);
                }
            }
            double result;
            result = calcShippingCosts(packages);
            if(result==Double.NaN)
            {
                outputPrice.setText("To heavy!");
            }
            else{
                outputPrice.setText(""+result);
            }



        }catch(Exception ex){
            inputLength.setText("");
            inputHeight.setText("");
            inputDepth.setText("");
            inputWeight.setText("");
            outputPrice.setText("No valid data2");

        }
    }

    public void loadDhlPrices(){
        casesDhl=5;
        priceDhl = new Packet[casesDhl];
        //the current Packet types are stored with the border values
        Packet p1 = new Packet(300,300,150,1000);
        p1.setPrice(389);
        Packet p2 = new Packet(600,300,150,2000);
        p2.setPrice(439);
        Packet p3 = new Packet(1200,600,600,5000);
        p3.setPrice(599);
        Packet p4 = new Packet(1200,600,600,10000);
        p4.setPrice(849);
        Packet p5 = new Packet(1200,600,600,31500);
        p5.setPrice(1649);

        priceDhl[0]=p1;
        priceDhl[1]=p2;
        priceDhl[2]=p3;
        priceDhl[3]=p4;
        priceDhl[4]=p5;


    }

    @Override
    public double calcShippingCosts(List<Packet> packets){
        loadMyPackage(packets);



        for (int i = 0; i < casesDhl; i++) {
            if ((myPacket.getLength() <= priceDhl[i].getLength()) && (myPacket.getDepth() <= priceDhl[i].getDepth()) && (myPacket.getHeight() <= priceDhl[i].getHeight()) && (myPacket.getWeight() <= priceDhl[i].getWeight())) {
                myPacket.setPrice(priceDhl[i].getPrice());
                break;
            }
        }
        if (!(myPacket.getPrice() == 0)) {
            return myPacket.getPrice();
        } else {
            return Double.NaN;

        }


    }

    public void loadMyPackage(List<Packet> packets){

        for(int a=0; a<packets.size(); a++){
            if(myPacket.getDepth()< ( packets.get(a)).getDepth() ){
                myPacket.setDepth(packets.get(a).getDepth());
            }
            if(myPacket.getLength() < packets.get(a).getLength()){
                myPacket.setLength(packets.get(a).getLength());
            }
            myPacket.setWeight(myPacket.getWeight()+packets.get(a).getWeight());
            myPacket.setHeight(myPacket.getHeight()+packets.get(a).getHeight());
        }

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        createMyPacket();
        packages= new ArrayList<>();
    }
}
