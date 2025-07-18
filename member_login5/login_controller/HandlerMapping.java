package controller;

import controller.admin.AdminLoginController;
import controller.admin.AdminLoginViewController;
import controller.admin.AdminMainController;
import controller.admin.AdminRegisterController;
import controller.admin.AdminRegisterViewController;
import controller.member.MemberFindIdActionController;
import controller.member.MemberFindIdFormController;
import controller.member.MemberFindPwActionController;
import controller.member.MemberFindPwFormController;
import controller.member.MemberLoginController;
import controller.member.MemberLoginViewController;
import controller.member.MemberRegisterController;
import controller.member.MemberRegisterViewController;
import controller.member.MemberResetPwActionController;
import controller.member.MemberResetPwFormController;
import controller.social.KakaoCallbackController;
import controller.social.NaverCallbackController;
import controller.social.NaverLoginController;
import controller.social.SocialLoginController;

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
         // 로그인 & 회원가입
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

        // 아이디/비밀번호 찾기 & 재설정
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

        // 이메일 인증 관련
        case "emailSend":
            controller = new EmailSendController();
            break;
        case "emailCodeVerify":
            controller = new EmailCodeVerifyController();
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

        }

        return controller;
    }
}
