import java.util.ArrayList;
import java.util.Collections;

public class MontyHallParadox {

    private static final int NUM_DOORS = 3;

    private ArrayList<Integer> doors;
    private int chosenDoor;

    public MontyHallParadox() {
        this.doors = new ArrayList<>();
        for (int i = 1; i <= NUM_DOORS; i++) {
            this.doors.add(i);
        }
        Collections.shuffle(this.doors);
        this.chosenDoor = -1;
    }

    public void chooseDoor(int doorNum) {
        if (doorNum < 1 || doorNum > NUM_DOORS) {
            throw new IllegalArgumentException("Invalid door number");
        }
        this.chosenDoor = doorNum;
    }

    public int revealGoat() {
        int revealDoor;
        do {
            revealDoor = (int) (Math.random() * NUM_DOORS) + 1;
        } while (revealDoor == this.chosenDoor || this.doors.get(revealDoor - 1) == 1);
        return revealDoor;
    }

    public boolean switchDoor() {
        int unchosenDoor = -1;
        for (int i = 1; i <= NUM_DOORS; i++) {
            if (i != this.chosenDoor && this.doors.get(i - 1) == 0) {
                unchosenDoor = i;
                break;
            }
        }
        return unchosenDoor != -1;
    }

    public boolean win() {
        return this.doors.get(this.chosenDoor - 1) == 1;
    }

    public static void main(String[] args) {
        MontyHallParadox game = new MontyHallParadox();
        game.chooseDoor(1);
        int revealedDoor = game.revealGoat();
        System.out.println("Door " + revealedDoor + " has a goat.");
        boolean switchDoor = game.switchDoor();
        if (switchDoor) {
            int unchosenDoor = game.switchDoor() ? game.chosenDoor : revealedDoor;
            game.chooseDoor(unchosenDoor);
            System.out.println("Switched to door " + unchosenDoor + ".");
        }
        System.out.println("You " + (game.win() ? "win!" : "lose."));
    }
}