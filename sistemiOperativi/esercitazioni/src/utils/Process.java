package utils;

public class Process {
    String name;
    int arrivalTime;
    int cpuBurst;

    public Process(String name, int arrivalTime, int cpuBurst) {
        this.name = name;
        this.arrivalTime = arrivalTime;
        this.cpuBurst = cpuBurst;
    }

    @Override
    public String toString() {
        return String.format("%s: %d CPU bursts remaining", name, cpuBurst);
    }
}
