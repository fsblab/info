import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


//inhalt des fensters zu erstellen im konstruktor
//in einer eigenen methode zur besseren übersicht


public class MakeWindowFunction {

    private static boolean newItemButtonPressed = false;
    private static boolean connectButtonPressed = false;


    public static void makeWindow(JFrame input) {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        menu.setMnemonic(KeyEvent.VK_M);
        JMenu view = new JMenu("View");
        view.setMnemonic(KeyEvent.VK_V);

        JToolBar toolbarForList = new JToolBar("registered people");
        JList<String> listOfPeople = new JList<>();
        toolbarForList.add(listOfPeople);
        input.add(toolbarForList, BorderLayout.EAST);

        JMenuItem save = new JMenuItem("save");
        JMenuItem saveAs = new JMenuItem("save as");
        JMenuItem open = new JMenuItem("open");
        JMenuItem settings = new JMenuItem("settings");

        JMenuItem close = new JMenuItem("close");
        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });


        menu.add(save);
        menu.add(saveAs);
        menu.add(open);
        menu.add(settings);
        menu.addSeparator();
        menu.add(close);


        JCheckBoxMenuItem netView = new JCheckBoxMenuItem("net view");
        netView.setState(true);

        JCheckBoxMenuItem familyTreeView = new JCheckBoxMenuItem("family tree view");

        JCheckBoxMenuItem listPeople = new JCheckBoxMenuItem("list all people");


        view.add(netView);
        view.add(familyTreeView);
        view.addSeparator();
        view.add(listPeople);

        menuBar.add(menu);
        menuBar.add(view);


        //-- hier beginnt der workspace part --//

        JToolBar toolBar = new JToolBar("Toolbar");
        JPanel panel = new JPanel();
        panel.setLayout(null);


        JButton selectButton = new JButton();
        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                newItemButtonPressed = false;
                connectButtonPressed = false;
            }
        });

        JButton newItemButton = new JButton();
        newItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
                newItemButtonPressed = true;
                connectButtonPressed = false;
            }
        });

        JButton connectButton = new JButton();
        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
                newItemButtonPressed = false;
                connectButtonPressed = true;
            }
        });


        try {
            Image selectPic = ImageIO.read(MainWindow.class.getResource("select.png"));
            selectButton.setIcon(new ImageIcon(selectPic));

            Image newItemPic = ImageIO.read(MainWindow.class.getResource("newItem.png"));
            newItemButton.setIcon(new ImageIcon(newItemPic));

            Image connectPic = ImageIO.read(MainWindow.class.getResource("connect.png"));
            connectButton.setIcon(new ImageIcon(connectPic));
        } catch (Exception e) {
            System.out.println(e);
        }

        toolBar.setLayout(new BoxLayout(toolBar, BoxLayout.PAGE_AXIS));
        toolBar.add(selectButton);
        toolBar.add(newItemButton);
        toolBar.add(connectButton);

        toolBar.setSize(65,140);
        toolBar.setLocation(0,0);

        panel.add(toolBar);


        panel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (newItemButtonPressed) {
//                    SetInfoBox.setStandartInfoBox(panel, e.getPoint());           //testweise auskommentiert, um es mit einer klasse anstelle einer funktion zu probieren

                    panel.add(new InfoBox(panel));
                    panel.validate();
                    panel.repaint();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });


        input.add(menuBar, BorderLayout.PAGE_START);
//        input.add(toolBar, BorderLayout.WEST);
        input.add(panel, BorderLayout.CENTER);
    }
}