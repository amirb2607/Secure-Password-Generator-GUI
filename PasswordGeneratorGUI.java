import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.SecureRandom;

public class PasswordGeneratorGUI extends JFrame {

    private JTextField lengthField;
    private JCheckBox upperCaseCheckBox, lowerCaseCheckBox, digitsCheckBox, specialCharsCheckBox;
    private JButton generateButton;
    private JTextArea passwordTextArea;

    public PasswordGeneratorGUI() {
        setTitle("Secure Password Generator by Amir");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initUI();
    }

    private void initUI() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2, 10, 10));

        panel.add(new JLabel("Desired Password Length:"));
        lengthField = new JTextField();
        panel.add(lengthField);

        panel.add(new JLabel("Include Uppercase Letters:"));
        upperCaseCheckBox = new JCheckBox();
        panel.add(upperCaseCheckBox);

        panel.add(new JLabel("Include Lowercase Letters:"));
        lowerCaseCheckBox = new JCheckBox();
        panel.add(lowerCaseCheckBox);

        panel.add(new JLabel("Include Digits:"));
        digitsCheckBox = new JCheckBox();
        panel.add(digitsCheckBox);

        panel.add(new JLabel("Include Special Characters:"));
        specialCharsCheckBox = new JCheckBox();
        panel.add(specialCharsCheckBox);

        generateButton = new JButton("Generate Password");
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generatePassword();
            }
        });
        panel.add(generateButton);

        passwordTextArea = new JTextArea();
        passwordTextArea.setEditable(false);
        panel.add(passwordTextArea);

        add(panel);
    }

    private void generatePassword() {
        int length = Integer.parseInt(lengthField.getText());
        boolean includeUpperCase = upperCaseCheckBox.isSelected();
        boolean includeLowerCase = lowerCaseCheckBox.isSelected();
        boolean includeDigits = digitsCheckBox.isSelected();
        boolean includeSpecialChars = specialCharsCheckBox.isSelected();

        String password = generateRandomPassword(length, includeUpperCase, includeLowerCase, includeDigits, includeSpecialChars);

        passwordTextArea.setText(password);
    }

    private String generateRandomPassword(int length, boolean includeUpperCase, boolean includeLowerCase,
                                          boolean includeDigits, boolean includeSpecialChars) {
        String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCase = "abcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";
        String specialChars = "!@#$%^&*()_-+=<>?";

        StringBuilder validChars = new StringBuilder();

        if(includeUpperCase) {
            validChars.append(upperCase);
        }
        if(includeLowerCase) {
            validChars.append(lowerCase);
        }
        if(includeDigits) {
            validChars.append(digits);
        }
        if(includeSpecialChars) {
            validChars.append(specialChars);
        }

        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(validChars.length());
            password.append(validChars.charAt(index));
        }
        return password.toString();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PasswordGeneratorGUI().setVisible(true);
            }
        });
    }
}