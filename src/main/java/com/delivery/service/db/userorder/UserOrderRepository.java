package com.delivery.service.db.userorder;

import com.delivery.service.db.user.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserOrderRepository extends JpaRepository<UserOrderEntity, Long> {
}
