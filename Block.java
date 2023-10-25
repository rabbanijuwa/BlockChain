import java.security.MessageDigest;  // Import necessary packages for hashing
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;     // Import for date formatting
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class Block {
    private int index; // Block's position in the blockchain
    private long timestamp; // Timestamp of when the block was created
    private String previousHash; // Hash of the previous block in the chain
    private String hash; // The block's own hash
    private Object data; // Data stored in the block (can be different types)
    private int difficulty; // Mining difficulty level
    private int nonce; // Nonce used in mining

    public Block(int index, String previousHash, Object data, int difficulty) {
        this.index = index;
        this.timestamp = new Date().getTime();
        this.previousHash = previousHash;
        this.data = data;
        this.difficulty = difficulty;
        this.nonce = 0;
        this.hash = mineBlock(); // Create the block's hash through mining
    }

    public String mineBlock() {
        String target = "0".repeat(difficulty); // The target hash pattern to achieve

        while (!hashIsValid(target)) { // Repeatedly mine until a valid hash is found
            nonce++; // Increment nonce
            hash = calculateHash(); // Recalculate the block's hash
        }

        return hash; // Return the valid hash
    }

    public boolean hashIsValid(String target) {
        return hash != null && hash.startsWith(target); // Check if the hash meets the target pattern
    }

    public String calculateHash() {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256"); // Create a SHA-256 hash object
            String input = index + timestamp + previousHash + data.toString() + difficulty + nonce; // Combine block data
            byte[] hashBytes = digest.digest(input.getBytes("UTF-8")); // Calculate the hash
            StringBuilder hexString = new StringBuilder();

            for (byte b : hashBytes) { // Convert hash bytes to a hexadecimal string
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0'); // Ensure two characters per byte
                hexString.append(hex);
            }

            return hexString.toString(); // Return the hash as a hexadecimal string
        } catch (NoSuchAlgorithmException | java.io.UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public int getIndex() {
        return index;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public String getHash() {
        return hash;
    }

    public Object getData() {
        return data;
    }

    public int getDifficulty() {
        return difficulty;
    }
}

class Blockchain {
    private List<Block> chain; // List to store the blocks in the blockchain
    private int difficulty; // Difficulty level for mining

    public Blockchain(int difficulty) {
        this.difficulty = difficulty;
        chain = new ArrayList<>(); // Create an empty blockchain list
        addGenesisBlock(); // Add the initial "Genesis" block
    }

    private void addGenesisBlock() {
        chain.add(new Block(0, "0", "Genesis Block", difficulty)); // Create and add the initial block to the chain
    }

    public void addBlock(Object data) {
        Block previousBlock = chain.get(chain.size() - 1); // Get the last block in the chain
        Block newBlock = new Block(previousBlock.getIndex() + 1, previousBlock.getHash(), data, difficulty); // Create a new block
        chain.add(newBlock); // Add the new block to the chain
    }

    public void printBlockchain() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy"); // Date formatting

        for (Block block : chain) {
            System.out.println("Block #" + block.getIndex());
            System.out.println("Timestamp: " + dateFormat.format(new Date(block.getTimestamp()))); // Print block information
            System.out.println("Previous Hash: " + block.getPreviousHash());
            System.out.println("Hash: " + block.getHash());
            System.out.println("Data: " + block.getData().toString());
            System.out.println("Difficulty: " + block.getDifficulty());
            System.out.println();
        }
    }
}

class CreditCardTransaction {
    private String cardNumber;
    private Date transactionDate;
    private double amount;
    private String merchant;

    public CreditCardTransaction(String cardNumber, Date transactionDate, double amount, String merchant) {
        this.cardNumber = cardNumber;
        this.transactionDate = transactionDate;
        this.amount = amount;
        this.merchant = merchant;
    }

    @Override
    public String toString() {
        return "CreditCardTransaction{cardNumber='" + cardNumber + "', transactionDate=" + transactionDate + ", amount=" + amount + ", merchant='" + merchant + "'}";
    }
}

class StudentGrade {
    private String studentId;
    private String courseCode;
    private int grade;

    public StudentGrade(String studentId, String courseCode, int grade) {
        this.studentId = studentId;
        this.courseCode = courseCode;
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "StudentGrade{studentId='" + studentId + "', courseCode='" + courseCode + "', grade=" + grade + "}";
    }
}

class BankingTransaction {
    private String accountNumber;
    private Date transactionDate;
    private double amount;
    private String transactionType;

    public BankingTransaction(String accountNumber, Date transactionDate, double amount, String transactionType) {
        this.accountNumber = accountNumber;
        this.transactionDate = transactionDate;
        this.amount = amount;
        this.transactionType = transactionType;
    }

    @Override
    public String toString() {
        return "BankingTransaction{accountNumber='" + accountNumber + "', transactionDate=" + transactionDate + ", amount=" + amount + ", transactionType='" + transactionType + "'}";
    }
}



