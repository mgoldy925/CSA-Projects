import java.util.Random;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Robber robber = new Robber();
        
        while (true)
        {
            Scanner scanner = new Scanner(System.in);
            System.out.println("\nType gm to get amount of money.");
            System.out.println("Type gg to get number of gold bars.");
            System.out.println("Type gd to get number of diamonds.");
            System.out.println("Type gb to get number of banks robbed since last arrested.");
            System.out.println("Type gv to get total value of loot.");
            System.out.println("Type r to rob a bank.");
            System.out.println("Type c to convert gold bars and/or diamonds to money.");
            
            String ans = scanner.nextLine();
            
            if (ans.equals("gm"))
            {
                double money = robber.getMoney();
                System.out.printf("You have $%.2f.\n", money);
            }
            else if (ans.equals("gg"))
            {
                int goldbars = robber.getGoldBars();
                System.out.println("You have " + goldbars + " gold bars.");
            }
            else if (ans.equals("gd"))
            {
                int diamonds = robber.getDiamonds();
                System.out.println("You have " + diamonds + " diamonds.");
            }
            else if (ans.equals("gb"))
            {
                int banks = robber.getBanks();
                System.out.println("You have robbed " + banks + " banks since you were last arrested.");
            }
            else if (ans.equals("gv"))
            {
                double value = robber.getValue();
                System.out.printf("Your money, gold bars, and diamonds have a total value of $%.2f.\n", value);
            }
            else if (ans.equals("r"))
            {
                if (robber.rob())
                {
                    System.out.println("You successfully robbed the bank without getting caught.  Good job!");
                }
                else
                {
                    System.out.println("You were caught by the police!  They took your gold bars, diamonds, and three-fourths of your money.");
                }
            }
            else if (ans.equals("c"))
            {
                int goldbars = 0;
                int diamonds = 0;
                
                while (goldbars < 0 && diamonds < 0)
                {
                    System.out.println("How many gold bars would you like to exchange for money?  Enter a whole number.");
                    goldbars = scanner.nextInt();
                    System.out.println("How many diamonds would you like to exchange for money?  Enter a whole number.");
                    diamonds = scanner.nextInt();
                }
                
                robber.convert(goldbars, diamonds);
                System.out.println("You successfully exchanged your goldbars/diamonds for money.");
            }
            else
            {
                System.out.println("\nPlease enter a valid string.");
            }
        }
    }
}

class Robber 
{
    Random rand = new Random();
    private double myMoney;
    private int myGoldBars;
    private int myDiamonds;
    private int myBanks;
    private boolean allowed;
    
    public Robber()
    {
        myMoney = 0;
        myGoldBars = 0;
        myDiamonds = 0;
        myBanks = 0;
        allowed = true;
    }

    public Robber(double money, int goldbars, int diamonds, int banks)
    {
        myMoney = money;
        myGoldBars = goldbars;
        myDiamonds = diamonds;
        myBanks = banks;
    }
    
    public double getMoney()
    {
        return myMoney;
    }
    
    public int getGoldBars()
    {
        return myGoldBars;
    }
    
    public int getDiamonds()
    {
        return myDiamonds;
    }
    
    public int getBanks()
    {
        return myBanks;
    }
    
    public double getValue()
    {
        double value = myMoney + myGoldBars * 6500 + myDiamonds * 35000;
        return value;
    }
    
    public boolean rob()
    {
        double caught = rand.nextDouble();
        
        if (caught > 0.96)
        {
            myMoney *= 0.75;
            myGoldBars = 0;
            myDiamonds = 0;
            myBanks = 0;
            return false;
        }
        
        double chance = rand.nextDouble();
        double stolen_money = 0;
        int stolen_goldbars = 0;
        int stolen_diamonds = 0;
        
        if (chance < 0.7)
        {
            stolen_money = 59000 * rand.nextDouble() + 1000;
        }
        else
        {
            stolen_money = 140000 * rand.nextDouble() + 60000;
        }
        
        if (chance > 0.85)
        {
            stolen_goldbars = rand.nextInt(11) + 5;
        }
        else if (chance > 0.6)
        {
            stolen_goldbars = rand.nextInt(9);
        }
        
        if (chance > 0.95)
        {
            stolen_diamonds = rand.nextInt(5) + 1;
        }
        
        myMoney += stolen_money;
        myGoldBars += stolen_goldbars;
        myDiamonds += stolen_diamonds;
        myBanks++;
        allowed = true;
        
        return true;
    }
    
    public void convert(int goldbars, int diamonds)
    {
        if (!allowed)
        {
            System.out.println("You've already been here!  Come back after you rob some more banks!");
            return;
        }
        
        if (goldbars > 6 || diamonds > 1)
        {
            System.out.println("You can only exchange 4 goldbars and 1 diamonds at most.");
            return;
        }
        
        double new_money = 0;
        new_money += (goldbars * 6500);
        new_money += (diamonds * 35000);
        
        myMoney += new_money;
        myGoldBars -= goldbars;
        myDiamonds -= myDiamonds;
        
        allowed = false;
    }
}