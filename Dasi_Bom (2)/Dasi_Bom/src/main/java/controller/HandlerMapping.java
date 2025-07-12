package controller;

public class HandlerMapping {
	private static HandlerMapping instance = new HandlerMapping();

	private HandlerMapping() {
	}

	public static HandlerMapping getInstance() {
		if (instance == null)
			instance = new HandlerMapping();
		return instance;
	}

	public Controller createController(String command) {
		Controller controller = null;

		switch (command) {
		case "main":
			controller = new MainController();
			break;
		case "memberLoginView":
			controller = new MemberLoginViewController();
			break;
		case "memberLogin":
			controller = new MemberLoginController();
			break;
		case "adminMain":
			controller = new AdminMainController();
			break;
		case "adminLoginView":
			controller = new AdminLoginViewController();
			break;
		case "adminLogin":
			controller = new AdminLoginController();
			break;
		case "missingInsertView":
			controller = new MissingInsertViewController();
			break;
		case "MissingView":
			controller = new MissingViewController();
			break;
		case "missingListView":
			controller = new MissingListViewController();
			break;
		case "missingList":
			controller = new MissingListController();
			break;
		case "missingMapSearch":
			controller = new MissingMapSearchController();
			break;
		case "WitnessView" :
			controller = new WitnessViewController();
			break;
		case "witnessInsertView":
			controller = new WitnessInsertViewController();
			break;
		case "witnessListView" :
			controller = new WitnessListViewController();
			break;
		case"witnessList":
			controller = new WitnessListController();
			break;
		case "witnessMapSearch" :
			controller = new WitnessMapSearchController();
			break;
			
			
		}
		return controller;
	}
}