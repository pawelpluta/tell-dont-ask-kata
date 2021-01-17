package com.pawelpluta.telldontaskkata.service;

import com.pawelpluta.telldontaskkata.domain.Package;

public interface DeliveryService {

    void send(Package packageToDeliver);
}
