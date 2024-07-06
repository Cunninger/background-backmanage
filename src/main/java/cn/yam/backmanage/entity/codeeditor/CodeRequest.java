package cn.yam.backmanage.entity.codeeditor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 功能：
 * 日期：2024/7/6 上午12:27
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class  CodeRequest {
    private String code;
    private String type;
    private String stdin;
}