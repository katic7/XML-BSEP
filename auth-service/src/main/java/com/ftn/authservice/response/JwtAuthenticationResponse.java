package com.ftn.authservice.response;

import com.ftn.authservice.dto.ProfileDto;

public class JwtAuthenticationResponse {
	
	private ProfileDto profile;
    private String accessToken;

    public JwtAuthenticationResponse(ProfileDto profile, String accessToken) {
    	this.profile = profile;
        this.accessToken = accessToken;
    }
    
    public ProfileDto getProfile() {
		return profile;
	}
    
    public void setProfile(ProfileDto profile) {
		this.profile = profile;
	}

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
    
}
