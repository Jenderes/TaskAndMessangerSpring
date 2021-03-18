package com.example.MessangerServer.dto;

public class ProfileDto {
    private String firstName;
    private String lastName;
    private String email;

    public ProfileDto(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}
