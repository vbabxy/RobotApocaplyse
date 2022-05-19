package com.robot.robotapocalypse.domain;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppResponse {

    private boolean success;
    private Object data;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object error;
    private String message;
}
