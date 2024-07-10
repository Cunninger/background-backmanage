package cn.yam.backmanage.entity.codeeditor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 功能：
 * 日期：2024/7/6 下午5:04
 */

/**
 * {
 *     "code": 0,
 *     "data": {
 *         "output":"",
 *         "code": 0,
 *         "time": 0,
 *         "message": ""
 *     }
 * }
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ExecutionResult {
    private int code;
    private ResultData data;


    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class ResultData {
        private String output;
        private int code;
        private int time;
        private String message;

    }
}