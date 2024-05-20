package com.junhyeong.heroglass.domain.dto.response;

import com.junhyeong.heroglass.domain.Address;
import com.junhyeong.heroglass.domain.Order;
import com.junhyeong.heroglass.domain.UserRole;
import java.util.List;

public record UserResponse(Long id, String username, String email, Address address, UserRole userRole,
                           List<Order> orders) {

}
