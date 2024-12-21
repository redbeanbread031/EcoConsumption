import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class EcoScoreApp {
    private static CardHistory cardHistory = new CardHistory();
    private static EcoScoreCalculator calculator = new EcoScoreCalculator();

    private static void displayProgress() {
        int totalScore = EcoScoreTracker.getTotalScore();

        System.out.println("\nCurrent Progress: ");
        if (totalScore < 20) {
            System.out.println("        ________");
            System.out.println("       /        \\");
            System.out.println("      /          \\");
            System.out.println("     |    흙      |");
            System.out.println("      \\          /");
            System.out.println("       \\________/");
        } else if (totalScore < 50) {
            System.out.println("         _ ");
            System.out.println("        / \\");
            System.out.println("       /   \\");
            System.out.println("      /     \\");
            System.out.println("         새싹");
        } else if (totalScore < 90) {
            System.out.println("         _ ");
            System.out.println("        / \\");
            System.out.println("       /   \\");
            System.out.println("      /     \\");
            System.out.println("       \\|/  ");
            System.out.println("        | 봉오리");
        } else if (totalScore < 100) {
            System.out.println("         @ ");
            System.out.println("        /|\\ ");
            System.out.println("       / | \\");
            System.out.println("        / \\ 꽃");
        } else {
            System.out.println("           @ ");
            System.out.println("         \\|/ ");
            System.out.println("       -- | -- 나비와 함께");
            System.out.println("         / \\");
        }

        int progressBars = totalScore / 5;
        System.out.println("진행도: " + "=".repeat(progressBars) + " " + totalScore + "/100");
    }

    private static void evaluateTransactions() {
        List<Transaction> unevaluatedTransactions = cardHistory.getUnevaluatedTransactions();
        if (unevaluatedTransactions.isEmpty()) {
            System.out.println("\nNo new transactions to evaluate.");
            return;
        }

        int monthlyScore = calculator.calculateScore(unevaluatedTransactions);
        cardHistory.markAsEvaluated(unevaluatedTransactions);

        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM");
        String formattedDate = currentDate.format(formatter);

        System.out.println("\nEco Score for " + formattedDate + " is: " + monthlyScore);
        EcoScoreTracker.updateScore(monthlyScore);
        displayProgress();
    }

    private static void inputTransaction() {
        Scanner scanner = new Scanner(System.in);
        int cnt = 0;
        System.out.println("입력할 카드 내역의 개수를 입력하세요: ");
        cnt = scanner.nextInt();
        scanner.nextLine();

        if (cnt <= 0) {
            System.out.println("잘못된 입력입니다. 메뉴로 돌아갑니다.");
            return;
        }

        while(cnt > 0) {
            System.out.print("카드 내역을 입력하세요: ");
            String description = scanner.nextLine();
            cardHistory.addTransaction(description);
            System.out.println("입력이 정상적으로 저장되었습니다.");
            cnt--;
        }
    }

    private static void displayAllTransactions() {
        List<Transaction> allTransactions = cardHistory.getAllTransactions();
        if (allTransactions.isEmpty()) {
            System.out.println("\nNo transactions found.");
            return;
        }

        System.out.println("\nAll Transactions:");
        for (Transaction transaction : allTransactions) {
            System.out.println("- " + transaction.getDescription());
        }
    }

    private static void printInfo() {
        System.out.println("========================================================");
        System.out.println("안녕하세요! 이 프로그램은 <마이데이터 기반 친환경 소비 촉진 서비스(가제)>의 데모 프로그램입니다.");
        System.out.println("현 프로그램은 5개의 메뉴로 되어 있으며, 각 메뉴는 서비스의 주요 매커니즘을 간략하게 구현한 것입니다.");
        System.out.println("또한, 본 서비스는 모든 기능이 구현된 상태가 아니며, 아이디어에 제공된 의도를 중심으로 작성되었음을 양해 부탁드립니다.");
        System.out.println("(주의할 점: 초반 mock data는 입력된 상태이므로 처음엔 메뉴 2번을 입력해 내역을 불러와 주시길 바랍니다.)");
        System.out.println("그렇다면 데모 서비스를 즐겨주시길 바랍니다!:)");
        System.out.println("========================================================");
    }

    private static void printMenu() {
        System.out.println("\nMenu: ");
        System.out.println("1. 카드 내역을 직접 입력합니다.");
        System.out.println("2. 카드 내역을 불러와 점수를 계산합니다.");
        System.out.println("3. 현재 점수를 출력합니다.");
        System.out.println("4. 모든 카드 내역을 출력합니다.");
        System.out.println("5. Exit");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Mock Data
        cardHistory.addTransaction("eco-friendly reusable bag purchase");
        cardHistory.addTransaction("green energy subscription");
        cardHistory.addTransaction("plastic bottle recycling initiative");
        cardHistory.addTransaction("친환경 식품 구입");
        cardHistory.addTransaction("eco-friendly reusable bag purchase");
        cardHistory.addTransaction("green energy subscription");
        cardHistory.addTransaction("chocolate");
        cardHistory.addTransaction("친환경 식품 구입");
        cardHistory.addTransaction("eco-friendly reusable bag purchase");
        cardHistory.addTransaction("green energy subscription");
        cardHistory.addTransaction("plastic bottleinitiative");
        cardHistory.addTransaction("식품 구입");
        cardHistory.addTransaction("eco-friendly reusable bag purchase");
        cardHistory.addTransaction("green energy subscription");
        cardHistory.addTransaction("plastic bottle recycling initiative");
        cardHistory.addTransaction("친환경 식품 구입");
        cardHistory.addTransaction("eco-friendly reusable bag purchase");
        cardHistory.addTransaction("green energy subscription");
        cardHistory.addTransaction("plastic bottle recycling initiative");
        cardHistory.addTransaction("친환경 식품 구입");
        cardHistory.addTransaction("eco-friendly reusable bag purchase");
        cardHistory.addTransaction("green energy subscription");

        printInfo();

        while (true) {
            printMenu();
            System.out.print("메뉴를 입력하세요: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("========================================================");
                    inputTransaction();
                    System.out.println("========================================================");
                    break;
                case 2:
                    System.out.println("========================================================");
                    evaluateTransactions();
                    System.out.println("========================================================");
                    break;
                case 3:
                    System.out.println("========================================================");
                    System.out.println("현재 점수: " + EcoScoreTracker.getTotalScore());
                    displayProgress();
                    System.out.println("========================================================");
                    break;
                case 4:
                    System.out.println("========================================================");
                    displayAllTransactions();
                    System.out.println("========================================================");
                    break;
                case 5:
                    System.out.println("프로그램을 종료합니다. 안녕히 가세요!");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("유효하지 않은 번호입니다. 다시 입력하세요.");
            }
        }
    }
}
