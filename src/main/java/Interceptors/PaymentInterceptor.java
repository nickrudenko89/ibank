package Interceptors;

import Entities.PaymentEntity;
import Enums.PaymentActionEnum;
import Services.PaymentService;
import Utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PaymentInterceptor implements HandlerInterceptor {
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private HttpSession session;

    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        if ((Constants.ApplicationPages.PAYMENT_CONFIRM_PAGE.equals(httpServletRequest.getServletPath()) || Constants.ApplicationPages.EDIT_PAYMENT_CONFIRM_PAGE.equals(httpServletRequest.getServletPath())) && HttpMethod.GET.toString().equals(httpServletRequest.getMethod())) {
            httpServletResponse.sendRedirect("/payments");
            return false;
        } else if (Constants.ApplicationPages.DO_PAYMENT_PAGE.equals(httpServletRequest.getServletPath())) {
            try {
                PaymentActionEnum.valueOf(httpServletRequest.getParameter("action").toUpperCase());
                PaymentEntity payment = paymentService.getPaymentById(Integer.valueOf(httpServletRequest.getParameter("id")));
                if (payment != null) {
                    return true;
                }
            } catch (Exception ex) {
                httpServletResponse.sendRedirect("/payments");
                return false;
            }
        } else if (Constants.ApplicationPages.CHEQUE_PAGE.equals(httpServletRequest.getServletPath())) {
            if (session.getAttribute("account") == null) {
                httpServletResponse.sendRedirect("/payments");
                return false;
            }
        }
        return true;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
