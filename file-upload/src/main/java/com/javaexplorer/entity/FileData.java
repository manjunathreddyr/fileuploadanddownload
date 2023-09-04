package com.javaexplorer.entity;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "file_data")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileData {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_type")
    private String fileType;

    @Lob
    private byte[] data;
}
