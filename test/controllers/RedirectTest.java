package controllers;

import org.junit.Test;
import play.Application;
import play.inject.guice.GuiceApplicationBuilder;
import play.mvc.Http;
import play.mvc.Result;
import play.test.Helpers;
import play.test.WithApplication;

import static org.junit.Assert.assertEquals;
import static play.test.Helpers.GET;
import static play.test.Helpers.route;


public class RedirectTest extends WithApplication {

    @Override
    protected Application provideApplication() {
        return new GuiceApplicationBuilder().build();
    }

    @Test
    public void セッションがないときにトップページからログイン画面にリダイレクト() {
        Http.RequestBuilder request = Helpers.fakeRequest()
                .method(GET)
                .uri("/");

        Result result = route(app, request);
        assertEquals("/login", result.redirectLocation().get());
    }

    @Test
    public void セッションがないときに編集ページからログイン画面にリダイレクト() {
        Http.RequestBuilder request = Helpers.fakeRequest()
                .method(GET)
                .uri("/editComment/1");

        Result result = route(app, request);
        assertEquals("/login", result.redirectLocation().get());
    }

    @Test
    public void セッションがないときに検索ページからログイン画面にリダイレクト() {
        Http.RequestBuilder request = Helpers.fakeRequest()
                .method(GET)
                .uri("/searchComments");

        Result result = route(app, request);
        assertEquals("/login", result.redirectLocation().get());
    }

    @Test
    public void セッションがないときにユーザーページからログイン画面にリダイレクト() {
        Http.RequestBuilder request = Helpers.fakeRequest()
                .method(GET)
                .uri("/user");

        Result result = route(app, request);
        assertEquals("/login", result.redirectLocation().get());
    }
}