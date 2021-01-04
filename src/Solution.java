import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.util.stream.*;

interface Company {
	void assignSalaries(int[] salaries);

	void averageSalary();

	void maxSalary();

	void minSalary();
}

/*
 * model output for cut and paste 
 * Incomes of ____ credited 
 * Average salary of ____ is ____ 
 * Maximum salary amongst ____ is ____ 
 * Minimum salary amongst ____ is ____
 */

class AccountantFirm extends EngineerFirm {
	public AccountantFirm(int n) {
		super(n);
	}

	public void assignSalaries(int[] salaries) {
		super.assignIncome(salaries);
		printMessages(0, "", "accountants");
	}

	public void maxSalary() {
		printMessages(super.MaxSalary(), "max", "accountants");
	}

	public void minSalary() {
		printMessages(super.MinSalary(), "min", "accountants");
	}

	public void averageSalary() {
		printMessages(super.AveSalary(), "ave", "accountants");
	}
}

class EngineerFirm {
	private final int[] income;

	public EngineerFirm(int n) {
		income = new int[n];
		for (int i = 0; i < n; i++) {
			income[i] = 0;
		}
	}

	public static void printMessages(double salaryAmount, String salarySpecification, String profession) {
		switch (salarySpecification) {
		case "max":
			System.out.print("Maximum salary amongst " + profession);
			System.out.printf(" is %d", (int) salaryAmount);
			System.out.println("");
			break;
		case "min":
			System.out.print("Minimum salary amongst " + profession);
			System.out.printf(" is %d", (int) salaryAmount);
			System.out.println("");
			break;
		case "ave":
			System.out.print("Average salary of " + profession);
			System.out.printf(" is %.2f", salaryAmount);
			System.out.println("");
			break;
		default:
			System.out.println("Incomes of " + profession + " credited");
			break;
		}
	}

	public void assignSalaries(int[] salaries) {
		if (salaries != null) {
			assignIncome(salaries);
			printMessages(0, "", "engineers");
		}
	}

	public void maxSalary() {
		printMessages(MaxSalary(), "max", "engineers");
	}

	public void minSalary() {
		printMessages(MinSalary(), "min", "engineers");
	}

	public void averageSalary() {
		printMessages(AveSalary(), "ave", "engineers");
	}

	public Integer MaxSalary() {
		List<Integer> list = Arrays.stream(income).boxed().collect(Collectors.toList());
		return list.stream().max(Integer::compare).get();
	}

	public Integer MinSalary() {
		List<Integer> list = Arrays.stream(income).boxed().collect(Collectors.toList());
		return list.stream().min(Integer::compare).get();
	}

	public double AveSalary() {
		List<Integer> list = Arrays.stream(income).boxed().collect(Collectors.toList());
		IntSummaryStatistics stats = list.stream().mapToInt((x) -> x).summaryStatistics();
		return stats.getAverage();
	}

	public void assignIncome(int[] salaries) {
		System.arraycopy(salaries, 0, income, 0, Math.min(income.length, salaries.length));
	}
}

public class Solution {
	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);
		String[] count = sc.nextLine().split(" ");
		EngineerFirm e = new EngineerFirm(Integer.parseInt(count[0]));
		AccountantFirm a = new AccountantFirm(Integer.parseInt(count[1]));
		count = sc.nextLine().split(" ");
		int[] incomeEngineers = new int[count.length];
		for (int i = 0; i < count.length; i++) {
			incomeEngineers[i] = Integer.parseInt(count[i]);
		}
		e.assignSalaries(incomeEngineers);
		count = sc.nextLine().split(" ");
		int[] incomeAccountants = new int[count.length];
		for (int i = 0; i < count.length; i++) {
			incomeAccountants[i] = Integer.parseInt(count[i]);
		}
		a.assignSalaries(incomeAccountants);
		e.averageSalary();
		e.maxSalary();
		e.minSalary();
		a.averageSalary();
		a.maxSalary();
		a.minSalary();
	}
}
