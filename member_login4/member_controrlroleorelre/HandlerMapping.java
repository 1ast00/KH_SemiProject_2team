package controller;

public class HandlerMapping {
    private static final HandlerMapping instance = new HandlerMapping();

    private HandlerMapping() {}

    public static HandlerMapping getInstance() {
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
            case "adminRegisterView":  
                controller = new AdminRegisterViewController();
                break;
            case "adminRegister":   
                controller = new AdminRegisterController();
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
            case "logout":
                controller = new LogoutController();
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
            case "naverLogin":
                controller = new NaverLoginController();
                break;   
           
                
                
            case "memberFindId":
                controller = new MemberFindIdFormController();
                break;
              case "memberFindIdAction":
                controller = new MemberFindIdActionController();
                break;
              case "memberFindPw":
                controller = new MemberFindPwFormController();
                break;
              case "memberFindPwAction":
                controller = new MemberFindPwActionController();
                break;
              case "memberResetPw":
                controller = new MemberResetPwFormController();
                break;
              case "memberResetPwAction":
                controller = new MemberResetPwActionController();
                break;

                
        }

        return controller;
    }
}
