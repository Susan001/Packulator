package testing;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ui.Controller;
import data.Packet;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * Created by khaze on 23.10.2017.
 */
public class ControllerTest {

    @BeforeAll
    public void beforeTest(){

    }

    @AfterAll
    public void afterTest(){

    }

    @Test
    public void main(){
        Packet myPacket = new Packet(0,0,0,0);
        Controller myController = new Controller();
        myController.createPacket();
        myController.resetPacket();
        assertEquals(myPacket, myController.myPacket);
    }
}
