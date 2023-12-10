package com.purehub.controller.log;

import com.purehub.service.log.SystemLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SystemLogController {

  @Autowired
  private SystemLogService systemLogService;
}
