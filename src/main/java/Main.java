package main.java;
import main.java.com.seitptt.controller.Controller;
import main.java.com.seitptt.model.Core;
import main.java.com.seitptt.view.View;

/**
 * Main class that initializes model, view and controller
 * @author Bokyung Lee (2431088L)
 *
 */
public class Main {
	public static void main(String[] args) {
		// model
		Core model=new Core();

		// controller
		Controller controller=new Controller(model);

		// view
		View view=new View(controller,model);

		controller.setView(view);
		view.setVisible(true);	
	}
}