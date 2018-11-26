package com.test.mychessapplication.Data;

public class Node {
    private int x;
    private int y;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    boolean isInsideChessboard() {
        return x>=0 && y>=0 && x<ChessBoard.chessSize && y<ChessBoard.chessSize;
    }

    boolean isSameNodeAs(Node node) {
        return x==node.x && y==node.y;
    }

}
