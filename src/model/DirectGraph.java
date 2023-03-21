package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

public class DirectGraph extends Graph {
	Set<Integer> vexFindEulerPath = new TreeSet<>();

	public DirectGraph(int numVex, int[][] matrix) {
		super(numVex, matrix);
		// TODO Auto-generated constructor stub
	}

	public DirectGraph(String filePath) {
		super(filePath);
	}

	@Override
	public void addEdge(int i, int j) {
		matrix[i][j] += 1;

	}

	@Override
	public void removeEdge(int row, int col) {
		if (this.matrix[row][col] > 0) {
			this.matrix[row][col] -= 1;
		}
	}

	@Override
	public void removeEdge(int[][] matrix, int row, int col) {
		if (matrix[row][col] > 0) {
			matrix[row][col] -= 1;
		}

	}

	@Override
	public boolean checkUnGraph() {
		return false;
	}

	@Override
	public int numEdges() {
		int result = 0;
		for (int col = 0; col < matrix.length; col++) {
			result += degInner(col);
		}
		return result;
	}

	@Override
	public int deg(int vertex) {
		// TODO Auto-generated method stub
		return 0;
	}

//Bậc ngoài là số cung xuất phát từ đỉnh đó
	@Override
	public int degOutside(int vertex) {
		int result = 0;
		for (int col = 0; col < matrix.length; col++) {
			if (matrix[vertex][vertex] == 1) {
				result += 1;
			} else {
				result += matrix[vertex][col];
			}
		}
		return result;
	}

//Bậc trong là số cung đi đến đỉnh 
	@Override
	public int degInner(int vertex) {
		int result = 0;
		for (int row = 0; row < matrix.length; row++) {
			if (matrix[vertex][vertex] == 1) {
				result += 1;
			} else {
				result += matrix[row][vertex];
			}
		}
		return result;
	}

	@Override
	public int sumDeg(int vex) {
		return degInner(vex) + degOutside(vex);
	}

	@Override
	public int[] DFSGraphRecursion() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] BFSGraph(int v) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] BFSGraph() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] diTimCacDinhLienThong() {

		return null;
	}

	@Override
	public int[] findPathTwoVexs(int s, int i) {
		// TODO Auto-generated method stub
		return null;
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

//	Một đơn đồ thị có hướng đầy đủ được định nghĩa là một đồ thị có hướng trong đó mỗi cặp đỉnh đều có một cạnh nối 
//	chúng và không có đỉnh nào kết nối với chính nó.
	@Override
	public boolean checkGraphK() {
		for (int i = 0; i < numVex; i++) {
			for (int j = 0; j < numVex; j++) {
				if (i != j) {// tránh đường chéo chính
					if (matrix[i][j] == 0 && matrix[j][i] == 0) {
						return false;
					}
				}
			}
		}
		return true;
	}

	@Override
	public boolean isEulerGraph() {
		int[][] matrix = createNonDirectGraph();
		Graph nonDirectgraph = new NonDirectGraph(matrix.length, matrix);
		if (nonDirectgraph.isConnected()) {
			for (int i = 0; i < numVex; i++) {
				if (degInner(i) != degOutside(i)) {
					return false;
				}
			}
			return true;
		} else {
			return false;
		}
	}

	public int[][] createNonDirectGraph() {
		int[][] result = new int[numVex][numVex];
		for (int row = 0; row < result.length; row++) {
			for (int col = 0; col < result.length; col++) {
				if (this.matrix[row][col] == 1) {
					result[row][col] = 1;
					result[col][row] = 1;
				}
			}
		}
		return result;
	}

	@Override
	public boolean isHalfEulerGraph() {
		if (isEulerGraph()) {
			return true;
		} else {
			int[][] matrix = createNonDirectGraph();
			Graph nonDirectgraph = new NonDirectGraph(matrix.length, matrix);
			if (nonDirectgraph.isConnected()) {
				int countDegX = 0;
				int countDegY = 0;
				for (int i = 0; i < numVex; i++) {
					if (degOutside(i) == degInner(i) + 1) {
						vexFindEulerPath.add(i);
						countDegX++;
					}
					if (degInner(i) == degOutside(i) - 1) {
						vexFindEulerPath.add(i);
						countDegY++;
					}
				}
				if (countDegX == 1 && countDegY == 1) {
					return true;
				} else {
					return false;
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
			int u = (int) vexFindEulerPath.toArray()[0];
			stack.push(u);
			while (stack.isEmpty() == false) {
				int x = stack.peek();
				ArrayList<Integer> keX = Util.getAdjecencyVer(matrixTemp, x);
//			Xóa từ từ tất cả các đỉnh có cạnh liên thong với x
				if (keX.isEmpty() == false) {
					int y = keX.get(0);
					stack.push(y);
					removeEdge(matrixTemp, x, y);
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
	public boolean checkHamiltonCycle() {
		if (isConnected() == false) {
			return false;
		} else {
			for (int i = 0; i < numVex; i++) {
				if ((degInner(i) != numVex / 2 && degOutside(i) != numVex / 2)
						|| (degInner(i) != numVex / 2 && degOutside(i) == numVex / 2)
						|| (degInner(i) == numVex / 2 || degOutside(i) != numVex / 2)) {
					return false;
				}
			}

			for (int i = 0; i < numVex; i++) {
				if (degInner(i) == 0 || degOutside(i) == 0) {
					return false;
				}
			}
			return true;
		}
	}

	@Override
	public boolean checkHamiltonPath() {
		if (isConnected() == false) {
			return false;
		} else {
			if (checkHamiltonCycle() == false) {
				return false;
			} else {
				return true;
			}
		}
	}

	@Override
	public void findHamiltonCycle() {
		// TODO Auto-generated method stub
	}

	@Override
	public void findHamiltonPath() {
		// TODO Auto-generated method stub
	}
}
