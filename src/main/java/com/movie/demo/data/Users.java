package com.movie.demo.data;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name="user_details")
public class Users {
    @Id
    @SequenceGenerator(name = "user_details_id_seq", allocationSize = 1, sequenceName = "user_details_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "user_details_id_seq")
    private int id;
    @Column(name="user_name",unique=true)
    private String name;
    private String password;
    private String roleName;
    @OneToOne
    private UserProfile profileId;
    @OneToMany(mappedBy = "users")
    private List<WatchList> watchLists;

    @OneToMany(mappedBy = "users")
    private List<Reviews> comments;
    public Users(int id,String name, String password, String roleName, UserProfile profileId){
        this.id=id;
        this.name=name;
        this.password=password;
        this.roleName=roleName;
        this.profileId=profileId;
    }
}
