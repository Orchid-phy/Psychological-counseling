package com.orchid.counselling.controller;

import com.orchid.counselling.server.WebSocketServer;
import com.orchid.counselling.util.JsonResult;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
public class MessageController {

    @GetMapping("/userRequest")
    public ResponseEntity<String> userRequest(){
        return ResponseEntity.ok("请求成功");
    }

    @GetMapping("/page")
    public ModelAndView page(){
        return new ModelAndView("websocket");
    }

    @RequestMapping("/push/{toUserId}")
    @ResponseBody
    public JsonResult pushToWeb(String message, @PathVariable String toUserId) throws IOException {

        WebSocketServer.sendInfo(message, toUserId);

        return new JsonResult(200, "success");
    }

}

