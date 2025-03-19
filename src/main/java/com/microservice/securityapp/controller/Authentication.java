/*package com.microservice.securityapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("v1")
public class Authentication {
  @Autowired
  private SessionRegistry sessionRegistry;
  @GetMapping("/test")
  private String test(){
  return "alberto";
  }
  @GetMapping("/dashboard")
    private String dashboard(){
    return "dashboard";
  }

  @GetMapping("/session")
    private ResponseEntity<?> getDetailsSession(){
    String sessionId="";
    User  userObject=null;
    List<Object> sessions= sessionRegistry.getAllPrincipals();
    for (Object session:sessions){
      if(session instanceof User){
        userObject=(User)session;
      }
      List<SessionInformation> sessionInformations=sessionRegistry.getAllSessions(session,false);
      for(SessionInformation sessionInformation:sessionInformations){
        if(sessionId.equals(sessionInformation.getSessionId())){

        }
      }
    }
    Map<String,Object> response = new HashMap<>();
    response.put("response","sessionResponse");
    response.put("sessionId",sessionId);
    response.put("userObject",userObject);
    return (ResponseEntity<?>) ResponseEntity.ok(response);
  }


}

 */