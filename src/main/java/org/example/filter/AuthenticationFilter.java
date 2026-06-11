package org.example.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebFilter("/customer-dashboard")
public class AuthenticationFilter
        implements Filter {

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req =
                (HttpServletRequest) request;

        HttpServletResponse res =
                (HttpServletResponse) response;

        HttpSession session =
                req.getSession(false);

        if (session == null ||
                session.getAttribute(
                        "loggedInCustomer"
                ) == null) {

            res.sendRedirect(
                    req.getContextPath()
                            + "/login"
            );

            return;
        }

        chain.doFilter(
                request,
                response
        );
    }
}