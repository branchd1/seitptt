package main;
import main.com.seitptt.controller.Controller;
import main.com.seitptt.model.Core;
import main.com.seitptt.view.View;

/**
 * Main class that initializes model, view and controller
 * @author 
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