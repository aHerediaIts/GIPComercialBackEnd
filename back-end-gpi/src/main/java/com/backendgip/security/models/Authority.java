package com.backendgip.security.models;


import org.springframework.security.core.GrantedAuthority;
public class Authority implements GrantedAuthority {
   private static final long serialVersionUID = 4899758780220150147L;
	private String authority;
   public Authority(String authority) {
       this.authority = authority;
   }
   @Override
   public String getAuthority() {
       return this.authority;
   }
	@Override
	public String toString() {
		return  authority;
	}
  
  
}
