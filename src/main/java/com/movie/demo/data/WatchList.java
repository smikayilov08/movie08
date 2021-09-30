package com.movie.demo.data;

import lombok.*;

import javax.persistence.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class WatchList {
    @Id
    @SequenceGenerator(sequenceName = "watch_list_id_seq",allocationSize = 1,name = "watch_list_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "watch_list_id_seq")
    private int id;
    private String movieName;
    @Column(nullable = false,columnDefinition="int default '0'")
    private long moviesId;
    @ManyToOne
    private Users users;

    public WatchList(String movieName,Users users,long movieId){
        this.movieName=movieName;
        this.users=users;
        this.moviesId=movieId;
    }
}
