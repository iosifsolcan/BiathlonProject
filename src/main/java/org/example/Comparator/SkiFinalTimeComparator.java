package org.example.Comparator;

import org.example.Writer.BiathlonExecute;

import java.util.List;

public class SkiFinalTimeComparator extends BiathlonExecute {
    private List<BiathlonExecute> list;

    public SkiFinalTimeComparator(List<BiathlonExecute> list) {
        super(list);
        this.list = list;
    }

    @Override
    public void compareSkiFinalTime() {
        super.compareSkiFinalTime();
        athletes.sort(java.util.Comparator.comparing(athlete -> athlete.skiFinalTime));
    }
}
