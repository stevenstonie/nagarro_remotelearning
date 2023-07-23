package Exercise2.imports;

import java.util.Stack;

public class Tank {
	private Stack<Integer> myTank;

	public Tank() {
		myTank = new Stack<>();
	}

	public void pushIntoTank(int number) {
		myTank.push(number);
	}

	public void popFromTank() {
		myTank.pop();
	}

	public void printTank() {
		System.out.println(myTank);
	}

	public void clearTank() {
		myTank.clear();
	}

	@Override
	public void finalize() {
		if (myTank.empty())
			System.out.println("the tank is empty");
		else {
			System.out.println("the tank is not yet empty");
		}
	}

	public Stack<Integer> getTank() {
		return myTank;
	}
}
