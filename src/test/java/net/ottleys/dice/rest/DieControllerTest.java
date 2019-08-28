/**
 * Copyright 2019 ottleys.net
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0

 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.ottleys.dice.rest;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import net.ottleys.dice.Die;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void rollStandardVariantTest() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/dice/roll/standard/d6/3")).andExpect(status().isOk()).andReturn();

        String body = result.getResponse().getContentAsString();

        Gson g = new Gson();
        @SuppressWarnings("unchecked")
        Class<List<Die>> cls = (Class<List<Die>>)(Class<?>)List.class;
        List<Die> dice = g.fromJson(body, cls);

        assertEquals(3, dice.size());

    }

    @Test
    public void rollStandardd10Test() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/dice/roll/standard/d10/4")).andExpect(status().isOk()).andReturn();

        String body = result.getResponse().getContentAsString();

        Gson g = new Gson();
        @SuppressWarnings("unchecked")
        Class<List<Die>> cls = (Class<List<Die>>)(Class<?>)List.class;
        List<Die> dice = g.fromJson(body, cls);

        assertEquals(4, dice.size());

    }

    @Test
    public void rollStandardd10Test2() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/dice/roll/standard/d10/4/ONE")).andExpect(status().isOk()).andReturn();

        String body = result.getResponse().getContentAsString();
        Gson g = new Gson();

        Type collectionType = new TypeToken<List<Die>>(){}.getType();
        List<Die> dice = g.fromJson(body, collectionType);

        Die roll1 = dice.get(0);

        assertEquals(4, dice.size());
        assertEquals(1, roll1.min);

    }

    @Test
    public void rollStandardd10Test3() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/dice/roll/standard/d10/4/TEN")).andExpect(status().isOk()).andReturn();

        String body = result.getResponse().getContentAsString();
        Gson g = new Gson();

        Type collectionType = new TypeToken<List<Die>>(){}.getType();
        List<Die> dice = g.fromJson(body, collectionType);
        Die roll1 = dice.get(0);

        assertEquals(4, dice.size());
        assertEquals(0, roll1.min);
        assertEquals(90, roll1.max);

    }

    @Test
    public void rollCustomVariantTest() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/dice/roll/custom/15/5")).andExpect(status().isOk()).andReturn();

        String body = result.getResponse().getContentAsString();

        Gson g = new Gson();
        Type collectionType = new TypeToken<List<Die>>(){}.getType();
        List<Die> dice = g.fromJson(body, collectionType);
        Die roll1 = dice.get(0);

        assertEquals(5, dice.size());
        assertEquals(15, roll1.max);
    }

    @Test
    public void rollCustomVariantTestFail() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/dice/roll/custom/-15/5")).andExpect(status().isBadRequest()).andReturn();

        String body = result.getResponse().getContentAsString();

        Gson g = new Gson();
        ApiError error = g.fromJson(body, ApiError.class);

        assertEquals("Sides must be greater than 1. (-15)",error.getMessage());
    }

    @Test
    public void rollStandardVariantTestFail() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/dice/roll/standard/d7/5")).andExpect(status().isBadRequest()).andReturn();

        String body = result.getResponse().getContentAsString();

        Gson g = new Gson();
        ApiError error = g.fromJson(body, ApiError.class);

        assertEquals("Unknown die variant: d7",error.getMessage());
    }
}