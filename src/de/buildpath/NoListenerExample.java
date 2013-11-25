package de.buildpath;

import java.lang.ref.WeakReference;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.stage.Stage;
import de.buildpath.objects.ShortLivingObjectWithAnonymousListener;

public class NoListenerExample extends Application {
	// Property which exists the whole application lifetime
	static StringProperty longLivingString = new SimpleStringProperty();

	// Weakreference to check whether the short living object was garbage
	// collected
	private WeakReference<ShortLivingObjectWithAnonymousListener> leakedObject;

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		// Object retains in memory, because it registers a ChangeListener on
		// the long living StringProperty
		leakedObject = new WeakReference<ShortLivingObjectWithAnonymousListener>(
				new ShortLivingObjectWithAnonymousListener());

		// Check whether Object retains in memory
		while (leakedObject.get() != null) {
			System.out.println("Element still alive");
			// Generate some Memory allocation to force the GC
			String[] generateOutOfMemoryStr = new String[999999];
		}

		System.out.println("Released the memory");
	}

}
