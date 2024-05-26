package cn.yam.backmanage.entity.algo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 功能：
 * 日期：2024/4/14 下午3:54
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LeetCodeData {
    public int total_solved;
    public int easy_solved;
    public int medium_solved;
    public int hard_solved;

    public String avatar;


    @Override
    public String toString() {
        return "LeetCodeData{" +
                "total_solved=" + total_solved +
                ", easy_solved=" + easy_solved +
                ", medium_solved=" + medium_solved +
                ", hard_solved=" + hard_solved +
                ", avatar='" + avatar + '\'' +
                '}';
    }

}