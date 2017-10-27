package data;

/**
 * Created by khaze on 18.09.2017.
 */
public class Packet {
    private int height;
    private int weight;
    private int depth;
    private int length;
    private int price;

    public Packet( int pLength, int pDepth, int pHeight, int pWeight){
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
    /**Return length
     *
     * @retun Packet length in mm
     */
    public int getDepth(){
        return depth;
    }
    /**Return length
     *
     * @retun Packet length in mm
     */
    public int getLength(){
        return length;
    }
    /**Return weight
     *
     * @retun Packet weight in gramm
     */
    public int getWeight(){
        return weight;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    /**Set length
     *
     * @param length
     */

    public void setLength(int length) {
        this.length = length;
    }
}
