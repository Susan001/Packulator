package data;

/**
 * Created by khaze on 18.09.2017.
 */
public class Package {
    private int height;
    private int weight;
    private int depth;
    private int length;
    private int price;

    public Package( int pLength, int pDepth, int pHeight, int pWeight){
        height=pHeight;
        weight=pWeight;
        depth=pDepth;
        length=pLength;
    }
    public void setPrice(int pPrice){
        price=pPrice;
    }
    public int getPrice(){
        return price;
    }
    public int getHeight(){
        return height;
    }
    public int getDepth(){
        return depth;
    }
    public int getLength(){
        return length;
    }
    public int getWeight(){
        return weight;
    }
}
