package com.movie.demo.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Reviews {
    @Id
    @SequenceGenerator(allocationSize = 1,name = "reviews_id_seq",sequenceName = "reviews_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "reviews_id_seq")
    private int id;
    @Embedded
    @Column(nullable = false)
    private Comment comments;
    private String moviesName;
    @Column(nullable = false,columnDefinition = "int default '0'")
    private long moviesId;
    @ManyToOne
    private Users users;

    public Reviews(Comment comments,String moviesName,Users users,int id){
        this.comments=comments;
        this.moviesName=moviesName;
        this.users=users;
        this.moviesId=id;
    }
}

