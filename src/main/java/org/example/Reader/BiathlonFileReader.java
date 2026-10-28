package org.example.Reader;


import org.example.Writer.BiathlonExecute;

import java.io.*;

import java.util.List;

public class BiathlonFileReader extends BiathlonExecute {
    private List<BiathlonExecute> athleteList;

    public BiathlonFileReader(List<BiathlonExecute> list) {
        super(list);
        this.athleteList = list;
    }

    private String line;
    private String[] athleteData;

    @Override
    public void readFile() {
        super.readFile();
        InputStream input = getClass().getClassLoader().getResourceAsStream("biathlon_results.csv");

        if (input == null) {
            System.out.println("File not found!");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                if (!line.isBlank()) {
                    BiathlonExecute athlete = new BiathlonExecute(athleteList);
                    athleteData = line.split(",");
                    athlete.athleteNumber = athleteData[0];
                    athlete.athleteName = athleteData[1];
                    athlete.countryCode = athleteData[2];
                    athlete.skiTimeResult = athleteData[3];
                    athlete.firstShootingRange = athleteData[4];
                    athlete.secondShootingRange = athleteData[5];
                    athlete.thirdShootingRange = athleteData[6];
                    athleteList.add(athlete);
                }
            }

        } catch (IOException e) {
            System.out.println("Error reading the file: ");
        }
    }

    public void readCsv(BufferedReader reader) throws IOException {

        reader.readLine();
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");

            BiathlonExecute athlete = new BiathlonExecute(athleteList);
            athlete.athleteNumber = parts[0];
            athlete.athleteName = parts[1];
            athlete.countryCode = parts[2];
            athlete.skiTimeResult = parts[3];
            athlete.firstShootingRange = parts[4];
            athlete.secondShootingRange = parts[5];
            athlete.thirdShootingRange = parts[6];

            athleteList.add(athlete);
        }
    }
}
