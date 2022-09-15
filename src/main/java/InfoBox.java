import lombok.Data;

import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;


/*
* diese datei realisiert das kleine info feld, in das die informationen über eine person eingetragen werden können
* */

@Data
public class InfoBox extends JPanel {
    private final GridBagConstraints layoutConstraints;
    private final JFileChooser filechooser;

    private JLabel surnameLabel;
    private JLabel forenameLabel;
    private JLabel dobLabel;
    private JLabel localityLabel;
    private JLabel streetLabel;
    private JLabel occupationLabel;
    private JLabel otherLabel;

    private JTextField surname;
    private JTextField forename;
    private JTextField dob;
    private JTextField locality;
    private JTextField street;
    private JTextField occupation;
    private JTextField other;

    private JPanel dragAndDropPanel;
    private final JPanel picturePanel;

    private JButton minimizeButton;
    private JButton deleteButton;
    private JButton pictureButton;
    private JButton infoButton;

    InfoBox(final JPanel parentPanel) {
        this.setLayout(new GridBagLayout());

        this.layoutConstraints = new GridBagConstraints();

        this.filechooser = new JFileChooser();


        //labels für persönliche info
        //
        this.surnameLabel = new JLabel("Surname:");
        this.forenameLabel = new JLabel("Forename:");
        this.dobLabel = new JLabel("Date of Birth:");
        this.localityLabel = new JLabel("Locality:");
        this.streetLabel = new JLabel("Street:");
        this.occupationLabel = new JLabel("Occupation:");
        this.otherLabel = new JLabel("Other:");



        //textfelder zu den labels zum eintragen
        //
        int columns = 15;

        this.surname = new JTextField();
        this.surname.setColumns(columns);
        this.forename = new JTextField();
        this.forename.setColumns(columns);
        this.dob = new JTextField();
        this.dob.setColumns(columns);
        this.locality = new JTextField();
        this.locality.setColumns(columns);
        this.street = new JTextField();
        this.street.setColumns(columns);
        this.occupation = new JTextField();
        this.occupation.setColumns(columns);
        this.other = new JTextField();
        this.other.setColumns(columns);


//        Person personInfo = new Person(surename.getText(), forename.getText(), dob.getText(), locality.getText(), street.getText(), occupation.getText(), other.getText());



        //Panel ganz oben damit man die position vom infopanel ändern kann mit drag and drop
        //
        this.layoutConstraints.weightx = 0.1;
        this.layoutConstraints.weighty = 0.2;

        final JPanel tempPanel = this;                                            //in Actionlistenern ist "this" selber eine Instanz des Actionlisteners, also variable für "this" als instanz der InfoBox Klasse

        this.dragAndDropPanel = new JPanel();
        this.dragAndDropPanel.addMouseListener(new MouseListener() {             //listener um infobox instanz position auf parentpanel zu verändern
            boolean pressed = false;
            @Override
            public void mouseClicked(MouseEvent e) {}
            @Override
            public void mousePressed(MouseEvent e) {
                pressed = true;
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                if (pressed) {
                    pressed = false;
                    tempPanel.setLocation(parentPanel.getMousePosition().x - 133, parentPanel.getMousePosition().y - 10);
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        });

        this.dragAndDropPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        this.dragAndDropPanel.setSize(100, 10);
        this.dragAndDropPanel.setCursor(new Cursor(Cursor.MOVE_CURSOR));

        this.layoutConstraints.gridx = 0;
        this.layoutConstraints.gridy = 0;
        this.layoutConstraints.gridwidth = 2;


        this.add(this.dragAndDropPanel, this.layoutConstraints);



        //Button um die Infobox zu minimieren und maximieren
        //
        this.minimizeButton = new JButton();
        this.minimizeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentPanel.remove(tempPanel);
                //TODO: minimierten panel erschaffen an der selben position
                parentPanel.validate();
                parentPanel.repaint();
            }
        });

        this.minimizeButton.setMargin(new Insets(0, 0, 0, 0));
        this.minimizeButton.setBackground(Color.WHITE);
        try {
            BufferedImage minimizePic = ImageIO.read(MainWindow.class.getResource("min.png"));
            this.minimizeButton.setIcon(new ImageIcon(minimizePic));
        } catch (Exception e) {
            System.out.println(e);
        }

        this.layoutConstraints.gridwidth = 1;
        this.layoutConstraints.gridx = 2;
        this.layoutConstraints.gridy = 0;
        this.add(this.minimizeButton, this.layoutConstraints);



        //Button um die infobox wieder zu löschen
        //
        this.deleteButton = new JButton();
        this.deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    parentPanel.remove(tempPanel);
                    parentPanel.validate();
                    parentPanel.repaint();
            }
        });

        this.deleteButton.setMargin(new Insets(0, 0, 0, 0));
        this.deleteButton.setBackground(Color.WHITE);
        try {
            BufferedImage deletePic = ImageIO.read(MainWindow.class.getResource("del.png"));
            this.deleteButton.setIcon(new ImageIcon(deletePic));
        } catch (Exception e) {
            System.out.println(e);
        }

        remove(tempPanel);                                                  //variable tempPanel löschen, um hoffentlich speicher freizugeben + sollte im folgenden nicht mehr essentiell sein
        this.layoutConstraints.gridwidth = 1;
        this.layoutConstraints.gridx = 3;
        this.layoutConstraints.gridy = 0;
        this.add(this.deleteButton, this.layoutConstraints);



        //Label an die richtige position setzen
        //
        this.layoutConstraints.weighty = 0;
        this.layoutConstraints.gridx = 0;
        this.layoutConstraints.gridy = 1;
        this.add(this.surnameLabel, this.layoutConstraints);

        this.layoutConstraints.gridy = 2;
        this.add(this.forenameLabel, this.layoutConstraints);

        this.layoutConstraints.gridy = 3;
        this.add(this.dobLabel, this.layoutConstraints);

        this.layoutConstraints.gridy = 4;
        this.add(this.localityLabel, this.layoutConstraints);

        this.layoutConstraints.gridy = 5;
        this.add(this.streetLabel, this.layoutConstraints);

        this.layoutConstraints.gridy = 6;
        this.add(this.occupationLabel, this.layoutConstraints);

        this.layoutConstraints.gridy = 7;
        this.add(this.otherLabel, this.layoutConstraints);



        //textfelder an die richtige position setzen
        //
        this.layoutConstraints.gridx = 1;
        this.layoutConstraints.gridy = 1;
        this.add(this.surname, this.layoutConstraints);

        this.layoutConstraints.gridy = 2;
        this.add(this.forename, this.layoutConstraints);

        this.layoutConstraints.gridy = 3;
        this.add(this.dob, this.layoutConstraints);

        this.layoutConstraints.gridy = 4;
        this.add(this.locality, this.layoutConstraints);

        this.layoutConstraints.gridy = 5;
        this.add(this.street, this.layoutConstraints);

        this.layoutConstraints.gridy = 6;
        this.add(this.occupation, this.layoutConstraints);

        this.layoutConstraints.gridy = 7;
        this.add(this.other,this. layoutConstraints);



        this.picturePanel = new JPanel();                                 //dieses panel existiert nur, damit ich eine umrandung für das bild setzen kann, die größer ist als das bild

        this.picturePanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        this.picturePanel.setSize(100, 100);
        this.layoutConstraints.gridy = 1;
        this.layoutConstraints.gridx = 2;
        this.layoutConstraints.gridheight = 6;
        this.layoutConstraints.gridwidth = 2;

        this.setPicture(null, this.picturePanel, this.layoutConstraints);


        //Button um für mehr details
        //
        this.pictureButton = new JButton("Pic");
        this.pictureButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int filechoice = filechooser.showOpenDialog(parentPanel);

                    if (filechoice == JFileChooser.APPROVE_OPTION) {
                        File imageFile = filechooser.getSelectedFile();

                        layoutConstraints.gridy = 1;
                        layoutConstraints.gridx = 2;
                        layoutConstraints.gridheight = 6;
                        layoutConstraints.gridwidth = 2;

                        setPicture(imageFile, picturePanel, layoutConstraints);
                    }
                } catch (Exception picExcept) {
                    System.out.println(picExcept);
                }
            }
        });

        this.infoButton = new JButton("info");

        this.layoutConstraints.gridheight = 1;
        this.layoutConstraints.gridwidth = 1;
        this.layoutConstraints.gridx = 2;
        this.layoutConstraints.gridy = 7;
        this.add(this.pictureButton, this.layoutConstraints);

        this.layoutConstraints.gridx = 3;
        this.add(this.infoButton, this.layoutConstraints);


        /*
        //kleines bild der person
        //
        try {
            BufferedImage picture = ImageIO.read(MainWindow.class.getResource("resources/blankFace.png"));
            JPanel picturePanel = new JPanel();                                 //dieses panel existiert nur, damit ich eine umrandung für das bild setzen kann, die größer ist als das bild

            picturePanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            picturePanel.setSize(100, 100);
            layoutConstraints.gridy = 1;
            layoutConstraints.gridx = 2;
            layoutConstraints.gridheight = 6;
            layoutConstraints.gridwidth = 2;

            Image resizedPicture = picture.getScaledInstance(picturePanel.getWidth(), picturePanel.getHeight(), Image.SCALE_SMOOTH);
            JLabel pictureLabel = new JLabel(new ImageIcon(resizedPicture));
            picturePanel.add(pictureLabel);
            this.add(picturePanel, layoutConstraints);
        } catch (Exception e) {
            System.out.println(e);
        }*/



        //das panel in den großen panel setzen
        //
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.setSize(400,170);
        this.setLocation(parentPanel.getMousePosition());
    }


    //keine ahnung ich glaube das als Funktion macht mir die Arbeit einfacher oder so
    //
    private void setPicture(File imageFile, JPanel picturePanel, GridBagConstraints layoutConstraints) {
        BufferedImage picture;
        System.out.println(imageFile);
        if (imageFile == null) {
            try {
                System.out.println("position 1");
//                picture = ImageIO.read(MainWindow.class.getResource(new File("resources/blankFace.png").getAbsolutePath().replace("\\", "/")));    doesn't work
                picture = ImageIO.read(MainWindow.class.getResource("blankFace.png"));

                Image resizedPicture = picture.getScaledInstance(picturePanel.getWidth(), picturePanel.getHeight(), Image.SCALE_SMOOTH);
                JLabel pictureLabel = new JLabel(new ImageIcon(resizedPicture));
                picturePanel.add(pictureLabel);
                this.add(picturePanel, layoutConstraints);
                System.out.println("position 2");
            } catch (Exception e) {
                System.out.println(e);
            }
        } else {
            try {
                System.out.println("position 3");
//                String absPath = imageFile.getAbsolutePath().replace('\\', '/');
//                System.out.println(absPath);
//                Files.copy(Paths.get(absPath), Paths.get("resources/test.png"));
                picture = ImageIO.read(MainWindow.class.getResource("del.png"));
//                picture = ImageIO.read(MainWindow.class.getResource(absPath));
                System.out.println("position 4");

                Image resizedPicture = picture.getScaledInstance(picturePanel.getWidth(), picturePanel.getHeight(), Image.SCALE_SMOOTH);
                JLabel pictureLabel = new JLabel(new ImageIcon(resizedPicture));
                picturePanel.add(pictureLabel);
                this.add(picturePanel, layoutConstraints);
                picturePanel.validate();
                picturePanel.repaint();
                System.out.println("position 5");
            } catch (Exception e) {
                System.out.println("position exception");
                System.out.println(e);
            }
        }
    }
}

//TODO: drag and drop korrigieren
//TODO: button zum minimieren des infopanels
//TODO: bild per mausklick auf picturepanel auswählen und ändern