package com.kenancode.fullstack_backend.model.dto;

import com.kenancode.fullstack_backend.model.Academic;
import com.kenancode.fullstack_backend.model.User;

public record UserDetails(User user, Academic academic) {
}
