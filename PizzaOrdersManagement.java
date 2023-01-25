//package Money;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class PizzaOrdersManagement {
    public static void main(String[] args) {
        Scanner in = new Scanner (System.in);
        int n = in.nextInt ();
        String dateId = "";
        if (n == 2 || n == 3) {
            dateId = in.next ();
        }
        int length = in.nextInt ();
        String[] allorders = new String[length];
        in.nextLine ();
        for (int i = 0; i < length; i++) {
            allorders[i] = in.nextLine ();
        }
        System.out.println ();
        if (n == 1) {
            System.out.println ("Total price:\n" + getTotalPrice (allorders));
        }

        else if (n == 2){
            ArrayList<String> idsearch = searchByID (dateId, allorders);
            for (String st : idsearch) {
                System.out.println (st);
            }
        }
        else if(n==3) {
            ArrayList<String> datesearch = searchByDate (dateId, allorders);
            for (String st : datesearch) {
                System.out.println (st);
            }
        }
        else if(n==4) {
            allorders = sortByID (allorders);
            for (String s : allorders) {
                System.out.println (s);
            }
        }
        else if(n==5) {
            allorders = sortByDateAndTime (allorders);
            for (String s : allorders) {
                System.out.println (s);
            }
        }
        else if(n==6) {
            String popularSize = mostPopularSize (allorders);
            System.out.println (popularSize);
        }
        else if(n==7) {
            String popularPizzaType = mostPopularPizzaType (allorders);
            System.out.println (popularPizzaType);
        }

    }
    //Task1
    public static int getTotalPrice(String[] allorders){
        int t=0;
        for (int i = 0; i <allorders.length ; i++) {
            t+=Integer.parseInt (allorders[i].split (" ")[3]);
        }
        return t;
    }
    //Task2
    public static ArrayList<String> searchByID(String id,String[] orders){
        ArrayList<String> s=new ArrayList<> ();
        String check="";
        s.add("Search by ID: "+id);
        for (String st:orders) {
            if(st.split (" ")[0].equals (id)){
                s.add (st);
                check="false";
            }
        }
        if(check.equals ("")) s.add ("No result");
        return s;
    }
    //Task3
    public static ArrayList<String> searchByDate(String date,String[] orders){
        ArrayList<String> s=new ArrayList<> ();
        String check="";
        s.add("Search by Date: "+date);
        for (String st:orders) {
            if(st.split (" ")[1].equals (date)){
                s.add (st);
                check="false";
            }
        }
        if(check.equals ("")) s.add ("No result");
        return s;
    }
    //Task4
    public static String[] sortByID(String[] orders){
        int[] id=new int[orders.length];
        for (int i = 0; i < orders.length; i++) {
            id[i]=Integer.parseInt (orders[i].split (" ")[0]);
        }
        String[] sorted_order=new String[orders.length];
        int index=0;
        Arrays.sort (id);
        for (int n :id ) {
            for (String s:orders) {
                if(Integer.parseInt (s.split (" ")[0])== (n)){
                    sorted_order[index]=s;
                    index++;
                    break;
                }
            }
        }
        return sorted_order;
    }
    //Task5
    public static String[] sortByDateAndTime(String[] orders){
        ArrayList<String> check=new ArrayList<> ();
        String[] sorted_order=new String[orders.length];
        int index=0;
        String[] dates=new String[orders.length];
        for (int i = 0; i <orders.length ; i++) {
            dates[i]="";
            dates[i]+=orders[i].substring (11, 15);
            dates[i]+=orders[i].substring (7, 11);
            dates[i]+=orders[i].substring (5, 7);
            dates[i]+=orders[i].substring (15, 21);
        }
        Arrays.sort (dates);
        for (String d :dates) {
            for ( String s: orders) {
                String date="";
                date+=s.substring (11, 15);
                date+=s.substring (7, 11);
                date+=s.substring (5, 7);
                date+=s.substring (15, 21);
                if(d.equals (date)&& !check.contains (s)){
                    sorted_order[index]=s;
                    index++;
                    check.add (s);
                    break;
                }
            }
        }
        return sorted_order;
    }
    //Task6
    public static String mostPopularSize(String[] orders){
        int[] popularSize=new int[orders.length];
        for (int i = 0; i < orders.length; i++) {
            popularSize[i]=Integer.parseInt (orders[i].split (" ")[5]);
        }
        ArrayList<String> check=new ArrayList<> ();
        String populars="";
        int mostPopular=0;
        for (int i = 0; i < popularSize.length; i++) {
            if(check.contains (popularSize[i]+"")) continue;
            check.add (popularSize[i]+"");
            int count=0;
            for (int j = 0; j < popularSize.length; j++) {
                if(i==j)continue;
                if(popularSize[i]==popularSize[j])count++;

            }
            if(mostPopular<count){
                mostPopular=count;
                populars="";
                populars+=popularSize[i]+"\n";
            }
            else if(mostPopular==count){
                populars+=popularSize[i]+"\n";
            }
        }
        populars="Most popular size:\n"+populars;
        return populars;
    }
    //Task7
    public static String mostPopularPizzaType(String[] orders){
        String[] pizzatype={"Cheese","Meat","Sausage","Vegetables"};
        String[] popularPizzaType=new String[orders.length];
        for (int i = 0; i < orders.length; i++) {
            popularPizzaType[i]="";
            String[] s=orders[i].split (" ");
            for (int j = 6; j < s.length; j++) {
                popularPizzaType[i]+=s[j]+" ";
            }
        }
        ArrayList<String> check=new ArrayList<> ();
        String populars="";
        int mostPopular=0;
        for (int i = 0; i < popularPizzaType.length; i++) {
            if(check.contains (popularPizzaType[i])) continue;
            check.add (popularPizzaType[i]);
            int count=0;
            for (int j = 0; j < popularPizzaType.length; j++) {
                if(i==j)continue;
                if(popularPizzaType[i].equals (popularPizzaType[j]))count++;
            }
            if(mostPopular<count){

                mostPopular=count;
                populars="";
                String type="";
                String[] st=popularPizzaType[i].split (" ");

                for (int j = 0; j < 4; j++) {
                    if(st[j].equals ("Yes"))type+=pizzatype[j]+"+";
                }
                populars+= (type.substring (0, type.length ()-1))+"\n";
            }
            else if(mostPopular==count){
                String type="";
                String[] st=popularPizzaType[i].split (" ");

                for (int j = 0; j < 4; j++) {
                    if(st[j].equals ("Yes"))type+=pizzatype[j]+"+";
                }
                populars+= (type.substring (0, type.length ()-1))+"\n";
            }
        }
        populars="Most popular Pizza type:\n"+populars;
        return populars;
    }
}