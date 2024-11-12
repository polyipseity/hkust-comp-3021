package base;

import java.util.List;
import java.util.function.*;

public class Account {
	public int id;
	public int balance;

	public Account(int id, int balance) {
		this.id = id;
		this.balance = balance;
	}

	// TODO: Task1 
	// replace the null with a lambda expression
	public static Consumer<Account> add100 = null

	

	// TODO: Task2
	/**
	*	define checkBound using lowerBound and upperBound
	 *  lowerBound is 0, upperBound is 10000
	 *  We want checkBound to check BOTH lowerBound AND upperBound.
	*/
	public static Predicate<Account> lowerBound = null;
	public static Predicate<Account> upperBound = null;
	public static Predicate<Account> checkBound = null;


	// TODO: Task3
	// replace the null with a lambda expression
	// implement a public interface AddMaker that contains single abstract method make(int N)
	// make(int N) returns a Consumer<Account> that adds N to the balance of the account
	// implements make(int N) to return a lambda expression
	// TODO

	// TODO
	public static AddMaker maker = null;


	// You can assume that all the Account in acconts have positive balances.
	public static int getMaxAccountID(List<Account> accounts) {
		// TODO: Task4 
		// replace the null with a stream expression
		Account maxOne = null;

		return maxOne.id;
	}


}
