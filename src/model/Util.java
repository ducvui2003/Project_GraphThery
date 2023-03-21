package model;

import java.util.ArrayList;

public class Util {
	public static int[] reverseArrayListToArray(ArrayList<Integer> arrayList) {
		int[] result = new int[arrayList.size()];
		int indexResult = 0;
		for (int i = arrayList.size() - 1; i >= 0; i--) {
			result[indexResult] = arrayList.get(i);
			indexResult++;
		}
		return result;
	}

	public static ArrayList<Integer> getAdjecencyVer(int[][] matrix, int v) {
		ArrayList<Integer> result = new ArrayList<>();
		for (int i = 0; i < matrix.length; i++) {
			if (matrix[v][i] != 0) {
				result.add(i);
			}
		}
		return result;
	}

	public ArrayList<Integer> giao(ArrayList<Integer> x, ArrayList<Integer> y) {
		ArrayList<Integer> result = new ArrayList<>();
		for (int i = 0; i < x.size(); i++) {
			for (int j = 0; j < y.size(); j++) {
				if (x.get(i) == y.get(j) && !result.contains(y.get(j))) {
					result.add(y.get(j));
				}
			}
		}
		return result;
	}

	public static ArrayList<Integer> getNonAdjecencyVer(int[][] matrix, int v) {
		ArrayList<Integer> result = new ArrayList<>();
		for (int i = 0; i < matrix.length; i++) {
			if (matrix[v][i] == 0) {
				result.add(i);
			}
		}
		return result;
	}

	public boolean equal2Array(ArrayList<Integer> x, ArrayList<Integer> y) {

		for (int i = 0; i < x.size(); i++) {
			if (!x.contains(y.get(i)) && !y.contains(x.get(i))) {
				return false;
			}
		}
		return true;

	}
}
