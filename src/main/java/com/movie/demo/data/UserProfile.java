package com.movie.demo.data;

import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserProfile {
    @Id
    @SequenceGenerator(name = "user_profile_id_seq",allocationSize = 1,sequenceName = "user_profile_id_seq")
    @GeneratedValue(generator = "user_profile_id_seq",strategy = GenerationType.SEQUENCE)
    private int id;
    @Column(unique=true,nullable = false)
    private String userName;
    @Column(nullable = false)
    private String password;
    @Column(unique = true,nullable = false)
    private String email;
    @OneToOne(mappedBy = "profileId")
    private Users usersId;
}
