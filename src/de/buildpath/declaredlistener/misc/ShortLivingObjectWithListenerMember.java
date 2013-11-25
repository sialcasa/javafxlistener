package de.buildpath.declaredlistener.misc;

import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.WeakChangeListener;

public class ShortLivingObjectWithListenerMember {

	private ChangeListener<String> changeListener = new ChangeListenerImpl();

	public enum Type {
		NOLISTENER, LISTENER, WEAKLISTENER, REMOVELISTENER;
	}

	public ShortLivingObjectWithListenerMember(
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
		longLivingString.addListener(changeListener);
	}

	private void weakListenerExample(StringProperty longLivingString) {
		longLivingString.addListener(new WeakChangeListener<String>(
				changeListener));
	}

	private void removeListenerExample(final StringProperty longLivingString) {
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
