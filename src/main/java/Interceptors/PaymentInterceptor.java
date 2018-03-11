package Interceptors;

import Daos.AccountDao;
import Entities.AccountEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PaymentInterceptor implements HandlerInterceptor {
    @Autowired
    private AccountDao accountDao;

    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        try {
            float paymentSum = Float.valueOf(httpServletRequest.getParameter("payment_sum").replace(',', '.'));
            httpServletRequest.setAttribute("payment_sum", paymentSum);
            //TODO не обновляется request
            //httpServletRequest.getServletContext().getRequestDispatcher().forward(httpServletRequest, httpServletResponse);
            AccountEntity account = accountDao.getAccountByAccountNumber(httpServletRequest.getParameter("payment_account"));
            if (account != null) {
                if ((account.getBalance() - paymentSum) >= 0.0) {
                    return true;
                } else {
                    httpServletResponse.sendRedirect(httpServletRequest.getParameter("previous_request_uri") + "&error=2");
                }
            } else {
                httpServletResponse.sendRedirect(httpServletRequest.getParameter("previous_request_uri") + "&error=3");
            }
            return false;
        } catch (Exception ex) {
            httpServletResponse.sendRedirect(httpServletRequest.getParameter("previous_request_uri") + "&error=1");
            return false;
        }
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
