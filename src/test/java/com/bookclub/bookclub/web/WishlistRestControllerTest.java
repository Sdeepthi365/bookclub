package com.bookclub.bookclub.web;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import com.bookclub.bookclub.model.WishlistItem;
import com.bookclub.bookclub.service.dao.WishlistDao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(WishlistRestController.class)
class WishlistRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WishlistDao wishlistDao;

    @Test
    void showWishlist_returnsJsonList_forAuthenticatedUser() throws Exception {
        WishlistItem item = new WishlistItem("9780316769532", "Catcher", "user");
        item.setId("1");
        when(wishlistDao.list("user")).thenReturn(List.of(item));

        mockMvc.perform(get("/api/wishlist")
                    .with(SecurityMockMvcRequestPostProcessors.user("user").roles("USER"))
                    .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[0].isbn").value("9780316769532"))
                .andExpect(jsonPath("$[0].title").value("Catcher"));
    }
}
