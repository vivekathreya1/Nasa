package com.vivek.spacepictures;

import com.vivek.spacepictures.model.Picture;
import com.vivek.spacepictures.utils.Constants;

import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class JsonParsingTest {
    @Rule
    public JsonParsingRule jsonParsingRule = new JsonParsingRule(Constants.GSON);

    @Test
    @JsonFileResource(fileName = "data.json", clazz = Picture[].class)
    public void testGetContributors() throws Exception {

        Picture[] picsList = jsonParsingRule.getValue();
        assertEquals(picsList.length, 26);
    }

}