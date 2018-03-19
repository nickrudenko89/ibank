package Interceptors;

import Entities.AccountEntity;
import Entities.UserEntity;
import Enums.UserTypeEnum;
import Services.AccountService;
import Services.UserService;
import Utils.Constants;
import Utils.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AccountOperationInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;
    @Autowired
    private AccountService accountService;

    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        if (Constants.ApplicationPages.CLOSE_ACCOUNT_PAGE.equals(httpServletRequest.getServletPath())) {
            try {
                AccountEntity account = accountService.getUserAccountById(Integer.valueOf(httpServletRequest.getParameter("id")));
                if (account != null) {
                    UserEntity user = userService.getLoggedUser(Integer.valueOf(CookieUtil.getBankCookie(httpServletRequest).getValue()));
                    if (user.getId() == account.getUser().getId()) {
                        return true;
                    }
                }
            } catch (Exception ex) {
            }
        } else if (Constants.ApplicationPages.OPEN_ACCOUNT_PAGE.equals(httpServletRequest.getServletPath())) {
            String accountCurrency = httpServletRequest.getParameter("currency");
            if (accountCurrency != null && !Constants.Strings.EMPTY_STRING.equals(accountCurrency)) {
                return true;
            }
        } else if (Constants.ApplicationPages.ACCOUNT_OPERATION_QUERY_PAGE.equals(httpServletRequest.getServletPath())) {
            UserEntity user = userService.getLoggedUser(Integer.valueOf(CookieUtil.getBankCookie(httpServletRequest).getValue()));
            if (user.getUserType() == UserTypeEnum.EMPLOYEE.getIndex()) {
                AccountEntity account = accountService.getUserAccountById(Integer.valueOf(httpServletRequest.getParameter("id")));
                if (account != null) {
                    return true;
                }
            }
        }
        httpServletResponse.sendRedirect("/accounts");
        return false;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
