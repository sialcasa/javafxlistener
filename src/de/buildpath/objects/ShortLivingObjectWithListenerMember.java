package de.buildpath.objects;

import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;

public class ShortLivingObjectWithListenerMember {

	private ChangeListener<String> changeListener = new ChangeListenerImpl();

	public ShortLivingObjectWithListenerMember() {
	}

	public ShortLivingObjectWithListenerMember(
			final StringProperty longLivingString) {
		longLivingString.addListener(changeListener);
	}

	public ShortLivingObjectWithListenerMember(
			final StringProperty longLivingString, boolean b) {
		longLivingString.addListener(changeListener);

		new Thread(new Runnable() {
			public void run() {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				longLivingString.removeListener(changeListener);

			}
		}).start();
	}
}
