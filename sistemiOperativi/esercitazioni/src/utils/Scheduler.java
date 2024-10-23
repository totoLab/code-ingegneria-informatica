package utils;

import java.util.*;
import java.util.concurrent.TimeUnit;

public abstract class Scheduler {
    protected List<Process> processes;
    protected Queue<Process> readyQueue = new LinkedList<>();
    protected Process currentProcess = null;
    protected int currentTime = -1;

    // Main scheduling method to be called by all subclasses
    public void schedule(List<Process> processes) {
        this.processes = processes;
        this.readyQueue = initializeQueue(); // Let subclasses decide the queue type

        while (!processes.isEmpty() || !readyQueue.isEmpty() || currentProcess != null) {
            currentTime++;

            // Add newly arrived processes to the ready queue
            Iterator<Process> processIterator = processes.iterator();
            while (processIterator.hasNext()) {
                Process p = processIterator.next();
                if (p.arrivalTime == currentTime) {
                    readyQueue.add(p);
                    System.out.println(currentTime + ": New process in queue: " + p);
                    processIterator.remove(); // Remove from original list once added to the queue
                }
            }

            // Select the next process to run based on the specific scheduler logic
            if (shouldSwitchProcess()) {
                if (currentProcess != null && currentProcess.cpuBurst > 0) {
                    readyQueue.add(currentProcess); // Add the current process back if not finished
                }
                currentProcess = selectNextProcess(); // Let subclasses select the next process
                resetQuantumOrCounters();
            }

            // Execute the selected process
            if (currentProcess != null) {
                executeBurst(currentProcess);
                System.out.printf("(%d) Executing %s\n", currentTime, currentProcess);
            } else {
                System.out.printf("(%d) No process is running\n", currentTime);
            }

            waitOneSecond(); // Simulate the passage of time
        }
    }

    protected abstract Process selectNextProcess();

    protected abstract boolean shouldSwitchProcess();

    protected void resetQuantumOrCounters() {}

    protected void executeBurst(Process process) {
        if (process != null) {
            process.cpuBurst--;
        }
    }

    protected Queue<Process> initializeQueue() {
        return new LinkedList<>();
    }

    private void waitOneSecond() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void test1() {
        List<Process> p = new ArrayList<>();
        p.add(new Process("P1", 0, 20));
        p.add(new Process("P2", 4, 15));
        p.add(new Process("P3", 8, 9));
        schedule(p);
    }

    public void test2() {
        List<Process> p = new ArrayList<>();
        p.add(new Process("P1", 0, 17));
        p.add(new Process("P2", 3, 14));
        p.add(new Process("P3", 4, 10));
        p.add(new Process("P4", 9, 4));
        p.add(new Process("P5", 11, 12));
        schedule(p);
    }
}
