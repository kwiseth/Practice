package com.alamedatechlab.googtest;

import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * NeedlesHaystackTest is a driver for the Google writing test sample "findNeedles()" method.
 *
 * @author Kelli Wiseth 2019 Revised the original Google findNeedles() method to accept an array of
 *     words and a locally stored textfile. Added methods to let users choose the size of the array
 *     (up to 5) and validate entries.
 * @param console Scanner object that accepts input from user, such as number of words to search for
 *     and the words themselves.
 */
public class NeedlesHaystackTest {

  /**
   * Verifies that the string entered at console is an integer between 1 and 5.
   *
   * @param validEntries Hard-coded array of allowable entries.
   * @param valueToCheck String entered at console.
   */
  static boolean checkEntry(int[] validEntries, int valueToCheck) {
    boolean test = false;
    for (int element : validEntries) {
      if (element == valueToCheck) {
        test = true;
      }
    }
    return test;
  } // public boolean checkEntry method

  /**
   * Finds and counts the number of each specified word (needle) (if found) that exist in the
   * specified file (haystack). Haystack is a plaintext file stored locally. Needles are up to 5
   * words entered at prompts on the command line.
   *
   * @param haystack String object comprising the plaintext from text file.
   * @param needles An array containing five String objects.
   * @see <a
   *     href="https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/String.html">
   *     java.lang.String</a>
   */
  public static void findNeedles(String haystack, String[] needles) {
    int[] counter = new int[needles.length];
    String[] words = haystack.toLowerCase().split("[ \"\'\t\n\b\f\r]", 0);
    for (int i = 0; i < needles.length; i++) {
      //  String[] words = haystack.toLowerCase().split("[ \"\'\t\n\b\f\r]", 0);
      for (int j = 0; j < words.length; j++) {
        if (words[j].compareTo(needles[i].toLowerCase()) == 0) {
          counter[i]++;
        }
      }
    }
    for (int j = 0; j < needles.length; j++) {
      System.out.println(needles[j] + ": " + counter[j]);
    }
  } // findNeedles method

  /**
   * Launches program and prompts user for three pieces of variable input: the number of words
   * (needles) in total that user wants to find; the specific words; and the name of the local text
   * file (haystack) to search through.
   */
  public static void main(String[] args) throws IOException, InputMismatchException {

    int[] validEntries = {1, 2, 3, 4, 5};
    String[] textValues = {"first", "second", "third", "fourth", "fifth"};
    System.out.println("*******************************************************");
    System.out.println("This console program accepts up to 5 words ('needles') to ");
    System.out.println("find in the file ('haystack') you specify.");
    System.out.println("*******************************************************");
    System.out.print("How many words do you want to search for? (5 max) : ");
    System.out.flush();

    Scanner console = new Scanner(System.in);
    try {

      // int needleCount = validateInput(rawInput);
      int needleCount = console.nextInt();
      boolean goodEntry = checkEntry(validEntries, needleCount);
      while (!goodEntry) {
        System.out.print("Please enter a number from 1 to 5 only: ");
        needleCount = console.nextInt();
        goodEntry = checkEntry(validEntries, needleCount);
      }
      String[] inputWords = new String[needleCount];

      for (int i = 0; i < inputWords.length; i++) {
        System.out.print("Type the " + textValues[i] + " word you want to find in the haystack: ");
        inputWords[i] = console.next();
      }
      String[] needles = inputWords;
      System.out.print("What is the name of the file (haystack)? : ");
      String inputFileName = console.next().trim();
      FileReader reader = new FileReader(inputFileName);
      Scanner in = new Scanner(reader);
      StringBuilder haystackBldr = new StringBuilder();

      while (in.hasNextLine()) {
        String line = in.nextLine();
        haystackBldr.append(line);
      }

      String haystack = haystackBldr.toString();

      findNeedles(haystack, needles);
      in.close();
      console.close();
    } catch (IOException exception) {
      System.out.println("Haystack file missing or other file error." + exception.toString());
    } catch (InputMismatchException e) {
      System.out.println("Numeric data required." + e.toString());
    }
  } // main method closing brace
} // public class NeedlesHaystackTest closing brace
