package controllers;

import com.google.common.collect.ImmutableMap;
import org.junit.Test;
import play.Application;
import play.api.test.CSRFTokenHelper;
import play.inject.guice.GuiceApplicationBuilder;
import play.mvc.Http;
import play.mvc.Result;
import play.test.Helpers;
import play.test.WithApplication;

import static org.junit.Assert.assertEquals;
import static play.mvc.Http.Status.BAD_REQUEST;
import static play.test.Helpers.POST;
import static play.test.Helpers.route;

public class ModelTest extends WithApplication {

    @Override
    protected Application provideApplication() {
        return new GuiceApplicationBuilder().build();
    }
    @Test
    public void nameとpasswordが未入力の場合はバリデーションエラー() {
        Http.RequestBuilder request = Helpers.fakeRequest()
                .method(POST)
                .bodyForm(ImmutableMap.of("name", "", "password", ""))
                .uri("/login");

        request = CSRFTokenHelper.addCSRFToken(request);

        Result result = route(app, request);
        assertEquals(BAD_REQUEST, result.status());
    }
}
