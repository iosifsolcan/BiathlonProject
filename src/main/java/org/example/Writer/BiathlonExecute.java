package org.example.Writer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class BiathlonExecute {
    public List<BiathlonExecute> athletes;
    public String athleteNumber;
    public String athleteName;
    public String countryCode;
    public String skiTimeResult;
    public String firstShootingRange;
    public String secondShootingRange;
    public String thirdShootingRange;
    public String skiTimeResultUpdated;
    public String skiFinalTime;

    public BiathlonExecute(List<BiathlonExecute> list) {
        this.athletes = list;
    }

    public enum ShotResult {
        HIT,
        MISS
    }

    public void readFile() {
    }

    ;

    public void calculatePenalties() {
    }

    ;

    public void compareSkiFinalTime() {
    }

    ;

    public void printPlacement() {
        try (BufferedWriter writer =
                     new BufferedWriter(new FileWriter("src/main/resources/placements.txt"))) {
            for (int i = 0; i < athletes.size(); i++) {
                BiathlonExecute athlete = athletes.get(i);
                if (i == 0) {
                    writer.write(
                            "Winner - "
                                    + athlete.athleteName
                                    + " "
                                    + athlete.skiFinalTime
                                    + " ("
                                    + athlete.skiTimeResultUpdated
                                    + ")");
                    writer.newLine();
                }
                if (i == 1) {
                    writer.newLine();
                    writer.write(
                            "Runner-up - "
                                    + athlete.athleteName
                                    + " "
                                    + athlete.skiFinalTime
                                    + " ("
                                    + athlete.skiTimeResultUpdated
                                    + ")");
                    writer.newLine();
                }
                if (i == 2) {
                    writer.newLine();
                    writer.write(
                            "Third Place - "
                                    + athlete.athleteName
                                    + " "
                                    + athlete.skiFinalTime
                                    + " ("
                                    + athlete.skiTimeResultUpdated
                                    + ")");
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
