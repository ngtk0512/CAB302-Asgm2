package Asgm2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Paint {
    JButton clearBtn, blackBtn, whiteBtn, greenBtn, blueBtn;
    DrawBoard drawBoard;
    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==clearBtn){
                drawBoard.clear

            }else if (e.getSource() == blackBtn){

            }
        }
    };

    public static void main(String[] args) {


    }

    public void display(){
        //create main frame
        JFrame frame = new JFrame("Paint");
        Container content = frame.getContentPane();
        //set layout on contain pane
        content.setLayout(new BorderLayout());
        //create draw board
        drawBoard = new DrawBoard();
        //add to content pane
        content.add(drawBoard,BorderLayout.CENTER);
        //create controls to apply colors
        JPanel controls = new JPanel();

        clearBtn = new JButton("Clear");
        blackBtn = new JButton("Black");
        whiteBtn = new JButton("White");
        blueBtn = new JButton("Blue");
    }
}

