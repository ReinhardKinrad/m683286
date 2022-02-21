package com.rdlab.marketplace.util;

import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {

  public static String getUserFromSecurityContext() {
    return SecurityContextHolder.getContext().getAuthentication().getName();
  }

}
