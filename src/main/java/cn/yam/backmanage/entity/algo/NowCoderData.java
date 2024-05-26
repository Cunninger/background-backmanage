package cn.yam.backmanage.entity.algo;

import lombok.Getter;
import lombok.Setter;

/**
 * 功能：
 * 日期：2024/4/15 上午8:34
 */
@Getter
@Setter
public class NowCoderData {


    public String rating;
    public String ratingRank;
    public String competitionCount;
    public String hasPassedProblem;
    public String avatar; // 这里是图片的数据，使用Base64编码
    // toString
    @Override
    public String toString() {
        return "NowCoderData{" +
                "rating=" + rating +
                ", ratingRank=" + ratingRank +
                ", competitionCount=" + competitionCount +
                ", hasPassedProblem=" + hasPassedProblem +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}