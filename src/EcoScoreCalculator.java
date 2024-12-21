
import java.util.*;

class EcoScoreCalculator {
    private static final List<String> ECO_KEYWORDS = Arrays.asList(
        "eco", "green", "recycle", "친환경", "recycling", "재사용", "재활용",
        "greenwashing", "그린워싱", "탄소발자국"
        );
    private static final int MAX_SCORE = 100;

    public int calculateScore(List<Transaction> transactions) {
        int score = 0;
        
        if (score >= 100) {
            return Math.min(score, MAX_SCORE);
        }

        for (Transaction transaction : transactions) {
            for (String keyword : ECO_KEYWORDS) {
                if (transaction.getDescription().toLowerCase().contains(keyword)) {
                    score += 1;
                    break;
                }
            }
        }

        return Math.min(score, MAX_SCORE);
    }
}