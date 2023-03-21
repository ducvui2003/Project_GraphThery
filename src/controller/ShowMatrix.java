package controller;

import javax.swing.JFrame;

import model.Graph;
import model.NonDirectGraph;
import run.FrameDemo;

public class ShowMatrix {
	int[][] matrix;
	JFrame frameDemo = FrameDemo.frame;

	public ShowMatrix(String link) {
		Graph graph = new NonDirectGraph(link);
		this.matrix = graph.getMatrix();
	}
}
