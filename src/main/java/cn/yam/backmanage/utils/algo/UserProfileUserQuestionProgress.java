package com.followjs.util;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 功能：
 * 日期：2024/4/14 下午5:25
 */

import java.util.List;
@Getter
@Setter
public class UserProfileUserQuestionProgress {
    private List<DifficultyCount> numAcceptedQuestions;
    private List<DifficultyCount> numFailedQuestions;
    private List<DifficultyCount> numUntouchedQuestions;

    // getters and setters for all fields

    public List<DifficultyCount> getNumAcceptedQuestions() {
        return numAcceptedQuestions;
    }

    public void setNumAcceptedQuestions(List<DifficultyCount> numAcceptedQuestions) {
        this.numAcceptedQuestions = numAcceptedQuestions;
    }

    public List<DifficultyCount> getNumFailedQuestions() {
        return numFailedQuestions;
    }

    public void setNumFailedQuestions(List<DifficultyCount> numFailedQuestions) {
        this.numFailedQuestions = numFailedQuestions;
    }

    public List<DifficultyCount> getNumUntouchedQuestions() {
        return numUntouchedQuestions;
    }

    public void setNumUntouchedQuestions(List<DifficultyCount> numUntouchedQuestions) {
        this.numUntouchedQuestions = numUntouchedQuestions;
    }
}