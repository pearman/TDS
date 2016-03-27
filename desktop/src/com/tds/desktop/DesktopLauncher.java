package com.tds.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.tds.TDS;
import java.awt.Toolkit;
import java.awt.Dimension;


public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                config.width = (int)screenSize.getWidth();
                config.height = (int)screenSize.getHeight();
                config.fullscreen = true;
		new LwjglApplication(new TDS(), config);
	}
}
