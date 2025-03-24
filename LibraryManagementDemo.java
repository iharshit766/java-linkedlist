class BookNode {
    String title;
    String author;
    String genre;
    int bookId;
    boolean isAvailable;
    BookNode next;
    BookNode prev;

    public BookNode(String title, String author, String genre, int bookId, boolean isAvailable) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.bookId = bookId;
        this.isAvailable = isAvailable;
        this.next = null;
        this.prev = null;
    }
}

class LibraryDoublyLinkedList {
    private BookNode head = null;
    private BookNode tail = null;
    private int count = 0;

    public void addAtBeginning(String title, String author, String genre, int bookId, boolean isAvailable) {
        BookNode newNode = new BookNode(title, author, genre, bookId, isAvailable);
        if (head == null) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        count++;
    }

    public void addAtEnd(String title, String author, String genre, int bookId, boolean isAvailable) {
        BookNode newNode = new BookNode(title, author, genre, bookId, isAvailable);
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        count++;
    }

    public void removeBook(int bookId) {
        if (head == null) {
            System.out.println("Library is empty.");
            return;
        }

        BookNode temp = head;
        while (temp != null) {
            if (temp.bookId == bookId) {
                if (temp == head) {
                    head = head.next;
                    if (head != null) head.prev = null;
                } else if (temp == tail) {
                    tail = tail.prev;
                    if (tail != null) tail.next = null;
                } else {
                    temp.prev.next = temp.next;
                    temp.next.prev = temp.prev;
                }
                count--;
                System.out.println("Book removed successfully.");
                return;
            }
            temp = temp.next;
        }
        System.out.println("Book not found.");
    }

    public void searchByTitleOrAuthor(String query) {
        BookNode temp = head;
        boolean found = false;
        while (temp != null) {
            if (temp.title.equalsIgnoreCase(query) || temp.author.equalsIgnoreCase(query)) {
                System.out.println("Book ID: " + temp.bookId + ", Title: " + temp.title + ", Author: " + temp.author + ", Genre: " + temp.genre + ", Available: " + temp.isAvailable);
                found = true;
            }
            temp = temp.next;
        }
        if (!found) {
            System.out.println("No books found.");
        }
    }

    public void updateAvailability(int bookId, boolean isAvailable) {
        BookNode temp = head;
        while (temp != null) {
            if (temp.bookId == bookId) {
                temp.isAvailable = isAvailable;
                System.out.println("Availability updated successfully.");
                return;
            }
            temp = temp.next;
        }
        System.out.println("Book not found.");
    }

    public void displayAllBooks() {
        BookNode temp = head;
        while (temp != null) {
            System.out.println("Book ID: " + temp.bookId + ", Title: " + temp.title + ", Author: " + temp.author + ", Genre: " + temp.genre + ", Available: " + temp.isAvailable);
            temp = temp.next;
        }
    }

    public void displayReverse() {
        BookNode temp = tail;
        while (temp != null) {
            System.out.println("Book ID: " + temp.bookId + ", Title: " + temp.title + ", Author: " + temp.author + ", Genre: " + temp.genre + ", Available: " + temp.isAvailable);
            temp = temp.prev;
        }
    }

    public int countBooks() {
        return count;
    }
}

public class LibraryManagementDemo {
    public static void main(String[] args) {
        LibraryDoublyLinkedList library = new LibraryDoublyLinkedList();
        library.addAtEnd("The Great Gatsby", "F. Scott Fitzgerald", "Fiction", 101, true);
        library.addAtBeginning("1984", "George Orwell", "Dystopian", 102, true);
        library.addAtEnd("To Kill a Mockingbird", "Harper Lee", "Classic", 103, false);
        
        System.out.println("All books in the library:");
        library.displayAllBooks();
        
        System.out.println("Total books: " + library.countBooks());
        
        System.out.println("Searching for books by 'George Orwell':");
        library.searchByTitleOrAuthor("George Orwell");
        
        System.out.println("Updating availability of book ID 103:");
        library.updateAvailability(103, true);
        
        System.out.println("Removing book with ID 102:");
        library.removeBook(102);
        
        System.out.println("Displaying books in reverse order:");
        library.displayReverse();
    }
}
