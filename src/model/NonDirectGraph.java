package model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

public class NonDirectGraph extends Graph {
	Set<Integer> oddVex = new TreeSet<>();

	public NonDirectGraph(int numVex, int[][] matrix) {
		super(numVex, matrix);
		// TODO Auto-generated constructor stub
	}

	public NonDirectGraph(String filePath) {
		super(filePath);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addEdge(int i, int j) {
		if (i >= 0 && i < numVex && j >= 0 && j < numVex) {
			if (i == j) {
				matrix[i][j] += 1;
			} else {
				matrix[i][j] += 1;
				matrix[j][i] += 1;

			}
		}
	}

	@Override
	public void removeEdge(int row, int col) {
		if (this.matrix[row][col] > 0) {
			this.matrix[row][col] -= 1;
			this.matrix[col][row] -= 1;
		}
	}

	@Override
	public void removeEdge(int[][] matrix, int row, int col) {
		if (matrix[row][col] > 0) {
			matrix[row][col] -= 1;
			matrix[col][row] -= 1;
		}

	}

	public boolean checkUnGraph() {
		boolean result = true;
		checkValid();
		for (int row = 0; row < matrix.length; row++) {
			for (int col = 0; col < matrix[row].length; col++) {
				if (matrix[row][col] != matrix[col][row]) {
					result = false;
				}
			}
		}
		return result;
	}

	@Override
	public int numEdges() {
		int result = 0;
		for (int row = 0; row < matrix.length; row++) {
			result += deg(row);
		}
		return result / 2;
	}

	@Override
	public int deg(int vertex) {
		int result = 0;
		for (int col = 0; col < matrix[vertex].length; col++) {
			if (this.matrix[col][col] == 1) {
				result += 2;
			} else {
				result += matrix[vertex][col];
			}
		}
		return result;
	}

	@Override
	public int degInner(int vetex) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int degOutside(int vetex) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int sumDeg(int vex) {
		return deg(vex);
	}

	@Override
	public int[] DFSGraphRecursion() {
		return DFSGraphRecursion(0);
	}

	public ArrayList<Integer> getAdjecencyVer(int v) {
		ArrayList<Integer> result = new ArrayList<>();
		for (int i = 0; i < numVex; i++) {
			if (matrix[v][i] != 0) {
				result.add(i);
			}
		}
		return result;
	}

	public int[] BFSGraph(int v) {
		ArrayList<Integer> closed = new ArrayList<>();
		Queue<Integer> queue = new LinkedList<>();
		queue.add(v);
		while (queue.isEmpty() == false) {
			int p = queue.poll();
			if (!closed.contains(p)) {
				closed.add(p);
				ArrayList<Integer> adjecencyP = getAdjecencyVer(p);
				for (int i = 0; i < adjecencyP.size(); i++) {
					if (!closed.contains(adjecencyP.get(i)) && !queue.contains(adjecencyP.get(i))) {
						queue.add(adjecencyP.get(i));
					}
				}
			}
		}

		int[] result = new int[numVex];
		for (int i = 0; i < closed.size(); i++) {
			result[i] = closed.get(i);
		}
		return result;
	}

	@Override
	public int[] BFSGraph() {
		return BFSGraph(0);
	}

	@Override
	public int[] diTimCacDinhLienThong() {
		int[] result = new int[numVex];
		int count = 1;
		for (int i = 0; i < numVex; i++) {
			if (visited[i] == false) {
				DFSGraphRecursion(i);
				for (int j = 0; j < result.length; j++) {
					if (visited[j] == true && result[j] == 0) {
						result[j] = count;
					}
				}
				count += 1;
			}
		}
		return result;
	}

	@Override
	public int[] findPathTwoVexs(int s, int d) {
		resetIndex_Visted_Iconnect();
		ArrayList<Integer> closed = new ArrayList<>(); // result
		Stack<Integer> stack = new Stack<>();
		Stack<Integer> parents = new Stack<>();
		stack.push(s);
		while (stack.isEmpty() == false) {
			int p = stack.pop();
			if (!closed.contains(p)) {
				closed.add(p);
				parents.add(p);
				ArrayList<Integer> adjecencyP = getAdjecencyVer(p); // danh sacsh cac canh ke cua p
				for (int i = adjecencyP.size() - 1; i >= 0; i--) {
					if (stack.contains(adjecencyP.get(i)) == false) {
						stack.push(adjecencyP.get(i));
					}
				}
			}
		}
		int[] result = new int[numVex];
		for (int i = 0; i < closed.size(); i++) {
			result[i] = closed.get(i);
		}
		return result;
	}

	@Override
	public void checkBiparititeGraph(int v, int color1, int color2) {
		if (visited[v] == false) {
			visited[v] = true;
			if (color[getAdjecencyVer(v).get(0)] == color1) {
				color[v] = color2;
			} else {
				color[v] = color1;
			}
		}
		for (int i = 0; i < numVex; i++) {
			if (matrix[v][i] != 0 && visited[i] == false) {
				visited[i] = true;
				if (color[v] == color1) {
					color[i] = color2;
				} else {
					color[i] = color1;
				}
				checkBiparititeGraph(i, color1, color2);
			}
		}
	}

	@Override
	public boolean checkBiparititeGraph() {
		visited[0] = true;
		color[0] = 1;
		checkBiparititeGraph(1, 1, 2);
		for (int i = 0; i < numVex; i++) {
			for (int j = 0; j < numVex; j++) {
				if (matrix[i][j] != 0 && color[i] == color[j]) {
					return false;
				}
			}
		}
		return true;
	}

//Kiểm tra đồ thị đủ Kn
	@Override
	public boolean checkGraphK() {
		for (int i = 0; i < numVex; i++) {
			if (deg(i) != numVex - 1) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean isEulerGraph() {
		if (this.getInconnect() == 1) {// Check lien thong
			for (int i = 0; i < numVex; i++) {// DL 1: check deg chan
				if (deg(i) % 2 != 0) {
					return false;
				}
			}
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean isHalfEulerGraph() {
		if (this.isEulerGraph()) {
			return true;
		} else {
			int numOddVex = 0;
			if (this.getInconnect() == 1) {
				for (int i = 0; i < numVex; i++) {
					if (deg(i) % 2 == 1) {
						oddVex.add(i);
						numOddVex++;
					}
				}
				if (numOddVex != 2) {// Xác định chỉ có 2 đỉnh bậc lẻ
					return false;
				} else {
					return true;
				}
			} else {
				return false;
			}

		}
	}

	@Override
	public int[] findEulerCycle() {
		return findEulerCycle(0);
	}

	@Override
	public int[] findEulerCycle(int v) {
		if (isEulerGraph() == false) {
			throw new RuntimeException("Don't valid");
		} else {
			Stack<Integer> stack = new Stack<>();
			ArrayList<Integer> result = new ArrayList<>();
			int[][] matrixTemp = this.matrix;
			int u = v;
			stack.push(u);
			while (stack.isEmpty() == false) {
				int x = stack.peek();
				ArrayList<Integer> keX = Util.getAdjecencyVer(matrixTemp, x);
//			Xóa từ từ tất cả các đỉnh có cạnh liên thong với x
				if (keX.isEmpty() == false) {
					int y = keX.get(0);
					stack.push(y);
					removeEdge(matrixTemp, x, y);
					removeEdge(matrixTemp, y, x);
				} else {
					x = stack.pop();
					result.add(x);
				}
			}
			return Util.reverseArrayListToArray(result);
		}
	}

	@Override
	public int[] findEulerPath() {
		if (isHalfEulerGraph() == true) {
			Stack<Integer> stack = new Stack<>();
			ArrayList<Integer> result = new ArrayList<>();
			int[][] matrixTemp = this.matrix;
			int u = (int) oddVex.toArray()[0];
			stack.push(u);
			while (stack.isEmpty() == false) {
				int x = stack.peek();
				ArrayList<Integer> keX = Util.getAdjecencyVer(matrixTemp, x);
//			Xóa từ từ tất cả các đỉnh có cạnh liên thong với x
				if (keX.isEmpty() == false) {
					int y = keX.get(0);
					stack.push(y);
					removeEdge(matrixTemp, x, y);
					removeEdge(matrixTemp, y, x);
				} else {
					x = stack.pop();
					result.add(x);
				}
			}
			return Util.reverseArrayListToArray(result);
		} else {
			throw new RuntimeException("Don't valid");
		}

	}

	@Override
	public boolean checkHamiltonPath() {
//		Kiểm tra liên thông
		if (isConnected() == false) {
			return false;
		} else {
			if (checkHamiltonCycle() == true) {
				return true;
			} else {
//			Đếm số bậc 1 và lớn hơn 1
				int countDegree1 = 0;
				int countDegreeEqualThan1 = 0;
				for (int i = 0; i < color.length; i++) {
					if (deg(i) == 1) {
						countDegree1++;
					} else {
						countDegreeEqualThan1++;
					}
				}
//			đường đi thì cần có 2 đỉnh đầu và đỉnh cuối của đường đi là bậc 1
				if (countDegree1 > 2) {
					return false;
				} else {
					if (countDegree1 <= 1 && countDegreeEqualThan1 == numVex - countDegree1) {
						return true;
					} else {
						return false;
					}
				}
			}
		}
	}

	@Override
	public boolean checkHamiltonCycle() {
//		Đồ thị đầy đủ
		if (isConnected() == false) {
			return false;
		} else {
			int countDegree1 = 0;
			int countDegreeEqualThan1 = 0;
			for (int i = 0; i < numVex; i++) {
				if (deg(i) == 1) {
					countDegree1++;
				} else {
					countDegreeEqualThan1++;
				}
			}
			if (countDegree1 != 0) {// có bậc 1=> ko có đường đu về cho chu trình
				return false;
			} else if (countDegree1 == 0 && countDegreeEqualThan1 == numVex) {
				return true;
			} else if (countDegree1 == 0 && checkBiparititeGraph() == true) {
				return true;
			} else if (checkGraphK() == true) {
				return true;
			}

		}
		return false;
	}

	@Override
	public void findHamiltonCycle() {
		if (checkHamiltonCycle() == false) {
			throw new RuntimeException("Không có chu trình Hamilton");
		} else {
//			Sử dụng thuật toán quay đầu
			for (int i = 0; i < numVex; i++) {
				visited[i] = false;
				arrayHamilton[i] = -1;
			}
			arrayHamilton[0] = 0;
			visited[0] = true;
			expendHamiltonCycles(1);
		}
	}

	private void expendHamiltonCycles(int v) {
		for (int i = 0; i < numVex; i++) {
			if (visited[i] == false && matrix[arrayHamilton[v - 1]][i] != 0) {
				arrayHamilton[v] = i;
				if (v < numVex - 1) {// Điểm dừng của đệ qui
					visited[i] = true;
					expendHamiltonCycles(v + 1);
					visited[i] = false;
				} else {
					if (matrix[arrayHamilton[v]][0] != 0) {
						printCycles(arrayHamilton);
					}
				}
			}
		}
	}

	private void printCycles(int[] path) {
		System.out.println("Chu trinh");
		for (int i = 0; i < path.length; i++) {
			System.out.print((path[i] + 1) + "->");
		}
		System.out.print((path[0] + 1) + "\n");
	}

	@Override
	public void findHamiltonPath() {
		if (checkHamiltonCycle() == false) {
			throw new RuntimeException("Không có chu trình Hamilton");
		} else {
//			Sử dụng thuật toán quay đầu
			for (int i = 0; i < numVex; i++) {
				visited[i] = false;
				arrayHamilton[i] = -1;
			}
			arrayHamilton[0] = 0;
			visited[0] = true;
			expendHamiltonPath(1);
		}
	}

	private void expendHamiltonPath(int v) {
		for (int i = 0; i < numVex; i++) {
			if (visited[i] == false && matrix[arrayHamilton[v - 1]][i] != 0) {
				arrayHamilton[v] = i;
				if (v < numVex - 1) {// Điểm dừng của đệ qui
					visited[i] = true;
					expendHamiltonPath(v + 1);
					visited[i] = false;
				} else {
					if (matrix[arrayHamilton[v]][0] != 0) {
						printPaths(arrayHamilton);
					}
				}
			}
		}
	}

	private void printPaths(int[] path) {
		System.out.println("Chu trinh");
		for (int i = 0; i < path.length; i++) {
			System.out.print((path[i] + 1) + "->");
		}
	}
}
