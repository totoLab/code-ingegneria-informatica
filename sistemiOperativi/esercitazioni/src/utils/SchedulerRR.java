package utils;

import java.util.Queue;
import java.util.LinkedList;

public class SchedulerRR extends Scheduler {

    private final int quantum;
    private int timeSliceCounter = 0;

    public SchedulerRR(int quantum) {
        this.quantum = quantum;
    }

    @Override
    protected boolean shouldSwitchProcess() {
        return currentProcess == null || timeSliceCounter == quantum || currentProcess.cpuBurst == 0;
    }

    @Override
    protected Process selectNextProcess() {
        timeSliceCounter = 0;
        return readyQueue.poll();
    }

    @Override
    protected void resetQuantumOrCounters() {
        timeSliceCounter = 0; // Reset the counter for RR
    }

    public static void main(String[] args) {
        Scheduler scheduler = new SchedulerRR(8);
        scheduler.test2();
    }
}
