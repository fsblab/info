/*
Diese Datei wurde durch InfoBox ersetzt.
Diese Datei hat keinen Nutzen mehr.
*/

import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;


public class SetInfoBox extends JPanel {
    public static void setStandartInfoBox(JPanel parentPanel, Point mousePosition) {
        JPanel infoPanel = new JPanel(new GridBagLayout());

        GridBagConstraints layoutConstraints = new GridBagConstraints();


        //labels damit man weiß was man eintragen muss
        //
        JLabel surenameLabel = new JLabel("Surname:");
        JLabel forenameLabel = new JLabel("Forename:");
        JLabel dobLabel = new JLabel("Date of Birth:");
        JLabel localityLabel = new JLabel("Locality:");
        JLabel streetLabel = new JLabel("Street:");
        JLabel occupationLabel = new JLabel("Occupation:");
        JLabel otherLabel = new JLabel("Other:");


        //textfelder zum eintragen
        //
        int columns = 15;

        JTextField surename = new JTextField();
        surename.setColumns(columns);
        JTextField forename = new JTextField();
        forename.setColumns(columns);
        JTextField dob = new JTextField();
        dob.setColumns(columns);
        JTextField locality = new JTextField();
        locality.setColumns(columns);
        JTextField street = new JTextField();
        street.setColumns(columns);
        JTextField occupation = new JTextField();
        occupation.setColumns(columns);
        JTextField other = new JTextField();
        other.setColumns(columns);


        //Panel ganz oben damit man die position vom infopanel ändern kann
        //
        layoutConstraints.weightx = 0.1;
        layoutConstraints.weighty = 0.2;

        JPanel dragAndDropPanel = new JPanel();
        dragAndDropPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        dragAndDropPanel.setSize(100, 10);
        dragAndDropPanel.setCursor(new Cursor(Cursor.MOVE_CURSOR));
        layoutConstraints.gridx = 0;
        layoutConstraints.gridy = 0;
        layoutConstraints.gridwidth = 3;
        infoPanel.add(dragAndDropPanel, layoutConstraints);


        //Label an die richtige position setzen
        //
        layoutConstraints.weighty = 0;
        layoutConstraints.gridwidth = 1;
        layoutConstraints.gridy = 1;
        infoPanel.add(surenameLabel, layoutConstraints);

        layoutConstraints.gridy = 2;
        infoPanel.add(forenameLabel, layoutConstraints);

        layoutConstraints.gridy = 3;
        infoPanel.add(dobLabel, layoutConstraints);

        layoutConstraints.gridy = 4;
        infoPanel.add(localityLabel, layoutConstraints);

        layoutConstraints.gridy = 5;
        infoPanel.add(streetLabel, layoutConstraints);

        layoutConstraints.gridy = 6;
        infoPanel.add(occupationLabel, layoutConstraints);

        layoutConstraints.gridy = 7;
        infoPanel.add(otherLabel, layoutConstraints);


        //textfelder an die richtige position setzen
        //
        layoutConstraints.gridx = 1;
        layoutConstraints.gridy = 1;
        infoPanel.add(surename, layoutConstraints);

        layoutConstraints.gridy = 2;
        infoPanel.add(forename, layoutConstraints);

        layoutConstraints.gridy = 3;
        infoPanel.add(dob, layoutConstraints);

        layoutConstraints.gridy = 4;
        infoPanel.add(locality, layoutConstraints);

        layoutConstraints.gridy = 5;
        infoPanel.add(street, layoutConstraints);

        layoutConstraints.gridy = 6;
        infoPanel.add(occupation, layoutConstraints);

        layoutConstraints.gridy = 7;
        infoPanel.add(other, layoutConstraints);


        //Button um für mehr details
        //
        JButton editButton = new JButton("Edit");
        JButton infoButton = new JButton("info");

        layoutConstraints.gridx = 2;
        layoutConstraints.gridy = 7;
        infoPanel.add(editButton, layoutConstraints);

        layoutConstraints.gridx = 3;
        infoPanel.add(infoButton, layoutConstraints);


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
            infoPanel.add(picturePanel, layoutConstraints);
        } catch (Exception e) {
            System.out.println(e);
        }



        //das panel in den großen panel setzen
        //
        infoPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        infoPanel.setSize(400,170);
        infoPanel.setLocation(mousePosition);

        parentPanel.add(infoPanel);
        parentPanel.validate();
        parentPanel.repaint();
    }
}