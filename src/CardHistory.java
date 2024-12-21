
import java.util.*;

class CardHistory {
    private List<Transaction> transactions;
    private Set<Transaction> evaluatedTransactions;

    public CardHistory() {
        this.transactions = new ArrayList<>();
        this.evaluatedTransactions = new HashSet<>();
    }

    public void addTransaction(String description) {
        transactions.add(new Transaction(description));
    }

    public List<Transaction> getUnevaluatedTransactions() {
        List<Transaction> unevaluated = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (!evaluatedTransactions.contains(transaction)) {
                unevaluated.add(transaction);
            }
        }
        return unevaluated;
    }

    public void markAsEvaluated(List<Transaction> evaluated) {
        evaluatedTransactions.addAll(evaluated);
    }

    public List<Transaction> getAllTransactions() {
        return new ArrayList<>(transactions);
    }
}