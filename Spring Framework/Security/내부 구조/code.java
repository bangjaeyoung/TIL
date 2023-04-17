SecurityContext context = SecurityContextHolder.getContext(); // Security Context
Authentication authentication = context.getAuthentication();  // authentication
authentication.getPrincipal();
authentication.getAuthorities();
authentication.getCredentials();
authentication.getDetails();
autehntication.isAuthenticated();
