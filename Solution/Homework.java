import stanford.karel.SuperKarel;

public class Homework extends SuperKarel {
    /* You fill the code here */
    int rows = 1, cols = 1, cnt = 0;

    public void faceEast() { //makes karel face east
        while (notFacingEast())
            turnLeft();
    }

    public void faceWest() { //makes karel face west
        while (notFacingWest())
            turnLeft();
    }

    public void faceNorth() { //makes karel face north
        while (notFacingNorth())
            turnLeft();
    }

    public void faceSouth() { //makes karel face south
        while (notFacingSouth())
            turnLeft();
    }

    public void countDimensions() { //counts the number of columns then counts the number of rows
        faceEast();
        while (!frontIsBlocked()) {
            move();
            cols++;
            cnt++;
        }
        faceNorth();
        while (!frontIsBlocked()) {
            move();
            rows++;
            cnt++;
        }
    }

    public boolean isEven(int x) { //checks if the number is even or not
        return x % 2 == 0;
    }


    public void moveClearBeeper() { //moves forward till karel reaches the wall while laying down beepers in empty areas
        while (frontIsClear()) {
            if (!beepersPresent())
                putBeeper();
            move();
            cnt++;
        }
        if (!beepersPresent())
            putBeeper();
    }

    public void moveXBeeper(int x) { // moves x amount of steps while laying down beepers in empty areas
        for (int i = 0; i != x; i++) {
            if (!beepersPresent())
                putBeeper();
            move();
            cnt++;
        }
        if (!beepersPresent())
            putBeeper();
    }

    public void moveX(int x) { //moves x amount of steps
        while (x-- != 0) {
            move();
            cnt++;
        }
    }

    public void drawCross() { // draws the cross for the outer chambers
        faceSouth();
        int halfr = rows / 2, halfc = cols / 2;
        if (isEven(rows) && isEven(cols)) { // case #1
            moveX(halfr - 1);
            faceWest();
            moveXBeeper(halfc - 1);
            faceNorth();
            moveXBeeper(halfr - 1);
            faceWest();
            moveX(1);
            faceSouth();
            moveXBeeper(halfr - 1);
            faceWest();
            moveXBeeper(halfc - 1);
            faceSouth();
            moveX(1);
            faceEast();
            moveXBeeper(halfc - 1);
            faceSouth();
            moveXBeeper(halfr - 1);
            faceEast();
            moveX(1);
            faceNorth();
            moveXBeeper(halfr - 1);
            faceEast();
            moveXBeeper(halfc - 1);
        } else if (!isEven(rows) && !isEven(cols)) { // case #2
            moveX(halfr);
            faceWest();
            moveClearBeeper();
            faceEast();
            moveX(halfc);
            faceNorth();
            moveClearBeeper();
            faceSouth();
            moveClearBeeper();
        } else if (!isEven(rows) && isEven(cols)) { // case #3
            moveX(halfr);
            faceWest();
            moveClearBeeper();
            faceEast();
            moveX(halfc - 1);
            faceNorth();
            moveClearBeeper();
            faceEast();
            moveX(1);
            faceSouth();
            moveClearBeeper();
            faceWest();
            moveX(1);
            faceNorth();
            moveXBeeper(halfr);
        } else { // case #4
            faceWest();
            moveX(halfc);
            faceSouth();
            moveClearBeeper();
            faceNorth();
            moveX(halfr - 1);
            faceEast();
            moveClearBeeper();
            faceNorth();
            moveX(1);
            faceWest();
            moveClearBeeper();
            faceSouth();
            moveX(1);
            faceEast();
            moveXBeeper(halfc);
        }
    }

    public void drawSquare() { // draws the inner chamber rectangle
        int height = Math.min(rows, cols) - 2;
        int halfr = rows / 2, halfc = cols / 2;
        if (isEven(rows) && isEven(cols)) { // case #1
            faceWest();
            moveX(halfc - height / 2);
            faceSouth();
            moveXBeeper(height / 2 - 1);
            faceWest();
            moveXBeeper(height - 1);
            faceNorth();
            moveXBeeper(height - 1);
            faceEast();
            moveXBeeper(height - 1);
            faceSouth();
            moveXBeeper(height / 2 - 2);
        } else if (!isEven(rows) && !isEven(cols)) { // case #2
            faceNorth();
            moveX(halfr - height / 2);
            faceEast();
            moveXBeeper(height / 2);
            faceNorth();
            moveXBeeper(height - 1);
            faceWest();
            moveXBeeper(height - 1);
            faceSouth();
            moveXBeeper(height - 1);
            faceEast();
            moveXBeeper(height / 2 - 1);
        } else if (!isEven(rows) && isEven(cols)) { // case #3
            height = ((height - 1) / 2);
            faceWest();
            moveX(height);
            faceNorth();
            moveXBeeper(height);
            faceEast();
            moveXBeeper(height * 2 + 1);
            faceSouth();
            moveXBeeper(height * 2);
            faceWest();
            moveXBeeper(height * 2 + 1);
            faceNorth();
            moveXBeeper(height - 1);
        } else { // case #4
            height = ((height - 1) / 2);
            moveX(height);
            faceSouth();
            moveXBeeper(height);
            faceWest();
            moveXBeeper(height * 2);
            faceNorth();
            moveXBeeper(height * 2 + 1);
            faceEast();
            moveXBeeper(height * 2);
            faceSouth();
            moveXBeeper(height - 1);
        }
    }

    public void run() {
        rows = 1; // initialize the rows and cols and cnt all again, so I can run Karel without having to stop it
        cols = 1;
        cnt = 0;
        setBeepersInBag(2500); // set the amount of Beepers in the bag
        countDimensions();
        if ((rows > 6 && cols > 6)) { // checks if it is possible for Karel to make the 4+4 division or not
            drawCross();
            drawSquare();
            System.out.println(cnt); // prints the number of steps that Karel took to complete the division
        } else {
            System.out.println("These dimensions are too small for karel to work");
        }
    }
}