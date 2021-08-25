package com.example.Segware.controllers;

import java.net.URI;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.Segware.config.BaseApi;

@SpringBootTest
@AutoConfigureMockMvc
class PostsControllerTest extends BaseApi {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void shouldReturn200_whenRequestAllPosts() throws Exception {
		URI uri = new URI("/posts");

		mockMvc.perform(MockMvcRequestBuilders.get(uri)).andExpect(MockMvcResultMatchers.status().is(200));
	};

	@Test
	void shouldReturnAListOfPosts_whenRequestAllPosts() throws Exception {
		URI uri = new URI("/posts");
		String json = "[{\"id\":1,\"upvotes\":5,\"message\":\"Mensagem de teste\",\"titulo\":\"titulo de teste\"}]";
		mockMvc.perform(MockMvcRequestBuilders.get(uri)).andExpect(MockMvcResultMatchers.content().json(json));
	};

	@Test
	void shouldReturnAPost_whenRequestPostDetails() throws Exception {
		URI uri = new URI("/posts/1");
		String json = "{\"id\":1,\"upvotes\":5,\"message\":\"Mensagem de teste\",\"titulo\":\"titulo de teste\"}";
		mockMvc.perform(MockMvcRequestBuilders.get(uri)).andExpect(MockMvcResultMatchers.content().json(json));
	};

	@Test
	void shouldReturn201_whenSendARequestOfTypePost() throws Exception {
		URI uri = new URI("/posts");
		String json = "{\"message\":\"Mensagem de teste\",\"titulo\":\"titulo de teste\"}";
		mockMvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(MockMvcResultMatchers.status().is(201));
	};

	@Test
	void shouldCreateANewPost_whenSendARequestOfTypePost() throws Exception {
		URI uri = new URI("/posts");
		String json = "{\"message\":\"Mensagem de teste\",\"titulo\":\"titulo de teste\"}";
		mockMvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(MockMvcResultMatchers.content().json(json));
	};
	
	@Test
	void shouldEditAPost_whenSendARequestOfTypePatch() throws Exception {
		URI uri = new URI("/posts/1");
		String json = "{\"message\":\"Editada\",\"titulo\":\"titulo Editado\"}";
		mockMvc.perform(MockMvcRequestBuilders.patch(uri).contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(MockMvcResultMatchers.content().json(json));
	};
}
