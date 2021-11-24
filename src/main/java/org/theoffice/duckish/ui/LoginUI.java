package org.theoffice.duckish.ui;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


import org.theoffice.duckish.proc.CRUD;
import org.theoffice.duckish.obj.Employee;
import org.theoffice.duckish.proc.DButilities;

public class LoginUI extends JFrame {

    public LoginUI() {
        this.setLayout(null);
        this.setSize(400, 500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        this.setTitle("Duckish - Log In");

        final JButton back = new JButton(" \uF80C ");
        back.setBounds(0, 0, 50, 30);
        back.setFont(new Font("VictorMono Nerd Font", Font.PLAIN, 16));
        back.setBorderPainted(false);
        back.addActionListener(e -> {
            new StartUI();
            this.dispose();
        });
        add(back);

        JLabel title = new JLabel("Duckish");
        title.setBounds(100, 70, 200, 50);
        title.setFont(new Font("Roboto", Font.BOLD, 50));
        add(title);

        JLabel usernameLB = new JLabel("Username");
        usernameLB.setBounds(100, 175, 63, 16);
        add(usernameLB);

        JTextField usernameTF = new JTextField();
        usernameTF.setBounds(100, 200, 250, 30);
        add(usernameTF);

        JLabel passwordLB = new JLabel("Password");
        passwordLB.setBounds(100, 250, 70, 16);
        add(passwordLB);

        JPasswordField passwordTF = new JPasswordField();
        passwordTF.setBounds(100, 275, 250, 30);
        add((passwordTF));

        final JButton logIn = new JButton("Log In");
        logIn.setFocusPainted(false);
        logIn.setBounds(100, 350, 200, 40);
        logIn.addActionListener(e -> {
            
            CRUD myCRUD = new CRUD("root","902020");//Cambiar esto
            myCRUD.connect();
            myCRUD.useDataBase();
            Employee emp = myCRUD.searchEmployee(usernameTF.getText());
            String hash = DButilities.encriptPWD(String.valueOf(passwordTF.getPassword()));
            if (emp.getPassword().equals(hash)) {
                myCRUD.disconnect();
                SystemUI myS = new SystemUI(emp);
                myS.setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(null,
                                "Incorrect Credentials. Press ok to cotinue.");
            }
        });
        add(logIn);

        try {
            BufferedImage img = ImageIO.read(
                    new File("src/main/resources/images/logo_nobg.png")); // wtf?
            JLabel bgLogo = new JLabel(new ImageIcon(img));
            bgLogo.setBounds(220, 300, 200, 200);
            add(bgLogo);

            BufferedImage icon = ImageIO.read(
                    new File("src/main/resources/images/logo.png"));
            this.setIconImage(icon);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
