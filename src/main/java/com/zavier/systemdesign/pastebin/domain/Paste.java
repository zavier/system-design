package com.zavier.systemdesign.pastebin.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity(name = "paste")
public class Paste {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "short_link", length = 7)
    public String shortLink;

    @Column(name = "expiration_length_in_minutes")
    private Integer expirationLength;

    @Column(name = "created_time")
    private Date createTime;

    @Column(name = "paste_path")
    private String pastePath;
}
