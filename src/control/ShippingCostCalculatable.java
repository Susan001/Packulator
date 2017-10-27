package control;
import data.Packet;
import java.util.List;

/**
 * Created by khaze on 27.10.2017.
 */
public interface ShippingCostCalculatable {

    public double calcShippingCosts(List<Packet> packets);
}
