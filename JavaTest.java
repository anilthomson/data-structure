import java.io.*;
import java.util.*;
import java.util.Spliterator.OfInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.IntStream.Builder;

class Solution {
  public static void main(String[] args) {
    ArrayList<String> stringsIn = new ArrayList<String>();
    ArrayList<String> stringsOut = new ArrayList<String>();

    // Complete the following test using Java and return your working code. Put your
    // code in only this file. Given a random alpha-numeric string with no special
    // characters, reformat the string without adding or removing any characters so
    // that no alphabet characters are adjacent to any other alphabet characters and
    // no numeric characters are adjacent to any other numeric characters, if
    // possible, and return the modified string. If it is not possible to reformat
    // the string in that way, then group all the alphabet characters at the
    // beginning of the string and all numeric characters at the end of the string.
    // In either case keep all alphabet characters in the same order relative to
    // each other before and after the formatting and also keep all numeric
    // characters in the same order releative to each other before and after the
    // formatting.

    // Example input1: "aabb12cc345"
    // Example output1: "a1a2b3b4c5c"
    // Example input2: "a1aa"
    // Example output2: "aaa1"

    stringsIn.add("aabb12cc345");
    stringsIn.add("a1aa");

    stringsOut.add("a1a2b3b4c5c");
    stringsOut.add("aaa1");

    int numCorrect = 0;

    for (int i = 0; i < stringsIn.size(); i++) {
      String result = reformatString(stringsIn.get(i));
      if (result.equals(stringsOut.get(i))) {
        numCorrect++;
      }
    }

    System.out.println("Correct: " + numCorrect + " out of total: " + stringsIn.size());

    return;
  }

  private static String reformatString(String str) {
    // Add your implementation here
    if (possible(str)) {
      return interleave(str);
    } else {
      return merge(str);
    }
  }

  private static boolean possible(String input) {
    return input.chars().filter(x -> x > 47 && x < 58).toArray().length
        + 1 == input.chars().filter(x -> x > 58).toArray().length;
  }

  private static String merge(String input) {
    IntStream numberstream = input.chars().filter(x -> x > 47 && x < 58);
    IntStream charstream = input.chars().filter(x -> x > 58);
    return IntStream.concat(charstream, numberstream)
        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
  }

  private static String interleave(String input) {
    IntStream numberstream = input.chars().filter(x -> x > 47 && x < 58);
    IntStream charstream = input.chars().filter(x -> x > 58);
    OfInt charspliterator = charstream.spliterator();
    OfInt numspliterator = numberstream.spliterator();
    Builder builder = IntStream.builder();
    while (charspliterator.tryAdvance(builder) && numspliterator.tryAdvance(builder));
    return builder.build().collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
        .toString();
  }
}
