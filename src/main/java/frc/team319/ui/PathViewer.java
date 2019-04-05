package frc.team319.ui;

import javax.swing.SwingUtilities;

import frc.team254.lib.trajectory.Path;
import frc.team319.trajectory.BobPath;

public class PathViewer {

	private static Viewer viewer = new Viewer();

	public static void showPath(BobPath bobPath, Path path) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				viewer.addPath(bobPath, path);
			}
		});
	}

}
