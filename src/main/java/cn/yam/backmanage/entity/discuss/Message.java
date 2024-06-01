package cn.yam.backmanage.entity.discuss;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class Message {
    private String toName;
    private String message;
}


