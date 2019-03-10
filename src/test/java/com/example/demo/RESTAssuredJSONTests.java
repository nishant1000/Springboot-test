package com.example.demo;

import com.example.demo.model.Note;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import io.restassured.response.Response;
import org.junit.Test;


import java.lang.reflect.Type;
import java.util.List;

import static io.restassured.RestAssured.get;
import static org.testng.Assert.assertEquals;

public class RESTAssuredJSONTests {
    private final static String ROOT_URI = "http://localhost:8080/api";

    @Test
    public void simple_get_test() {
        Response response = get(ROOT_URI + "/notes/1");
        //System.out.println(response.asString());

        // Get Gson object
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Note note = gson.fromJson(response.asString(), Note.class);
        System.out.println(note);
        Long id = 1L;
        assertEquals(id, note.getId());

        response = get(ROOT_URI + "/notes/");
        Type type = new TypeToken<List<Note>>() {}.getType();
        List<Note> notes = gson.fromJson(response.asString(), type);
        System.out.println(notes);

    }
}
