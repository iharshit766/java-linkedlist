import java.util.Scanner;

class Ticket {
    int ticketID;
    String customerName;
    String movieName;
    int seatNumber;
    String bookingTime;
    Ticket next;

    public Ticket(int ticketID, String customerName, String movieName, int seatNumber, String bookingTime) {
        this.ticketID = ticketID;
        this.customerName = customerName;
        this.movieName = movieName;
        this.seatNumber = seatNumber;
        this.bookingTime = bookingTime;
        this.next = null;
    }
}

class TicketReservationSystem {
    private Ticket last;
    private int count;

    public TicketReservationSystem() {
        this.last = null;
        this.count = 0;
    }

    public void addTicket(int ticketID, String customerName, String movieName, int seatNumber, String bookingTime) {
        Ticket newTicket = new Ticket(ticketID, customerName, movieName, seatNumber, bookingTime);
        if (last == null) {
            last = newTicket;
            last.next = last;
        } else {
            newTicket.next = last.next;
            last.next = newTicket;
            last = newTicket;
        }
        count++;
    }

    public void removeTicket(int ticketID) {
        if (last == null) {
            System.out.println("No tickets to remove.");
            return;
        }
        Ticket current = last.next, prev = last;
        do {
            if (current.ticketID == ticketID) {
                if (current == last && current.next == last) {
                    last = null;
                } else {
                    prev.next = current.next;
                    if (current == last) {
                        last = prev;
                    }
                }
                count--;
                System.out.println("Ticket removed successfully.");
                return;
            }
            prev = current;
            current = current.next;
        } while (current != last.next);
        System.out.println("Ticket ID not found.");
    }

    public void displayTickets() {
        if (last == null) {
            System.out.println("No tickets booked.");
            return;
        }
        Ticket current = last.next;
        do {
            System.out.println("Ticket ID: " + current.ticketID + ", Customer: " + current.customerName + ", Movie: " + current.movieName + ", Seat: " + current.seatNumber + ", Time: " + current.bookingTime);
            current = current.next;
        } while (current != last.next);
    }

    public void searchTicket(String query) {
        if (last == null) {
            System.out.println("No tickets booked.");
            return;
        }
        Ticket current = last.next;
        boolean found = false;
        do {
            if (current.customerName.equalsIgnoreCase(query) || current.movieName.equalsIgnoreCase(query)) {
                System.out.println("Found - Ticket ID: " + current.ticketID + ", Customer: " + current.customerName + ", Movie: " + current.movieName + ", Seat: " + current.seatNumber + ", Time: " + current.bookingTime);
                found = true;
            }
            current = current.next;
        } while (current != last.next);
        if (!found) {
            System.out.println("No tickets found for given query.");
        }
    }

    public int getTotalTickets() {
        return count;
    }
}

public class TicketReservationDemo {
    public static void main(String[] args) {
        TicketReservationSystem system = new TicketReservationSystem();
        system.addTicket(101, "Alice", "Inception", 12, "10:00 AM");
        system.addTicket(102, "Bob", "Interstellar", 15, "12:00 PM");
        system.displayTickets();
        system.searchTicket("Alice");
        system.removeTicket(101);
        system.displayTickets();
        System.out.println("Total tickets booked: " + system.getTotalTickets());
    }
}
