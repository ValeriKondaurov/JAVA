/**
 * Write a description of class SimpleChatBot here.
 *
 * @author Valeriy Kondaurov
 * @version dated Jan 3, 2017
 * @link https://github.com/ValeriKondaurov/Java
 */

package simplechatbot;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;
import javax.swing.text.*; // for StyledDocument
//import bot.*;

public class HW4SimpleChatBot extends JFrame implements ActionListener {
    final String TITLE_OF_PROGRAM = "Chatter: simple chatbot";
    final int START_LOCATION = 200;
    final int WINDOW_WIDTH = 350;
    final int WINDOW_HEIGHT = 450;
    final String CHB_AI = "AI";
    final String BTN_ENTER = "Enter";

    JTextArea dialogue; // area for dialog
    JCheckBox ai;       // enable/disable AI
    JTextField message; // field for entering messages
    // SimpleBot sbot;     // chat-bot class (in bot package)
    SimpleAttributeSet botStyle; // style bot text
    /**
     * Constructor:
     * Creating a window and all the necessary elements on it
     */
    SimpleChatBot() {
        setTitle(TITLE_OF_PROGRAM);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(START_LOCATION, START_LOCATION, WINDOW_WIDTH, WINDOW_HEIGHT);
        // area for dialog
        dialogue = new JTextArea();
        dialogue.setEditable(false);
        JScrollPane scrollBar = new JScrollPane(dialogue);
        // style for bot messages
        botStyle = new SimpleAttributeSet();
        StyleConstants.setItalic(botStyle, true);
        StyleConstants.setForeground(botStyle, Color.blue);
        //StyleConstants.setAlignment(botStyle, StyleConstants.ALIGN_RIGHT);
        // panel for checkbox, message field and button
        JPanel bp = new JPanel();
        bp.setLayout(new BoxLayout(bp, BoxLayout.X_AXIS));
        ai = new JCheckBox(CHB_AI);
        ai.doClick();
        message = new JTextField();
        message.addActionListener(this);
        JButton enter = new JButton(BTN_ENTER);
        enter.addActionListener(this);
        // adding all elements to the window
        bp.add(ai);
        bp.add(message);
        bp.add(enter);
        add(BorderLayout.CENTER, scrollBar);
        add(BorderLayout.SOUTH, bp);
        setVisible(true);
        //sbot = new SimpleBot(); // creating bot object
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                export(dialogue.getText());
                System.out.println("BYE");
            }
        });
    }

    public void export (String log) {
        File file = new File("SimpleChatBotLog.txt");
        FileWriter fr = null;
        try {
            fr = new FileWriter(file);
            fr.write(log);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally{
            try {
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * Listener of events from message field and enter button
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        if (message.getText().trim().length() > 0) {
            try {
                dialogue.append("You: " + message.getText() + "\n");
               dialogue.append(TITLE_OF_PROGRAM.substring(0, 9) + "    "+ "\n");
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        message.setText("");
        message.requestFocusInWindow();
    }






}
