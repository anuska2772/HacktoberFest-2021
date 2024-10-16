import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;

class bankAccount{
    private double balance;
    bankAccount(double initialBalance){
        this.balance=initialBalance;
    }

    public double getBalance(){
        return balance;
    }

    public void setBalance(double balance){
        this.balance=balance;
    }
}

class ATM{
    private bankAccount account;

    public ATM(bankAccount account){
        this.account=account;
    }

    public void withdraw(double amount, TextArea display ){
        if(amount>0 && amount<=account.getBalance()){
            account.setBalance(account.getBalance() - amount);
            display.setText("WITHDRAWAL SUCCESSFUL!\nYou have withdrawn="+ amount +"\nYour updated balance="+account.getBalance());
        

        }

        else if(amount > account.getBalance()){
            display.setText("INSUFFICIENT BALANCE!");

        }
        else{
            display.setText("INVALID AMOUNT ENTERED. Try again!");

        }
    }

    public void deposit(double amount, TextArea dispaly){
        if(amount>0){
            account.setBalance(amount+account.getBalance());
            dispaly.setText("SUCCESSFULLY DEPOSITED!\nAmount deposited="+amount+"\nYour updated balance="+ account.getBalance());

        }

        else{
            dispaly.setText("INVALID AMOUNT ENTERED! Try again!");

        }
    }

    public void checkBalance(TextArea display){
        display.setText("Your current balance is="+account.getBalance());

    }

}

public class ATMinterface extends Frame implements ActionListener {
    private ATM atm;
    private TextField amountField;
    private TextArea display;
    private Button withdrawButton, depositButton, checkBalButton, exitButton;

    public ATMinterface(bankAccount userAccount) {
        atm = new ATM(userAccount);

        setLayout(new FlowLayout());
        setTitle("ATM Machine");
        setSize(400, 300);

        Label amountLabel=new Label("Enter amount");
        amountField = new TextField(10);
        display = new TextArea(5, 30);
        display.setEditable(false);

        withdrawButton =new Button("Withdraw");
        depositButton =new Button("Deposit");
        checkBalButton =new Button("Check Balance");
        exitButton =new Button("Exit");

        add(amountLabel);
        add(amountField);
        add(withdrawButton);
        add(depositButton);
        add(checkBalButton);
        add(exitButton);
        add(display);

        withdrawButton.addActionListener(this);
        depositButton.addActionListener(this);
        checkBalButton.addActionListener(this);
        exitButton.addActionListener(this);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we){
                System.exit(0);
            }
        });
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){
        String command = ae.getActionCommand();
        double amount = 0;

        try{
            if(!amountField.getText().isEmpty()){
                amount=Double.parseDouble(amountField.getText());
            }
        }
        catch(NumberFormatException e){
            display.setText("Please enter a valid number.");
            return ;
        }

        switch(command){
            case "Withdraw":
                atm.withdraw(amount, display);
                break;

            case "Deposit":
                atm.deposit(amount, display);
                break;

            case "Check Balance":
                atm.checkBalance(display);
                break;

            case "Exit":
                System.exit(0);
                break;
        }
        amountField.setText("");
    }

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter User Account=");
        double userAcc=sc.nextDouble();
        bankAccount userAccount = new bankAccount(userAcc);

        new ATMinterface(userAccount);
    }
    
}
