import com.seitptt.controller.Controller;
import com.seitptt.model.Core;
import com.seitptt.view.View;

public class TestMain {
	public static void main(String[] args) {
		Core model=new Core();
		Controller controller=new Controller(model);
		View view=new View(controller,model);
		controller.setView(view);
		view.setVisible(true);
		
	}

}
