package sfidaHashing;

import java.util.*;

public class Finder extends Thread {
	
	private boolean found = false;
	private String result;
	private String letterToFix;
	
	static final int minLenght = 1, maxLenght = 6;

	
	public Finder(String name, String letterToFix) {
		super(name);
		this.letterToFix = letterToFix;
	}
	
	public boolean getFound() throws InterruptedException {
		this.join();
		return found;
	}
	
	public String getResult() {
		return result;
	}

	@Override
	public void run() {
		System.out.println("Thread " + this.getName() + " started");
		for (int j = minLenght; j <= maxLenght; j++) {
			if (found || HashingPuzzle.canReturn.get()) return;

			System.out.println("Thread " + this.getName() + ", checking max lenght " + j);
			generatePermutations(letterToFix, new HashSet<>(), j);
		}
	}
	
	private void generatePermutations(String currentPermutation, Set<Integer> usedIndices, int maxLenght) {
		if (HashingPuzzle.canReturn.get()) return;
		
        // base case: generated a permutation
        if (currentPermutation.length() == maxLenght) {
            // test the permutation using hashAndTest function
        	if (HashingPuzzle.testing) {
        		System.out.println(currentPermutation); //! uncomment only during testing, slows down the program significantly
        	}
            if (HashingPuzzle.hashAndTest(currentPermutation)) {
            	this.found = true;
            	this.result = currentPermutation;
                HashingPuzzle.canReturn.compareAndSet(false, true);
            }
            return;
        }
        
        // generate permutations by adding unused characters
        for (int i = 0; i < HashingPuzzle.alphabet.length(); i++) {
            if (!usedIndices.contains(i)) {
                // add the character at index i to the current permutation
                String newPermutation = currentPermutation + HashingPuzzle.alphabet.charAt(i);
                Set<Integer> newIndices = new HashSet<>(usedIndices);
                newIndices.add(i);
                // recurse to generate the rest of the permutation
                generatePermutations(newPermutation, newIndices, maxLenght);
            }
        }
    }

}
