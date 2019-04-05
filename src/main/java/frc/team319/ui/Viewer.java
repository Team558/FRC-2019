package frc.team319.ui;

import java.awt.FlowLayout;
import java.text.DecimalFormat;

import frc.team254.lib.trajectory.Path;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import frc.team319.trajectory.BobPath;

public class Viewer extends JFrame {

    private static final long serialVersionUID = 1L;
    JTabbedPane tabs;

    public Viewer() {
        setTitle("Paths");
        setLayout(new FlowLayout());
        setVisible(true);
        tabs = new JTabbedPane();
        tabs.setTabPlacement(JTabbedPane.LEFT);
        add(tabs);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void addPath(BobPath bobPath, Path path) {
        displayField(bobPath, path);
        pack();
    }

    private String getPathNameAndTime(Path path) {
        DecimalFormat df = new DecimalFormat("0.00##");
        StringBuilder title = new StringBuilder();
		title.append(path.getName()).append(" : ")
		.append(df.format(path.getTrajectory().getNumSegments() * path.getTrajectory().getSegment(0).dt))
        .append("s");
        return title.toString();
    }

    private void displayField(BobPath bobPath, Path path) {
        tabs.addTab(getPathNameAndTime(path), new FieldComponent("/frc/robot/resources/field_image.png", bobPath, path));
    }
}