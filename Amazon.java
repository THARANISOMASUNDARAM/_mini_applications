import java.util.*;
import javax.lang.model.util.ElementScanner14;
public class Amazon {
   static Scanner sc=new Scanner(System.in);
   static ArrayList <Vendor> ven=new ArrayList<>();
   static ArrayList <String> newV=new ArrayList<>();
   static ArrayList<Integer> newV1=new ArrayList<>(),index_i=new ArrayList<>(),index_j=new ArrayList<>();
   static ArrayList <Customer> cust=new ArrayList<>();
   static ArrayList <Product> all_prod=new ArrayList<>();
   static int index_v,index_c,index1,indexi,indexj;
    public static void main(String[] args) {
        
        home();
        
    }
    public static void home() {
        System.out.println("Welcome Amazon");
        System.out.println("Select your Choice:");
        System.out.println("1.<Admin>");
        System.out.println("2.<Vendor>");
        System.out.println("3.<Customer>");
        System.out.println("4.<Exit>");  
        int choice=sc.nextInt();
        System.out.println("\033[H\033[2J");
        System.out.flush();
        switch(choice){
            case 1:
                System.out.println("welcome admin");
                admin();
                break;
            case 2:
                
                System.out.println("welcome vendor");
                vendor();
                break;
            case 3:
                System.out.println("welcome customer");
                customer();
                break;
            default:
                home();
                break;
        }       
    }
    public static void admin() {
        System.out.println("Enter Username: ");
        sc.nextLine();
        String name=sc.nextLine();
        System.out.println("Enter  The Password: ");
        String pass=sc.nextLine();
        System.out.println("\033[H\033[2J");
        System.out.flush();
        if((name.equals("admin1") && pass.equals("123")) || (name.equals("admin2") && pass.equals("456"))){
            System.out.println("Successfully logged in as Admin!!!");
            true_admin();
        }
        else{
            System.out.println("Invalid username");
            home();
            }
    }
    public static void true_admin() {
            System.out.println("Enter choice:");
            System.out.println("1.add vendor");
            System.out.println("2.remove vendor");
            System.out.println("3.approve vendor");
            System.out.println("4. view vendor");
            System.out.println("5. Exit");
            int choice=sc.nextInt();
            flush();
            switch(choice){
                case 1:
                add_ven();
                break;
                case 2:
                rem_ven();
                break;
                case 3:
                ap_ven();
                break;
                case 4:
                view_ven();
                break;
                default:
                home();


            }

        }
    public static void add_ven() {
        sc.nextLine();
        System.out.println("Enter the vendor name:");
        String name=sc.nextLine();
        System.out.println("Password:");
        String pass=sc.nextLine();
        int id=ven.size() +1;
        
        ven.add(new Vendor(name,pass,id,new ArrayList<>()));
        flush();
        true_admin();

    }
    public static void rem_ven() {
        System.out.println("Enter the vendor id:");
        int id=sc.nextInt();
        for (int i = 0; i < ven.size(); i++) {
            if(ven.get(i).id==id){
                ven.remove(i);
                break;  
            }
            
        }
        true_admin();   
    }
    public static void ap_ven() {
        sc.nextLine();
        for (int i = 0; i < newV.size(); i++) {
            System.out.println(newV.get(i));
            System.out.println("Press yes to Approve!!");
            String s=sc.nextLine();
            if(s.equalsIgnoreCase("yes")){
                System.out.println("Approved!!");
                newV1.set(i,1);
            }
            else{
                System.out.println("Declined :(");
                newV1.set(i,0); 
            }
            admin();
        }
        
    }
    public static void view_ven() {
        System.out.println("name\tid\tpass");
        for (int i = 0; i < ven.size(); i++) {
            System.out.println(ven.get(i).name +"\t"+ ven.get(i).id +"\t"+ven.get(i).pass );
        }
        System.out.println("press ENTER to continue..");
        sc.nextLine();
        String s=sc.nextLine();
        flush();
        true_admin();
        
    }
    public static void vendor() {
        System.out.println("enter choice:");
        System.out.println("1.new vendor");
        System.out.println("2.existing vendor");
        System.out.println("3.Exit");
        int choice = sc.nextInt();
        switch(choice){
            case 1:
            new_ven();
            break;
            case 2:
            old_ven();
            break;
            default:
            home();
            
        }  
    }
    public static void old_ven() {
        System.out.println("Enter username: ");
        sc.nextLine();
        String name=sc.nextLine();
        System.out.println("password: ");
        String pass=sc.nextLine();
        if(check_ven(name,pass)){
            System.out.println("Logged in successfully!!");
            true_ven();
        }
        else{
            System.out.println("Enter the correct username or pass!");
            vendor();
        }


        
    }
    public static boolean check_ven(String name, String pass) {
        for (int i = 0; i < ven.size(); i++) {
            if(ven.get(i).name.equals(name) && ven.get(i).pass.equals(pass) ){
                index_v=i;
                return true;
            }
            
        }
        return false;  
    }
    public static void new_ven() {
        sc.nextLine();
        System.out.println("Username:");
        String name=sc.nextLine();
        System.out.println("Password:");
        String pass=sc.nextLine();
        if(check_ven(name, pass)){
            System.out.println("You already have an account!");
        }
        else{
        String s=name + " " +pass;
            if(contain(s) ){
                if(newV1.get(index1)==1){
                    newV.remove(index1);
                    newV1.remove(index1);
                    int id=ven.size() +1;
                    ven.add(new Vendor(name, pass, id, new ArrayList<>()));
                    System.out.println("Account created !!");
                }
                else if(newV1.get(index1)==-1){
                    System.out.println("Waiting for approval");
                    
                }else{
                    System.out.println("Sorry, you are not authorized :(");
                }
            }
            else{
                newV.add(s);
                newV1.add(-1);
                System.out.println("Waiting for approval");
            }

        }
        vendor();
    }
    public static boolean contain(String s) {            
        for (int i = 0; i < newV.size(); i++) {
            if(newV.get(i).equals(s)){
                index1=i;
                return true;
            }
        }
        return false;
    }
    public static void true_ven() {
        System.out.println("Enter choice");
        System.out.println("1.add product");
        System.out.println("2.remove product");
        System.out.println("3.view product");
        System.out.println("Exit");
        int choice=sc.nextInt();
        flush();
        switch(choice){
            case 1:
            add_pro();break;
            case 2: remove_pro();break;
            case 3: view_pro();break;
            default: vendor();
        }
    }
    public static void view_pro() {
        System.out.println("name\tprice\tsale\tquantity\ttype\t\toffer\tret\twaranty");
        for (int i = 0; i < ven.get(index_v).prod.size(); i++) {
            System.out.println(ven.get(index_v).prod.get(i));
            
        }

    }
    public static void remove_pro() {
        sc.nextLine();
        System.out.println("Enter name of the product:");
        String name=sc.nextLine();
        for (int i = 0; i < ven.get(index_v).prod.size(); i++) {
            if(ven.get(index_v).prod.get(i).name.equals(name) ){
                ven.get(index_v).prod.remove(i);
                break;

            }
            
        }
    }
    public static void add_pro() {
        sc.nextLine();
        System.out.println("Enter name:");
        String name=sc.nextLine();
        System.out.println("Enter type:");
        String type=sc.nextLine();
        System.out.println("Enter price:");
        int price=sc.nextInt();
        System.out.println("Enter quantity:");
        int quantity=sc.nextInt();
        System.out.println("Enter return Days:");
        int ret=sc.nextInt();
        System.out.println("Enter waranty:");
        int waranty=sc.nextInt();
        System.out.println("Enter offer:");
        int offer=sc.nextInt();
        ven.get(index_v).prod.add(new Product(name, type, price, 0, quantity, offer, ret, waranty));
        all_prod.add(new Product(name, type, price, 0, quantity, offer, ret, waranty));
        true_ven();
    }
    public static void customer() {
        System.out.println("1.Sign in");
        System.out.println("2.Sign up");
        int choice=sc.nextInt();
        flush();
        switch(choice){
            case 1:
            cus_signin();
            break;
            case 2:
            cus_signup();
            break;
            default:
            home();
        }
    }
    public static void cus_signup() {
        sc.nextLine();
        System.out.println("Enter username:");
        String name=sc.nextLine();
        System.out.println("Enter password:");
        String pass=sc.nextLine();
        if(check_cus(name,pass)){
            System.out.println("You already ahve an account please sign in!");
        }
        else{
            cust.add(new Customer(name, pass, 0, new ArrayList<>(), new ArrayList<>()));
        }
        customer();
    }
    private static boolean check_cus(String name, String pass) {
        for (int i = 0; i < cust.size(); i++) {
            if(cust.get(i).name.equals(name) && cust.get(i).pass.equals(pass)){
                index_c=i;
                return true;
            }
            
        }
        return false;
    }
    public static void cus_signin() {
        sc.nextLine();
        System.out.println("Enter username:");
        String name=sc.nextLine();
        System.out.println("Enter password:");
        String pass=sc.nextLine();
        if(check_cus(name,pass)){
            System.out.println("Login successfull!!");
            true_cus();
        }
        else{
            System.out.println("invalid username or passwrd!!");
        }
        customer();
    }
    private static void true_cus() {
        System.out.println("Enter Choice");
        System.out.println("1.search Product");
        System.out.println("2.Filter Product");
        System.out.println("3.Compare Product");
        System.out.println("4.View Cart");
        System.out.println("5.View Order");
        System.out.println("Exit");
        int choice=sc.nextInt();
        flush();
        switch(choice){
            case 1:
            search_pro();
            break;
            case 2:
            filter_pro();
            break;
            case 3:
            compare_pro();
            break;
            case 4:
            view_cart();
            break;
            case 5:
            view_order();
            break;
        }
        true_cus();
        
    }
    private static void compare_pro() {
        sc.nextLine();
        System.out.println("Enter product name 1:");
        String name1=sc.nextLine();
        System.out.println("Enter product name 2:");
        String name2=sc.nextLine();
        ArrayList <Product> temp=new ArrayList<>();
        for (int i = 0; i < ven.size(); i++) {
            for (int j = 0; j < ven.get(i).prod.size(); j++) {
                if(ven.get(i).prod.get(j).name.equals(name1)){
                    System.out.println(ven.get(i).prod.get(j));
                    break;  
                }
            }
        }
        for (int i = 0; i < ven.size(); i++) {
            for (int j = 0; j < ven.get(i).prod.size(); j++) {
                if(ven.get(i).prod.get(j).name.equals(name2)){
                    System.out.println(ven.get(i).prod.get(j));  
                    break;
                }
            }
        }
        
    }
    private static void view_order() {
        System.out.println("Your order");
        for (int i = 0; i <cust.get(index_c).order.size() ; i++) {
            System.out.println(cust.get(index_c).order.get(i));
            
            
        }
        System.out.println("1.return");
        System.out.println("2. cancel");
        int choice=sc.nextInt();
        flush();
        if(choice==1){
            return_prod();
           
        }
        else if(choice==2){
            cancel_prod();
            
        }
       true_cus();
    }
    private static void cancel_prod() {
        System.out.println("Enter the prodcut name");
        String name=sc.nextLine();
        find_prod(name);
        ven.get(indexi).prod.get(indexj).quantity+=1;
        ven.get(indexi).prod.get(indexj).sale-=1;
    }
    private static void return_prod() {
        System.out.println("Enter the prodcut name");
        String name=sc.nextLine();
        find_prod(name);
        ven.get(indexi).prod.get(indexj).quantity+=1;
        ven.get(indexi).prod.get(indexj).sale-=1;
    }
   
    private static void view_cart() {
        for (int i = 0; i < cust.get(index_c).cart.size(); i++) {
            System.out.println(cust.get(index_c).cart.get(i));
        }
        
        System.out.println("1.Buy now");
        System.out.println("2. remove product");
        int choice=sc.nextInt();
        if(choice==1){
            buy_now();
           
        }
        else if(choice==2){
            rem_prod();
            
        }
       true_cus();
    }
    private static void rem_prod() {
        System.out.println("Enter name to remove");
        String name=sc.nextLine();
        for (int i = 0; i < cust.get(index_c).cart.size(); i++) {
            if(cust.get(index_c).cart.get(i).name.equals(name)){
                cust.get(index_c).cart.remove(i);
                break;
            }
            
        }
    }
    private static void buy_now() {
        System.out.println("Are you sure to buy these products from cart?(y/n)");
        sc.nextLine();
        String c=sc.nextLine().toUpperCase();
        if(c.equals("Y") || c.equals("YES")){
            for (int i = 0; i < cust.get(index_c).cart.size(); i++) {
                cust.get(index_c).order.add(cust.get(index_c).cart.get(i));
                find_prod(cust.get(index_c).cart.get(i).name);
                ven.get(indexi).prod.get(indexj).quantity-=1;
                ven.get(indexi).prod.get(indexj).sale+=1;

            }
            cust.get(index_c).cart.clear();
        }
    }
    private static void find_prod(String name) {
        for (int i = 0; i < ven.size(); i++) {
            for (int j = 0; j < ven.get(i).prod.size(); j++) {
                if(ven.get(i).prod.get(j).name.equals(name)){
                    indexi=i;
                    indexj=j;
                }
                
            }
            
        }
    }
    private static void filter_pro() {
        ArrayList<String> temp=new ArrayList<>();
        
        for (int i = 0; i < ven.size(); i++) {
            for (int j = 0; j < ven.get(i).prod.size() ; j++) {
                if(!temp.contains(ven.get(i).prod.get(j).type)){
                    temp.add(ven.get(i).prod.get(j).type);
                    System.out.println(temp.size()+"\t"+temp.get(temp.size()-1));
                }
            }
            
        }
        System.out.println("Enter the type:");
        sc.nextLine();
        String w=sc.nextLine();
        for (int i = 0; i < ven.size(); i++) {
            for (int j = 0; j < ven.get(i).prod.size() ; j++) {
                if(ven.get(i).prod.get(j).type.equals(w)){
                    System.out.println(ven.get(i).prod.get(j).name +"\t"+ ven.get(i).prod.get(j).price);
                }
            }
            
        }
        System.out.println("Select a product");
        System.out.println("Enter the name");
        String name=sc.nextLine();
        label:for (int i = 0; i < ven.size(); i++) {
            for (int j = 0; j < ven.get(i).prod.size() ; j++) {
                if(ven.get(i).prod.get(j).name.contains(name)){
                    cust.get(index_c).cart.add(ven.get(i).prod.get(j));
                    System.out.println("Product added successfully");
                    break label;
                }
            }
            
        }
        true_cus();

    }
    private static void search_pro() {
        System.out.println("Enter a letter or word related to the product:");
        sc.nextLine();
        String w=sc.nextLine();
        for (int i = 0; i < ven.size(); i++) {
            for (int j = 0; j < ven.get(i).prod.size() ; j++) {
                if(ven.get(i).prod.get(j).name.contains(w)){
                    System.out.println(ven.get(i).prod.get(j).name +"\t"+ ven.get(i).prod.get(j).price);
                }
            }
            
        }
        System.out.println("Select a product");
        System.out.println("Enter the name");
        String name=sc.nextLine();
        label:for (int i = 0; i < ven.size(); i++) {
            for (int j = 0; j < ven.get(i).prod.size() ; j++) {
                if(ven.get(i).prod.get(j).name.contains(name)){
                    cust.get(index_c).cart.add(ven.get(i).prod.get(j));
                    System.out.println("Product added successfully");
                    index_i.add(i);
                    index_j.add(j);
                    break label;
                }
            }
            
        }


    }
    public static void flush() {
        System.out.print("\033[H\033[2J");
        System.out.flush();  
    }
   
}
class Product{
    String name,type;
    int price,quantity,sale,offer,waranty,ret;
    Product(String name,String type, int price,int sale,int quantity,int offer,int ret,int waranty){
        this.name=name;
        this.type=type;
        this.price=price;
        this.sale=sale;
        this.quantity=quantity;
        this.offer=offer;
        this.ret=ret;
        this.waranty=waranty;
    }
    public String toString(){
        return name+"\t"+price+"\t"+sale+"\t"+quantity+"\t"+type+"\t\t"+offer+"\t"+ret+"\t"+waranty;
    }

}
class Vendor{
    String name,pass;
    int id;
    ArrayList<Product> prod;
    Vendor(String name, String pass,int id, ArrayList<Product> prod){
        this.name=name;
        this.pass=pass;
        this.prod=new ArrayList<> (prod);
        this.id=id;
    }

}
class Customer{
    String name,pass;
    int wallet;
    ArrayList<Product> cart,order;
    Customer(String name,String pass,int wallet,ArrayList<Product> cart,ArrayList<Product> order){
        this.name=name;
        this.pass=pass;
        this.wallet=wallet;
        this.cart=new ArrayList<>(cart);
        this.order=new ArrayList<>(order);
    }
}
