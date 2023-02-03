package com.mycompany.ceng113_hw3;

import java.util.Scanner;

/*
The Volcano Corollary ☠️☠️

Goals
1. Calculate how many cells are filled with the hot lava for a given rock formation (as a 1D int array) in a valley.
2. Write an int returning method called puddle in Java getting a one-dimensional int array.

----------------------------------------------------------------------

Example Run #1:
Enter 10 numbers representing the rock formations in the valley:
8 3 2 1 6 0 7 3 2 5
There can be maximum observable amount of hot lava is (in cells): 28


Example Run #2:
Enter 10 numbers representing the rock formations in the valley:
5 3 4 4 6 8 0 8 5 7
There can be maximum observable amount of hot lava is (in cells): 14


Example Run #3:
Enter 10 numbers representing the rock formations in the valley:
10 5 0 9 8 1 0 2 3 7
There can be maximum observable amount of hot lava is (in cells): 35


Example Run #4:
Enter 10 numbers representing the rock formations in the valley:
7 1 2 10 8 1 7 2 3 5
There can be maximum observable amount of hot lava is (in cells): 22


Example Run #5:
Enter 10 numbers representing the rock formations in the valley:
3 0 0 8 1 2 3 0 0 7
There can be maximum observable amount of hot lava is (in cells): 35

----------------------------------------------------------------------

Figure 1:

R ---> represents rock formations that we prompted from the user
L ---> represents lava blocks

R 
R L L L L L R
R L L L R L R     
R L L L R L R L L R
R L L L R L R L L R
R R L L R L R R L R
R R R L R L R R R R
R R R R R L R R R R

----------------------------------------------------------------------

Figure 2:

          R L R 
          R L R L R
        R R L R L R
R L L L R R L R R R
R L R R R R L R R R
R R R R R R L R R R
R R R R R R L R R R
R R R R R R L R R R
 */
public class CENG113_HW3 {

    public static int puddle(int[] rocks) {
        int amountOfLava = 0;
        int leftIndex = 0;
        int rightIndex = findRightIndex(leftIndex, rocks);

        while (true) {
            int currentValue = Math.min(rocks[leftIndex], rocks[rightIndex]);
            for (int i = leftIndex + 1; i < rightIndex; i++) {
                amountOfLava += currentValue - rocks[i];
            }
            if (rightIndex == rocks.length - 1) {
                break;
            }
            leftIndex = rightIndex;
            rightIndex = findRightIndex(leftIndex, rocks);
        }
        return amountOfLava;
    }

    public static int findRightIndex(int fromIndex, int[] array) {
        // 1) Return the very first index that satisifies array[fromIndex] < array[i].
        for (int i = fromIndex; i < array.length; i++) {
            if (array[fromIndex] < array[i]) {
                return i;
            }
        }

        // 2) If there is no any greater element than array[fromIndex], return the index of the maximum element in the sub array.
        // sub array ---> elements between (fromIndex + 1)th and (array.length -1)th index (included)
        int max = array[fromIndex + 1]; // Assumption
        int indexOfMax = fromIndex + 1; // Assumption
        for (int i = fromIndex + 1; i < array.length; i++) {
            if (max < array[i]) {
                max = array[i];
                indexOfMax = i;
            }
        }
        return indexOfMax;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter 10 numbers representing the rock formations in the valley:");
        int[] rocks = new int[10];
        for (int i = 0; i < rocks.length; i++) {
            rocks[i] = input.nextInt();
        }
        input.close();
        System.out.println("There can be maximum observable amount of hot lava is (in cells): " + puddle(rocks));
    }
}
