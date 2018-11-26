package com.test.mychessapplication.Data;

import java.util.ArrayList;

public class ChessBoard {

    final static int chessSize = 8;

    private final static int row[] = {2, 2, -2, -2, 1, 1, -1, -1};
    private final static int col[] = {-1, 1, 1, -1, 2, -2, 2, -2};

    public ChessBoard() { }

    private ArrayList<Node> getActualNeighbors(Node node) {
        ArrayList<Node> neighborsList = new ArrayList<>();
        for (int i = 0; i < chessSize; i++) {
            Node neighbor = new Node(node.getX() + row[i], node.getY() + col[i]);
            if (neighbor.isInsideChessboard()) {
                neighborsList.add(neighbor);
            }
        }
        return neighborsList;
    }

    public ArrayList<ArrayList<Node>> getPaths(Node sourceNode, Node destinationNode) {
        ArrayList<ArrayList<Node>> paths = new ArrayList<>();
        for (Node n1 : getActualNeighbors(sourceNode)) {
            for (Node n2 : getActualNeighbors(n1)) {
                for (Node n3 : getActualNeighbors(n2)) {
                    if (n3.isSameNodeAs(destinationNode)) {
                        ArrayList<Node> tmp = new ArrayList<>();
                        tmp.add(sourceNode);
                        tmp.add(n1);
                        tmp.add(n2);
                        tmp.add(destinationNode);
                        paths.add(tmp);
                    }
                }
            }
        }
        return paths;
    }

}

