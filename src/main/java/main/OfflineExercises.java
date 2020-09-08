package main;


import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Stream;

public class OfflineExercises {

	// Given a string, return a string where
	// for every char in the original string,
	// there are three chars.

	// multChar("The") returns "TTThhheee"
	// multChar("AAbb") returns "AAAAAAbbbbbb"
	// multChar("Hi-There") returns "HHHiii---TTThhheeerrreee"

	public String multChar(String input) {
		StringBuilder finalStr= new StringBuilder();
		for(char c: input.toCharArray()){
			finalStr.append(c);
			finalStr.append(c);
			finalStr.append(c);
		}
		return finalStr.toString();
	}

	// Return the string (backwards) that is between the first and last appearance
	// of "bert"
	// in the given string, or return the empty string "" if there is not 2
	// occurances of "bert" - Ignore Case

	// getBert("bertclivebert") returns "evilc"
	// getBert("xxbertfridgebertyy") returns "egdirf"
	// getBert("xxBertfridgebERtyy") returns "egdirf"
	// getBert("xxbertyy") returns ""
	// getBert("xxbeRTyy") returns ""

	public String getBert(String input) {
		final int WORD_SIZE = 4; // bert = 4
		String resultStr ="";
		int beginIndex = input.toLowerCase().indexOf("bert");
		if(beginIndex!=-1){
			String tempStr = input.substring(beginIndex+4);
			int endIndex = tempStr.toLowerCase().indexOf("bert");
			if(endIndex!=-1){
				StringBuilder sb = new StringBuilder( input.substring(beginIndex+WORD_SIZE, beginIndex+endIndex+WORD_SIZE));
				resultStr = sb.reverse().toString();
			}
		}
		return resultStr;
	}

	// Given three ints, a b c, one of them is small, one is medium and one is
	// large. Return true if the three values are evenly spaced, so the
	// difference between small and medium is the same as the difference between
	// medium and large. Do not assume the ints will come to you in a reasonable
	// order.

	// evenlySpaced(2, 4, 6) returns true
	// evenlySpaced(4, 6, 2) returns true
	// evenlySpaced(4, 6, 3) returns false
	// evenlySpaced(4, 60, 9) returns false

	public boolean evenlySpaced(int a, int b, int c) {
		List<Integer> some_list = new ArrayList<>();
		some_list.add(a);
		some_list.add(b);
		some_list.add(c);
		Collections.sort(some_list);

		return Math.abs(some_list.get(0) - some_list.get(1))== Math.abs(some_list.get(1) - some_list.get(2));
	}

	// Given a string and an int n, return a string that removes n letters from the
	// 'middle' of the string.
	// The string length will be at least n, and be odd when the length of the input
	// is odd.

	// nMid("Hello", 3) returns "Ho"
	// nMid("Chocolate", 3) returns "Choate"
	// nMid("Chocolate", 1) returns "Choclate"

	public String nMid(String input, int a) {
		StringBuilder sb = new StringBuilder(input);
		int len = input.length();
		int pivot = (len-a)/2; //begin index
		int offset = pivot+a; //end index;

		String removeStr = input.substring(pivot, offset);

		return a>1?input.replace(removeStr,""):sb.deleteCharAt(pivot).toString();
	}

	// Given a string, return true if it ends in "dev". Ignore Case

	// endsDev("ihatedev") returns true
	// endsDev("wehateDev") returns true
	// endsDev("everoyonehatesdevforreal") returns false
	// endsDev("devisnotcool") returns false

	public boolean endsDev(String input) {
		boolean isFound = false;
		char [] charArr = input.toLowerCase().toCharArray();
		for(int i=0; i<charArr.length; i++){
			if(!isFound){
				if(charArr[i]=='d'){
					StringBuilder temp = new StringBuilder();
					int index = i;
					for(;index<charArr.length;index++){
						temp.append(charArr[index]);
						if(temp.toString().equals("dev")){
							isFound =true;
							break;
						}
					}
				}
			}else{
				break;
			}
		}
		return isFound;
	}

	// Given a string, return the length of the largest "block" in the string.
	// A block is a run of adjacent chars that are the same.
	//
	// superBlock("hoopplla") returns 2
	// superBlock("abbCCCddDDDeeEEE") returns 3
	// superBlock("") returns 0

	public int superBlock(String input) {
		List<Integer> listOfCharValue = new ArrayList<Integer>();
		for(int  i=0; i <input.length()-1;i++){
			int counter =0;
			for(int j =i+1; j<input.length();j++){
				if(input.charAt(i)==input.charAt(j)){
					counter++;
					i=j;
				}
			}
			listOfCharValue.add(counter+1);
		}
//		List<Integer> listOfCharValue = Arrays.asList(input.toCharArray())
//				.stream()
//				.mapToInt()
//				.collect(Collections.reverseOrder());
//		return (int) Arrays.asList(input.toCharArray()).stream().filter(c->c.equals('c')).count();
//		return (int)input.chars().count();

		Collections.sort(listOfCharValue);
		int len = listOfCharValue.size()-1;
		return len>0?listOfCharValue.get(len):0;
	}

	// given a string - return the number of times "am" appears in the String
	// ignoring case -
	// BUT ONLY WHEN the word "am" appears without being followed or proceeded by
	// other letters
	//
	// amISearch("Am I in Amsterdam") returns 1
	// amISearch("I am in Amsterdam am I?") returns 2
	// amISearch("I have been in Amsterdam") returns 0

	public int amISearch(String arg1) {
		String [] strArr = arg1.split(" ");
		int counter =0;
		for(String str: strArr){
			if(str.toLowerCase().equals("am")) counter++;
		}
		return counter;

	}

	// given a number
	// if this number is divisible by 3 return "fizz"
	// if this number is divisible by 5 return "buzz"
	// if this number is divisible by both 3 and 5return "fizzbuzz"
	//
	// fizzBuzz(3) returns "fizz"
	// fizzBuzz(10) returns "buzz"
	// fizzBuzz(15) returns "fizzbuzz"

	public String fizzBuzz(int arg1) {
		String resultStr = "";
		if(arg1%3== 0) resultStr = "fizz";
		if(arg1%5== 0) resultStr = "buzz";
		if(arg1%3== 0 && arg1%5== 0) resultStr ="fizzbuzz";
		return resultStr;

	}

	// Given a string split the string into the individual numbers present
	// then add each digit of each number to get a final value for each number
	// String example = "55 72 86"
	//
	// "55" will = the integer 10
	// "72" will = the integer 9
	// "86" will = the integer 14
	//
	// You then need to return the highest vale
	//
	// largest("55 72 86") returns 14
	// largest("15 72 80 164") returns 11
	// largest("555 72 86 45 10") returns 15

	public int largest(String arg1) {
		String [] strArr = arg1.split(" ");
		int [] intArr = new int[strArr.length];
		StringBuilder sb;

		for(int i =0; i<strArr.length;i++){
			sb= new StringBuilder(strArr[i]);
			int counter =0, sum = 0;
			while(counter<sb.length()){
				sum += Integer.parseInt(String.valueOf(sb.charAt(counter)));
				counter++;
			}
			intArr[i] = sum;
		}

		return Arrays.stream(intArr).max().getAsInt();

	}

}
