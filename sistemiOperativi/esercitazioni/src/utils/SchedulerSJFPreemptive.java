package utils;

import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.Queue;

public class SchedulerSJFPreemptive extends Scheduler {

    @Override
    protected Queue<Process> initializeQueue() {
        // Use a priority queue to always select the process with the shortest remaining burst
        return new PriorityQueue<>(Comparator.comparingInt(p -> p.cpuBurst));
    }

    @Override
    protected boolean shouldSwitchProcess() {
        // Switch if current process finished or there's a process with a shorter remaining time
        return currentProcess == null || currentProcess.cpuBurst == 0 ||
                (!readyQueue.isEmpty() && readyQueue.peek().cpuBurst < currentProcess.cpuBurst);
    }

    @Override
    protected Process selectNextProcess() {
        return readyQueue.poll();
    }

    public static void main(String[] args) {
        Scheduler scheduler = new SchedulerSJFPreemptive();
        scheduler.test1();
    }
}
