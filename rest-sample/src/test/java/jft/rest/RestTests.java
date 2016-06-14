package jft.rest;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Set;

/**
 * Created by Anna on 14.06.16.
 */
public class RestTests {

    @Test
    public void testCreateIssue() throws IOException {
        Set<Issue> oldIssues= getIssues();
        Issue newIssue = new Issue().withSubject("test subject").withDescription("test description");
        int id = createIssue(newIssue);
        Set<Issue> newIssues= getIssues();
        oldIssues.add(newIssue.withId(id));
        Assert.assertEquals(newIssues, oldIssues);
    }

    private int createIssue(Issue newIssue) throws IOException {
        String json = getExecutor().execute(Request.Post("http://demo.bugify.com/api/issues.json")
                .bodyForm(new BasicNameValuePair("subject", newIssue.getSubject()),
                        new BasicNameValuePair("description", newIssue.getDescription())))
                .returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        return parsed.getAsJsonObject().get("issue_id").getAsInt();
    }

    private Set<Issue> getIssues() throws IOException {
        String json = getExecutor().execute(Request.Get("http://demo.bugify.com/api/issues.json")).returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        return new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){}.getType());
    }

    private Executor getExecutor(){
        return Executor.newInstance().auth("LSGjeU4yP1X493ud1hNniA==", "");
    }


    public void skipIfNotFixed(int issueId) throws IOException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

    public boolean isIssueOpen(int issueId) throws IOException {

        String json = getExecutor().execute(Request.Get("http://demo.bugify.com/api/issues/" + issueId + ".json"))
                .returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonArray issues = parsed.getAsJsonObject().get("issues").getAsJsonArray();
        String status = issues.get(0).getAsJsonObject().get("state_name").getAsString();
        return (status.equals("Open"));
    }

    @Test
    public void testSkipping() throws IOException {
        skipIfNotFixed(1);
        System.out.println("not skipped");
    }
}
