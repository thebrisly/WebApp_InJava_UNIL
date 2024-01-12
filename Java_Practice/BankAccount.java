public class BankAccount {
    private double balance;

    // Constructeur initialisant le solde du compte
    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    // Méthode pour déposer de l'argent dans le compte
    public void deposit(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Le montant du dépôt ne peut pas être négatif.");
        }
        this.balance += amount;
        System.out.println("Dépôt de " + amount + " effectué. Nouveau solde : " + balance);
    }

    // Méthode pour retirer de l'argent du compte
    public void withdraw(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Le montant du retrait ne peut pas être négatif.");
        }

        if (amount > balance) {
            throw new IllegalStateException("Fonds insuffisants pour effectuer le retrait.");
        }

        this.balance -= amount;
        System.out.println("Retrait de " + amount + " effectué. Nouveau solde : " + balance);
    }

    public static void main(String[] args) {
        BankAccount account = new BankAccount(100.0);

        try {
            account.deposit(50.0);
            account.withdraw(30.0);
            account.withdraw(150.0);  // Tentative de retrait avec des fonds insuffisants
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }
}
