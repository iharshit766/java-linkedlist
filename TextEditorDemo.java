import java.util.LinkedList;

class TextState {
    String content;
    TextState prev;
    TextState next;

    public TextState(String content) {
        this.content = content;
        this.prev = null;
        this.next = null;
    }
}

class TextEditor {
    private TextState current;
    private int historySize;
    private int stateCount;

    public TextEditor(int historySize) {
        this.historySize = historySize;
        this.stateCount = 0;
        this.current = new TextState("");
    }

    public void addState(String newText) {
        TextState newState = new TextState(newText);
        newState.prev = current;
        if (current != null) {
            current.next = newState;
        }
        current = newState;
        stateCount++;
        if (stateCount > historySize) {
            removeOldestState();
        }
    }

    private void removeOldestState() {
        TextState temp = current;
        while (temp.prev != null && temp.prev.prev != null) {
            temp = temp.prev;
        }
        if (temp.prev != null) {
            temp.prev = null;
        }
        stateCount--;
    }

    public void undo() {
        if (current.prev != null) {
            current = current.prev;
        } else {
            System.out.println("No more undo available.");
        }
    }

    public void redo() {
        if (current.next != null) {
            current = current.next;
        } else {
            System.out.println("No more redo available.");
        }
    }

    public void displayCurrentState() {
        System.out.println("Current Text: " + current.content);
    }
}

public class TextEditorDemo {
    public static void main(String[] args) {
        TextEditor editor = new TextEditor(10);
        editor.addState("Hello");
        editor.addState("Hello, World!");
        editor.displayCurrentState();

        editor.undo();
        editor.displayCurrentState();

        editor.redo();
        editor.displayCurrentState();
    }
}
