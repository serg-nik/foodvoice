package ru.serg_nik.foodvoice.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import ru.serg_nik.foodvoice.BaseTest;
import ru.serg_nik.foodvoice.model.User;
import ru.serg_nik.foodvoice.security.jwt.JwtTokenProvider;

import javax.annotation.PostConstruct;
import java.io.UnsupportedEncodingException;
import java.util.Optional;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static ru.serg_nik.foodvoice.test_data.UserTestData.ADMIN;

abstract class BaseRestControllerTest extends BaseTest {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostConstruct
    private void postConstruct() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .addFilter(new CharacterEncodingFilter("UTF-8", true))
                .apply(springSecurity())
                .build();
    }

    protected ResultActions perform(MockHttpServletRequestBuilder builder) throws Exception {
        return mockMvc.perform(builder);
    }

    protected <T> MockHttpServletRequestBuilder postBuilder(String requestUri, MediaType contentType,
                                                            T content, User authUser) throws JsonProcessingException {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(requestUri)
                .contentType(contentType)
                .content(writeValueAsString(content));
        Optional.ofNullable(authUser).ifPresent(user ->
                requestBuilder.header("Authorization", "Bearer_" + jwtTokenProvider.create(user))
        );
        return requestBuilder;
    }

    protected MockHttpServletRequestBuilder getBuilder(String requestUri, MediaType contentType) {
        return MockMvcRequestBuilders
                .get(requestUri)
                .contentType(contentType);
    }

    protected <T> String writeValueAsString(T value) throws JsonProcessingException {
        return OBJECT_MAPPER.writeValueAsString(value);
    }

    protected <T> T readValue(String object, Class<T> tClass) throws JsonProcessingException {
        return OBJECT_MAPPER.readValue(object, tClass);
    }

    protected <T> T readValue(ResultActions resultActions, Class<T> tClass) throws Exception {
        return readValue(getContentAsString(resultActions.andReturn()), tClass);
    }

    protected <T> T readPageContent(ResultActions resultActions, TypeReference<T> typeReference) throws Exception {
        ObjectNode jsonNodes = OBJECT_MAPPER.readValue(getContentAsString(resultActions.andReturn()), ObjectNode.class);
        return OBJECT_MAPPER.readValue(jsonNodes.get("content").toString(), typeReference);
    }

    private String getContentAsString(MvcResult result) throws UnsupportedEncodingException {
        return result.getResponse().getContentAsString();
    }

}
