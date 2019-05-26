package Asgm2.src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Paint {
    JButton clearBtn, blackBtn, redBtn, blueBtn;
    DrawBoard drawBoard;
    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==clearBtn){
                drawBoard.clear();
            }else if (e.getSource() == blackBtn){
                drawBoard.black();
            }else if (e.getSource() == redBtn){
                drawBoard.red();
            }else if (e.getSource() == blueBtn){
                drawBoard.blue();
            }
        }
    };

    public static void main(String[] args) {
        new Paint().display();

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
        clearBtn.addActionListener(actionListener);
        blackBtn = new JButton("Black");
        blackBtn.addActionListener(actionListener);
        redBtn = new JButton("Red");
        redBtn.addActionListener(actionListener);
        blueBtn = new JButton("Blue");
        blueBtn.addActionListener(actionListener);

        //add to control panel
        controls.add(clearBtn);
        controls.add(blackBtn);
        controls.add(blueBtn);
        controls.add(redBtn);

        //add to content pane
        content.add(controls, BorderLayout.NORTH);
        frame.setSize(600,600);

        //show Swing paint result
        frame.setVisible(true);


    }
}

