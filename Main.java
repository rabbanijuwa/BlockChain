import java.util.Date;

public class Main {
    public static void main(String[] args) {
        int difficulty = 4; // Set the difficulty level for mining

        Blockchain blockchain = new Blockchain(difficulty); // Create a new blockchain with the specified difficulty

        // Add different types of blocks (CreditCardTransaction, StudentGrade, BankingTransaction) to the blockchain
        blockchain.addBlock(new CreditCardTransaction("1234-5678-9012-3456", new Date(), 50.0, "Online Store"));
        blockchain.addBlock(new StudentGrade("S123456", "Math", 90));
        blockchain.addBlock(new BankingTransaction("123456789", new Date(), 1000.0, "Deposit"));

        blockchain.printBlockchain(); // Print the entire blockchain
    }
}

