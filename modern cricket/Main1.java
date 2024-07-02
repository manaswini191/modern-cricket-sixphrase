import java.io.*;
import java.util.*;

public class Main1 {
    public static void main(String[] args) throws IOException, InterruptedException {
        List<String> team1 = readPlayers("C:/Users/manuk/OneDrive/Desktop/sixphrase/modern cricket/team1.txt");
        List<String> team2 = readPlayers("C:/Users/manuk/OneDrive/Desktop/sixphrase/modern cricket/team2.txt");
        Thread.sleep(1000);
        System.out.println("Players of Team A are: \n" + String.join(", ", team1));
        System.out.println();
        Thread.sleep(1000);
        System.out.println("Players of Team B are: \n" + String.join(", ", team2));
        System.out.println();
        int scoreA = playMatch(team1, "Team A", Integer.MAX_VALUE);
        int scoreB = playMatch(team2, "Team B", scoreA);
        System.out.println("\nFinal Results:");
        if (scoreA > scoreB) {
            Thread.sleep(1000);
            System.out.println("Hurray! Team A won by " + (scoreA - scoreB) + " runs");
        } else if (scoreA < scoreB) {
            Thread.sleep(1000);
            System.out.println("Hurray! Team B won by " + (scoreB - scoreA) + " runs");
        } else {
            Thread.sleep(1000);
            System.out.println("What a Match! It was a tie!");
        }
    }
    private static List<String> readPlayers(String filePath) throws IOException {
        List<String> players = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            players.add(line.trim());
        }
        reader.close();
        return players;
    }
    private static int playMatch(List<String> team, String teamName, int target) throws InterruptedException {
        Random random = new Random();
        int score = 0;
        int wickets = 0;
        int[] playerScores = new int[team.size()];
        Thread.sleep(2000);
        System.out.println();
        System.out.println("The Match is about to begin for " + teamName + "!");
        System.out.println();
        Thread.sleep(1000);
        System.out.println("balls     current score(at this ball)     total score");
        for (int ball = 1; ball <= 120 && wickets < 5; ball++) {
            int result = random.nextInt(10);
            if (result <= 4 || result == 6) {
                if (result == 6) {
                    Thread.sleep(1000);
                    System.out.println("Hurray! it's a 6");
                }
                score += result;
                playerScores[wickets] += result;
                Thread.sleep(1000);
                System.out.printf("%-10d %-30d %d / %d%n", ball, result, score, wickets);
                if (score > target) {
                    System.out.println("Hurray! " + teamName + " won!");
                    return score;
                }
            } else {
                Thread.sleep(1000);
                System.out.printf("%-10d %-30d %d / %d%n", ball, 0, score, wickets);
                System.out.println("Oh no! The player was " + res(result));
                if (++wickets < team.size()) {
                    playerScores[wickets] = 0;
                }
            }
            Thread.sleep(1000);
        }
        System.out.println("\nScorecard:");
        for (int i = 0; i < team.size(); i++) {
            Thread.sleep(500);
            System.out.printf("%s: %d%n", team.get(i), playerScores[i]);
        }
        Thread.sleep(1000);
        System.out.println("Total score: " + score + " / " + wickets);
        return score;
    }
    private static String res(int code) {
        switch (code) {
            case 5: return "Run Out";
            case 7: return "LBW";
            case 8: return "Caught Out";
            case 9: return "Bowled";
            default: return "Unknown";
        }
    }
}
