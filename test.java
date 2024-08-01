package labs;
import java.lang.reflect.Array;
import java.util.*;

public class test {
    //the test class
    public static void main(String[] args){
        //the required objects.
        int numberOFCards =4;
        card[] cards = new card[numberOFCards];
        cards[0] = new SilverCard(2001,0.15);

        cards[0].purchase(500);
        cards[0].purchase(100);

        cards[1] = new GoldenCard(2002,50);
        cards[1].purchase(150);

        cards[2] = new GoldenCard(2003,70);
        cards[2].purchase(100);
        cards[2].purchase(300);


        cards[3] = new SilverCard(2004,0.20);
        cards[3].purchase(200);
        cards[3].purchase(100);
        Arrays.sort(cards);
        System.out.println("all cards");
        System.out.println(Arrays.asList(cards));
        //sorting the array based on total amount.
        Arrays.sort(cards, Comparator.comparing(card::totalAmount));
        System.out.println("Cards sorted by total amount+fee");
        System.out.println(Arrays.asList(cards));







        //finding the total amount+fees of the golden card.
        double sum=0;
        for(card i:cards){
            if(i instanceof GoldenCard){
                sum+=((GoldenCard) i).totalAmount()+ ((GoldenCard) i).fees();


            }


        }

        System.out.println(" golden cards total : "+sum);

    }
}
abstract class card implements Comparable<card> {
    //card in my superclass.
    private ArrayList<Double> transaction = new ArrayList<Double>();
    private int num;
    public card(int num){
        this.num=num;
    }
    //the getters and setters of the private data fields.
    public int getNum(){
        return this.num;
    }
    public int setNum(int num){
        return this.num=num;
    }
    public ArrayList<Double> getTransaction(){
        return this.transaction;
    }
    public ArrayList<Double> setAmount(ArrayList<Double> Amount){
         return this.transaction =Amount;

    }
    public  ArrayList<Double> purchase(double amount){ //this class is to add the entered .
        transaction.add(amount);
        return transaction;

    }




    public abstract double fees(); //this method tho change the fees process as required.
    public boolean equals(card obj){ //to find the identical numbers card.
        if (this.num== obj.getNum()){
            return true;
        }
        return  false;
    }
    public int compareTo (card obj){ //to compare based on total amount including fees.
        Double.compare(totalAmount(), obj.totalAmount());
        Double.compare(fees(), obj.fees());



        return 0;
    }
    public double totalAmount(){ //finding the total amount.
        double sum=0;
        for(double i:transaction ){
            sum+=((double) i);
        }
        return sum;
    }
    public String toString(){ //tostring method to display the output.
        return "card (num: "+num+" ,total : "+(fees()+totalAmount())+")";
    }

}
class SilverCard extends card{
    //SilverCard is the subclass.
    private double transactionFee;
    public SilverCard(int num,double transactionFee){
        super(num);
        this.transactionFee=transactionFee;
    }
    public double fees(){ //fees of the silver card.
        return transactionFee*totalAmount();
    }
    @Override
    public  ArrayList<Double> purchase(double amount){ //overriding the purchase to add the fees, I removed it because I already add it in the tostring method.
        super.purchase(amount);
        getTransaction().remove(fees());
        return getTransaction();

    }

}
class GoldenCard extends card{
    //the second subclass is GoldenCard.
    private int monthlyFee;
    public GoldenCard(int num,int monthlyFee){
        super(num);
        this.monthlyFee=monthlyFee;
    }
    public double fees(){ //implementing the fees method .
        return this.monthlyFee= monthlyFee;
    }


}
