package org.example.RunApp;

import org.example.Writer.BiathlonExecute;
import org.example.Penalties.BiathlonPenaltyExecutor;
import org.example.Comparator.SkiFinalTimeComparator;
import org.example.Reader.BiathlonFileReader;

import java.util.ArrayList;
import java.util.List;

public class RunApp {
    private List<BiathlonExecute> list = new ArrayList<>();

    public void runApp() {
        BiathlonExecute reader = new BiathlonFileReader(list);
        reader.readFile();
        BiathlonExecute executor = new BiathlonPenaltyExecutor(list);
        executor.calculatePenalties();
        BiathlonExecute comparator = new SkiFinalTimeComparator(list);
        comparator.compareSkiFinalTime();
        comparator.printPlacement();
    }
}
