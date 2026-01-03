import javax.swing.*;
import java.awt.*;

public class T4 {
    static JTextField txt;
    static String op = "";
    static double a = 0;
    public static void main(String[] args) {
        JFrame f = new JFrame("Calculator");
        f.setSize(420, 520);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(new BorderLayout());
        txt = new JTextField("0");
        txt.setFont(new Font("Arial", Font.BOLD, 28));
        txt.setHorizontalAlignment(SwingConstants.CENTER);
        txt.setEditable(false);
        txt.setPreferredSize(new Dimension(400, 70));
        f.add(txt, BorderLayout.NORTH);
        JPanel mid = new JPanel(new BorderLayout());
        JPanel left = new JPanel(new GridLayout(4, 3, 10, 10));
        left.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 5));
        JPanel right = new JPanel(new GridLayout(5, 1, 10, 10));
        right.setBorder(BorderFactory.createEmptyBorder(15, 5, 15, 15));
        addBtn(left, "1"); addBtn(left, "2"); addBtn(left, "3");
        addBtn(left, "4"); addBtn(left, "5"); addBtn(left, "6");
        addBtn(left, "7"); addBtn(left, "8"); addBtn(left, "9");
        addBtn(left, "."); addBtn(left, "0"); addBtn(left, "=");
        addBtn(right, "C");
        addBtn(right, "+");
        addBtn(right, "-");
        addBtn(right, "*");
        addBtn(right, "/");
        mid.add(left, BorderLayout.CENTER);
        mid.add(right, BorderLayout.EAST);
        f.add(mid, BorderLayout.CENTER);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

    static void addBtn(JPanel p, String s) {
        JButton bt = new JButton(s);
        bt.setFont(new Font("Arial", Font.BOLD, 22));
        bt.addActionListener(e -> click(s));
        p.add(bt);
    }

    static void click(String s) {
        if (s.equals("C")) {
            txt.setText("0");
            op = "";
            a = 0;
            return;
        }

        if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")) {
            try {
                a = Double.parseDouble(txt.getText());
            } catch (Exception e) {
                a = 0;
            }
            op = s;
            txt.setText(a + " " + op + " ");
            return;
        }

        if (s.equals("=")) {
            if (op.equals("")) return;
            String t = txt.getText();
            String[] parts = t.split(" ");
            if (parts.length < 3) return;
            double b;
            try {
                b = Double.parseDouble(parts[2]);
            } catch (Exception e) {
                b = 0;
            }

            double r = 0;
            if (op.equals("+")) r = a + b;
            if (op.equals("-")) r = a - b;
            if (op.equals("*")) r = a * b;
            if (op.equals("/")) {
                if (b == 0) {
                    txt.setText("Error");
                    op = "";
                    return;
                }
                r = a / b;
            }

            if (r == (long) r) txt.setText("" + (long) r);
            else txt.setText("" + r);
            op = "";
            return;
        }

        if (txt.getText().contains(" ")) {
            txt.setText(txt.getText() + s);
        } else {
            if (txt.getText().equals("0")) txt.setText(s);
            else txt.setText(txt.getText() + s);
        }
    }
}
