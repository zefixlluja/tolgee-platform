package io.tolgee.api.v2.controllers

import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Controller

@Controller
class WebsocketController {
  @MessageMapping("/hello")
  @SendTo("/topic/greetings")
  fun greeting(): String {
    Thread.sleep(1000) // simulated delay
    return "Hello"
  }
}
