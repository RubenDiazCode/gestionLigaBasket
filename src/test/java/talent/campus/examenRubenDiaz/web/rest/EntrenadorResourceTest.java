package talent.campus.examenRubenDiaz.web.rest;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import talent.campus.examenRubenDiaz.AssertUtils;
import talent.campus.examenRubenDiaz.dto.EntrenadorPayload;
import talent.campus.examenRubenDiaz.model.Entrenador;
import talent.campus.examenRubenDiaz.repository.EntrenadorRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class EntrenadorResourceTest {

	@Autowired
	private MockMvc mvc;
	@Autowired
	private EntrenadorRepository entrenadorRepository;

	private String mapToJson(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(obj);
	}

	private <T> T mapFromJson(String json, Class<T> clazz) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(json, clazz);
	}

	@Test
	public void testFindAll() throws Exception {
		MvcResult mvcResult = this.mvc
				.perform(MockMvcRequestBuilders.get("/api/entrenador/").accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		Assert.assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
		String content = mvcResult.getResponse().getContentAsString();
		System.out.println(content);

		EntrenadorPayload[] response = this.mapFromJson(content, EntrenadorPayload[].class);
		List<Integer> real = Arrays.stream(response).map(entrenadorPayload -> entrenadorPayload.getIdEntrenador())
				.collect(Collectors.toList());
		AssertUtils.assertEquals(Arrays.asList(1, 2), real);

	}

	@Test
	public void testFindById() throws Exception {
		MvcResult mvcResult = this.mvc.perform(
				MockMvcRequestBuilders.get("/api/entrenador/id/{id}/", 1).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		Assert.assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
		String content = mvcResult.getResponse().getContentAsString();
		EntrenadorPayload response = this.mapFromJson(content, EntrenadorPayload.class);

		Assert.assertEquals(Integer.valueOf(1), response.getIdEntrenador());
		Assert.assertEquals("test", response.getNombre());
		Assert.assertEquals("entrenador1", response.getApellidos());
		Assert.assertEquals(Integer.valueOf(77), response.getEdad());
	}

	@Test
	public void testCreate() throws Exception {
		EntrenadorPayload request = new EntrenadorPayload();
		request.setNombre("Gregg");
		request.setApellidos("Popovich");
		request.setEdad(78);

		MvcResult mvcResult = this.mvc.perform(MockMvcRequestBuilders.post("/api/entrenador/")
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(this.mapToJson(request))).andReturn();
		Assert.assertEquals(HttpStatus.CREATED.value(), mvcResult.getResponse().getStatus());
		EntrenadorPayload response = this.mapFromJson(mvcResult.getResponse().getContentAsString(),
				EntrenadorPayload.class);

		Assert.assertEquals("Gregg", response.getNombre());
		Assert.assertEquals("Popovich", response.getApellidos());
		Assert.assertEquals(Integer.valueOf(78), response.getEdad());

		Optional<Entrenador> entrenadorOption = this.entrenadorRepository.findById(response.getIdEntrenador());
		Assert.assertTrue(entrenadorOption.isPresent());

		Entrenador entrenador = entrenadorOption.get();
		Assert.assertEquals("Gregg", entrenador.getNombre());
		Assert.assertEquals("Popovich", entrenador.getApellidos());
		Assert.assertEquals(Integer.valueOf(78), entrenador.getEdad());

	}

	@Test
	public void testDelete() throws Exception {
		MvcResult mvcResult = this.mvc.perform(
				MockMvcRequestBuilders.delete("/api/entrenador/{id}/", 1).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		Assert.assertEquals(HttpStatus.NO_CONTENT.value(), mvcResult.getResponse().getStatus());
		Optional<Entrenador> entrenadorOption = this.entrenadorRepository.findById(1);
		Assert.assertFalse(entrenadorOption.isPresent());

	}

	@Test
	public void testUpdate() throws Exception {
		EntrenadorPayload request = new EntrenadorPayload();
		request.setNombre("Gregg");
		request.setApellidos("Popovich");
		request.setEdad(78);

		MvcResult mvcResult = this.mvc.perform(MockMvcRequestBuilders.put("/api/entrenador/1/")
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(this.mapToJson(request))).andReturn();
		Assert.assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
		
		EntrenadorPayload response = this.mapFromJson(mvcResult.getResponse().getContentAsString(),
				EntrenadorPayload.class);
		Assert.assertEquals(Integer.valueOf(1), response.getIdEntrenador());
		Assert.assertEquals("Gregg", response.getNombre());
		Assert.assertEquals("Popovich", response.getApellidos());
		Assert.assertEquals(Integer.valueOf(78), response.getEdad());
		
		Optional<Entrenador> entrenadorOption = this.entrenadorRepository.findById(response.getIdEntrenador());
		Assert.assertTrue(entrenadorOption.isPresent());

		Entrenador entrenador = entrenadorOption.get();
		Assert.assertEquals("Gregg", entrenador.getNombre());
		Assert.assertEquals("Popovich", entrenador.getApellidos());
		Assert.assertEquals(Integer.valueOf(78), entrenador.getEdad());
		
	}

}
