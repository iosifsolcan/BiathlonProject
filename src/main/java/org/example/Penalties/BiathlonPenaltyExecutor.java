package org.example.Penalties;

import org.example.Writer.BiathlonExecute;

import java.util.List;

public class BiathlonPenaltyExecutor extends BiathlonExecute {
    private String shots;
    private String[] shotByShot;
    private List<BiathlonExecute> athletes;
    private int penalties;

    public BiathlonPenaltyExecutor(List<BiathlonExecute> list) {
        super(list);
        this.athletes = list;
    }

    @Override
    public void calculatePenalties() {
        ShotResult shotResult = null;
        for (BiathlonExecute athlete : athletes) {
            penalties = 0;
            shots = athlete.firstShootingRange;
            shotByShot = shots.split("");
            for (int i = 0; i < shotByShot.length; i++) {
                if (shotByShot[i].equalsIgnoreCase("x")) {
                    shotResult = ShotResult.HIT;
                } else if (shotByShot[i].equalsIgnoreCase("o")) {
                    shotResult = ShotResult.MISS;
                }
                if (shotResult == ShotResult.MISS) {
                    penalties += 10;
                }
            }

            shots = athlete.secondShootingRange;
            shotByShot = shots.split("");
            for (int i = 0; i < shotByShot.length; i++) {
                if (shotByShot[i].equalsIgnoreCase("x")) {
                    shotResult = ShotResult.HIT;
                } else if (shotByShot[i].equalsIgnoreCase("o")) {
                    shotResult = ShotResult.MISS;
                }
                if (shotResult == ShotResult.MISS) {
                    penalties += 10;
                }
            }

            shots = athlete.thirdShootingRange;
            shotByShot = shots.split("");
            for (int i = 0; i < shotByShot.length; i++) {
                if (shotByShot[i].equalsIgnoreCase("x")) {
                    shotResult = ShotResult.HIT;
                } else if (shotByShot[i].equalsIgnoreCase("o")) {
                    shotResult = ShotResult.MISS;
                }
                if (shotResult == ShotResult.MISS) {
                    penalties += 10;
                }
            }
            athlete.skiTimeResultUpdated = athlete.skiTimeResult + " + " + penalties;
            String[] timeParts = athlete.skiTimeResult.split(":");

            int minutes = Integer.parseInt(timeParts[0]);
            int seconds = Integer.parseInt(timeParts[1]);

            int totalSeconds = minutes * 60 + seconds + penalties;

            int finalMinutes = totalSeconds / 60;
            int finalSeconds = totalSeconds % 60;

            athlete.skiFinalTime = String.format("%02d:%02d", finalMinutes, finalSeconds);
        }

        super.calculatePenalties();
    }
}
