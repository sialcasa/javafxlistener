package de.buildpath.objects;

import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class ShortLivingObjectWithAnonymousListener {

	public ShortLivingObjectWithAnonymousListener() {

	}

	public ShortLivingObjectWithAnonymousListener(
			final StringProperty longLivingString) {
		longLivingString.addListener(new ChangeListener<String>() {
			public void changed(ObservableValue<? extends String> observable,
					String oldValue, String newValue) {
				// TODO Auto-generated method stub
			}
		});
	}

	public ShortLivingObjectWithAnonymousListener(
			final StringProperty longLivingString, boolean b) {

		final ChangeListener<String> changeListener = new ChangeListener<String>() {
			public void changed(ObservableValue<? extends String> observable,
					String oldValue, String newValue) {
				// TODO Auto-generated method stub
			}
		};
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
