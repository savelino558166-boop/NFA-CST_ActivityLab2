package LabAct2_CST4;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class NFA {
	
	static int Index, Current_State = 0;
	final static Random randomPicker_State = new Random();
	static String Q3 = "Unoccupied";
	static int[] State_q0A = {0, 1};
	static int[] State_q2B = {1, 3};

	
	/* Plan: 
	q0 -> 'a' -> q0/q1
	q0 -> 'b' -> q0
	q1 -> 'a'/'b' -> q2
	q2 -> 'b' -> q1/q3(accept)
	q2 -> 'a' -> q1
	q3 -> 'a'/'b' -> q3
	*/
	
	static void q0(char Pattern) {
		if (Pattern == 'a') { 
			Index++ ;
			Current_State = State_q0A[randomPicker_State.nextInt(2)];
			
		} else if (Pattern == 'b') {
			Index++ ;
		}
		
	}
	
	static void q1(char Pattern) {
		if (Pattern == 'a' || Pattern == 'b') {
			Index++;
			Current_State = 2;
			
		}
		
	}
	
	static void q2(char Pattern) {
		if (Pattern == 'a') {	
			Index++;
			Current_State = 1;

		} else if (Pattern == 'b') {
			Index++;
			Current_State = State_q2B[randomPicker_State.nextInt(2)];
			
			if (Current_State == 3) {
				Q3 = "Occupied";
			}

		}
	}
	
	static void q3_Accept(char Pattern) {
		if (Pattern == 'a' || Pattern =='b') {
			Index++;
		}
	}

	
	
	
	
	
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		
		System.out.print("NFA \nInput: ");
		String Input_strings = scn.next();
		
		scn.close();
		
		ArrayList<Character> Whole_strings = new ArrayList<Character>();		//it will adjust to the size of the strings given
		
		//putting the whole strings inside an array so it can be called later
		for (int counter = 0; counter <= (Input_strings.length() -1); counter++) {
			Whole_strings.add(Input_strings.charAt(counter));

		}
		
		try {
			
			while (Index < Input_strings.length()) {
				
			
				if (Current_State == 0) {
					q0(Whole_strings.get(Index));
			
				}	else if (Current_State == 1) {
					q1(Whole_strings.get(Index));

				}	else if (Current_State == 2) {
					q2(Whole_strings.get(Index));
			
				}	else if (Current_State == 3) {
					q3_Accept(Whole_strings.get(Index));
				}
		
			} // the Loop
		
		} finally {
			if (Q3.equals("Occupied")){
				System.out.println("Accept");
				
			} else {
				System.out.println("Reject");
			}
			

		}
		
		
	}
}