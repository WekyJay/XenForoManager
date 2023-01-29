package cn.wekyjay.www.tools.xfmanager;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;

import cn.wekyjay.www.tools.xfmanager.gui.MainGUI;

public class Main {
//	public static CountDownLatch latch = new CountDownLatch(1);
	public static void main(String[] args) {
			
			
			FlatLightLaf.setup();
			
			FlatLaf.setUseNativeWindowDecorations(true);
			
			MainGUI mg = new MainGUI();
	
			new GlobalLoader();
			
			mg.openGUI();

			
			
			
			
			

	}
}

