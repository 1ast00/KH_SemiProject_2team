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
		case "memberRegisterView":
			controller = new MemberRegisterViewController();
			break;
		case "memberRegister":
			controller = new MemberRegisterController();
			break;
		case "memberMypageInfoView":
			controller = new MemberMypageInfoView();
			break;
		case "checkMemberId":
			controller = new CheckMemberIdController();
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
		case "adminRegister":
			controller = new AdminRegisterController();
			break;
		case "adminRegisterView":
			controller = new AdminRegisterViewController();
			break;
		case "missingInsertView":
			controller = new MissingInsertViewController();
			break;
		case "missingView":
			controller = new MissingViewController();
			break;
		case "missingList":
			controller = new MissingListController();
			break;
		case "missingMapSearch":
			controller = new MissingMapSearchController();
			break;
		case "missingDelete":
			controller = new MissingDeleteController();
			break;
		case "witnessMapSearch":
			controller = new WitnessMapSearchController();
			break;
		case "witnessInsertView":
			controller = new WitnessInsertViewController();
			break;	
		case "witnessInsert":
			controller = new WitnessInsertController();
			break;
		case "witnessList":
			controller = new WitnessListController();
			break;
		case "witnessView":
			controller = new WitnessViewController();
			break;
		case "witnessDelete" :
			controller = new WitnessDeleteController();
			break;
		}
		return controller;
	}
}