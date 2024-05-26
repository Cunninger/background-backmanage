package cn.yam.backmanage.entity.algo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * 功能：
 * 日期：2024/4/29 下午9:50
 */
@Getter
@Setter
public class LuoGuData {
    public String fans;
    public String hasSumbitted;
    public String hasAccepted;
    public String Ranking;

    // toString
    @Override
    public String toString() {
        return "LuoGuData{" +
                "fans=" + fans +
                ", hasSumbitted=" + hasSumbitted +
                ", hasAccepted=" + hasAccepted +
                ", Ranking=" + Ranking +
                '}';
    }
}