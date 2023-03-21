package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

public abstract class Graph {
	protected int numVex;
	protected int[][] matrix;

	public int[][] getMatrix() {
		return matrix;
	}

	public void setMatrix(int[][] matrix) {
		this.matrix = matrix;
	}

	protected String filePath;
	protected int[] labbis;
	protected boolean[] visited;
	protected int[] result;
	protected int index;
	int inconnect;
	protected int[] color;
	protected int[] arrayHamilton;

	public Graph(int[][] matrix) {
		super();
		this.matrix = matrix;
	}

	public Graph(int numVex, int[][] matrix) {
		super();
		this.numVex = numVex;
		this.matrix = matrix;
		this.index = 0;
		this.visited = new boolean[numVex];
		this.result = new int[numVex];
		this.inconnect = 0;
		this.arrayHamilton = new int[numVex];
	}

	public Graph(String filePath) {
		super();
		this.filePath = filePath;
		loadFile();
		if (this.numVex != this.matrix.length) {
			throw new RuntimeException("Valid adjacency matrix");
		}
		index = 0;
		this.visited = new boolean[numVex];
		this.result = new int[numVex];
		this.inconnect = 0;
		this.color = new int[numVex];
		this.arrayHamilton = new int[numVex];
	}

	public void loadFile() {
		File file = new File(filePath);
		try {
			FileReader reader = new FileReader(file);
			BufferedReader br = new BufferedReader(reader);
			int size = Integer.parseInt(br.readLine());
			numVex = size;
			this.matrix = new int[size][size];
			int row = 0;
			String line = "";
			while ((line = br.readLine()) != null) {
				String[] token = line.split(" ");
				for (int i = 0; i < token.length; i++) {
					matrix[row][i] = Integer.parseInt(token[i]);
				}
				row++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void writeFile(String filePath) {
		int[][] matrix = this.matrix;
		File file = new File(filePath);
		try {
			FileWriter fileWriter = new FileWriter(file);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write(matrix.length + "");
			bufferedWriter.newLine();
			for (int i = 0; i < matrix.length; i++) {
				for (int j = 0; j < matrix[i].length; j++) {
					bufferedWriter.write(matrix[i][j] + " ");
				}
				bufferedWriter.newLine();
			}
			bufferedWriter.close();
			fileWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String printMatrix() {
		String result = " ";
		for (int row = 0; row < matrix.length; row++) {
			for (int col = 0; col < matrix[row].length; col++) {
				result += matrix[row][col] + " ";
			}
			result += "\n";
		}
		return result;
	}

//	Check Don DT
	public boolean checkValid() {
		if (matrix.length == matrix[0].length) {
//			Check khuyen and canh song song
			for (int row = 0; row < matrix.length; row++) {
				for (int col = 0; col < matrix[row].length; col++) {
					if (matrix[row][col] > 1 || matrix[row][row] > 0) {
						return false;
					}
				}
			}
		} else {
			return false;
		}
		return true;
	}

//	Check Đơn Đt
	public abstract boolean checkUnGraph();

	public abstract void addEdge(int row, int col);

	public abstract void removeEdge(int row, int col);

	public abstract void removeEdge(int[][] matrix, int row, int col);

	public int numVertexs() {
		return this.numVex;
	}

	public abstract int numEdges();

	public abstract int deg(int vertex);

	public abstract int degInner(int vetex);

	public abstract int degOutside(int vetex);

	public abstract int sumDeg(int vex);

//	Week 2
	public abstract int[] diTimCacDinhLienThong();

	public int[] DFSGraphNonRecursion(int v) {
		ArrayList<Integer> closed = new ArrayList<>(); // result
		Stack<Integer> stack = new Stack<>();
		stack.push(v);
		while (!stack.isEmpty()) {
			int p = stack.pop();
			if (!closed.contains(p)) {
				closed.add(p);
				ArrayList<Integer> adjecencyP = getAdjecencyVer(p); // danh sách các cạnh kề của p
				if (!adjecencyP.isEmpty()) {
					for (int i = adjecencyP.size() - 1; i >= 0; i--) {
						if (!stack.contains(adjecencyP.get(i))) {
							stack.push(adjecencyP.get(i));
						}
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

	public int[] DFSGraphNonRecursion() {
		return DFSGraphNonRecursion(0);
	}

	public int[] DFSGraphRecursion(int v) {
		if (index == numVex) {
			return result;
		} else {
			visited[v] = true;
			result[index] = v;// index = 0
			index++;// index ++ => index = 1
			for (int i = 0; i < matrix.length; i++) {
				if (matrix[v][i] != 0 && visited[i] == false) {// co canh lien thong va chua duoc thamsss
					DFSGraphRecursion(i);
				}
			}
		}
		index = 0;
		return result;
	}

	public abstract int[] DFSGraphRecursion();

	public abstract int[] BFSGraph(int v);

	public abstract int[] BFSGraph();

	protected ArrayList<Integer> getAdjecencyVer(int v) {
		ArrayList<Integer> result = new ArrayList<>();
		for (int i = 0; i < numVex; i++) {
			if (this.matrix[v][i] == 1) {
				result.add(i);
			}
		}
		return result;
	}

	public void resetIndex_Visted_Iconnect() {
		for (int i = 0; i < numVex; i++) {
			this.visited[i] = false;
		}
		index = 0;
		inconnect = 0;
	}

	public int getInconnect() {
		resetIndex_Visted_Iconnect();
		for (int i = 0; i < numVex; i++) {
			if (visited[i] == false) {
				inconnect += 1;
				DFSGraphRecursion(i);
			}
		}
		return inconnect;
	}

	public boolean isConnected() {
		getInconnect();
		if (inconnect == 1) {
			return true;
		} else {
			return false;
		}
	}

	public abstract int[] findPathTwoVexs(int s, int i);

	public abstract void checkBiparititeGraph(int v, int color1, int color2);

	public abstract boolean checkBiparititeGraph();

	public abstract boolean checkGraphK();

//	Week 3
	public abstract boolean isEulerGraph();

	public abstract boolean isHalfEulerGraph();

	public abstract int[] findEulerCycle();

	public abstract int[] findEulerCycle(int v);

	public abstract int[] findEulerPath();

//	Week 4
	public abstract boolean checkHamiltonCycle();

	public abstract boolean checkHamiltonPath();

	public abstract void findHamiltonCycle();

	public abstract void findHamiltonPath();
}
