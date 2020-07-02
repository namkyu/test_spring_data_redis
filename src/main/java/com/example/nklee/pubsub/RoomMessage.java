package com.example.nklee.pubsub;

import lombok.*;

import java.io.Serializable;

/**
 * @Project : test_spring_data_redis
 * @Date : 2020-06-01
 * @Author : nklee
 * @Description :
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomMessage implements Serializable {

    private static final long serialVersionUID = 2082503192322391880L;
    private String roomId;
    private String name;
    private String message;
}
