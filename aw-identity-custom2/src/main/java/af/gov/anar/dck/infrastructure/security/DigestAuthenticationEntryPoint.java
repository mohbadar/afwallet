package af.gov.anar.dck.infrastructure.security;//package com.nsia.config;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//public class DigestAuthenticationEntryPoint extends DigestAuthenticationEntryPoint
//{
//    @Override
//    public void commence( HttpServletRequest request, HttpServletResponse response,
//                          AuthenticationException authException )
//        throws IOException, ServletException
//    {
//        HttpServletResponse httpResponse = ( HttpServletResponse ) response;
//        String authHeader = httpResponse.getHeader( "WWW-Authenticate" );
//        if( authHeader != null )
//        {
//            httpResponse.sendError( HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase() );
//        }
//        else
//        {
//            super.commence( request, httpResponse, authException );
//        }
//    }
//}