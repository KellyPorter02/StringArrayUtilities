package com.zipcodewilmington;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * Created by leon on 1/29/18.
 */
public class StringArrayUtils {
    /**
     * @param array array of String objects
     * @return first element of specified array
     */ // TODO
    public static String getFirstElement(String[] array) {
        return array[0];
    }

    /**
     * @param array array of String objects
     * @return second element in specified array
     */
    public static String getSecondElement(String[] array) {
        return array[1];
    }

    /**
     * @param array array of String objects
     * @return last element in specified array
     */ // TODO
    public static String getLastElement(String[] array) {
        return array[array.length - 1];
    }

    /**
     * @param array array of String objects
     * @return second to last element in specified array
     */ // TODO
    public static String getSecondToLastElement(String[] array) {
        return array[array.length - 2];
    }

    /**
     * @param array array of String objects
     * @param value value to check array for
     * @return true if the array contains the specified `value`
     */ // TODO
    public static boolean contains(String[] array, String value) {

        for (int i = 0; i < array.length; i++) {
            String currWord = array[i];
            if (currWord.equals(value)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param array of String objects
     * @return an array with identical contents in reverse order
     */ // TODO
    public static String[] reverse(String[] array) {
        // reverse switching first/last

        int rightIdx = array.length - 1;
        for (int leftIdx = 0; leftIdx < array.length / 2; leftIdx++) {
           String leftStr = array[leftIdx];
           String rightStr = array[rightIdx];
           array[leftIdx] = rightStr;
           array[rightIdx] = leftStr;
           rightIdx--;
        }
        return array;
    }

    /**
     * @param array array of String objects
     * @return true if the order of the array is the same backwards and forwards
     */ // TODO
    public static boolean isPalindromic(String[] array) {
       // check ends that they are same

        int end = array.length - 1;
        for (int left = 0; left < array.length / 2; left++) {
            if (!array[left].equals(array[end])) {
                return false;
            }
            end--;
        }
        return true;
    }

    /**
     * @param array array of String objects
     * @return true if each letter in the alphabet has been used in the array
     */ // TODO
    public static boolean isPangramic(String[] array) {
        // must be at least 26 chars long
        // create alphabet string, ignore case
        // if not found, return false
        // add all strings together first
        // ignore case

        String alphaStr = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder arrAsSB = new StringBuilder();
        for (String word: array) {
            arrAsSB.append(word);
        }
        String arrStr = arrAsSB.toString().toLowerCase();

        if (arrAsSB.length() < 26) {
            return false;
        }

        for (int i = 0; i < alphaStr.length(); i++) {
            String currLetter = Character.toString(alphaStr.charAt(i));
            if (!arrStr.contains(currLetter)) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param array array of String objects
     * @param value value to check array for
     * @return number of occurrences the specified `value` has occurred
     */ // TODO
    public static int getNumberOfOccurrences(String[] array, String value) {
        int returnVal = 0;

        for (int i = 0; i < array.length; i++) {
            String currentStr = array[i];
            if (currentStr.equals(value)) {
                returnVal++;
            }
        }
        return returnVal;
    }

    /**
     * @param array         array of String objects
     * @param valueToRemove value to remove from array
     * @return array with identical contents excluding values of `value`
     */ // TODO
    public static String[] removeValue(String[] array, String valueToRemove) {
        // get number of occurneces
        // new array size minus that
        // if curr element equals, skip

        int numOcc = StringArrayUtils.getNumberOfOccurrences(array, valueToRemove);
        String[] removedArr = new String[array.length - numOcc];

        int index = 0;
        for (String currElement: array) {
            if (currElement.equals(valueToRemove)) {
                continue;
            }

            removedArr[index] = currElement;
            index++;
        }
        return removedArr;
    }

    /**
     * @param array array of chars
     * @return array of Strings with consecutive duplicates removes
     */ // TODO
    public static String[] removeConsecutiveDuplicates(String[] array) {
        // if current element is same as previous, don't add
        // first, add first (save value to compare)
        // if curr is same as compare, skip, increment to next
        // if next is same, skip adding, check next
        // if next is diff, add, change compare to current, increment j

        List<String> removedAL = new ArrayList<>();

        int idx = 0;
        String lastAdded = "";
        while (idx < array.length) {

            // first loop - add first element to new list, save value for comparison, increment
            if (idx == 0) {
                removedAL.add(array[idx]);
                lastAdded = array[idx];

                // any other loop but the first - check if currEl is same as last added
            } else {
                String currElement = array[idx];

                // if current element is different than last - add to AL, make lastA equal to curr, increment
                // if same, do nothing
                if (!lastAdded.equals(currElement)) {
                    removedAL.add(currElement);
                    lastAdded = currElement;
                }
            }

            idx++;
        }
        return removedAL.toArray(new String[0]);
    }

    /**
     * @param array array of chars
     * @return array of Strings with each consecutive duplicate occurrence concatenated as a single string in an array of Strings
     */ // TODO
    public static String[] packConsecutiveDuplicates(String[] array) {
        // save firstval - compare to next - same concat
        // dif - add to arrList - make next last added - repeat
        // first loop - start first string, save firstval
        // subsequent loops - if same do this, if dif do that
        // return new array


        List<String> packedArr = new ArrayList<>();

        String lastPackVal = "";
        StringBuilder concatSB = new StringBuilder();

        int loopNb = 1;
        for (String currentEl: array) {

            // if first loop - save 1st value and start SB for concatenating
            if (loopNb == 1) {
                lastPackVal = currentEl;
                concatSB.append(currentEl);
            // if last loop - if same, concat and add; if dif, add SB, add currEl
            } else if (loopNb == array.length) {
                if (lastPackVal.equals(currentEl)) {
                    concatSB.append(currentEl);
                    packedArr.add(concatSB.toString());
                } else {
                    packedArr.add(concatSB.toString());
                    packedArr.add(currentEl);
                }
            // if any other loop - if same, concat; if dif, add SB, make lastPack = currEl, start new SB with currEl
            } else {
                if (lastPackVal.equals(currentEl)) {
                    concatSB.append(currentEl);
                } else {
                    packedArr.add(concatSB.toString());
                    lastPackVal = currentEl;
                    concatSB = new StringBuilder(currentEl);
                }
            }
            loopNb++;
        }

        return packedArr.toArray(new String[0]);
    }


}
