package com.atguigu.springcloud.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Storage implements Serializable {

    private static final long serialVersionUID = 1333671438598035418L;

    private Long id;

    private Long productId;

    private Integer total;

    private Integer used;

    private Integer residue;
}
