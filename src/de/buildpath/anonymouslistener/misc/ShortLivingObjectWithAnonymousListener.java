package de.buildpath.anonymouslistener.misc;

import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.WeakChangeListener;

public class ShortLivingObjectWithAnonymousListener {

	public enum Type {
		NOLISTENER, LISTENER, WEAKLISTENER, REMOVELISTENER;
	}

	public ShortLivingObjectWithAnonymousListener(
			final StringProperty longLivingString, Type type) {
		switch (type) {
		case LISTENER:
			listenerExample(longLivingString);
			break;
		case WEAKLISTENER:
			weakListenerExample(longLivingString);
			break;
		case REMOVELISTENER:
			removeListenerExample(longLivingString);
			break;
		default:
			break;
		}

	}

	private void listenerExample(StringProperty longLivingString) {
		longLivingString.addListener(createChangelistener());
	}

	private void weakListenerExample(StringProperty longLivingString) {
		longLivingString.addListener(new WeakChangeListener<String>(
				createChangelistener()));
	}

	private void removeListenerExample(final StringProperty longLivingString) {
		final ChangeListener<String> changeListener = createChangelistener();
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

	private ChangeListener<String> createChangelistener() {
		return new ChangeListener<String>() {
			public void changed(ObservableValue<? extends String> observable,
					String oldValue, String newValue) {

			}
		};
	}
}
