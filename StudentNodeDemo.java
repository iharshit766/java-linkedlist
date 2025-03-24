class StudentNode {
    int rollNo;
    String name;
    int age;
    String grade;
    StudentNode next;

    public StudentNode(int rollNo, String name, int age, String grade) {
        this.rollNo = rollNo;
        this.name = name;
        this.age = age;
        this.grade = grade;
        this.next = null;
    }
}

class StudentLinkedList {
    private StudentNode head;

    public void addAtBeginning(int rollNo, String name, int age, String grade) {
        StudentNode newNode = new StudentNode(rollNo, name, age, grade);
        newNode.next = head;
        head = newNode;
    }

    public void addAtEnd(int rollNo, String name, int age, String grade) {
        StudentNode newNode = new StudentNode(rollNo, name, age, grade);
        if (head == null) {
            head = newNode;
            return;
        }
        StudentNode temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newNode;
    }

    public void addAtPosition(int rollNo, String name, int age, String grade, int position) {
        if (position == 0) {
            addAtBeginning(rollNo, name, age, grade);
            return;
        }
        StudentNode newNode = new StudentNode(rollNo, name, age, grade);
        StudentNode temp = head;
        for (int i = 0; i < position - 1; i++) {
            if (temp == null) {
                System.out.println("Position out of range");
                return;
            }
            temp = temp.next;
        }
        newNode.next = temp.next;
        temp.next = newNode;
    }

    public void deleteByRollNo(int rollNo) {
        if (head != null && head.rollNo == rollNo) {
            head = head.next;
            return;
        }
        StudentNode temp = head, prev = null;
        while (temp != null && temp.rollNo != rollNo) {
            prev = temp;
            temp = temp.next;
        }
        if (temp == null) {
            System.out.println("Student not found");
            return;
        }
        prev.next = temp.next;
    }

    public void searchByRollNo(int rollNo) {
        StudentNode temp = head;
        while (temp != null) {
            if (temp.rollNo == rollNo) {
                System.out.println("Roll No: " + temp.rollNo + ", Name: " + temp.name + ", Age: " + temp.age + ", Grade: " + temp.grade);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Student not found");
    }

    public void updateGrade(int rollNo, String newGrade) {
        StudentNode temp = head;
        while (temp != null) {
            if (temp.rollNo == rollNo) {
                temp.grade = newGrade;
                return;
            }
            temp = temp.next;
        }
        System.out.println("Student not found");
    }

    public void displayAll() {
        StudentNode temp = head;
        while (temp != null) {
            System.out.println("Roll No: " + temp.rollNo + ", Name: " + temp.name + ", Age: " + temp.age + ", Grade: " + temp.grade);
            temp = temp.next;
        }
    }
}

public class StudentNodeDemo {
    public static void main(String[] args) {
        StudentLinkedList sll = new StudentLinkedList();
        sll.addAtEnd(1, "Alice", 20, "A");
        sll.addAtEnd(2, "Bob", 21, "B");
        sll.addAtBeginning(3, "Charlie", 19, "C");
        sll.displayAll();
        sll.updateGrade(2, "A+");
        sll.deleteByRollNo(3);
        sll.searchByRollNo(2);
        sll.displayAll();
    }
}
