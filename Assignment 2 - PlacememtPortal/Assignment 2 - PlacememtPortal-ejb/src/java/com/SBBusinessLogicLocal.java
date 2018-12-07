package com;

import java.util.Map;
import javax.ejb.Local;
import org.json.simple.JSONArray;

@Local
public interface SBBusinessLogicLocal {

    String signIn(String username, String password);

    String signUp(String name, String username, String email, String dob, String password);

    String addQuestion(String question, String option1, String option2, String option3, String option4, String answer, String company);

    String getScore(String username);

    JSONArray getTop(int n);

    JSONArray getHistory(String username);

    JSONArray getCompany();

    JSONArray getQuestions(String company, int count);

    String submitTest(Map<String, String[]> parameterMap, int n, String username);
}
