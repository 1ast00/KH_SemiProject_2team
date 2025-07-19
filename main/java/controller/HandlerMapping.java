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
		case "socialLogin":
            controller = new SocialLoginController();
            break;
		case "naverCallback":
            controller = new NaverCallbackController();
            break;
        case "kakaoCallback":
            controller = new KakaoCallbackController();
            break;
		case "memberLogout":
			controller = new MemberLogoutController();
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
		case "missingInsert":
		    controller = new MissingInsertController();
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
		case "img/witness" :
			controller = new WitnessImageController();
			break;
		case "missingSearch": //추가
		    controller = new MissingSearchController();
		    break;
		case "noticeList" : //추가
			controller = new NoticeListController();
			break;
		case "noticeView":  //추가
			controller = new NoticeViewController();
			break;
		case "legalInfoList" : //추가
			controller = new LegalInfoListController();
			break;
		}
		return controller;
	}
}