package com.bookclub.bookclub.web;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
class WishlistRestControllerFindByIdTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WishlistDao wishlistDao;

    @Test
    void findById_returnsItem() throws Exception {
        WishlistItem item = new WishlistItem("9780593099322", "Some Book", "user");
        item.setId("abc123");
        when(wishlistDao.find("abc123")).thenReturn(item);

        mockMvc.perform(get("/api/wishlist/abc123")
                    .with(SecurityMockMvcRequestPostProcessors.user("user").roles("USER"))
                    .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("abc123"))
                .andExpect(jsonPath("$.isbn").value("9780593099322"))
                .andExpect(jsonPath("$.title").value("Some Book"));
    }
}
