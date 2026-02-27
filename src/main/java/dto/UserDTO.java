package com.revhire.user.dto;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {

    private Integer userId;

    private String name;

    private String email;

    private String phone;

    private String location;

    private String currentEmploymentStatus;
    // EMPLOYED / UNEMPLOYED / STUDENT etc.

    private String password;

    private String role;
    // JOB_SEEKER / EMPLOYER

    private LocalDateTime createdAt;
}