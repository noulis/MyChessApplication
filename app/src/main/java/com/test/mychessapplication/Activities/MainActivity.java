package com.test.mychessapplication.Activities;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.test.mychessapplication.Data.ChessBoard;
import com.test.mychessapplication.Data.Node;
import com.test.mychessapplication.R;
import com.test.mychessapplication.Widgets.ChessCell;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "xaxa" + MainActivity.class.getSimpleName();
    private boolean hasAlreadySelectedOne = false;
    private Node node1 = null;
    private Node node2 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setCellListeners();
        setResetListener();
    }

    private void setCellListeners() {
        TableLayout chessBoard = findViewById(R.id.chess_board);
        int count = chessBoard.getChildCount();
        for (int i=0; i<count; i++) {
            View v = chessBoard.getChildAt(i);
            if (v instanceof TableRow) {
                TableRow row = (TableRow) v;
                int rowCount = row.getChildCount();
                for (int r=0; r<rowCount; r++) {
                    View v2 = row.getChildAt(r);
                    if (v2 instanceof ChessCell) {
                        ChessCell cell = (ChessCell) v2;
                        cell.setOnClickListener(this);
                    }
                }
            }
        }
    }

    private void setResetListener() {
        Button resetButton = findViewById(R.id.reset_button);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetNodes();
            }
        });
    }

    @Override
    public void onClick(View v) {
        String id = getResources().getResourceEntryName(v.getId());
        Log.d(TAG, "clicked " + id);
        if (! hasAlreadySelectedOne) {
            hasAlreadySelectedOne = true;
            node1 = getNodeFromCellId(id);
            setStrokeToView(v);
            return;
        }
        node2 = getNodeFromCellId(id);
        setStrokeToView(v);
        findPathsBetweenTwoNodes(node1, node2);
    }

    private Node getNodeFromCellId(String id) {
        int x = getXDimenFromLetter(String.valueOf(id.charAt(0)));
        int y = getCorrectYDimen(String.valueOf(id.charAt(1)));
        return new Node(x, y);
    }

    private int getCorrectYDimen(String yDimen) {
        return Integer.valueOf(yDimen) - 1;
    }

    private int getXDimenFromLetter(String xDimen) {
        switch (xDimen) {
            case "a":
                return 0;
            case "b":
                return 1;
            case "c":
                return 2;
            case "d":
                return 3;
            case "e":
                return 4;
            case "f":
                return 5;
            case "g":
                return 6;
            case "h":
                return 7;
        }
        return 0;
    }

    private void findPathsBetweenTwoNodes(Node node1, Node node2) {
        Log.d(TAG, "node 1 x = " + node1.getX() + "y = " + node1.getY());
        Log.d(TAG, "node 2 x = " + node2.getX() + "y = " + node2.getY());
        // todo alert kai sto ok reset
        ArrayList<ArrayList<Node>> paths = new ChessBoard().getPaths(node1, node2);
        showResult(paths);
    }

    private void resetNodes() {
        hasAlreadySelectedOne = false;
        node1 = null;
        node2 = null;
        finish();
        startActivity(getIntent());
    }

    private void setStrokeToView(View v) {
        GradientDrawable border = new GradientDrawable();
        border.setStroke(10, Color.RED);
        v.setBackground(border);
    }

    private void showResult(ArrayList<ArrayList<Node>> paths) {
        String msg = printNodes(paths);
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle(R.string.result_alert_dialog_title);
        alertDialog.setMessage(msg.isEmpty() ? getString(R.string.result_alert_error_msg) : msg);
        alertDialog.setCancelable(false);
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        resetNodes();
                    }
                });
        alertDialog.show();
    }

    private String printNodes(ArrayList<ArrayList<Node>> paths) {
        String out = "";
        for (ArrayList<Node> path : paths) {
            for (Node node : path) {
                int x = node.getX();
                int y = node.getY();
                String move = getXAxis(x) + getYAxis(y);
                out += move + " -> ";
            }
            out += "\n-------\n";
        }
        return out;
    }

    private String getXAxis(int x) {
        switch (x) {
            case 0:
                return "A";
            case 1:
                return "B";
            case 2:
                return "C";
            case 3:
                return "D";
            case 4:
                return "E";
            case 5:
                return "F";
            case 6:
                return "G";
            case 7:
                return "H";
        }
        return "";
    }

    private String getYAxis(int y) {
        return String.valueOf(y+1);
    }

}
