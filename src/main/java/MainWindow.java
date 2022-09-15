import javax.swing.*;


public class MainWindow extends JFrame {

    private MainWindow(String title) {
        super();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle(title);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        MakeWindowFunction.makeWindow(this);
    }

    public static void main(String[] args) {
        MainWindow window = new MainWindow("info");

        window.setVisible(true);
    }
}