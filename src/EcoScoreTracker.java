class EcoScoreTracker {
    private static int totalScore = 0;

    public static void updateScore(int monthlyScore) {
        totalScore += monthlyScore;
    }

    public static int getTotalScore() {
        return totalScore;
    }
}