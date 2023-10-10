/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ab811.webapps2020;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ab811
 */
public class LoginRedirect implements Filter{
    FilterConfig fc;

    /**
     *
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        fc=filterConfig;
    }

    /**
     *
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpreq = (HttpServletRequest) request;
        HttpServletResponse httpres = (HttpServletResponse) response;
        String page = httpreq.getRequestURI();
        if(page.equals("/Webapps2020/")){
            if (httpreq.getUserPrincipal() != null) {
                if(httpreq.isUserInRole("admins")){
                    System.out.println("1");
                    httpres.sendRedirect("/Webapps2020/faces/admins/home.xhtml");
                }else if(httpreq.isUserInRole("users")){
                    httpres.sendRedirect("/Webapps2020/faces/users/home.xhtml");
                    System.out.println("2");
                }
            }else{
                chain.doFilter(request, response);
            }
        }else{
            chain.doFilter(request, response);
        }
    }
    
    /**
     *
     */
    @Override
    public void destroy() {
    }
}
