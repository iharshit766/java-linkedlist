class ProcessNode {
    int processId;
    int burstTime;
    int priority;
    ProcessNode next;

    public ProcessNode(int processId, int burstTime, int priority) {
        this.processId = processId;
        this.burstTime = burstTime;
        this.priority = priority;
        this.next = null;
    }
}

class RoundRobinScheduler {
    private ProcessNode head = null;
    private ProcessNode tail = null;
    private int timeQuantum;

    public RoundRobinScheduler(int timeQuantum) {
        this.timeQuantum = timeQuantum;
    }

    public void addProcess(int processId, int burstTime, int priority) {
        ProcessNode newNode = new ProcessNode(processId, burstTime, priority);
        if (head == null) {
            head = tail = newNode;
            tail.next = head; // Circular link
        } else {
            tail.next = newNode;
            tail = newNode;
            tail.next = head; // Maintain circular structure
        }
    }

    public void removeProcess(int processId) {
        if (head == null) {
            System.out.println("No processes in the queue.");
            return;
        }

        ProcessNode current = head, prev = null;
        do {
            if (current.processId == processId) {
                if (current == head) {
                    tail.next = head.next;
                    head = head.next;
                } else {
                    prev.next = current.next;
                    if (current == tail) {
                        tail = prev;
                    }
                }
                System.out.println("Process " + processId + " removed after execution.");
                return;
            }
            prev = current;
            current = current.next;
        } while (current != head);

        System.out.println("Process not found.");
    }

    public void simulateRoundRobin() {
        if (head == null) {
            System.out.println("No processes to schedule.");
            return;
        }

        ProcessNode current = head;
        while (true) {
            System.out.println("Executing Process ID: " + current.processId + " for " + Math.min(current.burstTime, timeQuantum) + " units.");
            current.burstTime -= timeQuantum;
            if (current.burstTime <= 0) {
                int completedProcessId = current.processId;
                removeProcess(completedProcessId);
                if (current == tail) break; // If last process executed
            }
            current = current.next;
        }
    }

    public void displayProcesses() {
        if (head == null) {
            System.out.println("No processes to display.");
            return;
        }

        ProcessNode temp = head;
        do {
            System.out.println("Process ID: " + temp.processId + ", Burst Time: " + temp.burstTime + ", Priority: " + temp.priority);
            temp = temp.next;
        } while (temp != head);
    }
}

public class RoundRobinDemo {
    public static void main(String[] args) {
        RoundRobinScheduler scheduler = new RoundRobinScheduler(4);
        scheduler.addProcess(1, 10, 2);
        scheduler.addProcess(2, 5, 1);
        scheduler.addProcess(3, 8, 3);

        System.out.println("Processes in the scheduler:");
        scheduler.displayProcesses();

        System.out.println("Starting Round Robin Scheduling:");
        scheduler.simulateRoundRobin();
    }
}
