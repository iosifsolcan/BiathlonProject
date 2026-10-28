import org.example.Comparator.SkiFinalTimeComparator;
import org.example.Penalties.BiathlonPenaltyExecutor;
import org.example.Reader.BiathlonFileReader;
import org.example.Writer.BiathlonExecute;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestingBiathlonPenalties {

    @Test
    public void csvParsing() {
        List<BiathlonExecute> list = new ArrayList<>();
        BiathlonFileReader reader1 = new BiathlonFileReader(list);
        String csv =
                "AthleteNumber,AthleteName,CountryCode,SkiTimeResult(Minutes:Seconds),FirstShootingRange,SecondShooting,ThirdShooting\n"
                        + "12,Michael,UK,12:25,xxxox,xxoxo,xxxxx\n"
                        + "5,Daniel,RO,12:22,xxxxx,xxoox,xoxxx\n"
                        + "11,Edward,UK,12:45,xxoxo,xxxxx,xxxox";
        BufferedReader reader = new BufferedReader(new StringReader(csv));
        try {
            reader1.readCsv(reader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        BiathlonPenaltyExecutor executor = new BiathlonPenaltyExecutor(list);
        executor.calculatePenalties();
        assertEquals("Michael", list.get(0).athleteName);
        assertEquals("5", list.get(1).athleteNumber);
        assertEquals("xxoox", list.get(1).secondShootingRange);
        assertEquals("13:15", list.get(2).skiFinalTime);
        assertEquals("Daniel", list.get(1).athleteName);
        assertEquals("Edward", list.get(2).athleteName);

        SkiFinalTimeComparator comparator = new SkiFinalTimeComparator(list);
        comparator.compareSkiFinalTime();

        assertEquals("Daniel", list.get(0).athleteName);
        assertEquals("Michael", list.get(1).athleteName);
        assertEquals("Edward", list.get(2).athleteName);
        assertEquals("12:55", list.get(1).skiFinalTime);
    }

    @Test
    public void testBiathlonPenaltyExecutor() {
        List<BiathlonExecute> athletes = new ArrayList<>();
        BiathlonExecute athlete = new BiathlonExecute(athletes);
        athlete.athleteName = "Piotr Smitzer";
        athlete.firstShootingRange = "xxxxx";
        athlete.secondShootingRange = "xxxxx";
        athlete.thirdShootingRange = "xxxxx";
        athlete.skiTimeResult = "30:10";
        athletes.add(athlete);
        BiathlonExecute athlete1 = new BiathlonExecute(athletes);
        athlete1.athleteName = "Jimmy Smiles";
        athlete1.firstShootingRange = "xxoox";
        athlete1.secondShootingRange = "xooxo";
        athlete1.thirdShootingRange = "xxxxo";
        athlete1.skiTimeResult = "29:15";
        athletes.add(athlete1);
        BiathlonPenaltyExecutor executor = new BiathlonPenaltyExecutor(athletes);
        executor.calculatePenalties();
        assertEquals("30:10", athletes.get(0).skiTimeResult);
        assertEquals("30:10 + 0", athletes.get(0).skiTimeResultUpdated);
        assertEquals("Piotr Smitzer", athletes.get(0).athleteName);
        assertEquals("29:15", athletes.get(1).skiTimeResult);
        assertEquals("29:15 + 60", athletes.get(1).skiTimeResultUpdated);
        assertEquals("Jimmy Smiles", athletes.get(1).athleteName);
        assertEquals("30:15", athletes.get(1).skiFinalTime);
    }

    @ParameterizedTest
    @MethodSource("data")
    void test(
            String firstShootingRange,
            String secondShootingRange,
            String thirdShootingRange,
            String skiTimeResult,
            String expected) {
        BiathlonExecute athlete = new BiathlonExecute(List.of());
        athlete.firstShootingRange = firstShootingRange;
        athlete.secondShootingRange = secondShootingRange;
        athlete.thirdShootingRange = thirdShootingRange;
        athlete.skiTimeResult = skiTimeResult;
        BiathlonExecute executor = new BiathlonPenaltyExecutor(List.of(athlete));
        executor.calculatePenalties();
        assertEquals(expected, athlete.skiFinalTime);
    }

    static Stream<Arguments> data() {
        return Stream.of(
                Arguments.of("xxxxx", "xoxxx", "xooxx", "10:00", "10:30"),
                Arguments.of("xxoox", "xxxxo", "xoxxo", "11:00", "11:50"));
    }
}
