package com.codestates.preproject040.dto.security;

import java.util.Map;

@SuppressWarnings("unchecked")
public record GoogleOAuth2Response(
        String sub,
        String email,
        String name,
        String photoUrl
) {
    public static GoogleOAuth2Response from(Map<String, Object> attributes) {
        return new GoogleOAuth2Response(
                String.valueOf(attributes.get("sub")),
                String.valueOf(attributes.get("email")),
                String.valueOf(attributes.get("name")),
                String.valueOf(attributes.get("photoUrl"))
        );
    }

    public String sub() { return sub; }
    public String email() { return email; }
    public String nickname() { return name; }
    public String photoUrl() { return photoUrl; }

}
