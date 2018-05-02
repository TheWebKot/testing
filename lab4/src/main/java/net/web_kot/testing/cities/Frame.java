package net.web_kot.testing.cities;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    
    private Cities cities;
    
    public Frame() {
        super("Игра в города");
        
        this.setLayout(null);
        this.getContentPane().setPreferredSize(new Dimension(500, 160));
        this.pack();
        
        JTextField input = new JTextField();
        input.setBounds(10, 10, 250, 38);
        input.setFont(input.getFont().deriveFont(18F));
        input.setHorizontalAlignment(JTextField.CENTER);
        this.add(input);
        
        JButton button = new JButton("Ответить!");
        button.setBounds(10, 58, 250, 38);
        button.setFont(button.getFont().deriveFont(14F));
        this.add(button);
        
        JLabel player = new JLabel("Игрок: 1");
        player.setBounds(10, 120, 100, 40);
        player.setFont(player.getFont().deriveFont(18F));
        this.add(player);
        
        JLabel time = new JLabel("00:10");
        time.setBounds(130, 120, 130, 40);
        time.setFont(time.getFont().deriveFont(18F));
        time.setHorizontalAlignment(JTextField.RIGHT);
        this.add(time);
        
        JTextArea log = new JTextArea();
        log.setEditable(false);
        JScrollPane scroll = new JScrollPane(log);
        scroll.setBounds(270, 10, 220, 140);
        this.add(scroll);
        
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        cities = new Cities();
        
        Timer timer = new Timer(100, e -> {
            player.setText("Игрок: " + cities.getCurrentPlayer());
            
            int t = cities.getRemainingTime() / 1000;
            time.setText(formatTime(t / 60) + ":" + formatTime(t % 60));
        });
        timer.start();
        
        button.addActionListener(e -> {
            try {
                cities.answer(input.getText());
                if(log.getText().length() != 0) log.setText(log.getText() + "\n");
                log.setText(log.getText() + " " + input.getText());
                log.setCaretPosition(log.getText().length());
            } catch(Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
            input.setText("");
        });
        input.addActionListener(button.getActionListeners()[0]);
    }
    
    private String formatTime(int value) {
        if(value < 10) return "0" + value;
        return "" + value;
    }
    
}
